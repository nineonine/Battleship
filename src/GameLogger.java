import java.util.Date;

public class GameLogger {
	private static String FILEPATH = "path to saved replays";
	private StringBuilder log;

	public GameLogger() {
		StringBuilder log = new StringBuilder();
		log.append(new Date() + "\n");
	}
	
	public void updateLog(String s) {
		this.log.append(s);
	}
	
	public void saveToFile() {
	
		// Saves game to specific file
	}
}
