import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class AI extends Player {

	Random rand;
	Operator<Object> op;
	String name;
	Cell[][] field;
	Cell[][] enemyField;
	int mines;
	int attempts;
	int shipsToDestroy;
	LinkedList<Ship> fleet;
	LinkedList<String> shipCoords;
	LinkedList<String> mineCoords;
	LinkedList<String> shotMemory;
	LinkedList<String> possiblePlacestoShootAt;
	String lastAim;
	String supposedOrientation;

	public AI() {
		this.rand = new Random();
	}

	public AI(String name, int mines, FieldService service, Operator<Object> op) {
		this.op = op;
		this.name = name;
		this.mines = mines;
		this.field = service.generateField();
		this.fleet = service.dispatchShips();
		this.shipsToDestroy = this.fleet.size();
		this.attempts = 0;
		this.shipCoords = new LinkedList<String>();
		this.mineCoords = new LinkedList<String>();
		this.shotMemory = service.generateTags();
		this.possiblePlacestoShootAt = new LinkedList<String>();
		this.rand = new Random();
		this.supposedOrientation = "undefined";
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
		
		op.printLine(this.name + " is shooting ... \n");

		int shots = 1;

		while (shots != 0) {
			this.attempts++;
			
			if (this.lastAim != null && !service.returnCellByTag(this.lastAim,
					passedPlayer.returnField()).returnShip().IsDestroyed()) { // <-- we aimed previous time and the ship we are shooting at is not destroyed 
				
				// saving pointer to the cell we are working on
				Cell cell = service.returnCellByTag(this.lastAim,
						passedPlayer.returnField());
				/*
				 this.possiblePlacestoShootAt works like a small cache history of shots which are related to one ship. 
				 When we damage some ship, we consider this aimed cell in our next shot and will shoot somewhere around. 
				 We also store proper orientation of shooting. 2 consequent successful shots prove the hypothesis of supposed orientation of the ship.
				 In case of proved orientation hypothesis we consider this factor while refreshing our 'possiblePlacestoShootAt' history so we can filter out wrong coords
				 Consider example --> 
					 Lets pretend that we have our ship in b1-b2-b3
					 Shot 1: Bot randomly chooses 'b2'. He hits and stores this.lastAim = 'b2' and next time he will choose from possible cells around : this.possiblePlacestoShootAt = [b1, b3, a2, c2]
					 Shot 2: Bot randomly chooses from 'this.possiblePlacestoShootAt' = [b1, b3, a2, c2] and gets  'b1'. He shoots again and he AIMS - this proves orientation and we store supposedOrientation = 'h' (horizontal) and lastAim to 'b1'
					 Shot 3: Bot remembers lastAim = 'b1'. he appends new possiblePlacestoShootAt [a1, c1] to existing one [b3, a2, c2] 
					 Shot 3(cont): now we have 'this.possiblePlacestoShootAt' = [b1, b3, a2, c3, a1, c1] and 'this.supposedOrientation' = h
					 Shot 3(cont): So we filter out the vertical'ish coords which leaves us with [b3]
					 We continue unless the ship we are shooting at is totally destroyed - then we just flush our this.possiblePlacestoShootAt, this.lastAim, this.supposedOrientation
				*/
				this.possiblePlacestoShootAt = new LinkedList<String>(
						new HashSet<String>(service.appendLists(service.filterOutBadCoords(this.possiblePlacestoShootAt, 
																this.lastAim, 
																this.supposedOrientation),
											service.getPossibleTargetsInVicinity(cell,this.supposedOrientation))));
				
				String pickedCoord = "";
				if(this.possiblePlacestoShootAt.size() == 0) {
					pickedCoord = service.returnCellByTag(this.lastAim, passedPlayer.returnField()).returnShip().getFirstOccurenceOfnotDamagedPart();
				} else {
					pickedCoord = this.possiblePlacestoShootAt.removeLast(); // our HashSet -> LinkedList casting sorts the collection automatically so we don't bother with random picking because randomness is implicit in this transformation
				}
				
				this.shotMemory.remove(pickedCoord); // removing from shotMemory

				if (passedPlayer.returnShipCoords().contains(pickedCoord)) { // if we aimed
					
					this.supposedOrientation = service.getOrientation(cell.tag,
							pickedCoord); // hypothesis proved ! - saved the right orientation
					
					op.printLine(this.name + " aimed at " + pickedCoord);
					cell.isShot = true;
					passedPlayer.returnShipCoords().remove(pickedCoord);
					service.returnCellByTag(this.lastAim,
							passedPlayer.returnField()).isShot = true;

					Ship hitShip = service.returnCellByTag(pickedCoord,
							passedPlayer.returnField()).returnShip();
					hitShip.destroyCell(pickedCoord);
					if (!hitShip.isDestroyed) {
						this.possiblePlacestoShootAt = service.filterOutBadCoords(this.possiblePlacestoShootAt, 
												 								  this.lastAim, 
																				  this.supposedOrientation);
						this.possiblePlacestoShootAt.remove(pickedCoord);
						this.lastAim = pickedCoord; // we are not completely done with that ship. Have to aim somewhere in vicinity
					} else {
						this.lastAim = null; // job done - ship destroyed. Clearing cache to shoot randomly next time
						this.supposedOrientation = "undefined"; 
						this.shipsToDestroy--;
						this.possiblePlacestoShootAt = new LinkedList<String>();
						
					}
					++shots;
				} else { // <-- its a miss
					op.printLine(this.returnName() + " tried to aim at "
							+ pickedCoord + " ... but missed :(\n");
					service.returnCellByTag(pickedCoord,
							passedPlayer.returnField()).isShot = true;
					if(this.possiblePlacestoShootAt.size() > 1) {
						this.switchOrientation(service.getOrientation(cell.tag,
								pickedCoord));
					}
					this.possiblePlacestoShootAt.remove(pickedCoord);	// next time we shoot - our
																		// possiblePlacestoShootAt value
																		// will be cleared out of 'bad
																		// orientation' coords
				}

			} else { // <---- we did not aim previous time - its a random shot
				String coord = this.getAndRemoveRandomTagFromShotMemory();
				Cell cell = service.returnCellByTag(coord,
						passedPlayer.returnField());
				if (passedPlayer.returnShipCoords().contains(coord)) { // its a
																		// hit
					Ship hitShip = service.returnCellByTag(coord,
							passedPlayer.returnField()).slot;
					cell.isShot = true;

					op.printLine(this.name + " aimed at " + coord);
					passedPlayer.returnShipCoords().remove(coord);

					hitShip.destroyCell(coord);
					if (!hitShip.isDestroyed) {
						this.lastAim = coord; // we are not completely done with
												// that ship. Have to aim
												// somewhere in vicinity
					} else {
						this.lastAim = null;
						this.shipsToDestroy--;

												// Clearing cache to shoot
												// randomly next time
					}
					++shots;

				} else { // <-- its a miss
					op.printLine(this.returnName() + " tried to aim at "
							+ coord + " ... but missed :(\n");
					cell.isShot = true;
				}
			}
	
			
			// check for winner
			if (passedPlayer.returnShipCoords().size() == 0) {
				op.printLine(this.returnName() + " wins !");
				op.printLine(this.returnName() + " with " + this.attempts + " attempts");
				service.setWinner(this);
				service.showBoard(passedPlayer);
				return;
			}
			--shots;
			op.waitTimer(2000);
		}
		
		
		passedPlayer.shootAt(this, service);
	}

	

	@Override
	public void placeShips(FieldService service) {
		op.printLine("\t" + this.name + " is placing ships ... \n");

		while (!this.fleet.isEmpty()) {

			String randomCoord = service.getRandomTag();
			String randomOrientation = service.getRandomOrientation();
			String command = randomCoord + " " + randomOrientation;
			if (service.possibleToPlaceShip(
					service.returnCellByTag(command.substring(0, 2), field),
					this.fleet.getLast(), this.returnField(),
					command.substring(command.length() - 1),
					this.fleet.getLast().size)) {
				service.placeShip(command, this.fleet.getLast(),
						this.returnField(), this.shipCoords);
				this.fleet.removeLast();
				
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
	public void placeMines(Cell[][] field, LinkedList<String> passedMineCoords,
			FieldService service) {
		op.printLine("\t" + this.name + " is placing mines ... \n");

		while (this.mines != 0) {
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
		
		int randomIndex = rand.nextInt(this.shotMemory.size());
		return this.shotMemory.remove(randomIndex);
	}
	
	private void switchOrientation(String orientation) {
		switch (orientation) {
		case "v":
			this.supposedOrientation = "h";
			return;
		case "h":
			this.supposedOrientation = "v";
			return;
		}

	}
	
	@Override
	public String toString() {
		return "AI [name=" + name + ", field=" + Arrays.toString(field)
				+ ", enemyField=" + Arrays.toString(enemyField) + ", mines="
				+ mines + ", attempts=" + attempts + ", fleet=" + fleet + "]";
	}

}
