package superhero;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class GameCharacter implements Comparable<GameCharacter> {

	//fields
	private final String characterName;
	private final int characterCost;
	private final Set<Power> powers;
	
	//constructor
	public GameCharacter(String name,int cost,Power...powers) {
		this.characterName = name;
		this.characterCost = cost;
		this.powers = new HashSet<>(Arrays.asList(powers));
	}
	
	//getters
	public String getName() {
		return this.characterName;
	}
	
	public int getCost() {
		return this.characterCost;
	}
	
	public Set<Power> getPowers(){
		return new HashSet<>(powers);
	}
	
	
	//equals(), hashCode() and toString()
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + characterCost;
		result = prime * result + ((characterName == null) ? 0 : characterName.hashCode());
		result = prime * result + ((powers == null) ? 0 : powers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GameCharacter other = (GameCharacter) obj;
		if (characterCost != other.characterCost)
			return false;
		if (characterName == null) {
			if (other.characterName != null)
				return false;
		} else if (!characterName.equals(other.characterName))
			return false;
		if (powers == null) {
			if (other.powers != null)
				return false;
		} else if (!powers.equals(other.powers))
			return false;
		return true;
	}
	
	public String toString() {
		return  this.characterName + "("  + this.characterCost + "): " + this.powers;
	}
	
	//sorting
	public int compareTo(GameCharacter otherCharacter) {
		int otherCost = otherCharacter.getCost();
		return this.getCost()-otherCost;
	}
}
