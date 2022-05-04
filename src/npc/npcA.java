package npc;

import main.GamePanel;

public class npcA extends Npc {

    public npcA(GamePanel gamePanel, String mapName) {
        super(gamePanel);

        this.mapName = mapName;
        npcName = "npca";
        mapX = 200;
        mapY = 433;

        getNpcImage();
        update();
    }

    public npcA(GamePanel gamePanel, String mapName, int mapX, int mapY) {
        super(gamePanel);

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;
        npcName = "npca";

        getNpcImage();
        update();
    }

    public void getNpcImage() {
        left1 = setup("char_02");
        left2 = setup("char_02_1");
    }

}
