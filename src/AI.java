import java.util.ArrayList;
import java.util.Arrays;


public class AI extends Player{
	
	Operator op;
	String name;
	Cell[][] field;
	Cell[][] enemyField;
	int mines;
	int attempts;
	ArrayList<Ship> fleet;
	
	public AI(String name, int mines, FieldService service, Operator op) {
		this.op = op;
		this.name = name;
		this.mines = mines;
		this.field = service.generateField();
		this.attempts = 0;
	}
	
	public Cell[][] returnField() {
		return this.field;
	}

	@Override
	public void shoot(Cell[][] field, String coord) {
		
		
	}

	@Override
	public void placeShips() {

	}

	@Override
	public void placeMines(Cell[][] field, FieldService service) {
		op.printLine("\t" + this.name + " is placing mines ... \n");
		try {
			Thread.sleep(2000);
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
