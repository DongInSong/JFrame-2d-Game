package main;

import object.Obj_Sword;

public class AssetManager {

    GamePanel gamePanel;

    public AssetManager(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.obj[0] = new Obj_Sword("map01");
        gamePanel.obj[1] = new Obj_Sword("map02", 300, gamePanel.player.y);

    }
}
