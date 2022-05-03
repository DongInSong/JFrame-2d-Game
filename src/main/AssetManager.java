package main;

import object.ItemBrie;
import object.monPoop;
import object.monSlime;

public class AssetManager {

    GamePanel gamePanel;

    public AssetManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        //gamePanel.obj[5] = new ItemBrie("map02");
    }

    public void setMonster() {
        gamePanel.monster[1] = new monSlime("map02", 300, gamePanel.player.y);
        gamePanel.monster[2] = new monSlime("map02", 200, gamePanel.player.y);
        gamePanel.monster[3] = new monSlime("map02", 100, gamePanel.player.y);
        gamePanel.monster[4] = new monSlime("map01");
        gamePanel.monster[5] = new monPoop("map02", 150, gamePanel.player.y);
        gamePanel.monster[6] = new monPoop("map01", 200, gamePanel.player.y);

    }
}
