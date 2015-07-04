import java.util.Scanner;

public class Operator {

	Scanner sc;
	String lastCommand;

	String menuLevel; // can be main / game / settings / scores;
	String gameStage; // ship placement and game itself

	public void setMenuLevel(String s) {
		this.menuLevel = s;
	}

	public void setMenuLevel(String s1, String s2) {
		this.menuLevel = s1;
		this.gameStage = s2;
	}

	public Operator(String env) {
		this.menuLevel = env;
		sc = new Scanner(System.in);
	}

	public void printMenu() {

		System.out.println();
		System.out.println("   __________");
		System.out.println("   |  menu  |");
		System.out.println("   ----------");
		System.out.println("1. Single Player");
		System.out.println("2. Multi Player");
		System.out.println("3. Settings");
		System.out.println("4. Scores");
		System.out.println("5. Exit");

	}

	public String listen() {
		this.lastCommand = this.sc.next();
		return this.lastCommand;
	}

	public void printHelp() {

		switch (this.menuLevel) {
		
		case "game":
			
			switch (this.gameStage) {
			
			case "setup":

				break;
				
			case "fight":

				break;
			}

			break;

		case "main":

			break;

		case "settings":

			break;

		case "scores":

			break;

		}

	}

}