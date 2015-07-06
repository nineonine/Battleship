import java.util.Arrays;
import java.util.LinkedList;

public class Human extends Player {

	Operator<Object> op;
	String name;
	Cell[][] field;
	int mines;
	int attempts;
	LinkedList<Ship> fleet;
	LinkedList<String> shipCoords;
	LinkedList<String> mineCoords;

	public Human(String name, int mines, FieldService service,
			Operator<Object> op) {
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
	public void placeShips(FieldService service) {

		op.printLine(this.name + " is placing ships ... \n");
		op.printLine("Please enter cell coordinates");
		op.printLine("Example : 'a1 h' will result in placing 4sized ship in a1-a2-a3-a4");
		op.printLine("\t\t\tdisplaying example field here <--------\n");

		while (!this.fleet.isEmpty()) {

			op.printLine("Placing ship of size : " + fleet.getLast().size + " . Ships left: " +this.fleet.size());
			String command = op.listen();
			if (command.matches("^[a-hA-H]{1}[1-8]{1}[\\s]{1}[hHvV]{1}(.*)")) {
				// example : 'g1 v' or 'a6 h'
				// we can now try placing ships
				if (service.possibleToPlaceShip(
						service.returnCellByTag(command.substring(0, 2), field),
						this.fleet.getLast(),
						this.returnField(),
						command.substring(command.length() - 1),
						this.fleet.getLast().size)) 
				{
					service.placeShip(command, this.fleet.getLast(),
							this.returnField(), this.shipCoords);
					this.fleet.removeLast();
					op.printLine("Current ship placement : "
							+ shipCoords.toString());
				} else {
					op.printLine("Impossible to place ship here. It is either occupied or you are going out of field bounds");
					op.printLine(" ------------- display players field here");
				}

			} else {
				op.printLine("Wrong command. Please try again.");
				op.printHelp();
				continue;
			}
		}
		
		op.printLine("\n" + this.name + " finished deploying ships...\n");

	}

	@Override
	public void placeMines(Cell[][] passedField, LinkedList<String> passedMineCoords, FieldService service) {

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
				Cell cell = service.returnCellByTag(coord.substring(0, 2),
						passedField);

				if (cell.isMined) {
					op.printLine("This cell is already mined. Please choose another one.");
					continue;
				} else {
					passedMineCoords.add(coord.substring(0, 2));
					cell.isMined = true;
					this.mines--;
					op.printLine(coord.substring(0, 2) + " is mined ! "
							+ this.mines + " mines left.");
				}

			} else {
				op.printLine("Wrong command. Please try again.");
				op.printHelp();
				continue;
			}
		}
		op.debug("mine coords : "+ passedMineCoords);
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
