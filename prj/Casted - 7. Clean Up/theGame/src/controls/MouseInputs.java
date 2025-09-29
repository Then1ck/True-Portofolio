package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseInputs implements MouseListener{
	private ArrayList<Buttons> ButtonList;
	
	public MouseInputs() {
		ButtonList = new ArrayList<Buttons>();
		ButtonList.add(new Buttons(MouseEvent.BUTTON1, "M1"));
		ButtonList.add(new Buttons(MouseEvent.BUTTON2, "M2"));
		ButtonList.add(new Buttons(MouseEvent.BUTTON3, "M3"));
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int temp = e.getButton();
		for(Buttons element : ButtonList) {
			if(element.compKeyCode(temp)) element.keyPressed();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		int temp = e.getButton();
		for(Buttons element : ButtonList) {
			if(element.compKeyCode(temp))element.keyReleased();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean held(int index) {
		return ButtonList.get(index).isHeld();
	}
	
	public boolean pressed(int index) {
		return ButtonList.get(index).isPressed();
	}

	public void fullReset() {
		for(Buttons element : ButtonList) {
			element.reset();
		}
	}
	
	public ArrayList<Buttons> getButtonList(){
		return ButtonList;
	}
	
}
