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
	public void shoot(Cell[][] field, String coord) {
		
		
	}

	@Override
	public void placeShips(FieldService service) {
		op.printLine("\t" + this.name + " is placing ships ... \n");
		// emulation of thinking bot ^_^
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
