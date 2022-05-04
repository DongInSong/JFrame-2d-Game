package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import map.MapManager;
import object.Monster;
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

    KeyHandler keyHandler = new KeyHandler(this);
    Thread gameThread;
    public Player player = new Player(this, keyHandler);
    public MapManager mapManager = new MapManager(this, player);
    public AssetManager assetManager = new AssetManager(this);
    public Object obj[] = new Object[10];
    public Monster monster[] = new Monster[10];

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenhight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetManager.setObject();
        assetManager.setMonster();
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

    public void update() {
        player.update();
        mapManager.mapChange();
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                if (mapManager.mapName.equals(monster[i].mapName)) {
                    monster[i].update();
                }
            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // MAP
        mapManager.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // MONSTER
        for (int i = 0; i < monster.length; i++) {
            if (monster[i] != null) {
                monster[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // DEBUG
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
