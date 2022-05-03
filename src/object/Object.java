package object;

import java.awt.image.BufferedImage;
import main.GamePanel;
import java.awt.Graphics2D;

public class Object {

    // DEFAULT
    public BufferedImage objImg;
    public String objName;
    public int objType;

    // SPRITE
    public BufferedImage[] sprite;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public String direction = "right";
    public int speed = 1;

    // POSITION
    public int mapNum;
    public String mapName;
    public int mapX;
    public int mapY = 433 + 24;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        if (gamePanel.mapManager.mapName.equals(mapName)) {
            g2.drawImage(objImg, mapX, mapY, gamePanel.tileSize/2, gamePanel.tileSize/2, null);
        }
    }
}
