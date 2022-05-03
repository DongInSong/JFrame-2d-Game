package object;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Obj_Sword extends ObjectInfo{

    public Obj_Sword(String mapName){

        this.mapName = mapName;

        objName = "Sword";
        try {
            objImg = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/playerL1.png"));
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
        try {
            objImg = ImageIO.read(getClass().getResourceAsStream("/res/Images/player/playerL1.png"));
        } catch (IOException e) {

        }
    }
}
