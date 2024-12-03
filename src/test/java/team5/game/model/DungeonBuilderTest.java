package team5.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap.SimpleEntry;
import java.util.Stack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test class for DungeonBuilder
 */
public class DungeonBuilderTest {
    /** The difficulty of the dungeon */
    private static final Difficulty DUNGEON_DIFFICULTY = Difficulty.EASY;
    /** The width of the dungeon */
    private static final int DUNGEON_WIDTH = 10;
    /** The height of the dungeon */
    private static final int DUNGEON_HEIGHT = 10;

    /** The dungeon myBuilder */
    private static DungeonBuilder myBuilder;
    /** The dungeon */
    private static Room[][] myDungeon;

    /** Set up the dungeon myBuilder and dungeon */
    @BeforeAll
    public static void setUp() {
        myBuilder = new DungeonBuilder()
                .setWidth(DUNGEON_WIDTH)
                .setHeight(DUNGEON_HEIGHT)
                .setDifficulty(DUNGEON_DIFFICULTY);

        myDungeon = myBuilder.build();
    }

    /** Test for DungeonBuilder getHeight method */
    @Test
    public void testGetHeight() {
        final int expectedHeight = DUNGEON_HEIGHT;
        final int actualHeight = myBuilder.getHeight();

        assertEquals(expectedHeight, actualHeight);
    }

    /** Test for DungeonBuilder getWidth method */
    @Test
    public void testGetWidth() {
        final int expectedWidth = DUNGEON_WIDTH;
        final int actualWidth = myBuilder.getWidth();

        assertEquals(expectedWidth, actualWidth);
    }

    /** Test for DungeonBuilder getDifficulty method */
    @Test
    public void testGetDifficulty() {
        final Difficulty expectedDifficulty = DUNGEON_DIFFICULTY;
        final Difficulty actualDifficulty = myBuilder.getDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }

