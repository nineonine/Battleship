public class GameLogger {
	private static String FILEPATH = "path to saved replays";
	public StringBuilder log;

	public GameLogger(String date) {
		this.log = new StringBuilder(date);
	}
	
	public void updateLog(String s) {
		this.log.append(s+"\n");
	}
	
	@Override
	public String toString() {
		return "GameLogger [log=" + log + "]";
	}

	public void saveToFile() {
	
		// Saves game to specific file
	}
	
}
