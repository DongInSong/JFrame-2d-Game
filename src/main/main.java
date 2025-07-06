// Code adapted from RyiSnow's 2D Java Game Programming tutorials
// YouTube: https://www.youtube.com/@RyiSnow

package main;

import javax.swing.JFrame;

public class main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("2d Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
