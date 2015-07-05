import java.util.LinkedList;



public class FieldService {
	
	Settings st;
	
	public FieldService(Settings st) {
		this.st = st;
	}
	
	// Generates two-dimensional array of Cells and connects them
	public Cell[][] generateField() {
		
		// Creating empty field
		Cell[][] field = new Cell[8][8];
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				field[i][j]=new Cell(i,j);
			}
		}
		
		// Connecting Phase
		for(int i=0;i<field.length;i++)
		{
			for(int j=0;j<field.length;j++)
			{
				if(i==0)
					field[i][j].north=null;
				else
					field[i][j].north=field[--i][j];
				if(j==0)
					field[i][j].west=null;
				else
					field[i][j].west=field[i][--j];
				if(i==field.length-1)
					field[i][j].south=null;
				else
					field[i][j].south=field[++i][j];
				if(j==field.length-1)
					field[i][j].east=null;
				else
					field[i][j].east=field[i][++j];
			}
		}
		
		return field;
	}
	
	public Cell returnCellByTag(String tag, Cell[][] field) {
		
		Cell cell = null; // searching cell by tag
		
		for(int i=0;i<field.length;i++) {
			for(int j=0;j<field.length;j++){
				if(field[i][j].tag.matches(tag)) {
					cell = field[i][j];
				}
			}
		}
		return cell;
	}

	public LinkedList<Ship> dispatchShips() {
		int numOf4sized = st.numOfships4;
		int numOf3sized = st.numOfships3;
		LinkedList<Ship> stack = new LinkedList<Ship>();
		while(numOf4sized!=0) {
			Ship ship = new Ship(4);
			stack.add(ship);
			numOf4sized--;
		}
		while(numOf3sized!=0) {
			Ship ship = new Ship(3);
			stack.add(ship);
			numOf3sized--;
		}
		return stack;
	}

	public boolean possibleToPlaceShip(Ship ship, Cell[][] field, String orientation) {
		
		
		
		return false;
	}
	
	public void placeShip(String tag, Ship ship, Cell[][] field) {
		
	}
	

//	public static void main(String args[])
//	{
//		Cell[][] obj = new FieldService().generateField();
////		for(int i=0;i<obj.length;i++)
////		{
////			for(int j=0;j<obj.length;j++)
////			{
////				
////				System.out.print((obj[i][j]).toString()+"\t");
////			}
////			System.out.println();
////		}
//
////		System.out.println(obj[0][0]);
////		System.out.println(obj[0][0].north);
////		System.out.println(obj[0][0].south);
////		System.out.println(obj);
////		System.out.println(new FieldService().returnCellByTag("a2", obj));
//		
//	}
	
}
