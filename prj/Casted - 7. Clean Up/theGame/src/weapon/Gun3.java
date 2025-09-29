package weapon;

import java.util.ArrayList;

import mainPanel.Camera;

public class Gun3 extends RangedWeapons{
	
	private int maxDelay, delay, maxShotCounter;
	
	public Gun3(int mDelay, int mShot) {
		Shots = new ArrayList<Projectiles>();
		maxDelay = mDelay;
		maxShotCounter = mShot;
		delay = 0;
		dueDeletion = new ArrayList<Projectiles>();
	}
	
	public void addProjectiles(int x1, int y1, int x2, int y2, Camera cams) {
		for(int i=0;i<maxShotCounter;i++) {
			Shots.add(new Projectiles(x1, y1, x2, y2, 20, 30, cams, 3, 1.1, 2));	
		}
		delay = maxDelay;
	}

	public ArrayList<Projectiles> getShots(){
		return Shots;
	}
	
	public boolean countDown() {
		if(delay>0)delay--;
		return delay == 0 ? true : false;
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
