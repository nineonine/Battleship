import java.util.LinkedList;

public abstract class Player {
	
	String name;
	Cell[][] field;
	int mines;
	int attempts;
	LinkedList<Ship> fleet;
	LinkedList<String> shipCoords;
	LinkedList<String> mineCoords;
	
	public Player() {
		
	}
	
	public void shootAt(Player passedPlayer, FieldService service) {

	}
	
	public void placeShips(FieldService service) {
		
	}
	
	public void placeMines(Cell[][] field, LinkedList<String> mineCoords, FieldService service) {
		
	}
	
	public String returnName() {
		return this.name;
	}

	public Cell[][] returnField() {
		return this.field;
	}
	
	public LinkedList<String> returnMineCoords() {
		return this.mineCoords;
	}

	public LinkedList<String> returnShipCoords() {
	
		return this.shipCoords;
	}
	

}
