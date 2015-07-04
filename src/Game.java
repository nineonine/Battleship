
public class Game {
	public FieldService service;
	public Player p1;
	public Player p2;
	public Operator op;
	
	public Game(Operator op, Settings st) {
		
		this.op = op;
		service = new FieldService();
		this.p1 = new Human(st.player1Name, st.numOfMines, service, op);
		if(st.multiPlayerOn) {
			this.p2 = new Human(st.player2Name, st.numOfMines, service, op);
		} else {
			this.p2 = new AI("Bot", st.numOfMines, service);
		}
		
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p2.field);
		this.p1.placeMines(p2.field, service);
		this.p2.placeMines(p1.field, service);
		
		this.p1.placeShips();
		this.p2.placeShips();
		
		
	}
	
	
	
	public void run() {

		
		// check mine-ship overlapping
		// if true - update 'damaged' cells on fields and inform player about that
		// i think this method has to belong to FieldService
		// service.checkAndUpdateField(p1.field)     <---- example
		
		//randomly get the first-mover
		
		
	}

}
