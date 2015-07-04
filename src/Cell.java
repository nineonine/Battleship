
public class Cell {
	
	Cell north,south,east,west;
	String tag;
	boolean isMined;
	boolean isShot;
	private int x;
	private int y;
	private Ship slot;
	
	
	public Cell(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		
		
	}

	public void deployShip(Ship ship) {
		this.slot = ship;
	}

	//debugging
	public String toString() {
		return "Cell : x : " + x + " , y : " + y;
	}
	
	

	
}
