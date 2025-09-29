package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardInputs implements KeyListener{
	private ArrayList<Buttons> keyList, dueReset;
	public KeyboardInputs() {
		keyList = new ArrayList<Buttons>();
		keyList.add(new Buttons(KeyEvent.VK_W, "W"));
		keyList.add(new Buttons(KeyEvent.VK_A, "A"));
		keyList.add(new Buttons(KeyEvent.VK_S, "S"));
		keyList.add(new Buttons(KeyEvent.VK_D, "D"));
		keyList.add(new Buttons(KeyEvent.VK_SPACE, "Space"));
		keyList.add(new Buttons(KeyEvent.VK_ESCAPE, "Esc"));
		keyList.add(new Buttons(KeyEvent.VK_SHIFT, "Shift"));
		keyList.add(new Buttons(KeyEvent.VK_Q, "Q"));
		keyList.add(new Buttons(KeyEvent.VK_E, "E"));
		keyList.add(new Buttons(KeyEvent.VK_1, "1"));
		keyList.add(new Buttons(KeyEvent.VK_2, "2"));
		keyList.add(new Buttons(KeyEvent.VK_3, "3"));
		keyList.add(new Buttons(KeyEvent.VK_4, "4"));
		keyList.add(new Buttons(KeyEvent.VK_5, "5"));
		keyList.add(new Buttons(KeyEvent.VK_6, "6"));
		keyList.add(new Buttons(KeyEvent.VK_7, "7"));
		keyList.add(new Buttons(KeyEvent.VK_8, "8"));
		keyList.add(new Buttons(KeyEvent.VK_9, "9"));
		keyList.add(new Buttons(KeyEvent.VK_0, "0"));
		keyList.add(new Buttons(KeyEvent.VK_F1, "F1"));
		keyList.add(new Buttons(KeyEvent.VK_F2, "F2"));
		keyList.add(new Buttons(KeyEvent.VK_F3, "F3"));
		keyList.add(new Buttons(KeyEvent.VK_F4, "F4"));
		dueReset = new ArrayList<Buttons>();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int temp = e.getKeyCode();
		for(Buttons element : keyList) {
			if(element.compKeyCode(temp)) {
				element.keyPressed();
				dueReset.add(element);
			}
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int temp = e.getExtendedKeyCode();
		for(Buttons element : keyList) {
			if(element.compKeyCode(temp)) {
				element.keyReleased();
				dueReset.add(element);
			}
		}
		
	}
	
//	public void fullReset() {
//		for(Buttons element : keyList) {
//			element.reset();
//		}
//	}
	
	public boolean held(int index) {
		return keyList.get(index).isHeld();
	}
	
	public boolean pressed(int index) {
		return keyList.get(index).isPressed();
	}
	
	public ArrayList<Buttons> getKeyList(){
		return keyList;
	}
	
	public void addReset(Buttons val) {
		dueReset.add(val);
	}
	
	public void fullReset() {
		for(Buttons element : dueReset) {
			keyList.get(keyList.indexOf(element)).reset();
		}
		dueReset.clear();
	}
	
}
