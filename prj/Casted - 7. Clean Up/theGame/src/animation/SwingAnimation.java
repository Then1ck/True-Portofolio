package animation;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class SwingAnimation extends Animation{

	private int step;
	
	public SwingAnimation(ArrayList<BufferedImage> AFrames, int idx, int ASX, int ASY) {
		super(AFrames, idx, ASX, ASY, 8);
		step = 1;
	}

	@Override
	public void Animate(Graphics2D g2, int screenX, int screenY) {
		if(counter < delayFrames) {
			counter++;
			draw(g2, screenX, screenY);
			return;
		}
		counter = 0;
		if(super.index<=0) {
			step = 1;
		}else if(super.index>=super.animationFrames.size()-1) {
			step = -1;
		}
		draw(g2, screenX, screenY);
		super.index += step;
		
	}

	@Override
	public void draw(Graphics2D g2, int screenX, int screenY) {
		g2.drawImage(animationFrames.get(index), screenX-artSizeX/2, screenY-artSizeY/2, artSizeX, artSizeY, null);
	}

}
