package object;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class monPoop extends Monster {

    public monPoop(String mapName) {

        this.mapName = mapName;
        monName = "poop";
        monLv = 8;

        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_1R.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_2R.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_3R.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_4R.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_5R.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_1L.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_2L.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_3L.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_4L.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_5L.png"));
        } catch (IOException e) {

        }
        mapX = 200;
        mapY = 433;
    }

    public monPoop(String mapName, int mapX, int mapY) {

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;
        monName = "poop";
        monLv = 8;

        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_1R.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_2R.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_3R.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_4R.png"));
            right5 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_5R.png"));

            left1 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_1L.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_2L.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_3L.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_4L.png"));
            left5 = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/Poop_5L.png"));
        } catch (IOException e) {

        }
    }

    public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            randomDirection = random.nextInt(100) + 1;
            //System.out.println(randomDirection);

            if (randomDirection <= 40) {
                direction = "right";
            }
            if (randomDirection > 40 && randomDirection <= 60) {
                direction = "stop";
            }
            if (randomDirection > 60) {
                direction = "left";
            }
            actionLockCounter = 0;
        }
    }

    public void update() {

        setAction();

        // RANDOM DIRECTION MOVE
        switch (direction) {
            case "right":
                if (mapX < 400) {
                    mapX += speed;
                } else
                    direction = "stop";
                if (spriteNum == 1) {
                    monImg = right1;
                } else if (spriteNum == 2) {
                    monImg = right2;
                } else if (spriteNum == 3) {
                    monImg = right3;
                } else if (spriteNum == 4) {
                    monImg = right4;
                } else if (spriteNum == 5) {
                    monImg = right5;
                }
                break;
            case "left":
                if (mapX > 50) {
                    mapX -= speed;
                } else
                    direction = "stop";
                if (spriteNum == 1) {
                    monImg = left1;
                } else if (spriteNum == 2) {
                    monImg = left2;
                } else if (spriteNum == 3) {
                    monImg = left3;
                } else if (spriteNum == 4) {
                    monImg = left4;
                } else if (spriteNum == 5) {
                    monImg = left5;
                }
                break;
            case "stop":
                monImg = right1;

        }

        // SPRITE
        spriteCount++;
        if (spriteCount > 5) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum = 3;
            } else if (spriteNum == 3) {
                spriteNum = 4;
            } else if (spriteNum == 4) {
                spriteNum = 5;
            } else if (spriteNum == 5) {
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }
}
