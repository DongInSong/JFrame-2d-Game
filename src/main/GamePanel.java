package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import map.MapManager;
import object.ObjectInfo;

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
    public ObjectInfo obj[] = new ObjectInfo[10];
    


    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenhight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void setupGame(){
        assetManager.setObject();
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

        //double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            // System.out.println("Running");

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                FPS = drawCount;
                drawCount = 0;
                timer=0;
            }

            // try {
            //     double remainingTime = nextDrawTime - System.nanoTime();
            //     remainingTime = remainingTime / 1000000;

            //     if (remainingTime < 0) {
            //         remainingTime = 0;
            //     }

                
            //     Thread.sleep((long) remainingTime);
            //     nextDrawTime += drawInterval;
                

            // } catch (InterruptedException e) {
            //     // TODO Auto-generated catch block
            //     e.printStackTrace();
            // }
        }

    }

    public void update() {
        player.update();
        mapManager.MapChange();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // MAP
        mapManager.draw(g2);

        // OBJECT
        for(int i = 0 ; i<obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // DEBUG
        if(keyHandler.showDebug == true){
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            g2.setColor(Color.BLACK);
            int x = 20;
            int y = 40;
            int lineHeight = 20;

            g2.drawString("Debug ----", x, y);
            g2.drawString("FPS: "+ FPS, x+340, y);y+=lineHeight;
            g2.drawString("X: "+ player.x, x, y);y+=lineHeight;
            g2.drawString("Map: "+ mapManager.mapName, x, y);y+=lineHeight;

        }

        g2.dispose();
    }
}
