package floor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import mainPanel.Camera;
import mainPanel.GamePanel;

public class Flower extends Floor{
	public Flower(int worldX, int worldY, GamePanel _gp, Camera cams) {
		super(worldX, worldY, _gp.getTileSize(), _gp.getTileSize(), true, false, cams, 3);
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(getClass().getResourceAsStream("/tiles/Flowers.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.addImage(temp);
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(Image, WorldX-Cams.getCamX(), WorldY-Cams.getCamY(), Width, Height, null);
	}
}
