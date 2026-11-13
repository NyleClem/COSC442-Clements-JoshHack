
package game;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class EXAMCombatRulesTest {

    // Question 1: Max HP boundary tests.
    @ParameterizedTest
    @CsvSource({
        "98, 1, 99",
        "99, 1, 100",
        "99, 2, 100"
    })
    void testMaxHpBoundariesReturnsExpected(int currentHp, int amount, int expectedHp) {
        // Arrange
        int maxHp = 100;

        // Act
        int calculatedHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);

        // Assert
        assertEquals(expectedHp, calculatedHp);
    }

    // Question 1: Death boundary and isDead() tests.
    @ParameterizedTest
    @CsvSource({
        "1, -1, 0, true",
        "50, -49, 1, false",
        "1, 1, 2, false",
        "20, -21, -1, true"
    })
    void testDeathBoundariesReturnsExpected(int currentHp, int amount,int expectedHp, boolean expectedDead) {
        // Arrange
        int maxHp = 100;

        // Act
    int calculatedHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);
    boolean isDead = CombatRules.isDead(calculatedHp);

        // Assert
        assertEquals(expectedHp, calculatedHp);
        assertEquals(expectedDead, isDead);
    }

    //Question 1: Zero amount test
    @Test
    void testZeroAmountReturnsHpUnchanged() {
        // Arrange
        int currentHp = 1;
        int amount = 0;
        int maxHp = 100;

        // Act
        int calculatedHp = CombatRules.calculateNewHp(currentHp, maxHp, amount);

        // Assert
        assertEquals(1, calculatedHp);
    }
}
