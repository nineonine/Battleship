import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FieldService {

	Settings st;
	Operator<Object> op;
	LinkedList<String> listOfCoords;
	Random rand;
	public Player winner;

	public FieldService() {

	}

	public FieldService(Operator<Object> op, Settings st) {
		this.st = st;
		this.op = op;
		this.listOfCoords = generateTags();
		this.rand = new Random();
	}

	public void setWinner(Player p) {
		this.winner = p;
	}

	public String getRandomTag() {
		int randomIndex = rand.nextInt((this.listOfCoords.size() - 1));
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

				if (i == 0) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = null;
					field[i][j].south = field[cachedI + 1][j];
					if (i != 0) {
						field[i][j].west = field[i][cachedJ - 1];
					} else {
						field[i][j].west = null;
					}
					if (j < 7) {
						field[i][j].east = field[i][cachedJ + 1];
					} else {
						field[i][j].east = null;
					}

				} else if (i == 7) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI - 1][j];
					field[i][j].south = null;
					if (j != 0) {
						field[i][j].west = field[i][cachedJ - 1];
					} else {
						field[i][j].west = null;
					}
					if (j < 7) {
						field[i][j].east = field[i][cachedJ + 1];
					} else {
						field[i][j].east = null;
					}

				} else if (j == 0) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI - 1][j];
					field[i][j].south = field[cachedI + 1][j];
					field[i][j].west = null;
					field[i][j].east = field[i][cachedJ + 1];
				} else if (j == 7) {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI - 1][j];
					field[i][j].south = field[cachedI + 1][j];
					field[i][j].west = field[i][cachedJ - 1];
					field[i][j].east = null;
				} else {
					int cachedI = i;
					int cachedJ = j;
					field[i][j].north = field[cachedI - 1][j];
					field[i][j].south = field[cachedI + 1][j];
					field[i][j].west = field[i][cachedJ - 1];
					field[i][j].east = field[i][cachedJ + 1];
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

	public boolean possibleToPlaceShip(Cell cell, Ship ship, Cell[][] field,
			String orientation, int counter) {

		if (counter == 0) {
			return true;
		} else if (cell == null) {
			return false;
		} else if (cell.slot != null) {
			return false;
		} else {
			switch (orientation) {
			case "v":
				cell = cell.south;
				break;
			case "h":
				cell = cell.east;
				break;
			}

			return this.possibleToPlaceShip(cell, ship, field, orientation,
					--counter);
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
				ship.shipCoords.add(cell.tag);
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
				ship.shipCoords.add(cell.tag);
				op.printLine("Ship(" + ship.size
						+ ") was successfully deployed to Cell " + cell.tag);
				coords.add(cell.tag);
				cell = cell.south;
				size--;
			}
			break;
		}
		op.debug("Ship(" + ship.size + ") coords : " + ship.shipCoords);
	}

	public String getRandomOrientation() {

		Random rand = new Random();
		return rand.nextBoolean() ? "h" : "v";
	}

	public LinkedList<String> generateTags() {

		LinkedList<String> all = new LinkedList<String>();
		LinkedList<String> letters = new LinkedList<String>(Arrays.asList("a",
				"b", "c", "d", "e", "f", "g", "h"));
		for (String e : letters) {
			for (int i = 1; i < 9; i++) {
				all.add(e + i);
			}
		}
		return all;
	}

	public LinkedList<String> appendLists(LinkedList<String> l1,
			LinkedList<String> l2) {
		LinkedList<String> list = new LinkedList<String>(l1);
		if (l1.size() == 0) {
			return list = l2;
		} else {
			list.addAll(l2);
		}
		return list;
	}

	public LinkedList<String> getPossibleTargetsInVicinity(Cell c, String tag) {
		LinkedList<String> list = new LinkedList<String>();

//		op.debug("passed cell (previously aimed) : " + c);
//		op.debug("passed tag : " + tag);

		switch (tag) {
		case "v":
			if (c.north != null && !(c.north.isShot)) {
				list.add(c.north.tag);
				op.debug(c.north + " isShot : " + c.north.isShot);
			}
			if (c.south != null && !(c.south.isShot)) {
				list.add(c.south.tag);
				op.debug(c.south + " isShot :" + c.south.isShot);
			}
			break;
		case "h":
			if (c.east != null && !(c.east.isShot)) {
				list.add(c.east.tag);
				op.debug(c.east + " isShot : " + c.east.isShot);
			}

			if (c.west != null && !(c.west.isShot)) {
				list.add(c.west.tag);
				op.debug(c.west + " isShot " + c.west.isShot);
			}
			break;
		default:
			op.debug("no orientation passed - checking everything");
			if (c.north != null && !(c.north.isShot)) {
				list.add(c.north.tag);
				op.debug(c.north + " isShot : " + c.north.isShot);
			}
			if (c.south != null && !(c.south.isShot)) {
				list.add(c.south.tag);
				op.debug(c.south + " isShot :" + c.south.isShot);
			}
			if (c.east != null && !(c.east.isShot)) {
				list.add(c.east.tag);
				op.debug(c.east + " isShot : " + c.east.isShot);
			}
			if (c.west != null && !(c.west.isShot)) {
				list.add(c.west.tag);
				op.debug(c.west + " isShot " + c.west.isShot);
			}
			break;
		}
		
		return list;
	}

	public void detectMineOverlapping(Player player) {
		
		// iterating through all the placed ship coords
		for (int i = 0; i < player.returnShipCoords().size()-1; i++) {
			// iterating through all placed mines
			for (int j = 0; j < player.returnMineCoords().size(); j++) {
				String tag = player.returnMineCoords().get(j);
				// GOT SOME ERROR HERE
				// Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 13, Size: 13
				// It happens rarely so will fix it later
				if (player.returnShipCoords().get(i).equals(tag)) { // <--- if we aim
					player.returnShipCoords().remove(i);
					op.printLine("Mine at '" + player.returnMineCoords().get(j)
							+ "' exploded. \n" + player.returnName()
							+ "'s ship is damaged !\n");
				}
				// if mine blows up, we set this cell to isShot - true
				this.returnCellByTag(tag, player.returnField()).isShot = true;
			}
		}
	}

	public String getOrientation(String tag1, String tag2) {
	
		if (tag1.substring(0, 1).equals(tag2.substring(0, 1)) ) {
			return "h";
		} else {
			return "v";
		}
	}

	public LinkedList<String> filterOutBadCoords(
			LinkedList<String> possiblePlacestoShootAt, String lastAim,
			String supposedOrientation) {
		LinkedList<String> list = new LinkedList<String>();
		op.debug("Current this.possiblePlacestoShootAt : " + possiblePlacestoShootAt + " <--- before filtering INSIDE filterOutBadCoords");
		op.debug("Current lastAim : " + lastAim);
		int size = possiblePlacestoShootAt.size();
		switch (supposedOrientation) {
		case "h":
			op.debug("because orientation is 'h' ");
			for (int i=0;i<size; i++) {
				String e = possiblePlacestoShootAt.get(i);
				if(lastAim.substring(0,1).equals(e.substring(0,1))) {
					list.add(e);
					op.debug(e + " was added ");
				} else {
					op.debug(e + " was filtered out");
				}
			}

			break;
		case "v":
			op.debug("because orientation is 'v' ");
			for (int i=0;i<size; i++) {
				String e = possiblePlacestoShootAt.get(i);
				System.out.println(e + " and " + lastAim);
				if(!lastAim.substring(0,1).equals(e.substring(0,1))) {
					list.add(e);
					op.debug(e + " was added ");
				} else {
					op.debug(e + " was filtered out");
				}
			}
			break;
		default:
			for (int i=0; i<size;i++) {
				String e = possiblePlacestoShootAt.get(i);
				list.add(e);
			}
			op.debug("no orientation - adding and returning everything");
			break;
		}
		op.debug("this is what does filterOutBadCoords() return : " + list);
		return list;
	}

}
