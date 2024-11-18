import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Ex7_20210808043 {
	public static void main(String[] args) {
		Warrior warrior = new Warrior("alifuat");
		Party party = new Party();
		warrior.joinParty(party);
	}
}

//Interfaces
interface Damageable {
	public void takeDamage(int damage);
	public void takeHealing(int healing);
	public boolean isAlive();
}

interface Caster {
	public void castSpell(Damageable target);
	public void learnSpell(Spell spell);
}

interface Combat extends Damageable {
	public void attack(Damageable target);
	public void lootWeapon(Weapon weapon);
}

interface Useable {
	public int use();
}

interface Comparable {
	public int compareTo(Character ch);
}

//Classes
class Spell implements Useable {
	private int minHeal;
	private int maxHeal;
	private String name;
	
	public Spell(int minHeal, int maxHeal, String name) {
		this.minHeal = minHeal;
		this.maxHeal = maxHeal;
		this.name = name;
	}
	
	public int getMinHeal() {
		return this.minHeal;
	}



	public void setMinHeal(int minHeal) {
		this.minHeal = minHeal;
	}



	public int getMaxHeal() {
		return this.maxHeal;
	}



	public void setMaxHeal(int maxHeal) {
		this.maxHeal = maxHeal;
	}



	public String getName() {
		return this.name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	private int heal() {
		return (int) ((Math.random()*(maxHeal - minHeal + 1))+ minHeal);
	}

	@Override
	public int use() {
		return heal();
	}
	
}

class Weapon implements Useable {
	private int minDamage;
	private int maxDamage;
	private String name;
	
	public Weapon(int minDamage, int maxDamage, String name) {
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.name = name;
	}
	
	public int getMinDamage() {
		return this.minDamage;
	}

	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	public int getMaxDamage() {
		return this.maxDamage;
	}

	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public int attack() {
		return (int) ((Math.random()*(maxDamage - minDamage + 1))+ minDamage);
	}

	@Override
	public int use() {
		return attack();
	}
	
}

class Attributes {
	private int strength;
	private int intelligence;
	
	public Attributes() {
		this.strength = 3;
		this.intelligence = 3;
	}
	
	public Attributes(int strength, int intelligence) {
		this.strength = strength;
		this.intelligence = intelligence;
	}	
	
	public void increaseStrength(int amount) {
		this.strength += amount;
	}
	
	public void increaseIntelligence(int amount) {
		this.intelligence += amount;
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public int getIntelligence() {
		return this.intelligence;
	}
	
	public String toString() {
		return "Attributes [Strength= " + strength + ", intelligence= " + intelligence + "]";
	}
	
}

abstract class Character implements Comparable {
	private String name;
	protected int level;
	protected Attributes attributes;
	protected int health;
	private String ID;
	
	public Character(String name) {
		this.name = name;
		this.level = 0;
	}
	
	public Character(String name, Attributes attributes) {
		this.name = name;
		this.attributes = attributes;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}
	
	public String getID() {
		return this.ID;
	}
	
	public abstract void levelUp();
	
	public String toString() {
		return "Character " + " LvL: " + level + " – " + attributes;
	}
	
	@Override
	public int compareTo(Character ch) {
		if (this.level == ch.getLevel()) return 1;
		return 0;
	}
	
}

abstract class PlayableCharacter extends Character implements Damageable {
	private boolean inParty;
	private Party party;
	
	public PlayableCharacter(String name, Attributes attributes) {
		super(name,attributes);
	}
	
	public boolean isInParty() {
		return this.inParty;
	}
	