    /** Test for DungeonBuilder getRoom method */
    @Test
    public void testGetRoom() {
        final int expectedX = 0;
        final int expectedY = 0;
        final Room expectedRoom = myDungeon[expectedX][expectedY];
        final Room actualRoom = myBuilder.getRoom(new SimpleEntry<>(expectedX, expectedY));

        assertEquals(expectedRoom, actualRoom);
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms valid location on y
     * lower bound
     */
    @Test
    public void testIsValidLocationMinY() {
        final int expectedX = 1;
        final int expectedY = 0;
        assertTrue(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms valid location on x
     * lower bound
     */
    @Test
    public void testIsValidLocationMinX() {
        final int expectedX = 0;
        final int expectedY = 1;
        assertTrue(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms valid location on y
     * upper bound
     */
    @Test
    public void testIsValidLocationMaxY() {
        final int expectedX = 1;
        final int expectedY = DUNGEON_HEIGHT - 1;
        assertTrue(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms valid location on x
     * upper bound
     */
    @Test
    public void testIsValidLocationMaxX() {
        final int expectedX = DUNGEON_WIDTH - 1;
        final int expectedY = 1;
        assertTrue(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms negative is out of
     * bounds on
     * y
     */
    @Test
    public void testIsValidLocationNegativeY() {
        final int expectedX = 1;
        final int expectedY = -1;
        assertFalse(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms negative is out of
     * bounds on
     * x
     */
    @Test
    public void testIsValidLocationNegativeX() {
        final int expectedX = -1;
        final int expectedY = 1;
        assertFalse(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms > DUNGEON_WIDTH is
     * out of bounds on
     * x
     */
    @Test
    public void testIsValidLocationOutOfBoundsX() {
        final int expectedX = DUNGEON_WIDTH;
        final int expectedY = 1;
        assertFalse(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /**
     * Test for DungeonBuilder isValidLocation method. Confirms > DUNGEON_HEIGHT is
     * out of bounds on
     * y
     */
    @Test
    public void testIsValidLocationOutOfBoundsY() {
        final int expectedX = 1;
        final int expectedY = DUNGEON_HEIGHT;
        assertFalse(myBuilder.isValidLocation(expectedX, expectedY));
    }

    /** Test for DungeonBuilder no args constructor. For width. */
    @Test
    public void testNoArgsConstructorWidth() {
        final DungeonBuilder builder = new DungeonBuilder();

        final int expectedWidth = 0;
        final int actualWidth = builder.getWidth();

        assertEquals(expectedWidth, actualWidth);
    }

    /** Test for DungeonBuilder no args constructor. For height. */
    @Test
    public void testNoArgsConstructorHeight() {
        final DungeonBuilder builder = new DungeonBuilder();

        final int expectedHeight = 0;
        final int actualHeight = builder.getHeight();

        assertEquals(expectedHeight, actualHeight);
    }

    /** Test for DungeonBuilder no args constructor. For difficulty. */
    @Test
    public void testNoArgsConstructorDifficulty() {
        final DungeonBuilder builder = new DungeonBuilder();

        final Difficulty expectedDifficulty = null;
        final Difficulty actualDifficulty = builder.getDifficulty();

        assertEquals(expectedDifficulty, actualDifficulty);
    }

    /** Test for DungeonBuilder build method. Confirms all rooms exist. */
    @Test
    public void testBuildRoomsExist() {
        for (int x = 0; x < DUNGEON_WIDTH; x++) {
            for (int y = 0; y < DUNGEON_HEIGHT; y++) {
                // Check if room exists
                if (myDungeon[x][y] == null) {
                    Assertions.fail("Room at (" + x + ", " + y + ") is null");
                }
            }
        }
    }

    /** Test for DungeonBuilder build method. Confirms all rooms are connected */
    @Test
    public void testBuildConnectivity() {
        final int expectedVisits = DUNGEON_WIDTH * DUNGEON_HEIGHT;
        boolean[][] visited = new boolean[DUNGEON_WIDTH][DUNGEON_HEIGHT];

        int actualVisits = 0;
        int currentX = 0;
        int currentY = 0;
        int newX;
        int newY;

        // Stack for DFS - stores coordinates as SimpleEntry pairs
        final Stack<SimpleEntry<Integer, Integer>> stack = new Stack<>();
        stack.push(new SimpleEntry<>(0, 0));

        while (!stack.isEmpty()) {
            SimpleEntry<Integer, Integer> current = stack.peek();
            currentX = current.getKey();
            currentY = current.getValue();

            // If we've already visited this cell, pop it and continue
            if (visited[currentX][currentY]) {
                stack.pop();
                continue;
            }

            // Mark current cell as visited
            visited[currentX][currentY] = true;
            actualVisits++;

            // Flag to track if we found any unvisited neighbors
            boolean foundUnvisited = false;

            // Try all directions
            for (Direction dir : Direction.values()) {
                newX = dir.calculateNewX(currentX);
                newY = dir.calculateNewY(currentY);

                // Check if the new position is valid, unvisited, and connected
                if (myBuilder.isValidLocation(newX, newY) &&
                        !visited[newX][newY] &&
                        isConnected(new SimpleEntry<>(currentX, currentY), dir)) {

                    stack.push(new SimpleEntry<>(newX, newY));
                    foundUnvisited = true;
                }
            }

            // If we didn't find any unvisited neighbors, pop the current cell
            if (!foundUnvisited) {
                stack.pop();
            }
        }

        // Verify all rooms were visited
        assertEquals(expectedVisits, actualVisits,
                "Not all rooms were reachable. Expected " + expectedVisits +
                        " visits but got " + actualVisits);
    }

    /**
     * Check if there's a valid connection from the last position to the room in the
     * given direction.
     * 
     * @param lastPos the position of the last room
     * @param theDir  the direction to check
     * 
     * @return true if the last room an new room are connected
     */
    private boolean isConnected(final SimpleEntry<Integer, Integer> currentPos,
            final Direction dir) {
        final int currentX = currentPos.getKey();
        final int currentY = currentPos.getValue();
        final int newX = dir.calculateNewX(currentX);
        final int newY = dir.calculateNewY(currentY);

        // Check bounds first
        if (!myBuilder.isValidLocation(newX, newY)) {
            return false;
        }

        final Room currentRoom = myDungeon[currentX][currentY];
        final Room newRoom = myDungeon[newX][newY];

        if (currentRoom == null || newRoom == null) {
            return false;
        }

        // Check if both rooms have doors connecting them
        final boolean currentRoomConnects = currentRoom.getDoors()[dir.ordinal()];
        final boolean newRoomConnects = newRoom.getDoors()[dir.getOpposite().ordinal()];

        return currentRoomConnects && newRoomConnects;
    }
}
