package hitboxes;

public class Hitbox {
	private int Up, Down, Left, Right;
	public Hitbox(int up, int down, int left, int right) {
		Up = up;
		Down = down;
		Left = left;
		Right = right;
	}
	public int getUp() {
		return Up;
	}
	public int getDown() {
		return Down;
	}
	public int getLeft() {
		return Left;
	}
	public int getRight() {
		return Right;
	}
	
	
}
