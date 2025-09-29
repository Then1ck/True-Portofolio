package controls;

public class Buttons {
	private int Code;
	private boolean pressed, released, held, toPress, toRelease;
	private String Identifier;
	
	public Buttons(int _Code, String _Identifier) {
		this.Code = _Code;
		this.pressed = false;
		this.released = false;
		this.held = false;
		this.toPress = true;
		this.toRelease = false;
		this.Identifier = _Identifier;
	}
	
	public boolean compKeyCode(int _Code) {
		return this.Code == _Code;
	}
	
	public void keyPressed() {
		if(toPress) {
			toPress=false;
			toRelease=true;
			pressed=true;
		}
		held=true;
	}
	
	public void keyReleased() {
		if(toRelease) {
			toPress=true;
			toRelease=false;
			released=true;
		}
		held=false;
	}
	
	public void reset() {
		pressed=false;
		released=false;
	}
	
	public boolean isHeld() {
		return held;
	}
	
	public boolean isPressed() {
		return pressed;
	}
	
	public boolean isReleased() {
		return released;
	}
	
	public String getId() {
		return Identifier;
	}
}
