package main;

import entity.Entity;

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
}
