
public class Settings {
	
	String player1Name;
	String player2Name;
	boolean multiPlayerOn;
	boolean recordMatch;
	boolean disableMines;
	int numOfships4;
	int numOfships3;
	int numOfMines;
	
	public Settings() {
		//DEFAULT
		this.player1Name = "Player 1";
		this.player2Name = "Player 2";
		this.multiPlayerOn = false;
		this.recordMatch = true;
		this.numOfMines = 3;
		this.numOfships4 = 2;
		this.numOfships3 = 2;
		this.disableMines = false;
	}

	public void customize(Operator operator) {	
		
	}

}
