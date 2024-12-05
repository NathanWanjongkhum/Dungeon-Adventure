package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DungeonTest {
    /** The difficulty of the dungeon */
    private static final Difficulty DUNGEON_DIFFICULTY = Difficulty.EASY;
    /** The width of the dungeon */
    private static final int DUNGEON_WIDTH = 10;
    /** The height of the dungeon */
    private static final int DUNGEON_HEIGHT = 10;

    /** The dungeon */
    private static Dungeon myDungeon;

    @BeforeEach
    public void setUp() {
        myDungeon = new Dungeon(DUNGEON_WIDTH, DUNGEON_HEIGHT, DUNGEON_DIFFICULTY);
        myDungeon.init();
    }

    /**
     * Test for Dungeon constructor. Throws an exception if the dungeon parameters
     * are invalid.
     */
    @Test
    public void testFailedBuild() {
        Dungeon dungeon = new Dungeon(0, 0, Difficulty.EASY);
        Assertions.assertThrows(IllegalStateException.class, dungeon::init);
    }

    /** Test for Dungeon constructor. Confirms all parameters are set. */
    @Test
    public void testParameterConstructor() {
        assertEquals(DUNGEON_WIDTH, myDungeon.getWidth());
        assertEquals(DUNGEON_HEIGHT, myDungeon.getHeight());
        assertEquals(DUNGEON_DIFFICULTY, myDungeon.getDifficulty());
    }

    /** Test for Dungeon init method. */
    @Test
    public void testInit() {
        for (int x = 0; x < DUNGEON_WIDTH; x++) {
            for (int y = 0; y < DUNGEON_HEIGHT; y++) {
                // Check if room exists
                if (myDungeon.getRoom(x, y) == null) {
                    Assertions.fail("Room at (" + x + ", " + y + ") is null");
                }
            }
        }
    }

    /** Test for Dungeon getWidth method. */
    @Test
    public void testGetWidth() {
        final int expectedWidth = DUNGEON_WIDTH;
        final int actualWidth = myDungeon.getWidth();

        assertEquals(expectedWidth, actualWidth);
    }

    /** Test for Dungeon getHeight method. */
    @Test
    public void testGetHeight() {
        final int expectedHeight = DUNGEON_HEIGHT;
        final int actualHeight = myDungeon.getHeight();

        assertEquals(expectedHeight, actualHeight);
    }

    /** Test for Dungeon getDifficulty method. */
    @Test
    public void testGetDifficulty() {
        final Difficulty expectedDifficulty = DUNGEON_DIFFICULTY;
        final Difficulty actualDifficulty = myDungeon.getDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }

    /** Test for Dungeon getDungeon method. */
    @Test
    public void testGetDungeon() {
        final Room[][] expectedDungeon = myDungeon.getDungeon();
        final Room[][] actualDungeon = myDungeon.getDungeon();

        assertEquals(expectedDungeon, actualDungeon);
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms valid location on y lower
     * bound
     */
    @Test
    public void testIsValidLocationMinY() {
        final int expectedX = 1;
        final int expectedY = 0;
        assertTrue(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms valid location on x lower
     * bound
     */
    @Test
    public void testIsValidLocationMinX() {
        final int expectedX = 0;
        final int expectedY = 1;
        assertTrue(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms valid location on y upper
     * bound
     */
    @Test
    public void testIsValidLocationMaxY() {
        final int expectedX = 1;
        final int expectedY = DUNGEON_HEIGHT - 1;
        assertTrue(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms valid location on x upper
     * bound
     */
    @Test
    public void testIsValidLocationMaxX() {
        final int expectedX = DUNGEON_WIDTH - 1;
        final int expectedY = 1;
        assertTrue(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms negative is out of bounds
     * on y
     */
    @Test
    public void testIsValidLocationNegativeY() {
        final int expectedX = 1;
        final int expectedY = -1;
        assertFalse(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms negative is out of bounds
     * on x
     */
    @Test
    public void testIsValidLocationNegativeX() {
        final int expectedX = -1;
        final int expectedY = 1;
        assertFalse(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms > DUNGEON_WIDTH is out of
     * bounds on x
     */
    @Test
    public void testIsValidLocationOutOfBoundsX() {
        final int expectedX = DUNGEON_WIDTH;
        final int expectedY = 1;
        assertFalse(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isValidLocation method. Confirms > DUNGEON_HEIGHT is out of
     * bounds on y
     */
    @Test
    public void testIsValidLocationOutOfBoundsY() {
        final int expectedX = 1;
        final int expectedY = DUNGEON_HEIGHT;
        assertFalse(myDungeon.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for Dungeon isConnected method. Confirms valid connection on y lower
     * bound
     */
    @Test
    public void testIsConnectedOutOfBounds() {
        final int expectedX = DUNGEON_WIDTH;
        final int expectedY = DUNGEON_HEIGHT;
        final Direction expectedDirection = Direction.NORTH;
        assertFalse(myDungeon.isConnected(expectedX, expectedY, expectedDirection));
    }

    /** Test for Dungeon getRoom method. Confirms room exists. */
    @Test
    public void testGetRoom() {
        final int expectedX = 0;
        final int expectedY = 0;
        final Room expectedRoom = myDungeon.getRoom(expectedX, expectedY);
        final Room actualRoom = myDungeon.getRoom(expectedX, expectedY);

        assertEquals(expectedRoom, actualRoom);
    }

    /** Test for Dungeon getStartRoom method. Confirms room exists. */
    @Test
    public void testGetStartRoom() {
        Room firstStartRoom = myDungeon.getStartRoom();
        Room secondStartRoom = myDungeon.getStartRoom();
        assertNotNull(firstStartRoom);
        assertSame(firstStartRoom, secondStartRoom);
    }

    /**
     * Test for Dungeon getPillarCount method. Confirms pillar count is incremented
     * by 1
     */
    @Test
    public void testGetPillarCount() {
        final int expectedPillarCount = myDungeon.getPillarCount();
        myDungeon.collectPillar();
        final int actualPillarCount = myDungeon.getPillarCount();

        assertEquals(expectedPillarCount + 1, actualPillarCount);
    }

    /**
     * Test for Dungeon addExit method. Confirms exit is added to start room
     */
    @Test
    public void testAddExit() {
        myDungeon.addExit();
        final Room startRoom = myDungeon.getStartRoom();

        assertTrue(startRoom.getItem() instanceof Exit);
    }

    /**
     * Test for Dungeon toString method. Confirms that is a string of the correct
     * dimensions
     */
    @Test
    public void testToStringDimensions() {
        final String theString = myDungeon.toString();

        final String[] rows = theString.split("\n");
        for (String row : rows) {
            // Rooms are 7 characters wide "R[DWDW]"
            assertEquals(DUNGEON_WIDTH * 7, row.length());
        }

        assertEquals(DUNGEON_HEIGHT, rows.length);
    }

    /**
     * Test for Dungeon toString method. Sets a room to "X" when it is null.
     */
    @Test
    public void testToStringNullRooms() {
        myDungeon.getDungeon()[0][0] = null;

        final String theString = myDungeon.toString();
        char theChar = theString.charAt(0);

        assertEquals('X', theChar);
    }
}
