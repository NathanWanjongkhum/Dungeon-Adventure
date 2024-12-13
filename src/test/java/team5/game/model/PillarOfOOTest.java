package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import team5.game.model.PillarOfOO.PillarType;
/**
 * Tests for pillar of OO item class
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class PillarOfOOTest {
    /** Test for Bomb Constructor */
    @Test
    public void testPillarOfOOConstructor() {
        final PillarOfOO pillar = new PillarOfOO(PillarType.ABSTRACTION);
        assertEquals(pillar.getPillar(), PillarType.ABSTRACTION);
        assertEquals(pillar.getCount(), 1);
    }
}
