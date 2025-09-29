package weapon;

import java.util.ArrayList;

import mainPanel.Camera;

public class Gun1 extends RangedWeapons{

	private int maxDelay, delay;
	
	public Gun1(int mDelay) {
		Shots = new ArrayList<Projectiles>();
		maxDelay = mDelay;
		dueDeletion = new ArrayList<Projectiles>();
	}
	
	public void addProjectiles(int x1, int y1, int x2, int y2, Camera cams) {
		delay = maxDelay;
		Shots.add(new Projectiles(x1, y1, x2, y2, 30, 45, cams, 1, 45));
	}
	
	public ArrayList<Projectiles> getShots(){
		return Shots;
	}
	
	public boolean countDown() {
		if(delay>0)delay--;
		return delay <= 0 ? true : false;
	}

	public void addDel(Projectiles X) {
		dueDeletion.add(X);
	}
	
	public void removeProjectiles() {
		for(Projectiles element : dueDeletion) {
			Shots.remove(element);
		}
		dueDeletion.clear();
	}
	
}
