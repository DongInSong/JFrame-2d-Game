package ui;

import java.awt.image.BufferedImage;
import main.GamePanel;
import main.UtilityTool;
import npc.Npc;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.lang.String;
import java.nio.Buffer;

import javax.imageio.ImageIO;

public class Ui {

    GamePanel gamePanel;

    public BufferedImage space;

    public Ui(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        getUiImage();
    }

    public void update() {
        gamePanel.collisionChecker.npcCheck();
        gamePanel.collisionChecker.monsterCheck();
    }

    public void getUiImage() {
        space = uiSetup("space");
    }

    public BufferedImage uiSetup(String imageName) {
        UtilityTool utilityTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/Images/UI/" + imageName + ".png"));
            image = utilityTool.scaleImage(image, gamePanel.tileSize, image.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        Font font;

            for (int i = 0; i < gamePanel.npc.length; i++) {
                if (gamePanel.npc[i] != null && gamePanel.npc[i].collisionOn) {
                    if (gamePanel.mapManager.mapName.equals(gamePanel.npc[i].mapName)) {
                        g2.drawImage(gamePanel.ui.space, gamePanel.npc[i].mapX, gamePanel.npc[i].mapY - 40, null);
                    }
                }
            }
        }
}
