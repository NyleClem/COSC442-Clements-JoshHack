
package game;

import java.awt.Color;
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
}
