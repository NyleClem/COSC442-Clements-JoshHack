package game;

import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import org.junit.jupiter.api.Test;

public class ItemTest {

    @Test
    void testDetailsWithModifiedValuesReturnsExpectedDetails() {
        // Arrange
        Item item = new Item('s', Color.GRAY, "Sword", "a sword");
        item.modifyAttackValue(5);
        item.modifyDefenseValue(3);

        // Act
        String details = item.details();

        // Assert
        assertEquals("  attack:5  defense:3", details);
    }

    @Test
    void testDetailsWithDefaultValuesReturnsEmptyString() {
        // Arrange
        Item item = new Item('s', Color.GRAY, "Sword", "a sword");

        // Act
        String details = item.details();

        // Assert
        assertEquals("", details);
    }

    @Test
    void testDetailsWithRangedAttackAboveZeroIncludesRangedValue() {
        // Arrange
        Item item = new Item('b', Color.GRAY, "Bow", "a bow");
        item.modifyRangedAttackValue(1);

        // Act
        String details = item.details();

        // Assert
        assertEquals("  ranged:1", details);
    }

    @Test
    void testDetailsWithThrownAndFoodValuesIncludesBoth() {
        // Arrange
        Item item = new Item('%', Color.GREEN, "Treated Ration", "a ration");
        item.modifyThrownAttackValue(3);
        item.modifyFoodValue(100);

        // Act
        String details = item.details();

        // Assert
        assertEquals("  thrown:4  food:100", details);
    }
}