import java.util.LinkedList;

public class FieldService {

	Settings st;
	Operator<Object> op;

	public FieldService() {

	}

	public FieldService(Operator<Object> op, Settings st) {
		this.st = st;
		this.op = op;
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

	public boolean possibleToPlaceShip(String tagCoord, Ship ship,
			Cell[][] field, String orientation) {
		
		int size = ship.size;
		Cell cell = this.returnCellByTag(tagCoord, field);
		
		if(cell.slot != null) {
			return false; // this cell is already occupied
		}
		
		while(cell != null && cell.slot == null) {
			
		}
		
		return true;
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
				op.print("Ship(" + ship.size
						+ ") was successfully deployed to Cell " + cell.tag);
				coords.add(cell.tag);
				cell = cell.south;
				size--;
			}
			break;
		}

	}

//	public static void main(String args[]) {
//		FieldService fs = new FieldService();
//		Cell[][] field = fs.generateField();
//		Cell cell = new FieldService().returnCellByTag("h1", field);
//		System.out.println(cell);
//		System.out.println("SHOULD BE SOUTH " + cell.south);
//		System.out.println("SHOULD BE NORTH " + cell.north);
//		System.out.println("SHOULD BE EAST  " + cell.east);
//		System.out.println("SHOULD BE WEST " + cell.west);
//		for (int i = 0; i < field.length; i++) {
//			for (int j = 0; j < field.length; j++) {
//
//				System.out.print((field[i][j]).toString() + "\t");
//			}
//			System.out.println();
//		}
//		System.out.println();
//
//		
//
//	}

}
