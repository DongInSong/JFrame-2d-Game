package object;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Npc_B extends Object{

    String imgPath = "/res/Images/npc/BloodSlime.png";

    public Npc_B(String mapName){

        this.mapName = mapName;

        objName = "A";
        objType = 2;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
        mapX = 300;
        mapY = 433;
    }

    public Npc_B(String mapName, int mapX, int mapY){

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;

        objName = "A";
        objType = 2;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
    }
}
