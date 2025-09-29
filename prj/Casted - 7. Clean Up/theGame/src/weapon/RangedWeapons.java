package weapon;

import java.util.ArrayList;

import mainPanel.Camera;

public abstract class RangedWeapons extends Weapons{
	protected ArrayList<Projectiles> Shots, dueDeletion;
	
	public abstract void addProjectiles(int x1, int y1, int x2, int y2, Camera cams);
	public abstract ArrayList<Projectiles> getShots();
	public abstract boolean countDown();
	public abstract void addDel(Projectiles X);
	public abstract void removeProjectiles();
	
	public void setProjectileList(ArrayList<Projectiles> shots, ArrayList<Projectiles> dD) {
		Shots = shots;
		dueDeletion = dD;
	}
}
