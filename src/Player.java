import java.util.LinkedList;

public abstract class Player {
	
	String name;
	Cell[][] field;
	int mines;
	int attempts;
	LinkedList<Ship> fleet;
	LinkedList<String> shipCoords;
	
	public Player() {
		
	}
	
	public void shoot(Cell[][] field, String coord) {
		
	}
	
	public void placeShips(FieldService service) {
		
	}
	
	public void placeMines(Cell[][] field, FieldService service) {
		
	}

	public Cell[][] returnField() {
		return this.field;
	}
	

}
