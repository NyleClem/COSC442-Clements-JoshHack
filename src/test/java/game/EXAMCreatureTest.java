
package game;

import java.awt.Color;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EXAMCreatureTest {

    private World world;
    private Creature creature;

    @BeforeEach
    void setUp() {
        Tile[][][] tiles = new Tile[3][3][1];

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                tiles[x][y][0] = Tile.FLOOR;
            }
        }

        world = new World(tiles);

        creature = new Creature(world, '@', Color.GRAY, "josh", 100, 10, 5);

        creature.x = 1;
        creature.y = 1;
        creature.z = 0;
    }

    //Question 2:  false branch
    @Test
    void testModifyHpCreatureSurvives() {
        // Arrange
        int amount = -99;
        int expectedHp = 1;

        // Act
        creature.modifyHp(amount, "Bullet wound survivor");

        // Assert
        assertEquals(expectedHp, creature.hp());
    }

    // Exam Question 2: true branch
    @Test
    void testModifyHpCreatureDies() {
        // Arrange
        int amount = -101;
        int expectedHp = -1;

        // Act
        creature.modifyHp(amount, "stab wound");

        // Assert
        assertEquals(expectedHp, creature.hp());
        assertEquals("stab wound", creature.causeOfDeath());
    }
    @Test
void testDeadCreatureRemovedFromWorld() {
    // Arrange
    new CreatureAi(creature);
    world.add(creature);
    int amount = -101;

    // Act
    creature.modifyHp(amount, "stab wound");

    // Assert
    assertNull(world.creature(1, 1, 0));
}

    @Test
    void testMeleeAttackReducesDefenderHp() {
        Creature defender = new Creature(world, 'g', Color.GREEN, "goblin", 50, 0, 9);

        creature.meleeAttack(defender);

        assertEquals(49, defender.hp());
    }

    @Test
    void testModifyManaWithinBounds() {
        creature.modifyMana(-2);

        assertEquals(3, creature.mana());
    }

    @Test
    void testModifyManaClampsAtZero() {
        creature.modifyMana(-6);

        assertEquals(0, creature.mana());
    }

    @Test
    void testModifyManaClampsAtMaximum() {
        creature.modifyMana(2);

        assertEquals(creature.maxMana(), creature.mana());
    }

    @Test
    void testEffectsViewIsUnmodifiableAndReflectsInternalUpdates() {
        new CreatureAi(creature);
        List<Effect> effects = creature.effects();
        Effect effect = new Effect(2);
        Item potion = new Item('!', Color.RED, "potion", null);
        potion.setQuaffEffect(effect);

        creature.quaff(potion);

        assertEquals(1, effects.size());
        assertSame(effect, effects.get(0));
        assertThrows(UnsupportedOperationException.class, () -> effects.add(new Effect(1)));
        assertEquals(1, creature.effects().size());
    }

    @Test
    void testDeathDropsAllInventoryItemsWhenIteratingOverDefensiveCopy() {
        new CreatureAi(creature);
        world.add(creature);
        Item firstItem = new Item('!', Color.RED, "first potion", null);
        Item secondItem = new Item('!', Color.BLUE, "second potion", null);
        creature.inventory().add(firstItem);
        creature.inventory().add(secondItem);

        creature.modifyHp(-101, "test death");

        assertNull(creature.inventory().get(0));
        assertNull(creature.inventory().get(1));

        int droppedItems = 0;
        for (int x = 0; x < world.width(); x++) {
            for (int y = 0; y < world.height(); y++) {
                Item item = world.item(x, y, 0);
                if (item == firstItem || item == secondItem)
                    droppedItems++;
            }
        }
        assertEquals(2, droppedItems);
    }
}
