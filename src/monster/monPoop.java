package monster;

import java.util.Random;
import main.GamePanel;

public class monPoop extends Monster {

    public monPoop(GamePanel gamePanel, String mapName) {
        super(gamePanel);
        this.mapName = mapName;
        monName = "poop";
        monLv = 8;
        mapX = 200;
        mapY = 433;

        getMonsterImage();
    }

    public monPoop(GamePanel gamePanel, String mapName, int mapX, int mapY) {
        super(gamePanel);
        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;
        monName = "poop";
        monLv = 8;

        getMonsterImage();
    }

    public void getMonsterImage(){
        right1 = setup("Poop_1R");
        right2 = setup("Poop_2R");
        right3 = setup("Poop_3R");
        right4 = setup("Poop_4R");
        right5 = setup("Poop_5R");
        left1 = setup("Poop_1L");
        left2 = setup("Poop_2L");
        left3 = setup("Poop_3L");
        left4 = setup("Poop_4L");
        left5 = setup("Poop_5L");
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
