package object;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;
import java.awt.Graphics2D;

public class Object {
    
    GamePanel gamePanel;

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

    UtilityTool utilityTool = new UtilityTool();

    public Object(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public BufferedImage setup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Images/monster/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, gamePanel.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        if (gamePanel.mapManager.mapName.equals(mapName)) {
            g2.drawImage(objImg, mapX, mapY, gamePanel.tileSize/2, gamePanel.tileSize/2, null);
        }
    }
}
