/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import java.awt.Point;

public class Wizard extends Entity {
	private int xp;
	private int mana;
	private Spell[] spells;
	// get the basic combat log
	StringBuilder combatLog = super.basicLog();
	// spell log
	StringBuilder spellLog = new StringBuilder();

	public Wizard(String name, int id, int hp, Point pos, int aXp, int aMana,
			Spell[] aSpells) {
		super(name, id, hp, pos);
		xp = aXp;
		mana = aMana;
		spells = aSpells;
	}

	private void earnXP(int aXp) {
		xp += aXp;// add xp to character
	}

	/**
	 * @param String, Entity
	 * @return check if the attack can be done by comparing the spells, if yes, do it and add xp to self
	 */
	public boolean attack(String type, Entity roll) {
		boolean flag = false;
		if (this.getHp() > 0 && roll != null) {// character has to be alive to
												// implement an attack
			for (Spell aSpell : spells) {// check if wizard has already learned
											// this spell
				if (aSpell.getName() == type && mana >= aSpell.cost
						&& roll.getHp() > 0) {
					flag = true;
					roll.beAttackted(aSpell.damage);
					if (roll.getHp() <= 0)
						earnXP(20);// 20 xp for killing a roll
					else
						earnXP(10);// 10 xp for a simple not deadly attack
					mana -= aSpell.cost;
					break;// end the for-loop
				}
			}
		}
		return flag;
	}

	public int getXP() {
		return xp;
	}

	/**
	 * @return String: complete the combat log
	 */
	public String toString() {
		for (int i = 0; i < spells.length; i++) {
			spellLog.append(" ").append(spells[i].name).append(": ")
			.append(spells[i].cost).append(" casting cost, ")
			.append(spells[i].damage).append(" damage caused");
			if (i != spells.length - 1) {
				spellLog.append(";");// the end of the sentence has no ";"
			}
		}
		String spells = spellLog.toString();
		return combatLog.append(this.getXP()).append(" XP,").append(spells).toString();
	}
}
