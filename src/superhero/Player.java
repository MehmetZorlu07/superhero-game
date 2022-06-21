package superhero;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {

	//fields
	private int playerCoins;
	private Set<GameCharacter> characterSet;
	
	//constructor
	public Player(int coins,Set<GameCharacter> characters) {
		this.playerCoins = coins;
		this.characterSet = new HashSet<>(characters);
	}
	
	//getters
	public int getCoins() {
		return this.playerCoins;
	}
	
	public Set<GameCharacter> getCharacters(){
		return this.characterSet;
	}
	
	//buy method
	public void buy(GameCharacter gc) throws IllegalArgumentException{
		if(this.getCharacters().contains(gc)){
			throw new IllegalArgumentException("You already have this character.");
		}
		else if(this.getCoins()<gc.getCost()) {
			throw new IllegalArgumentException("You don't have enough coins to buy this character.");
		}
		else {
			this.characterSet.add(gc);
			this.playerCoins -= gc.getCost();
		}
	}
	
	//choose characters method
	public Set<GameCharacter> chooseCharacters(Power... neededPowers){
		//List of required powers for the level
		List<Power> requirements = new ArrayList<Power>(Arrays.asList(neededPowers));
		//Set of characters to be filled and returned
		Set<GameCharacter> result =  new HashSet<>();
		for(GameCharacter gc : characterSet) { 
			boolean found = false;
			for(Power p : gc.getPowers()) {
				if(requirements.contains(p)) {
					requirements.remove(p);
					found = true;
				}
			}
			if(found) 
				result.add(gc);
		}
		if(requirements.isEmpty())
			return result;
		else {
			List<GameCharacter> allCharacters = new ArrayList<>(GameCharacters.getAllCharacters());
			Collections.sort(allCharacters);
			List<GameCharacter> charactersToBuy = new ArrayList<>();
			int playerCoins = this.getCoins();
			for(GameCharacter gc : allCharacters) {
				if(playerCoins > 0) {
					boolean hasPower = false;
					if(!(this.characterSet.contains(gc)) && playerCoins >= gc.getCost()) {
						for	(Power p : gc.getPowers()) {
							if(requirements.contains(p)) {
								requirements.remove(p);
								hasPower = true;
							}
						}
					}
						if(hasPower) {
							charactersToBuy.add(gc);
							playerCoins-=gc.getCost();
					}
				}
			}
			if(requirements.isEmpty() && playerCoins>=0) {
				for(GameCharacter GC : charactersToBuy) {
					result.add(GC);
					this.buy(GC);
				}
				return result;
			}
		}
		return null;
	}
}
