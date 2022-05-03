package object;

import java.awt.image.BufferedImage;
import main.GamePanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.String;

public class Monster {

    // DEFAULT
    public BufferedImage monImg;
    public String monName;
    public int monLv;
    public int monType;

    // NPC or MONSTER
    public BufferedImage left1, left2, left3, left4, left5,
            right1, right2, right3, right4, right5;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public String direction = "right";
    public int speed = 1;
    public int actionLockCounter = 118;

    // POSITION
    public int mapNum;
    public String mapName;
    public int mapX;
    public int mapY;

    int randomDirection = 0;

    public void setAction() {
    }

    public void update() {
    }

    public void draw(Graphics2D g2, GamePanel gamePanel) {
        Font font;
        if (gamePanel.mapManager.mapName.equals(mapName)) {
            g2.drawImage(monImg, mapX, mapY, gamePanel.tileSize, gamePanel.tileSize, null);
            
            g2.setColor(new Color(0, 0, 0, 150));
            g2.fillRect(mapX, mapY + 55, gamePanel.tileSize, 15);
            Rectangle r = new Rectangle(mapX, mapY + 55, gamePanel.tileSize, 15);

            g2.setColor(Color.WHITE);
            font = (g2.getFont().deriveFont(Font.BOLD, 10));
            gamePanel.centerString(g2, r, monName, font);
        }
    }
}
