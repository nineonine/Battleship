
public class Cell {
	
	Cell north,south,east,west;
	String tag;
	boolean isMined;
	boolean isShot;
	private int x;
	private int y;
	Ship slot;
	
	public Cell(int x, int y) {
		super();
		this.tag = convertXYtoTag(x, y);
		this.x = x;
		this.y = y;
		this.isMined = false;
		this.isShot = false;
		this.slot = null;
	}

	public void deployShip(Ship ship) {
		this.slot = ship;
	}
	
	public static String convertXYtoTag(int x, int y) {
		String s = null;
		switch(x) {
		case 0:
			s = "a";break;
		case 1:
			s = "b";break;
		case 2:
			s = "c";break;
		case 3:
			s = "d";break;
		case 4:
			s = "e";break;
		case 5:
			s = "f";break;
		case 6:
			s = "g";break;
		case 7:
			s = "h";break;	
		}
		return s + (y+1);
	}

	//debugging
	public String toString() {
		return "Cell : " + tag + "(" + x + ";" + y + ")";
	}
	
	

	
}
