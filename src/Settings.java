
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
		this.numOfMines = 1;
		this.numOfships4 = 1;
		this.numOfships3 = 1;
		this.disableMines = false;
	}

	public int getNumOfships4() {
		return numOfships4;
	}

	public void setNumOfships4(int numOfships4) {
		this.numOfships4 = numOfships4;
	}

	public int getNumOfships3() {
		return numOfships3;
	}

	public void setNumOfships3(int numOfships3) {
		this.numOfships3 = numOfships3;
	}

	public int getNumOfMines() {
		return numOfMines;
	}

	public void setNumOfMines(int numOfMines) {
		this.numOfMines = numOfMines;
	}

	public void customize(Operator operator) {	
		System.out.println("Default Settings: \nMines :"+ getNumOfMines()
		+ "\n3 place ships : "+getNumOfships4() +"\n4 place ships : "+getNumOfships3());
		System.out.println("Do you want to change game format?(Y or N)");

		boolean chk = true;
		while(chk){
			String flag= operator.listen();
			switch(flag){
			case "Y":
			case "y":
				System.out.println("Enter the number of mines you want to put:");
				int mines=Integer.parseInt(operator.listen());
				setNumOfMines(mines);
				System.out.println("Enter the number of 3 place ship you want to put:");
				int ships3=Integer.parseInt(operator.listen());
				setNumOfships3(ships3);
				System.out.println("Enter the number of 4 place ship you want to put:");
				int ships4=Integer.parseInt(operator.listen());
				setNumOfships4(ships4);
				chk=false;
				break;
			case "N":
			case "n":
				System.out.println("Default settings applied");
				chk=false;
				break;
			default:
				System.out.println("Unknown command. Type choice Y or N");
				continue;
			}// you should print 1 to run the game, it'l change all the properties of mines and ships.
		}
		System.out.println("Change Settings: \nMines :"+ getNumOfMines()
				+ "\n3 place ships : "+getNumOfships4() +"\n4 place ships : "+getNumOfships3());
	}

}
