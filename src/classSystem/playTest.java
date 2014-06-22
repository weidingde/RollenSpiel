/**
 * 
 * @author Hengwen Zhang, Wei Ding, Julius Friedrich Muth, Cong Chen
 * @version 2.0
 */

package classSystem;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

public class playTest {
	Priest priest1;
	Priest priest2;
	Warrior warrior1;
	Warrior warrior2;// dead warrior
	Wizard wizard1;
	Wizard wizard2;// this will be null
	Troll troll;
	Troll troll2;// a troll that can't be killed
	Troll troll3;// dead troll

	Spell[] spells;

	// this method will generate the first part of combat log correctly
	public String correctLog(String name, int id, int hp, Point pos) {
		StringBuilder attackLog = new StringBuilder();
		attackLog.append(name).append(" (").append(id)
				.append("): position x: ").append(pos.x).append(", y: ")
				.append(pos.y).append(", ").append(hp).append(" HP, ");
		return attackLog.toString();
	}

	@Before
	public void init() {
		Spell fireBall = new Spell("Fire Ball", 20, 25);
		Spell chaosBolt = new Spell("Chaos Bolt", 400, 199);
		Spell evilsHand = new Spell("Evils Hand", 80, 80);
		spells = new Spell[] { fireBall, chaosBolt, evilsHand };// define
																// spells

		priest1 = new Priest("Docter Dog", 1, 200, new Point(0, 5), 0, 120);
		warrior1 = new Warrior("Black Jack", 2, 500, new Point(9, 7), 0, 50);
		warrior2 = new Warrior("Dead Jack", 2, 0, new Point(9, 7), 0, 50);
		wizard1 = new Wizard("Warlock", 3, 390, new Point(15, 5), 0, 1200,
				spells);
		wizard2 = null;
		troll = new Troll("Victory", 9, 2000, new Point(10, 5), 80);
		troll2 = new Troll("Gott Troll", 9, 99999999, new Point(10, 4), 80);
		troll3 = new Troll("Forgotten Hotel", 9, 0, new Point(10, 4), 80);
		priest2 = new Priest("Nasty Snail", 4, 0, new Point(16, 8), 0, 70);
	}

	@Test
	public void testPriest() {
		// attack ability test
		assertFalse("Priest can not attack!", priest1.attack("Melee", warrior1));

		// empty target healing test
		assertFalse("Priest should not heal empty target",
				priest1.heal(wizard2));

		// dead priest test
		assertFalse("A dead Priest can not heal any target!",
				priest2.heal(priest1));

		// healing ability test
		assertTrue("Healing Warlock failed", priest1.heal(wizard1));
		assertTrue("Healing Nasti Nail failed", priest1.heal(priest2));

		// combat log test
		StringBuilder heal = new StringBuilder();
		assertTrue(
				"Combat log has not been correctly generated!",
				priest1.toString().indexOf(
						heal.append(
								correctLog("Docter Dog", 1, 200,
										new Point(0, 5)))
								.append(priest1.getXP()).append(" XP, ")
								.append(priest1.getHealing())
								.append(" healing power").toString()) > -1);
	}

	@Test
	public void testWizard() {
		// empty target test
		assertFalse("Each attack must have a target",
				wizard1.attack("Chaos Bolt", wizard2));

		// dead wizard test
		Wizard dead = wizard1;// make a wizard to kill
		// kill the wizard for our test,wizard1 earn 20XP+10XP for 2 attacks
		wizard1.attack("Chaos Bolt", dead);
		wizard1.attack("Chaos Bolt", dead);
		assertFalse("a dead wizard can not attack!",
				dead.attack("Fire Ball", troll));

		// exp test
		// make our dead wizard get ready to be killed again ;-P
		// our dead wizard will have 112 HP
		priest1.heal(dead);
		assertEquals(30, wizard1.getXP());

		// attack dead test
		assertFalse("Can't attack dead target!",
				wizard1.attack("Evils Hand", priest2));

		// normal attack test
		assertTrue("Attack a valid target failed",
				wizard1.attack("Fire Ball", priest1));

		// combat log test
		StringBuilder wizardLog = new StringBuilder();
		assertTrue(
				"Combat log has not been correctly generated!",
				wizard1.toString()
						.indexOf(
								wizardLog
										.append(correctLog("Warlock", 3, 390,
												new Point(15, 5)))
										.append(wizard1.getXP())
										.append(" XP, ")
										.append("Fire Ball: 20 casting cost, 25 damage caused; Chaos Bolt: 400 casting cost, 199 damage caused; Evils Hand: 80 casting cost, 80 damage caused")
										.toString()) > -1);

	}

	@Test
	public void warriorTest() {
		// empty target test
		assertFalse("Each attack must have a target",
				warrior1.attack("Melee", priest2));

		// dead warrior test
		assertFalse("a dead wizard can not attack!",
				warrior2.attack("Melee", priest1));

		// normal attack test
		assertTrue("Attack a valid target failed",
				warrior1.attack("Melee", troll2));

		// exp test
		assertEquals(10, warrior1.getXP());

		// combat log test
		StringBuilder strenght = new StringBuilder();
		assertTrue(
				"Combat log has not been correctly generated!",
				warrior1.toString().indexOf(
						strenght.append(warrior1.getXP()).append(" XP, ")
								.append(warrior1.getStrength())
								.append(" attack power").toString()) > -1);
	}

	@Test
	public void trollTest() {
		// empty target test
		assertFalse("Each attack must have a target",
				troll.attack("Melee", wizard2));

		// dead troll test
		assertFalse("a dead troll can not attack!",
				troll3.attack("Melee", priest1));

		// normal attack test
		assertTrue("Attack a valid target failed",
				troll.attack("Melee", troll2));

		// combat log test
		StringBuilder strenght = new StringBuilder();
		assertTrue(
				"Combat log has not been correctly generated!",
				troll.toString()
						.indexOf(
								strenght.append(
										correctLog("Victory", 9, 2000,
												new Point(10, 5)))
										.append(troll.getStrength())
										.append(" attack power").toString()) > -1);

	}
	
	@Test
	public void partyTest(){
Party party=new Party(3);
		
		//add troll test
		assertFalse("Troll shoudn't be assigned to any party",
				party.join(troll));
		
		//add null test
		assertFalse("Can't add empty character to party", party.join(wizard2));
		assertEquals(0, party.getSize());
		
		//normal add test
		assertTrue("a valid character failed to join party", party.join(priest1));
		assertTrue("a valid character failed to join party", party.join(wizard1));
		assertTrue("a valid character failed to join party", party.join(warrior1));
		assertEquals(3, party.getSize());
		
		//over flow test
		assertFalse("no character will be add to a full party", party.join(warrior2));
		
		//add meaningless character
		assertFalse("empty character shouldn't be added", party.join(wizard2));
		
		//duplicate add test
		assertFalse("duplicated character shouldn't be added", party.join(priest1));
		
		//dismiss test
		assertTrue("dismiss a valid party member failed", party.dismiss(priest1));
		assertEquals(2, party.getSize());
		
		//dismiss a null pointer
		assertFalse("empty character can not be dismissed", party.dismiss(wizard2));
		assertEquals(2, party.getSize());
		
		//dismiss a character that doesn't exist in the party
		assertFalse("character must be exist in party, before dismiss", party.dismiss(warrior2));
		assertEquals(2, party.getSize());
		

	}
}
