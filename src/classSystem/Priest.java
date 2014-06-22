/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import java.awt.Point;

public class Priest extends Entity {

	private int xp;
	private int healing;

	public Priest(String name, int id, int hp, Point pos, int aXp, int aHealing) {
		super(name, id, hp, pos);
		xp = aXp;
		healing = aHealing;
	}

	/**
	 * 
	 * @param aXp int: add aXp to the xp of character
	 */
	private void earnXP(int aXp) {
		xp += aXp;
	}

	/**
	 * 
	 * @param roll Entity
	 * @return boolean: check if heal-action can be done, if yes, do this and increase xp
	 */
	public boolean heal(Entity roll) {
		if (this.getHp() > 0 && roll != null) {
			roll.beHealed(healing);
			earnXP(10);
			return true;
		} else
			return false;
	}

	/**
	 * priest can not attack
	 */
	public boolean attack(String type, Entity roll) {
		return false;
	}

	public int getXP() {
		return xp;
	}
	
	public int getHealing(){
		return healing;
	}

	/**
	 * String: complete the combat log
	 */
	public String toString() {
		StringBuilder combatLog = super.basicLog();// get the basic combat log
		combatLog.append(xp).append(" XP, ").append(healing)
				.append(" healing power\n");
		return combatLog.toString();

	}
}
