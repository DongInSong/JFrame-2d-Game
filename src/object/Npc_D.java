package object;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Npc_D extends Object{

    String imgPath = "/res/Images/npc/curby.png";

    public Npc_D(String mapName){

        this.mapName = mapName;

        objName = "A";
        objType = 2;

        try {
            objImg = ImageIO.read(getClass().getResourceAsStream(imgPath));
        } catch (IOException e) {

        }
        mapX = 150;
        mapY = 433;
    }

    public Npc_D(String mapName, int mapX, int mapY){

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
