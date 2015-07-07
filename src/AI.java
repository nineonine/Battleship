import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;


public class AI extends Player{
	
	Random rand;
	Operator<Object> op;
	String name;
	Cell[][] field;
	Cell[][] enemyField;
	int mines;
	int attempts;
	LinkedList<Ship> fleet;
	LinkedList<String> shipCoords;
	LinkedList<String> mineCoords;
	LinkedList<String> shotMemory;
	String lastAim;
	
	public AI() {
		this.rand = new Random();
	}
	
	public AI(String name, int mines, FieldService service, Operator<Object> op) {
		this.op = op;
		this.name = name;
		this.mines = mines;
		this.field = service.generateField();
		this.fleet = service.dispatchShips();
		this.attempts = 0;
		this.shipCoords = new LinkedList<String>();
		this.mineCoords = new LinkedList<String>();
		this.shotMemory = service.generateTags();
		this.rand = new Random();
		this.lastAim = "";
	}
	
	public String returnName() {
		return this.name;
	}
	
	public Cell[][] returnField() {
		return this.field;
	}

	public LinkedList<String> returnShipCoords() {
		return this.shipCoords;
	}
	
	public LinkedList<String> returnMineCoords() {
		return this.mineCoords;
	}

	@Override
	public void shootAt(Player passedPlayer, FieldService service) {
		
		int shots = 1;
		op.printLine(this.name + " is shooting ... \n");
		op.debug("...but he does not quite know how to do that so he waits");
		
		passedPlayer.shootAt(this, service);

	}

	@Override
	public void placeShips(FieldService service) {
		op.printLine("\t" + this.name + " is placing ships ... \n");
		
		while(!this.fleet.isEmpty()) {
			
			String randomCoord = service.getRandomTag();
			String randomOrientation = service.getRandomOrientation();		
			String command = randomCoord + " " + randomOrientation;
			if (service
					.possibleToPlaceShip(service.returnCellByTag(
							command.substring(0, 2), field), this.fleet
							.getLast(), this.returnField(), command
							.substring(command.length() - 1), this.fleet
							.getLast().size)) {
				service.placeShip(command, this.fleet.getLast(),
						this.returnField(), this.shipCoords);
				this.fleet.removeLast();
				op.printLine("Current ship placement : "
						+ shipCoords.toString());
			} else {
				op.printLine("Impossible to place ship here. It is either occupied or you are going out of field bounds");
				op.printLine(" ------------- display players field here");
			}
		}
		
		// emulation of thinking bot ^_^
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		op.printLine("\t" + this.name + " completed ship placement !\n");

	}

	@Override
	public void placeMines(Cell[][] field, LinkedList<String> passedMineCoords, FieldService service) {
		op.printLine("\t" + this.name + " is placing mines ... \n");

		while(this.mines!=0) {
			passedMineCoords.add(this.getAndRemoveRandomTagFromShotMemory());
			this.mines--;
		}
		// emulation of thinking bot ^_^
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		op.printLine("\t" + this.name + " completed mine placement !\n");

	}
	
	public String getAndRemoveRandomTagFromShotMemory() {
		
		int randomIndex = rand.nextInt((this.shotMemory.size()-1));
		return this.shotMemory.remove(randomIndex);
	}
	
	

	@Override
	public String toString() {
		return "AI [name=" + name + ", field="
				+ Arrays.toString(field) + ", enemyField="
				+ Arrays.toString(enemyField) + ", mines=" + mines
				+ ", attempts=" + attempts + ", fleet=" + fleet + "]";
	}

}
