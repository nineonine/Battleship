
public class App {

	private static Settings settingsObj;
	private static Game game;
	private static Operator<Object> operator;

	private static boolean notDone; // controlling the main loop of execution. Helps us exit the program 


	public static void main(String[] args) {

		notDone = true;
		operator = new Operator<Object>("menu");
		settingsObj = new Settings(); // Instantiating default settings 
		
		

		while(notDone) {
			operator.printMenu();
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
					/*System.out.println("Default Properties: \nMines :"+ settingsObj.getNumOfMines()
							+ "\n3 place ships : "+settingsObj.getNumOfships4() +"\n4 place ships : "+settingsObj.getNumOfships3());
					System.out.println("Do you want to change game format?(Y or N)");
					
					boolean chk = true;
					while(chk){
						String flag= operator.listen();
						switch(flag){
						case "Y":
						case "y":
							System.out.println("Enter the number of mines you want to put:");
							int mines=Integer.parseInt(operator.listen());
							settingsObj.setNumOfMines(mines);
							System.out.println("Enter the number of 3 place ship you want to put:");
							int ships3=Integer.parseInt(operator.listen());
							settingsObj.setNumOfships3(ships3);
							System.out.println("Enter the number of 4 place ship you want to put:");
							int ships4=Integer.parseInt(operator.listen());
							settingsObj.setNumOfships4(ships4);
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
					// changing some settings object */
					settingsObj.customize(operator);
				break;

				case "4":	// scores
					operator.setMenuLevel("scores");

					// display scores
					// scores are read from and stored in a file

					//scores.show();

				break;

				case "5":	// exit

					operator.printLine("RULES TO BE DISPLAYED HERE");

				break;
				
				case "6":	// exit

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



