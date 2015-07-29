import java.io.File;
import java.util.Date;
import java.util.Scanner;

public class Operator<T> {
	
	Date date;
	Scanner sc;
	String lastCommand;
	String path;

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
		this.path = System.getProperty("user.dir");
		new File(this.path + "/replays").mkdirs();
	}
	
	public void printLine(T s) {
		System.out.println(s);
	}
	
	public void print(T s) {
		System.out.print(s);
	}
	
	public void debug(T s) {
		System.out.println(s);
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
		System.out.println("5. Rules");
		System.out.println("6. Exit");

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
			
		case "rules":
			
			break;

		}

	}
	
	public String listen() {
		this.lastCommand = this.sc.nextLine();
		return this.lastCommand;
	}
	
	public String dateStamp() {
		
		this.date = new Date();
		
		return date.toString();
	}
	public void waitTimer(int w){
		try {
			Thread.sleep(w);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}