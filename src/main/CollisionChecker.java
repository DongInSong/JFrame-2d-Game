package main;

import entity.Entity;
import entity.Player;
import npc.Npc;

public class CollisionChecker {

    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void check(Entity entity) {
        switch (entity.direction) {
            case "right":

                // END OF MAP_ RIGHT
                if (entity.x > 430) {
                    if (!gamePanel.mapManager
                            .isExistMap(gamePanel.mapManager.portal[gamePanel.mapManager.portalIndex][3])) {
                        entity.collisionOn = true;
                        break;
                    }
                }
        }
        switch (entity.direction) {
            case "left":

                // END OF MAP_ LEFT
                if (entity.x < 0) {
                    if (!gamePanel.mapManager
                            .isExistMap(gamePanel.mapManager.portal[gamePanel.mapManager.portalIndex][2])) {
                        entity.collisionOn = true;
                        break;
                    }
                }
        }
    }

    public void npcCheck() {
        for (int i = 0; i < gamePanel.npc.length; i++) {
            if (gamePanel.npc[i] != null) {
                if (gamePanel.mapManager.mapName.equals(gamePanel.npc[i].mapName)) {
                    if (gamePanel.player.x + 48 >= gamePanel.npc[i].mapX) {
                        gamePanel.npc[i].collisionOn = true;
                    } else
                        gamePanel.npc[i].collisionOn = false;

                    if (gamePanel.player.x >= gamePanel.npc[i].mapX + 48) {
                        gamePanel.npc[i].collisionOn = false;
                    }
                }
            }
        }
    }

    public void monsterCheck() {
        for (int i = 0; i < gamePanel.monster.length; i++) {
            if (gamePanel.monster[i] != null) {
                if (gamePanel.mapManager.mapName.equals(gamePanel.monster[i].mapName)) {
                    if (gamePanel.player.x + 48 >= gamePanel.monster[i].mapX) {
                        gamePanel.monster[i].collisionOn = true;
                    } else
                        gamePanel.monster[i].collisionOn = false;

                    if (gamePanel.player.x >= gamePanel.monster[i].mapX + 48) {
                        gamePanel.monster[i].collisionOn = false;
                    }
                }
            }
        }
    }
}
