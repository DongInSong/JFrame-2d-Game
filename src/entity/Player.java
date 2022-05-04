package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.UtilityTool;
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
        defaultMap = 1;
    }

    public void setPlayerPosition(int x) {
        this.x = x;
    }

    public void getPlayerImage() {

        right1 = setup("PlayerR1");
        right2 = setup("PlayerR2");
        left1 = setup("PlayerL1");
        left2 = setup("PlayerL2");

    }

    public BufferedImage setup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {
        if (keyHandler.rightPressed || keyHandler.leftPressed) {

            if (keyHandler.rightPressed == true) {
                direction = "right";
            }

            else if (keyHandler.leftPressed == true) {
                direction = "left";
            }

            collisionOn = false;
            gamePanel.collisionChecker.check(this);

            if(collisionOn == false){
                switch(direction){
                    case "right": x += speed; break;
                    case "left": x -= speed; break;
                }
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
            // if (spriteCount > 5) {
            // if (spriteNum == 1) {
            // spriteNum = 2;
            // } else if (spriteNum == 2) {
            // spriteNum = 3;
            // } else if (spriteNum == 3) {
            // spriteNum = 4;
            // } else if (spriteNum == 4) {
            // spriteNum = 5;
            // } else if (spriteNum == 5) {
            // spriteNum = 1;
            // }
            // spriteCount = 0;
            // }
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
                // else if (spriteNum == 3) {
                // image = right3;
                // } else if (spriteNum == 4) {
                // image = right4;
                // } else if (spriteNum == 5) {
                // image = right5;
                // }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else if (spriteNum == 2) {
                    image = left2;
                }
                // else if (spriteNum == 3) {
                // image = left3;
                // } else if (spriteNum == 4) {
                // image = left4;
                // } else if (spriteNum == 5) {
                // image = left5;
                // }
                break;
        }
        g2.drawImage(image, x, y, null);
    }

}
