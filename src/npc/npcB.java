package npc;

import main.GamePanel;

public class npcB extends Npc {

    public npcB(GamePanel gamePanel, String mapName) {
        super(gamePanel);

        this.mapName = mapName;
        npcName = "npcb";
        mapX = 300;
        mapY = 433;

        getNpcImage();
        update();
    }

    public npcB(GamePanel gamePanel, String mapName, int mapX, int mapY) {
        super(gamePanel);

        this.mapName = mapName;
        this.mapX = mapX;
        this.mapY = mapY;
        npcName = "npcb";

        getNpcImage();
        update();
    }

    public void getNpcImage() {
        left1 = setup("char_03");
        left2 = setup("char_03_1");
    }

}
