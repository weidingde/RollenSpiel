/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import java.awt.Point;

public abstract class Entity {
	String name;
	int id;
	private int hp;
	Point position;
    boolean hasJoined; // to check whether the character has already joined a group

	public Entity(String aName, int aId, int hitPoint, Point pos) {
		name = aName;
		id = aId;
		hp = hitPoint;
		position = pos;
		hasJoined = false;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public int getHp() {
		return hp;
	}

	public Point getPosition() {
		return position;
	}
	 
	/**
	 * 
	 * @return StringBuilder: generate the first part of combat log, which is same for every Entity
	 */
	public StringBuilder basicLog() {
		StringBuilder attackLog = new StringBuilder();
		attackLog.append(name).append(" (").append(id).append("): position x: ")
				.append(position.x).append(", y: ").append(position.y)
				.append(", ").append(hp).append(" HP, ");
		return attackLog;
	} 
	
	/**
	 * if the character has been attacked, the hp reduces
	 * @param damage int
	 */
	public void beAttackted(int damage){
		hp-=damage;
		if (hp < 0) {
			hp = 0;
		}
	}
	
	/**
	 * if the character has been healed by priest, the hp increases
	 * @param healing int
	 */
	public void beHealed(int healing){
		hp+=healing;
	}
	
	public abstract String toString();

	public abstract boolean attack(String name, Entity roll);
}
