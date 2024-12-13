package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
/**
 * Tests for monster factory
 * 
 * @author Holden Tsang
 * @version 12 Dec 2024
 */
public class MonsterFactoryTest {

    /** Test for MonsterFactory */
    @Test
    public void testMonsterFactory() {
        final Monster ogre = new Ogre("Og");
        final Monster skeleton = new Skeleton("Bones");
        final Monster goblin = new Goblin("Gob");

        final Monster ogre2 = MonsterFactory.createMonster('O', "Og");
        final Monster skeleton2 = MonsterFactory.createMonster('S', "Bones");
        final Monster goblin2 = MonsterFactory.createMonster('G', "Gob");
        assertEquals(ogre, ogre2);
        assertEquals(skeleton, skeleton2);
        assertEquals(goblin, goblin2);
    }
}
