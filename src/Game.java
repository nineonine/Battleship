import java.util.Random;


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
		
		op.printLine("\t\tGame setup starts !");
		op.printLine("Date : " + op.dateStamp()+"\n");
		
		
		this.p1 = new Human(st.player1Name, st.numOfMines, service, op);
		if(st.multiPlayerOn) {
			this.p2 = new Human(st.player2Name, st.numOfMines, service, op);} else {
			this.p2 = new AI("Bot", st.numOfMines, service, op);}
		
		logger.updateLog(this.p1.name + " vs " + this.p2.name);
		
		op.printLine("Entering mine placement phase ...\n");
		
		this.p1.placeMines(p2.returnField(), p2.returnMineCoords(), service);
		this.p2.placeMines(p1.returnField(), p1.returnMineCoords(), service);
		
		this.p1.placeShips(service);
		this.p2.placeShips(service);
		
		op.printLine("Everyone ready !!! Game starts !\n");
		
	}
	
	
	
	public void run() {
		
//		Random rand = new Random();
		
		this.service.detectMineOverlapping(this.p1);
		this.service.detectMineOverlapping(this.p2);
		
		//now we have our players with set mines/ships and exploded mines/ships
		while(this.p1.returnShipCoords().size() !=0 && this.p2.returnShipCoords().size() != 0) {
//			rand.nextInt(2)+1
			switch(0) {
			case 0:
				this.p1.shootAt(p2, service);
				break;
			case 1:
				this.p2.shootAt(p1, service);
				break;
			}
		}
		

		//randomly get the first-mover
	
		
		
	}

}
