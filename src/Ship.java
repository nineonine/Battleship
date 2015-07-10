import java.util.LinkedList;


public class Ship {
	public int size;
	public LinkedList<String> shipCoords;
	public boolean isDamaged;
	public boolean isDestroyed;

	public Ship(int size) {
		this.size = size;
		this.isDamaged = false;
		this.isDestroyed = false;
		this.shipCoords = new LinkedList<String>();
	}
	
	public void destroyCell(String coord) {
		this.shipCoords.remove(coord);
		this.isDamaged = true;
		if (this.shipCoords.size() == 0) {
			this.isDestroyed = true;
		}
	}
	
	public boolean IsDamaged() {	
		return this.isDamaged = this.shipCoords.size() < this.size ? true : false;
	}
	
	public boolean IsDestroyed() {	
		return isDestroyed = this.shipCoords.size() == 0;
	}
}
