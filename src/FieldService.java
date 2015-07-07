import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;

public class FieldService {

	Settings st;
	Operator<Object> op;
	LinkedList<String> listOfCoords;
	Random rand;

	public FieldService() {

	}

	public FieldService(Operator<Object> op, Settings st) {
		this.st = st;
		this.op = op;
		this.listOfCoords = generateTags();
		this.rand = new Random();
	}
	
	public String getRandomTag() {
		int randomIndex = rand.nextInt((this.listOfCoords.size()-1));
		return this.listOfCoords.get(randomIndex);
	}

	// Generates two-dimensional array of Cells and connects them
	public Cell[][] generateField() {

		// Creating empty field
		Cell[][] field = new Cell[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				field[i][j] = new Cell(i, j);
			}
		}

		// Connecting Phase
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				
				if(i==0) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = null;
					field[i][j].south = field[cachedI+1][j];
					if(i!=0) {
						field[i][j].west = field[i][cachedJ-1];
					} else {
						field[i][j].west = null;
					}
					if(j<7) {
						field[i][j].east = field[i][cachedJ+1];
					} else {
						field[i][j].east = null;
					}
					
				} else if(i==7) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI-1][j];
					field[i][j].south = null;
					if(j!=0) {
						field[i][j].west = field[i][cachedJ-1];
					} else {
						field[i][j].west = null;
					}
					if(j<7) {
						field[i][j].east = field[i][cachedJ+1];	
					} else {
						field[i][j].east = null;
					}
					
				} else if (j==0) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI-1][j];
					field[i][j].south = field[cachedI+1][j];
					field[i][j].west = null;
					field[i][j].east = field[i][cachedJ+1];
				} else if (j==7) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI-1][j];
					field[i][j].south = field[cachedI+1][j];
					field[i][j].west = field[i][cachedJ-1];
					field[i][j].east = null;
				} else  {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI-1][j];
					field[i][j].south = field[cachedI+1][j];
					field[i][j].west = field[i][cachedJ-1];
					field[i][j].east = field[i][cachedJ+1];
				}
			}
		}
		return field;
	}

	public Cell returnCellByTag(String tag, Cell[][] field) {
		// searching cell by tag

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field.length; j++) {
				if (field[i][j].tag.matches(tag)) {
					return field[i][j];
				} 
			}
		}
		return null;
	}

	public LinkedList<Ship> dispatchShips() {
		int numOf4sized = st.numOfships4;
		int numOf3sized = st.numOfships3;
		LinkedList<Ship> stack = new LinkedList<Ship>();
		while (numOf4sized != 0) {
			Ship ship = new Ship(4);
			stack.add(ship);
			numOf4sized--;
		}
		while (numOf3sized != 0) {
			Ship ship = new Ship(3);
			stack.add(ship);
			numOf3sized--;
		}
		return stack;
	}

	public boolean possibleToPlaceShip(Cell cell, Ship ship,
			Cell[][] field, String orientation, int counter) {
		
		if(counter == 0) {
			return true;
		}
		else if(cell==null) {
			return false;
		} else if (cell.slot != null) {
			return false;
		} else {
			switch(orientation) {
			case "v":
				cell = cell.south;
				break;
			case "h":
				cell = cell.east;
				break;
			}
			
			return this.possibleToPlaceShip(cell, ship, field, orientation, --counter);
		}
	}

	public void placeShip(String tag, Ship ship, Cell[][] field,
			LinkedList<String> coords) {

		int size = ship.size;
		Cell cell;

		//
		cell = this.returnCellByTag(tag.substring(0, 2), field);
		switch (tag.substring(3, 4).toLowerCase()) {
		case ("h"):
			while (size != 0) {
				cell.deployShip(ship);
				op.printLine("Ship(" + ship.size
						+ ") was successfully deployed to Cell " + cell.tag);
				coords.add(cell.tag);
				cell = cell.east;
				size--;
			}
			break;
		case ("v"):
			while (size != 0) {
				cell.deployShip(ship);
				op.printLine("Ship(" + ship.size
						+ ") was successfully deployed to Cell " + cell.tag);
				coords.add(cell.tag);
				cell = cell.south;
				size--;
			}
			break;
		}

	}
	
	public String getRandomOrientation() {
		
		Random rand = new Random();
		return rand.nextBoolean() ? "h" : "v";
	}
	
	public LinkedList<String> generateTags() {
		
		LinkedList<String> all = new LinkedList<String>();
		LinkedList<String> letters = new LinkedList<String>(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"));
		for(String e : letters) {
			for(int i=1;i<9;i++) {
				all.add(e+i);
			}
		}
		return all;
	}

	public void detectMineOverlapping(Player player) {
		
		for(int i = 0; i < player.returnShipCoords().size();i++) {
			for(int j = 0; j < player.returnMineCoords().size();j++) {
				String tag = player.returnMineCoords().get(j);
				if(player.returnShipCoords().get(i).equals(tag)) {
					player.returnShipCoords().remove(i);
					op.printLine("Mine at '" + player.returnMineCoords().get(j) + "' exploded. \n"
							+ player.returnName() + "'s ship is damaged !\n");
				} 
				this.returnCellByTag(tag, player.returnField()).isShot = true;
			}
		}		
	}

//	public static void main(String args[]) {
//		FieldService fs = new FieldService();
//		Cell[][] field = fs.generateField();
//		for (int i = 0; i < field.length; i++) {
//			for (int j = 0; j < field.length; j++) {
//				System.out.print((field[i][j]).toString() + "\t");}System.out.println();}System.out.println();}

}
