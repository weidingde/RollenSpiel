/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

public class Spell {
	String name;
	int cost;
	int damage;

	public Spell(String aName, int manaCost, int theDamage) {
		name = aName;
		cost = manaCost;
		damage = theDamage;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}

	public int getDamage() {
		return damage;
	}

}
