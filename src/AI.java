import java.util.Arrays;
import java.util.LinkedList;


public class AI extends Player{
	
	Operator<Object> op;
	String name;
	Cell[][] field;
	Cell[][] enemyField;
	int mines;
	int attempts;
	LinkedList<Ship> fleet;
	LinkedList<String> shipCoords;
	LinkedList<String> mineCoords;
	
	public AI(String name, int mines, FieldService service, Operator<Object> op) {
		this.op = op;
		this.name = name;
		this.mines = mines;
		this.field = service.generateField();
		this.fleet = service.dispatchShips();
		this.attempts = 0;
		this.shipCoords = new LinkedList<String>();
		this.mineCoords = new LinkedList<String>();
	}
	
	public String returnName() {
		return this.name;
	}
	
	public Cell[][] returnField() {
		return this.field;
	}

	public LinkedList<String> returnShipCoords() {
		return this.shipCoords;
	}
	
	public LinkedList<String> returnMineCoords() {
		return this.mineCoords;
	}

	@Override
	public void shootAt(Player passedPlayer, FieldService service) {

	}

	@Override
	public void placeShips(FieldService service) {
		op.printLine("\t" + this.name + " is placing ships ... \n");
		// emulation of thinking bot ^_^
		//
		// For testing purposes, just for now AI places always at a1 v, a2 v, a3 v, a4 v;
		
		while(!this.fleet.isEmpty()) {
			service.placeShip("a1 v", this.fleet.removeLast(),
					this.returnField(), this.shipCoords);
			service.placeShip("a2 v", this.fleet.removeLast(),
					this.returnField(), this.shipCoords);
			service.placeShip("a3 v", this.fleet.removeLast(),
					this.returnField(), this.shipCoords);
			service.placeShip("a4 v", this.fleet.removeLast(),
					this.returnField(), this.shipCoords);
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		op.printLine("\t" + this.name + " completed ship placement !\n");

	}

	@Override
	public void placeMines(Cell[][] field, LinkedList<String> passedMineCoords, FieldService service) {
		op.printLine("\t" + this.name + " is placing mines ... \n");
		// emulation of thinking bot ^_^
		passedMineCoords.add("a1");
		passedMineCoords.add("a2");
		passedMineCoords.add("a3");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		op.printLine("\t" + this.name + " completed mine placement !\n");

	}

	@Override
	public String toString() {
		return "AI [name=" + name + ", field="
				+ Arrays.toString(field) + ", enemyField="
				+ Arrays.toString(enemyField) + ", mines=" + mines
				+ ", attempts=" + attempts + ", fleet=" + fleet + "]";
	}

}
