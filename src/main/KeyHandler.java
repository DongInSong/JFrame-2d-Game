package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public boolean rightPressed, leftPressed,
            // DEBUG KEY
            showDebug, showDrawTime;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_ENTER) {
            if (showDebug == false) {
                showDebug = true;
            } else if (showDebug == true) {
                showDebug = false;
            }
        }

        if (code == KeyEvent.VK_ESCAPE) {
            if (gamePanel.gameState == gamePanel.playState) {
                gamePanel.gameState = gamePanel.pauseState;
            } else if (gamePanel.gameState == gamePanel.pauseState) {
                gamePanel.gameState = gamePanel.playState;
            }
        }

        if (code == KeyEvent.VK_1) {
            gamePanel.mapManager.teleport(6);
        }

        if (code == KeyEvent.VK_A) {
            if (showDrawTime == false) {
                showDrawTime = true;
            } else if (showDrawTime == true) {
                showDrawTime = false;
            }
        }

        if (code == KeyEvent.VK_R) {

            // 몬스터 죽이기 TEST용 코드
            int[] target = { 1, 2, 3 };
            gamePanel.mapManager.monstersInMap();
            // ------------------------

            gamePanel.assetManager.removeMonster(target);

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

    }

}
