package object;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Sword extends Object{

    String imgPath = "/res/Images/sword/01.png";

    public Obj_Sword(String mapName){

        this.mapName = mapName;

        objName = "Sword";
        objType = 1;
        
        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
        mapX = 130;
        mapY = 433;
    }

    public Obj_Sword(String mapName, int mapX, int mapY){

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;

        objName = "Sword";
        objType = 1;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
    }
}
