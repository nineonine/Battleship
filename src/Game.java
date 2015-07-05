
public class Game {
	public FieldService service;
	public Player p1;
	public Player p2;
	public Operator op;
	public GameLogger logger;
	
	public Game(Operator op, Settings st) {
		
		this.op = op;
		service = new FieldService();
		logger = new GameLogger(op.dateStamp());
		
		op.printLine("\t\tGame starts !");
		op.printLine("Date : " + op.dateStamp()+"\n");
		
		
		this.p1 = new Human(st.player1Name, st.numOfMines, service, op);
		if(st.multiPlayerOn) {
			this.p2 = new Human(st.player2Name, st.numOfMines, service, op);} else {
			this.p2 = new AI("Bot", st.numOfMines, service, op);}
		
		logger.updateLog(this.p1.name + " vs " + this.p2.name);
		
//		System.out.println(p1);
//		System.out.println(p2);
		op.printLine("Entering mine placement phase ...\n");
		
		this.p1.placeMines(p2.returnField(), service);
		this.p2.placeMines(p1.returnField(), service);
		
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
