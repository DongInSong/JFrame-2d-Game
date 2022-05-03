package object;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ItemBrie extends Object{

    String imgPath = "/res/Images/object/brie.png";

    public ItemBrie(String mapName){

        this.mapName = mapName;

        objName = "brie";
        objType = 0;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
        mapX = 130;
    }

    public ItemBrie(String mapName, int mapX, int mapY){

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;

        objName = "brie";
        objType = 0;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
    }
}
