package entities;

import hitboxes.Hitbox;
import mainPanel.Camera;

public abstract class Entity {
	protected int WorldX, WorldY, MaxSpeed, Health;
	protected Hitbox HBox, LBox;
	protected Camera Cams;
	protected int LimitX, LimitY;

	public Entity(int worldX, int worldY, int maxSpeed, int health, Camera cams, int limitX, int limitY) {
		super();
		this.Cams = cams;
		WorldX = worldX;
		WorldY = worldY;
		MaxSpeed = maxSpeed;
		Health = health;
		LimitX = limitX;
		LimitY = limitY;
	}
	
	public void setLBox(int top, int bottom, int left, int right) {
		LBox = new Hitbox(top, bottom, left, right);
	}
	
	public int getWorldX() {
		return WorldX;
	}
	
	public int getWorldY() {
		return WorldY;
	}
	
	public Hitbox getHbox() {
		return HBox;
	}
	
	public Hitbox getLbox() {
//		System.out.println("LBox error");
		return LBox;
	}
	
}
