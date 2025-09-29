package hitboxes;

public class CollisionCheck {
	public boolean checkV(Hitbox H1, Hitbox H2, int y1, int y2) {
		if(y1+H1.getUp()<y2+H2.getDown()) {
			return true;
		}
		return false;
	}
	public boolean checkH(Hitbox H1, Hitbox H2, int x1, int x2) {
		if(x1+H1.getLeft()<x2+H2.getRight()) {
			return true;
		}
		return false;
	}
	
}
