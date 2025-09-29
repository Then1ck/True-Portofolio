package controls;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import mainPanel.GamePanel;

public class MouseWheelInputs implements MouseWheelListener{
	
	private GamePanel Gp;
	
	public MouseWheelInputs(GamePanel _gp) {
		super();
		this.Gp = _gp;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println(e.getWheelRotation());
		if(e.getWheelRotation()<0)Gp.getGame().upWeapon();
		if(e.getWheelRotation()>0)Gp.getGame().downWeapon();
		
	}

}
