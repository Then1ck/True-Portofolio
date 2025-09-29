package weapon;

import java.util.ArrayList;

import mainPanel.Camera;

public class Gun2 extends RangedWeapons{

	private int maxDelay, delay, maxBurstDelay, maxShotCounter, burstDelay, shotCounter, tempDelay;
	
	public Gun2(int mDelay, int mBurst, int mShot) {
		Shots = new ArrayList<Projectiles>();
		maxDelay = mDelay;
		maxBurstDelay = mBurst;
		maxShotCounter = mShot;
		shotCounter = maxShotCounter;
		burstDelay = maxBurstDelay;
		tempDelay = 0;
		dueDeletion = new ArrayList<Projectiles>();
	}
	
	public void addProjectiles(int x1, int y1, int x2, int y2, Camera cams) {
		tempDelay = maxDelay;
		burstDelay--;
		if(burstDelay<=0) {
			burstDelay = maxBurstDelay;
			shotCounter--;
			Shots.add(new Projectiles(x1, y1, x2, y2, 20, 50, cams, 10, 1, 30));
		}
		if(shotCounter<=0) {
			shotCounter = maxShotCounter;
			delay = maxDelay;
		}
	}
	
	public ArrayList<Projectiles> getShots(){
		return Shots;
	}
	
	public boolean countDown() {
		if(delay <= 0) {
			if(tempDelay != 0) {
				tempDelay--;
			}else {
				tempDelay = maxDelay;
				shotCounter = maxShotCounter;
			}
		}else delay--;
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
