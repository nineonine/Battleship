import java.util.ArrayList;
import java.util.Arrays;


public class Human extends Player{
	
	Operator op;
	String name;
	Cell[][] field;
	int mines;
	int attempts;
	ArrayList<Ship> fleet;
	
	public Human(String name, int mines, FieldService service, Operator op) {
		this.op = op;
		this.name = name;
		this.mines = mines;
		this.field = service.generateField();
		System.out.println(this.field[0][0]);
		System.out.println(this.field[1][1]);
		this.attempts = 0;
	}

	@Override
	public void placeShips() {

	}

	@Override
	public void placeMines(Cell[][] field) {

	}
	
	@Override
	public void shoot(Cell[][] field, String coord) {
		
		
	}

	@Override
	public String toString() {
		return "Human [op=" + op + ", name=" + name + ", field="
				+ Arrays.toString(field) + ", mines=" + mines + ", attempts="
				+ attempts + ", fleet=" + fleet + "]";
	}
	

}
