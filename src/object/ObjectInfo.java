package object;

import java.awt.image.BufferedImage;

import main.GamePanel;

import java.awt.Graphics2D;

public class ObjectInfo {

    public BufferedImage objImg;
    public String objName;

    public int mapNum;
    public String mapName;
    public int mapX;
    public int mapY;

    public void draw(Graphics2D g2, GamePanel gamePanel) {
            if (gamePanel.mapManager.mapName.equals(mapName)) {
                g2.drawImage(objImg, mapX, mapY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
