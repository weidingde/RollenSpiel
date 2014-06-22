/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import java.util.ArrayList;

public class Party {
	int capacity;
	private ArrayList<Entity> members;

	public Party(int number) {
		capacity = number;
		members = new ArrayList<Entity>();
	}

    /**
     * 
     * @param newRoll Entity
     * @return boolean: check if the join action can be succeed, if yes, do this action
     */
	public boolean join(Entity newRoll) {
		if (members.size() < capacity && newRoll != null && newRoll.hasJoined == false && newRoll instanceof Troll == false) {
            newRoll.hasJoined = true;
            return members.add(newRoll);
        }
        else return false;
	}

	/**
	 * 
	 * @param toDismiss Entity
	 * @return boolean: check if the dismiss action can be succeed, if yes, do this action
	 */
	public boolean dismiss(Entity toDismiss) {
		if(members.contains(toDismiss) == true) {
            toDismiss.hasJoined = false;
            return members.remove(toDismiss);
        }
        else return false;

	}

	/**
	 * 
	 * @return int: the number of members in the party
	 */
	public int getSize() {
		int partyNumber = members.size();
        return partyNumber;
	}
    
    /**
     * 
     * @return Entity[]: return the array of members in the party
     */
	public Entity[] getMembers() {
        Entity[] memberList = new Entity[members.size()];
        memberList = members.toArray(memberList);
    	return memberList;
    }
}
