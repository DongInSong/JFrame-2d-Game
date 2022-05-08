package npc;

import main.GamePanel;

public class npcC extends Npc {

    public npcC(GamePanel gamePanel, String mapName) {
        super(gamePanel);

        this.mapName = mapName;
        npcName = "npcc";
        mapX = 150;
        mapY = 433;

        getNpcImage();
        update();
    }

    public npcC(GamePanel gamePanel, String mapName, int mapX, int mapY) {
        super(gamePanel);

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;
        npcName = "npcc";

        getNpcImage();
        update();
    }

    public void getNpcImage() {
        left1 = setup("char_04");
        left2 = setup("char_04_1");
    }

}
