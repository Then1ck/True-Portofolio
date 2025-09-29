package mainPanel;

import entities.Player;

public class Camera {
	private Player P1;
	private SimulatedMouse SimulM;
	private int CamX, CamY, halfScreenX, halfScreenY;
	
	public Camera(SimulatedMouse simulM, GamePanel _gp) {
		this.SimulM = simulM;
		this.halfScreenX = _gp.getWidth()/2;
		this.halfScreenY = _gp.getHeight()/2;
	}
	
	public void addPlayer(Player p1) {
		P1 = p1;
		declareCam();
	}
	
	public void declareCam() {
		CamX = P1.getWorldX()-(halfScreenX+(halfScreenX-SimulM.getSMouseX())/5);
		CamY = P1.getWorldY()-(halfScreenY+(halfScreenY-SimulM.getSMouseY())/5);
	}
	
	public int getCamX() {
		return CamX;
	}
	
	public int getCamY() {
		return CamY;
	}
	
	
}
