package monster;

import java.util.Random;
import main.GamePanel;

public class monSlime extends Monster {

    public monSlime(GamePanel gamePanel, String mapName) {
        super(gamePanel);
        
        this.mapName = mapName;
        monName = "slime";
        monLv = 5;
        mapX = 200;
        mapY = 433;

        getMonsterImage();
    }

    
    public monSlime(GamePanel gamePanel, String mapName, int mapX, int mapY) {
        super(gamePanel);

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;
        monName = "slime";
        monLv = 5;

        getMonsterImage();
    }
    
    public void getMonsterImage(){
        right1 = setup("slime");
        right2 = setup("slime1");
        left1 = setup("slime");
        left2 = setup("slime1");
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
                spriteNum = 1;
            }
            spriteCount = 0;
        }
    }

}
