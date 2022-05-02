package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.GamePanel;

public class Player extends Entity {
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 433;
        speed = 5;
        direction = "right";
        defaultMap = 2;
    }

    public void setPlayerPosition(int x) {
        this.x = x;
    }

    public void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/playerR1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/playerR2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/playerL1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/playerL2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyHandler.rightPressed || keyHandler.leftPressed) {

            if (keyHandler.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            if (keyHandler.leftPressed == true) {
                direction = "left";
                x -= speed;
            }

            spriteCount++;
            if (spriteCount > 5) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCount = 0;
            }
        } else
            spriteNum = 1;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else if (spriteNum == 2) {
                    image = right2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                break;
        }
        g2.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }

}
