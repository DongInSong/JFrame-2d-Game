package main;

import monster.Monster;
import monster.monPoop;
import monster.monSlime;
import npc.npcA;

public class AssetManager {

    GamePanel gamePanel;

    public AssetManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setObject() {
        // gamePanel.obj[5] = new ItemBrie("map02");
    }

    public void setNpc() {
        gamePanel.npc[0] = new npcA(gamePanel, "map01");
    }

    public void setMonster() {
        gamePanel.monster[1] = new monSlime(gamePanel, "map02", 300, gamePanel.player.y);
        gamePanel.monster[2] = new monSlime(gamePanel, "map02", 200, gamePanel.player.y);
        gamePanel.monster[3] = new monSlime(gamePanel, "map02", 100, gamePanel.player.y);
        gamePanel.monster[5] = new monPoop(gamePanel, "map02", 150, gamePanel.player.y);
    }

    public void removeMonster(int[] target) {
        int i = 0;
        int j = 0;

        for (int a : target) {
            for (Monster b : gamePanel.monster) {
                // for (int i = 0; i < target.length; i++) {
                // for (int j = 0; j < gamePanel.monster.length; j++) {
                if (gamePanel.monster[j] != null) {
                    if (gamePanel.mapManager.mapName.equals(gamePanel.monster[j].mapName) && target[i] == j) {
                        System.out.println(target[i] + "=" + j);
                        System.out.println("monster " + j + " has removed" + "(" + gamePanel.monster[j].monName + "}");
                        gamePanel.monster[j] = null;
                    }
                }
                j++;
            }
            j = 0;
            i++;
        }
    }
}
