/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import java.awt.Point;

public class Warrior extends Entity {
	private int strength;
	private int xp;

	public Warrior(String name, int id, int hp, Point pos, int aXp,
			int aStrength) {
		super(name, id, hp, pos);
		xp = aXp;
		strength = aStrength;
	}

	private void earnXP(int aXp) {
		xp += aXp;// add xp to character
	}

	/**
	 * @param String, Entity
	 * @return boolean: check if the attact can be done, if yes, do it and add ex to self
	 */
	public boolean attack(String type, Entity roll) {
		if (this.getHp() > 0 && roll != null && type == "Melee"
				&& roll.getHp() > 0) {
			roll.beAttackted(strength);
			if (roll.getHp() <= 0)
				earnXP(20);// 20 xp for killing a character
			else
				earnXP(10);// 10 xp for a simple not deadly attack
			return true;
		} else
			return false;
	}
	
	public int getXP() {
		return xp;
	}
	
	public int getStrength(){
		return strength;
	}

	/**
	 * @return String: complete the combat log
	 */
	public String toString() {
		StringBuilder combatLog = super.basicLog();// get the basic log
		combatLog.append(xp).append(" XP, ").append(strength)
				.append(" attack power\n");// generate the rest log
		return combatLog.toString();
	}
}
