package floor;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import hitboxes.Hitbox;
import mainPanel.Camera;

public abstract class Floor {
	protected int WorldX, WorldY, Width, Height, TileID;
	protected BufferedImage Image;
	protected boolean Passable, Collision;
	protected Camera Cams;
	protected Hitbox HBox;
	
	public Floor(int worldX, int worldY, int width, int height, boolean passable, boolean collision, Camera cams, int tileID) {
		this.WorldX = worldX;
		this.WorldY = worldY;
		this.Width = width;
		this.Height = height;
		this.Passable = passable;
		this.Collision = collision;
		this.Cams = cams;
		this.TileID = tileID;
	}
	
	public void addImage(BufferedImage img) {
		this.Image = img;
	}
	
	public BufferedImage getImage() {
		return Image;
	}
	
	public int getTileID() {
		return TileID;
	}
	
	public boolean getPassable() {
		return Passable;
	}
	
	public boolean getCollision() {
		return Collision;
	}
	
	public Hitbox getHitbox() {
		return HBox;
	}
	
	public int getX() {
		return this.WorldX;
	}
	
	public int getY() {
		return this.WorldY;
	}
	
	public abstract void draw(Graphics2D g2);
}
