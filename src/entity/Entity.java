package entity;

import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int speed;

    public BufferedImage left1, left2, 
                        right1, right2;
    public String direction;
    public int defaultMap;

    public int spriteCount = 0;
    public int spriteNum = 1;

}
