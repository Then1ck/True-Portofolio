package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import mainPanel.SimulatedMouse;

public class MouseMovements implements MouseMotionListener{
	
	private int MouseX, MouseY;
	private SimulatedMouse SimulM;
	private boolean cursorType;
	
	public MouseMovements() {
		cursorType = true;
		MouseX = 0;
		MouseY = 0;
	}
	
	public void SimulAdd(SimulatedMouse simulM) {
		this.SimulM = simulM;
	}
	
	public void flipCursor() {
		cursorType = !cursorType;
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		if(cursorType)SimulM.Reset();
		else SimulM.LayOver();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		MouseX = e.getX();
		MouseY = e.getY();
		if(cursorType)SimulM.Reset();
		else SimulM.LayOver();
		
	}
	
	public int getMouseX() {
		return MouseX;
	}
	
	public int getMouseY() {
		return MouseY;
	}
	

}
