package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import entity.Player;
import java.awt.Graphics2D;
import main.GamePanel;

public class MapManager {

    GamePanel gamePanel;
    Player player;
    Map[] map;
    int mapImageNum[][];
    public String portal[][];

    int totalMap;
    int portalInfo = 4; // [4] Map Code, Current Map, Previous Map, Next Map
    public int currentMap;
    public String mapName;

    public MapManager(GamePanel gamePanel, Player player) {

        this.gamePanel = gamePanel;
        this.player = player;
        getTotalMap();
        map = new Map[10];
        mapImageNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        portal = new String[totalMap][portalInfo];
        currentMap = player.defaultMap;

        getMapImage();
        loadPortal();
        loadMap(portal[currentMap][1]);
    }

    public int getTotalMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/Maps/portal.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while (br.readLine() != null) {
                totalMap++;
            }
            System.out.println("Portal Data: " + totalMap);
            br.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return totalMap;

    }

    public void getCurrentMap() {
        for (int i = 0; i < portal.length; i++) {
            if (portal[i][1].equals(mapName)) {
                currentMap = Integer.parseInt(portal[i][0]);
            }
        }
    }

    public void monstersInMap() {
        for (int i = 0; i < gamePanel.obj.length; i++) {
            if (gamePanel.monster[i] != null) {
                if (mapName.equals(gamePanel.monster[i].mapName)) {
                    gamePanel.monster[i].setAction();
                    System.out.println("monster " + i + " in this map ("+mapName+")");
                }
            }
        }
    }

    public void mapChange() {
        if (player.x > 460) {
            player.setPlayerPosition(-25);
            loadMap(portal[currentMap][3]);
            getCurrentMap();

            // RESET OBJECT (MONSTER)
            gamePanel.monseterRefresh();

        } else if (player.x < -35) {
            player.setPlayerPosition(450);
            loadMap(portal[currentMap][2]);
            getCurrentMap();

            // RESET OBJECT (MONSTER)
            gamePanel.monseterRefresh();

        }
    }

    public void loadPortal() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/Maps/portal.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < totalMap && row < portalInfo) {
                String line = br.readLine();
                while (row < portalInfo) {
                    String numbers[] = line.split(" ");
                    String mapNum = numbers[row];

                    portal[col][row] = mapNum;
                    row++;
                }
                if (row == portalInfo) {
                    row = 0;
                    col++;
                }
            }
            br.close();

            for (int i = 0; i < totalMap; i++) {
                for (int j = 0; j < portalInfo; j++) {
                    System.out.print(portal[i][j] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getMapImage() {

        try {
            map[0] = new Map();
            map[0].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/sky.png"));
            map[1] = new Map();
            map[1].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/Grassbottom.png"));
            map[2] = new Map();
            map[2].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/Grasstop.png"));
            map[3] = new Map();
            map[3].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/Grassbottom2.png"));
            map[4] = new Map();
            map[4].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/Grasstop2.png"));
            map[5] = new Map();
            map[5].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/blocktop.png"));
            map[6] = new Map();
            map[6].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/blockbottom.png"));
            map[7] = new Map();
            map[7].image = ImageIO.read(getClass().getResourceAsStream("/res/Images/map/floor.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String mapName) {
        this.mapName = mapName;

        try {
            InputStream is = getClass().getResourceAsStream("/res/Maps/" + mapName + ".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {

                String line = br.readLine();

                while (col < gamePanel.maxScreenCol) {

                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapImageNum[col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }

            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void draw(Graphics2D g2) {

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
            int tileNum = mapImageNum[col][row];

            g2.drawImage(map[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if (col == gamePanel.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
