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

    int totalMap = 4;
    int portalInfo = 4; // [4] Map Code, Current Map, Previous Map, Next Map

    public int currentMap;
    public String mapName;

    public MapManager(GamePanel gamePanel, Player player) {

        this.gamePanel = gamePanel;
        this.player = player;
        map = new Map[10];
        mapImageNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        portal = new String[totalMap][portalInfo];

        currentMap = (player.defaultMap - 1);

        getMapImage();
        loadPortal();
        loadMap(portal[currentMap][1]);
    }

    public void MapChange() {
        if (player.x > 450) {
            player.setPlayerPosition(-25);
            loadMap(portal[currentMap][3]);
            currentMap++;

        } else if (player.x < -25) {
            player.setPlayerPosition(450);
            loadMap(portal[currentMap][2]);
            currentMap--;
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
                    System.out.print(portal[i][j]+" ");
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
