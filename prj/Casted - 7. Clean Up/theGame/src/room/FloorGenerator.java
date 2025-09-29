package room;

import mainPanel.Camera;
import mainPanel.GamePanel;
import floor.*;

public class FloorGenerator {
	public Floor generateFloor(int id, int posX, int posY, GamePanel _gp, Camera _cams) {
		switch(id) {
		case 1:
			return new BlankGreen(posX, posY, _gp, _cams);
		case 2:
			return new Grass(posX, posY, _gp, _cams);
		case 3:
			return new Flower(posX, posY, _gp, _cams);
		case 4:
			return new Water(posX, posY, _gp, _cams);
		}
		return null;
	}
}
