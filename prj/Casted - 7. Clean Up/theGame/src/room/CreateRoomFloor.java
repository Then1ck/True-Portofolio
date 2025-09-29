package room;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;

import mainPanel.Camera;
import mainPanel.GamePanel;
import floor.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CreateRoomFloor {
	private Floor[][] curFloor;
	private Random Rand;
	BufferedImage newRoom;
	FloorGenerator Tg;
	private GamePanel Gp;
	private Camera Cams;
	
	public CreateRoomFloor(int length, int height, GamePanel _gp, Camera cams, BufferedImage newImage) {
		curFloor = new Floor[height][length];
		Rand = new Random();
		Tg = new FloorGenerator();
		Gp = _gp;
		Cams = cams;
		newRoom = newImage;
	}
	
	public CreateRoomFloor(int length, int height, GamePanel _gp, Camera cams) {
		curFloor = new Floor[height][length];
		Rand = new Random();
		Tg = new FloorGenerator();
		Gp = _gp;
		Cams = cams;
		
		newRoom = new BufferedImage(length*_gp.getTileSize(), height*_gp.getTileSize(), BufferedImage.TYPE_INT_BGR);
		Graphics g = newRoom.getGraphics();
		
		for(int i=0;i<height;i++) {
			for(int j=0;j<length;j++) {
				switch(Rand.nextInt(4)+1) {
				case 1:
					curFloor[i][j] = new BlankGreen(j*_gp.getTileSize(), i*_gp.getTileSize(), _gp, cams);
					break;
				case 2:
					curFloor[i][j] = new Grass(j*_gp.getTileSize(), i*_gp.getTileSize(), _gp, cams);
					break;
				case 3:
					curFloor[i][j] = new Flower(j*_gp.getTileSize(), i*_gp.getTileSize(), _gp, cams);
					break;
				case 4:
					curFloor[i][j] = new Water(j*_gp.getTileSize(), i*_gp.getTileSize(), _gp, cams);
					break;
				}
				
			}
		}
		
		for(int i=0;i<height;i++) {
			for(int j=0;j<length;j++) {
				g.drawImage(curFloor[i][j].getImage(), j*_gp.getTileSize(), i*_gp.getTileSize(), _gp.getTileSize(), _gp.getTileSize(), null);
			}
		}
		
		try {
			ImageIO.write(newRoom, "png", new File("tempFiles/temp/room.png"));
			BufferedWriter Bw = new BufferedWriter(new FileWriter("tempFiles/temp/map.txt"));
			for(int i=0;i<height;i++) {
				for(int j=0;j<length;j++) {
					Bw.write(String.valueOf(curFloor[i][j].getTileID()));
					Bw.write(" ");
				}
				Bw.newLine();
			}
			Bw.close();
			System.out.println("Print success");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Floor getFloor(int i, int j) {
		return curFloor[i][j];
	}
	
	public BufferedImage getRoom() {
		return newRoom;
	}
	
	public void loadMap() {
		System.out.println("Load Map attempt");
		BufferedReader Br = null;
		try {
			Br = new BufferedReader(new FileReader("tempFiles/temp/map.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int i=0, j=0;
		while(true) {
			String line = null;
			try {
				line = Br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(line==null)break;
			String num[] = line.split(" ");
			for(String element : num) {
				int id = Integer.parseInt(element);	
				curFloor[i][j] = Tg.generateFloor(id, j*Gp.getTileSize(), i*Gp.getTileSize(), Gp, Cams);
				j++;
			}
			i++;
			j=0;
		}
		
	}
	
	public Floor[][] getCur(){
//		System.out.println("Floor Error");
		return curFloor;
	}
}
