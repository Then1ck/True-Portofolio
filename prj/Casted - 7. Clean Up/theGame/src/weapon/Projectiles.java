package weapon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import hitboxes.Hitbox;
import mainPanel.Camera;

public class Projectiles {
	private double Speed, locX, locY;
	private double SpeedX, SpeedY;
	private Camera Cams;
	private BufferedImage projectileArt;
	private Hitbox HBox;
	private int Timer, Timing;
	private double Slow;
	
	public Projectiles(int posX, int posY, int targetX, int targetY, int speed, int timer, Camera cams, double slow, int timing) {
		System.out.println(posX + " " + posY);
		Speed = speed;
		if(targetX-posX==0) {
			if(targetY > posY)SpeedY = Speed;
			else SpeedY = -Speed;
			SpeedX = 0;
		}else {
			double m = (double)(targetY-posY)/(double)(targetX-posX);//if x = 1, y = m;
			double c = Math.sqrt((Speed*Speed)/(1+m*m));
			SpeedX = c;
			SpeedY = m*c;
		}
		
		if(targetX < posX) {
			SpeedX = -SpeedX;
			SpeedY = -SpeedY;
		}
		
		locX = posX;
		locY = posY;
		Cams = cams;
		try {
			projectileArt = ImageIO.read(getClass().getResourceAsStream("/projectiles/Bullet5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Timer = timer;
		Slow = slow;
		Timing = timing;
	}
	
	public Projectiles(int posX, int posY, int targetX, int targetY, int speed, int timer, Camera cams, int randomSpread, double slow, int timing) {
		
		double mult = circleSnap(posX, posY, targetX, targetY);
		targetX = (int)(mult*(targetX-posX)) + posX;
		targetY = (int)(mult*(targetY-posY)) + posY;
		if(targetX-posX==0&&targetY-posY==0) {
			targetY = posY - 300;
		}
		
		Random rand = new Random();
		
		double spread = rand.nextDouble();
		switch(rand.nextInt(3)) {
		case 0:
			break;
		case 1:
			double temp = (targetX-posX);
			targetX += -(targetY-posY)/randomSpread*spread;
			targetY += temp/randomSpread*spread;
			break;
		case 2:
			double temp2 = targetX-posX;
			targetX += (targetY-posY)/randomSpread*spread;
			targetY += -temp2/randomSpread*spread;
			break;
		}
		System.out.println(targetX + " " + targetY);
//		targetX += rand.nextInt(randomSpread*2)+1-randomSpread;
//		targetY += rand.nextInt(randomSpread*2)+1-randomSpread;
		Speed = speed;
		if(targetX-posX==0) {
			if(targetY > posY)SpeedY = Speed;
			else SpeedY = -Speed;
			SpeedX = 0;
		}else {
			double m = (double)(targetY-posY)/(double)(targetX-posX);//if x = 1, y = m;
			
//			if(rand.nextInt(2)==0) {
//				m = rand.nextDouble()/m;
//			}else m -= m/rand.nextDouble();
			
			double c = Math.sqrt((Speed*Speed)/(1+m*m));
			SpeedX = c;
			SpeedY = m*c;
		}
		
		if(targetX < posX) {
			SpeedX = -SpeedX;
			SpeedY = -SpeedY;
		}
		
		locX = posX;
		locY = posY;
		Cams = cams;
		try {
			projectileArt = ImageIO.read(getClass().getResourceAsStream("/projectiles/Bullet5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Timer = timer;
		Slow = slow;
		Timing = timing;
	}
	
	public boolean update() {
		locX += SpeedX;
		locY += SpeedY;
		
		if(Timer%Timing==0) {
			SpeedX /= Slow;
			SpeedY /= Slow;
		}
		
		if(Timer<=0) {
			return true;
		}
		Timer--;
		return false;
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(projectileArt, (int)locX-Cams.getCamX()-8, (int)locY-Cams.getCamY()-8, 16, 16, null);
	}
	
	public void printV() {
		System.out.println(SpeedX + " " + SpeedY);
	}
	
	public double circleSnap(int posX, int posY, int targetX, int targetY) {
		int lineX = targetX - posX, lineY = targetY - posY;
		if(lineX==0)System.out.println("X");
		if(lineY==0)System.out.println("Y");
		double mult = Math.sqrt((double)(90000)/(double)(lineX*lineX+lineY*lineY));
		System.out.println(mult);
		return mult;
	}
}
