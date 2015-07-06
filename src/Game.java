
public class Game {
	public FieldService service;
	public Player p1;
	public Player p2;
	public Operator<Object> op;
	public GameLogger logger;
	
	public Game(Operator<Object> op, Settings st) {
		
		this.op = op;
		service = new FieldService(this.op, st);
		logger = new GameLogger(op.dateStamp());
		
		op.printLine("\t\tGame starts !");
		op.printLine("Date : " + op.dateStamp()+"\n");
		
		
		this.p1 = new Human(st.player1Name, st.numOfMines, service, op);
		if(st.multiPlayerOn) {
			this.p2 = new Human(st.player2Name, st.numOfMines, service, op);} else {
			this.p2 = new AI("Bot", st.numOfMines, service, op);}
		
		logger.updateLog(this.p1.name + " vs " + this.p2.name);
		
		op.printLine("Entering mine placement phase ...\n");
		
		this.p1.placeMines(p2.returnField(), service);
		this.p2.placeMines(p1.returnField(), service);
		
		this.p1.placeShips(service);
		this.p2.placeShips(service);
		
		op.printLine("Everyone ready !!! Game starts !");
		
	}
	
	
	
	public void run() {

		
		// check mine-ship overlapping
		// if true - update 'damaged' cells on fields and inform player about that
		// i think this method has to belong to FieldService
		// service.checkAndUpdateField(p1.field)     <---- example
		
		//randomly get the first-mover
		
		
	}

}
