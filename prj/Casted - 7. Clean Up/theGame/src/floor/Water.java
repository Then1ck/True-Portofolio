package floor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import hitboxes.Hitbox;
import mainPanel.Camera;
import mainPanel.GamePanel;

public class Water extends Floor{
	
	public Water(int worldX, int worldY, GamePanel _gp, Camera cams) {
		super(worldX, worldY, _gp.getTileSize(), _gp.getTileSize(), false, false, cams, 4);
		BufferedImage temp = null;
		try {
			temp = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.addImage(temp);
		HBox = new Hitbox(0, _gp.getTileSize()-1, 0, _gp.getTileSize()-1);
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(Image, WorldX-Cams.getCamX(), WorldY-Cams.getCamY(), Width, Height, null);
	}
}
