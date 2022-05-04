package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import map.MapManager;
import monster.Monster;
import npc.Npc;
import object.Object;

import java.awt.Rectangle;
import java.lang.String;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class GamePanel extends JPanel implements Runnable {

    final int originalSize = 16; // 16x16 pixels
    final int scale = 3; // 16*3(scale)

    public final int tileSize = originalSize * scale; // 48x48
    public final int maxScreenCol = 10;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 480
    public final int screenhight = tileSize * maxScreenRow; // 576
    public int FPS;

    public KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public AssetManager assetManager = new AssetManager(this);

    // ENTITY AND OBJECTS
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyHandler);
    public MapManager mapManager = new MapManager(this, player);
    public Object obj[] = new Object[10];
    public Monster monster[] = new Monster[10];
    public Npc npc[] = new Npc[10];

    // GAME STATE
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenhight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetManager.setObject();
        assetManager.setNpc();
        assetManager.setMonster();
        gameState = playState;
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / 60;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                FPS = drawCount;
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void monseterRefresh() {
        assetManager.setMonster();
    }

    // UPDATE
    public void update() {
        if (gameState == playState) {
            player.update();
            mapManager.mapChange();
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (mapManager.mapName.equals(monster[i].mapName)) {
                        monster[i].update();
                    }
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    if (mapManager.mapName.equals(npc[i].mapName)) {
                        npc[i].update();
                    }
                }
            }
        }

        if(gameState == pauseState){

        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        long drawStart = 0;
        if (keyHandler.showDrawTime == true) {
            drawStart = System.nanoTime();
        }

        // MAP
        mapManager.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2);
            }
        }

        // MONSTER
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                monster[i].draw(g2);
            }
        }

        // Npc
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].draw(g2);
            }
        }

        // PLAYER
        player.draw(g2);

        // DEBUG

        if (keyHandler.showDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.BLACK);
            g2.drawString("Draw Time: " + passed, 100, 40);
            System.out.println(passed);
        }

        if (keyHandler.showDebug == true) {
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.BLACK);
            int x = 20;
            int y = 40;
            int lineHeight = 20;

            g2.drawString("Debug ----", x, y);
            g2.drawString("FPS: " + FPS, x + 340, y);
            y += lineHeight;
            g2.drawString("X: " + player.x, x, y);
            y += lineHeight;
            g2.drawString("Map: " + mapManager.mapName, x, y);
            y += lineHeight;
            g2.drawString("Map_Index: " + mapManager.portalIndex, x, y);
            y += lineHeight;
            g2.drawString("Map_code: " + mapManager.mapCode, x, y);
            y += lineHeight;
            g2.drawString("Collision: " + player.collisionOn, x, y);
            y += lineHeight;

            // OBJECT REDBOX
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    if (mapManager.mapName.equals(obj[i].mapName)) {
                        g2.setColor(new Color(0, 0, 255, 150));
                        g2.fillRect(obj[i].mapX, obj[i].mapY, obj[i].objImg.getWidth(), obj[i].objImg.getHeight());
                    }
                }
            }

            // MONSTER REDBOX
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    if (mapManager.mapName.equals(monster[i].mapName)) {
                        g2.setColor(new Color(255, 0, 0, 150));
                        g2.fillRect(monster[i].mapX, monster[i].mapY, tileSize, tileSize);
                    }
                }
            }

        }
        g2.dispose();
    }

    public void centerString(Graphics2D g, Rectangle r, String s,
            Font font) {
        FontRenderContext frc = new FontRenderContext(null, true, true);

        Rectangle2D r2D = font.getStringBounds(s, frc);
        int rWidth = (int) Math.round(r2D.getWidth());
        int rHeight = (int) Math.round(r2D.getHeight());
        int rX = (int) Math.round(r2D.getX());
        int rY = (int) Math.round(r2D.getY());

        int a = (r.width / 2) - (rWidth / 2) - rX;
        int b = (r.height / 2) - (rHeight / 2) - rY;

        g.setFont(font);
        g.drawString(s, r.x + a, r.y + b);
    }
}
