/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import java.awt.Point;

public class Troll extends Entity {
	private int strenght;

	public Troll(String name, int id, int hp, Point pos, int aStrength) {
		super(name, id, hp, pos);
		strenght = aStrength;
	}

	/**
	 * @param String, Entity
	 * @return boolean: check if the attack can succeed and do it
	 */
	public boolean attack(String type, Entity roll) {
		if (this.getHp() > 0 && roll != null && type == "Melee"
				&& roll.getHp() > 0) {
			roll.beAttackted(strenght);
			return true;
		} else
			return false;
	}
	
	public int getStrength(){
		return strenght;
	}

	/**
	 * @return String: complete the combatLog
	 */
	public String toString() {
		StringBuilder combatLog = super.basicLog();// get the basic log
		combatLog.append(strenght).append(" attack power\n");// finish the rest
		return combatLog.toString();
	}
}
