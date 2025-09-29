package animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class LoopAnimation extends Animation{

	public LoopAnimation(ArrayList<BufferedImage> AFrames, int ASX, int ASY) {
		super(AFrames, ASX, ASY, 8);
	}

	@Override
	public void Animate(Graphics2D g2, int screenX, int screenY) {
		if(counter < delayFrames) {
			counter++;
			draw(g2, screenX, screenY);
			return;
		}
		counter = 0;
		super.index ++;
		if(super.index>=super.animationFrames.size()) {
			super.index=0;
		}
		draw(g2, screenX, screenY);
	}
	
	@Override
	public void draw(Graphics2D g2, int screenX, int screenY) {
		g2.drawImage(animationFrames.get(index), screenX-artSizeX/2, screenY-artSizeY/2, artSizeX, artSizeY, null);
	}
	
}