	public void joinParty(Party party) {
		if (isInParty());
		else {
			try {
				party.addCharacter(this);
				inParty = true;
				this.party = party;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void quitParty() {
		if (isInParty()) {
			try {
				this.party.removeCharacter(this);
				inParty = false;
				this.party = null;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		else;
	}
	
	@Override
	public void takeDamage(int damage) {
		super.health -= damage;
	}
	
	@Override
	public void takeHealing(int healing) {
		super.health += healing;		
	}
	
	@Override
	public boolean isAlive() {
		if (super.health > 0) return true;
		return false;
	}
	
	@Override
	public void levelUp() {
		super.level += 1;
	}
	
}

abstract class NonPlayableCharacter extends Character {

	public NonPlayableCharacter(String name, Attributes attributes) {
		super(name, attributes);
	}
	
}

class Merchant extends NonPlayableCharacter {
	public Merchant(String name) {
		super(name, new Attributes(0, 0));
		super.setID("Merchant");
	}
	
	@Override
	public void levelUp() {
		// TODO Auto-generated method stub
		
	}
	
}

class Skeleton extends NonPlayableCharacter implements Combat {
	
	public Skeleton(String name, Attributes attributes) {
		super(name, attributes);
		super.health = 15;
		super.setID("Skeleton");
	}
	
	@Override
	public void levelUp() {
		super.level += 1;
		super.attributes.increaseIntelligence(1);
		super.attributes.increaseStrength(1);
	}

	@Override
	public void takeDamage(int damage) {
		super.health -= damage;
	}

	@Override
	public void takeHealing(int healing) {
		super.health -= healing;
	}

	@Override
	public boolean isAlive() {
		if (super.health > 0) return true;
		return false;
	}

	@Override
	public void attack(Damageable target) {
		target.takeDamage(super.attributes.getIntelligence()+super.attributes.getStrength());
	}

	@Override
	public void lootWeapon(Weapon weapon) {
		// TODO Auto-generated method stub
		
	}
	
}

class Warrior extends PlayableCharacter implements Combat {
	private Useable weapon;
	
	public Warrior(String name) {
		super(name, new Attributes(4, 2));
		super.health = 35;
		super.setID("Warrior");
	}

	@Override
	public void levelUp() {
		super.level += 1;
		super.attributes.increaseStrength(2);
		super.attributes.increaseIntelligence(1);
	}

	@Override
	public void attack(Damageable target) {
		target.takeDamage(weapon.use()+super.attributes.getStrength());
	}

	@Override
	public void lootWeapon(Weapon weapon) {
		this.weapon = weapon;		
	}
	
}

class Cleric extends PlayableCharacter implements Caster {
	private Useable spell;
	
	public Cleric(String name) {
		super(name, new Attributes(2, 4));
		super.health = 25;
		super.setID("Cleric");
	}

	@Override
	public void levelUp() {
		super.level += 1;
		super.attributes.increaseStrength(1);
		super.attributes.increaseIntelligence(2);
	}

	@Override
	public void castSpell(Damageable target) {
		target.takeDamage(spell.use()+super.attributes.getIntelligence());
	}

	@Override
	public void learnSpell(Spell spell) {
		this.spell = spell;
	}
	
}

class Paladin extends PlayableCharacter implements Combat, Caster {
	private Useable weapon;
	private Useable spell;
	
	public Paladin(String name) {
		super(name, new Attributes());
		super.health = 30;
		super.setID("Paladin");
	}

	@Override
	public void levelUp() {
		if (level % 2 == 1) {

            attributes.increaseStrength(2);
            attributes.increaseIntelligence(1);
            level += 1;
        }
        else {

            attributes.increaseStrength(-2);
            attributes.increaseIntelligence(-1);
            level -= 1;
        }
	}

	@Override
	public void castSpell(Damageable target) {
		target.takeHealing(spell.use()+super.attributes.getIntelligence());;
	}

	@Override
	public void learnSpell(Spell spell) {
		this.spell = spell;
	}

	@Override
	public void attack(Damageable target) {
		target.takeDamage(weapon.use()+super.attributes.getStrength());
	}

	@Override
	public void lootWeapon(Weapon weapon) {
		this.weapon = weapon;		
	}
	
}

class Party {
	private final int partyLimit = 8;
	private ArrayList<Combat> fighters = new ArrayList<Combat>();
	private ArrayList<Caster> healers = new ArrayList<Caster>();
	private int mixedCount = 0;
	private ArrayList<Character> firstCharacters = new ArrayList<Character>();
	private ArrayList<Character> secondCharacters = new ArrayList<Character>();
	
	public void addCharacter(PlayableCharacter character) throws PartyLimitReachedException, AlreadyInPartyException, IncompatibleTypeOfCharacter {
		if (character instanceof Combat) {
			if ((fighters.size() + healers.size() - mixedCount) < partyLimit) {
				if (!fighters.contains((Combat) character)) {
					fighters.add((Combat) character);
				} else {
					throw new AlreadyInPartyException("The party has the same object...");
				}
			} else {
				throw new PartyLimitReachedException("The party reached to limit...");
			}
		} else if (character instanceof Caster) {
			if ((fighters.size() + healers.size() - mixedCount) < partyLimit) {
				if (!healers.contains((Caster) character)) {
					healers.add((Caster) character);
				} else {
					throw new AlreadyInPartyException("The party has the same object...");
				}
			} else {
				throw new PartyLimitReachedException("The party reached to limit...");
			}
		} else if (character instanceof Combat && character instanceof Caster) {
			if ((fighters.size() + healers.size() - mixedCount) < partyLimit) {
				if (!fighters.contains((Combat) character) && !healers.contains((Caster) character)) {
					fighters.add((Combat) character);
					healers.add((Caster) character);
					mixedCount += 1;
				} else {
					throw new AlreadyInPartyException("The party has the same object...");
				}
			} else {
				throw new PartyLimitReachedException("The party reached to limit...");
			}
		} else {
			throw new IncompatibleTypeOfCharacter("The character object is not compatible...");
		}
	}
	
	public void removeCharacter(PlayableCharacter character) throws CharacterIsNotInPartyException, IncompatibleTypeOfCharacter {
		if (character.getID().equals("Warrior")) {
			if (fighters.contains((Combat) character)) {
				fighters.remove((Combat) character);
			} else {
				throw new CharacterIsNotInPartyException("The character is not in the party...");
			}
		} else if (character.getID().equals("Cleric")) {
			if (healers.contains((Caster) character)) {
				healers.remove((Caster) character);
			} else {
				throw new CharacterIsNotInPartyException("The character is not in the party...");
			}
		} else if (character.getID().equals("Paladin")) {
			if (fighters.contains((Combat) character) && healers.contains((Caster) character)) {
				fighters.remove((Combat) character);
				healers.remove((Caster) character);
				mixedCount -= 1;
			} else {
				throw new CharacterIsNotInPartyException("The character is not in the party...");
			}
		} else {
			throw new IncompatibleTypeOfCharacter("The character object is not compatible...");
		}
	}
	
	public void partyLevelUp() {
		ArrayList<Character> characters = combine();
		
		for (Character i : characters) {
			i.level++;
		}
		
	}
	
	public  ArrayList<Character> combine() {
		for (int i = 0;i < fighters.size();i++) {
			firstCharacters.add((Character) fighters.get(i));
			secondCharacters.add((Character) healers.get(i));
		}
		
		firstCharacters.addAll(secondCharacters);
		
		return firstCharacters;
	}

	@Override
	public String toString() {
		String list = "";
		Character empty = new Character(null) {
			
			@Override
			public void levelUp() {
				// TODO Auto-generated method stub
				
			}
		};
		
		ArrayList<Character> Ch1s = combine();
		ArrayList<Character> Ch3s = new ArrayList<Character>();
		
		int size = Ch1s.size();
		Character ch1 = Ch1s.get(0);
		
		for (int i = 0;i < size;i++) {
			for (int j = 0;j < Ch1s.size();i++) {
				if (ch1.getLevel() <= Ch1s.get(i).getLevel()) {
					ch1 = Ch1s.get(i);
					Ch1s.add(i, empty);;
				}
			}
			Ch3s.add(ch1);
		}
		
		for (int i = 0;i < Ch3s.size();i++) {
			list += Ch3s.get(i).getName() + " : " + Ch3s.get(i).getLevel() + "\n";
		}
		
		return list;
	}
	
}

class Barrel implements Damageable {

	private int health = 30;
	private int capacity = 10;
	
	public void explode() {
		System.out.println("Explodes");
	}
	
	public void repair() {
		System.out.println("Repairing");
	}
	
	@Override
	public void takeDamage(int damage) {
		this.health -= damage;
		if (this.health < 0) {
			this.explode();
		}
	}

	@Override
	public void takeHealing(int healing) {
		this.health += healing;
		this.repair();
	}

	@Override
	public boolean isAlive() {
		if (this.health < 0) return false;
		return true;
	}
	
}

class TrainingDummy implements Damageable {

	private int health = 25;
	
	@Override
	public void takeDamage(int damage) {
		this.health -= damage;
	}

	@Override
	public void takeHealing(int healing) {
		this.health += healing;
	}

	@Override
	public boolean isAlive() {
		if (this.health < 0) return false;
		return true;
	}
	
}

//Exceptions
class PartyLimitReachedException extends Exception {
	public PartyLimitReachedException(String message) {
		super(message);
	}
}

class AlreadyInPartyException extends Exception {
	public AlreadyInPartyException(String message) {
		super(message);
	}
}

class CharacterIsNotInPartyException extends Exception {
	public CharacterIsNotInPartyException(String message) {
		super(message);
	}
}

class IncompatibleTypeOfCharacter extends Exception {
	public IncompatibleTypeOfCharacter(String message) {
		super(message);
	}
}



