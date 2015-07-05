import java.util.ArrayList;
import java.util.Arrays;

public class Human extends Player {

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
		this.attempts = 0;
	}
	
	public Cell[][] returnField() {
		return this.field;
	}

	@Override
	public void placeShips() {

	}

	@Override
	public void placeMines(Cell[][] passedField, FieldService service) {
		
		op.printLine(this.name + " is placing mines ... \n");
		op.printLine("Please enter cell coordinates");
		op.printLine("Example : 'a1' or 'h8'");
		// command for placing ships
		// a1

		while (this.mines != 0) {
			String coord = op.listen();
			if (coord.matches("^[a-hA-H]{1}[1-8]{1}(.*)")) {
				
				// getting Cell here
				// if isMined then 'continue' - we have to re enter coords
				// else -> setting isMined to TRUE
				// decrementing this.mines
				Cell cell = service.returnCellByTag(coord.substring(0,2), passedField);

				if (cell.isMined) {
					op.printLine("This cell is already mined. Please choose another one.");
					continue;
				} else {
					cell.isMined = true;
					this.mines--;
					op.printLine(coord.substring(0,2) + " is mined ! " + this.mines + " mines left.");
				}

			} else {
				op.printLine("Wrong command. Please try again.");
				op.printHelp();
				continue;
			}
		}
		op.printLine("Mine placement Complete !\n");
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
