import java.util.ArrayList;
import java.util.Arrays;


public class AI extends Player{
	
	String name;
	Cell[][] field;
	Cell[][] enemyField;
	int mines;
	int attempts;
	ArrayList<Ship> fleet;
	
	public AI(String name, int mines, FieldService service) {
		this.name = name;
		this.mines = mines;
		this.field = service.generateField();
		this.attempts = 0;
	}

	@Override
	public void shoot(Cell[][] field, String coord) {
		
		
	}

	@Override
	public void placeShips() {

	}

	@Override
	public void placeMines(Cell[][] field) {

	}

	@Override
	public String toString() {
		return "AI [name=" + name + ", field="
				+ Arrays.toString(field) + ", enemyField="
				+ Arrays.toString(enemyField) + ", mines=" + mines
				+ ", attempts=" + attempts + ", fleet=" + fleet + "]";
	}

}
