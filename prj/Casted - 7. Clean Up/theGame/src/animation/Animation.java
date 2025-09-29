package animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Animation {
	protected ArrayList<BufferedImage> animationFrames;
	protected int index, artSizeX, artSizeY, delayFrames, counter;
	
	public Animation(ArrayList<BufferedImage> AFrames, int ASX, int ASY, int _delayFrames) {
		animationFrames = AFrames;
		artSizeX = ASX;
		artSizeY = ASY;
		delayFrames = _delayFrames;
		counter = 0;
	}
	
	public Animation(ArrayList<BufferedImage> AFrames, int idx, int ASX, int ASY, int _delayFrames) {
		animationFrames = AFrames;
		index = idx;
		artSizeX = ASX;
		artSizeY = ASY;
		delayFrames = _delayFrames;
		counter = 0;
	}
	
	public abstract void Animate(Graphics2D g2, int screenX, int screenY);
	public abstract void draw(Graphics2D g2, int screenX, int screenY);
}
