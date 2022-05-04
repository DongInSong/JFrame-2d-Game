package object;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;

public class ItemBrie extends Object{

    String imgPath = "/res/Images/object/brie.png";

    public ItemBrie(GamePanel gamePanel, String mapName){
        super(gamePanel);
        this.mapName = mapName;

        objName = "brie";
        objType = 0;
        mapX = 130;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
            utilityTool.scaleImage(objImg, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemBrie(GamePanel gamePanel,String mapName, int mapX, int mapY){
        super(gamePanel);
        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;

        objName = "brie";
        objType = 0;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
            utilityTool.scaleImage(objImg, gamePanel.tileSize, gamePanel.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
