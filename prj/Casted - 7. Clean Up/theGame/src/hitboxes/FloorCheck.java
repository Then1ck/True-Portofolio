package hitboxes;

import floor.Floor;

public class FloorCheck {
	
	private int Length, Height;
	public FloorCheck(int length, int height) {
		Length = length;
		Height = height;
	}
	
	public int moveUp(int movement, int x, int y, Hitbox h, Floor[][] curFloor) {
//		System.out.println("Test1");
		int tempMove = movement;
		int tempY = y-movement+h.getUp();
//		System.out.println(tempY/64 + " " + (x+h.getLeft())/64 + " " + (x+h.getRight())/64);

		if(tempY<0)return y+h.getUp();
		
		
		Floor leftFloor = curFloor[tempY/64][(x+h.getLeft())/64];
		Floor rightFloor = curFloor[tempY/64][(x+h.getRight())/64];
//		System.out.println("Test2");
//		if(leftFloor == null)System.out.println("NULL");
		
		if(!leftFloor.getPassable()) {
//			System.out.println("Test 2.1");
			Hitbox hBox = leftFloor.getHitbox();
//			System.out.println("Test 2.2");
			if((tempY < leftFloor.getY()+hBox.getDown()) && (x+h.getLeft() < leftFloor.getX()+hBox.getRight())) {
//				System.out.println("Test 2.3");
				tempMove = y+h.getUp()-leftFloor.getY()-hBox.getDown();
			}
//			System.out.println("Test 2.4");
		}
//		System.out.println("Test3");
		if(!rightFloor.getPassable()) {
//			System.out.println("Test 3.1");
			Hitbox hBox = rightFloor.getHitbox();
//			System.out.println("Test 3.2");
			if(tempY < rightFloor.getY()+hBox.getDown() && (x+h.getRight()  > rightFloor.getX()+hBox.getLeft())) {
//				System.out.println("Test 3.3");
				int temp2 = y+h.getUp()-rightFloor.getY()-hBox.getDown();
				tempMove = (tempMove < temp2 ? tempMove : temp2);
//				System.out.println("Test 3.4");
			}
		}
//		System.out.println(tempMove);
		return tempMove;
	}
	
	public int moveLeft(int movement, int x, int y, Hitbox h, Floor[][] curFloor) {
		int tempMove = movement;
		int tempX = x-movement+h.getLeft();

		if(tempX<0)return x+h.getLeft();
		
		Floor upFloor = curFloor[(y+h.getUp())/64][tempX/64];
		Floor downFloor = curFloor[(y+h.getDown())/64][tempX/64];
		if(!upFloor.getPassable()) {
			Hitbox hBox = upFloor.getHitbox();
			if((tempX < upFloor.getX()+hBox.getRight()) && (y+h.getUp() < upFloor.getY()+hBox.getDown())) {
				tempMove = x+h.getLeft()-upFloor.getX()-hBox.getRight();
			}
		}
		if(!downFloor.getPassable()) {
			Hitbox hBox = downFloor.getHitbox();
			if((tempX < downFloor.getX()+hBox.getRight()) && (y+h.getDown() > downFloor.getY()+hBox.getUp())) {
				int temp2 = x+h.getLeft()-downFloor.getX()-hBox.getRight();
				tempMove = (tempMove < temp2 ? tempMove : temp2);
			}
		}
		
		return tempMove;
	}
	
	public int moveDown(int movement, int x, int y, Hitbox h, Floor[][] curFloor) {
		int tempMove = movement;
		int tempY = y+movement+h.getDown();
		
		if(!isValid(tempY/64, Height))return Height*64-y-h.getDown()-1;
		
		Floor leftFloor = curFloor[tempY/64][(x+h.getLeft())/64];
		Floor rightFloor = curFloor[tempY/64][(x+h.getRight())/64];
		
//		System.out.println(leftFloor.getPassable() + " " + rightFloor.getPassable());
		
		if(!leftFloor.getPassable()) {
			Hitbox hBox = leftFloor.getHitbox();
//			System.out.println(tempY + " " + (leftFloor.getY()+hBox.getUp()));
			if((tempY > leftFloor.getY()+hBox.getUp()) && (x+h.getLeft() < leftFloor.getX()+hBox.getRight())) {
//				System.out.println(leftFloor.getY() + " " + hBox.getUp() + " " + y + " " + h.getDown());
				tempMove = leftFloor.getY()+hBox.getUp()-y-h.getDown();
			}
		}
		if(!rightFloor.getPassable()) {
			Hitbox hBox = rightFloor.getHitbox();
//			System.out.println(tempY + " " + (rightFloor.getY()+hBox.getUp()));
			if((tempY > rightFloor.getY()+hBox.getUp()) && (x+h.getRight() > rightFloor.getX()+hBox.getLeft())) {
//				System.out.println(rightFloor.getY() + " " + hBox.getUp() + " " + y + " " + h.getDown());
				int temp2 = rightFloor.getY()+hBox.getUp()-y-h.getDown();
				tempMove = (tempMove < temp2 ? tempMove : temp2);
			}
		}
		
//		System.out.println(tempMove);
		return tempMove;
	}
	
	public int moveRight(int movement, int x, int y, Hitbox h, Floor[][] curFloor) {
		int tempMove = movement;
		int tempX = x+movement+h.getRight();

		if(!isValid(tempX/64, Length))return Length*64-x-h.getRight()-1;
		
		Floor upFloor = curFloor[(y+h.getUp())/64][tempX/64];
		Floor downFloor = curFloor[(y+h.getDown())/64][tempX/64];
		if(!upFloor.getPassable()) {
			Hitbox hBox = upFloor.getHitbox();
			if((tempX > upFloor.getX()+hBox.getLeft()) && (y+h.getUp() < upFloor.getY()+hBox.getDown())) {
				tempMove = upFloor.getX()+hBox.getLeft()-x-h.getRight();
			}
		}
		if(!downFloor.getPassable()) {
			Hitbox hBox = downFloor.getHitbox();
			if((tempX > downFloor.getX()+hBox.getLeft()) && (y+h.getDown() > downFloor.getY()+hBox.getUp())) {
				int temp2 = downFloor.getX()+hBox.getLeft()-x-h.getRight();
				tempMove = (tempMove < temp2 ? tempMove : temp2);
			}
		}
		
		return tempMove;
	}
	
	public boolean isValid(int val, int limit) {
		if(val >= limit)return false;
		
		return true;
	}
}
