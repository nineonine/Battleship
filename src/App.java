

public class App {

	private static Settings settingsObj;
	private static Game game;
	private static Operator operator;

	private static boolean notDone; // controlling the main loop of execution. Helps us exit the program 


	public static void main(String[] args) {

		notDone = true;
		operator = new Operator("menu");
		settingsObj = new Settings(); // Instantiating default settings 
		
		operator.printMenu();

		while(notDone) {

			String choice = operator.listen();

			switch (choice) {

				case "1":	// single player game
					operator.setMenuLevel("game", "setup");
					settingsObj.multiPlayerOn = false;
					game = new Game(operator, settingsObj);

					game.run();

					// restart game or go to main menu

				break;

				case "2":	// multi player game
					operator.setMenuLevel("game", "setup");
					settingsObj.multiPlayerOn = true;
					game = new Game(operator, settingsObj);

					game.run();

					// restart game or go to main menu

				break;

				case "3":	// settings 
					operator.setMenuLevel("settings");

					// changing some settings object

					settingsObj.customize(operator);

				break;

				case "4":	// scores
					operator.setMenuLevel("scores");

					// display scores
					// scores are read from and stored in a file

					//scores.show();

				break;

				case "5":	// exit

					System.out.println("PROGRAM TERMINATED");
					System.exit(0);

				break;

				default:

					System.out.println("Unknown command. Type help or menu");
					continue;

			}
		}
	}
}



