package team5.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import team5.game.controller.MonsterFactory;

/**
 * Dungeon is a class that represents a dungeon.
 */
public class Dungeon implements Serializable {
    private static final String[] OGRE_NAMES = {"Og", "Shrek", "Negark", "Dokog", "Murog"};
    private static final String[] GOBLIN_NAMES = {"Gob", "Boblin", "Gober", "Goberd", "Goblug"};
    private static final String[] SKELETON_NAMES = {"Bones", "Skele", "Skull", "Rattle", "Skele-Tom"};

    /** The dungeon */
    private Room[][] myDungeon;
    /** The width of the dungeon */
    private int myWidth;
    /** The height of the dungeon */
    private int myHeight;
    /** The difficulty of the dungeon */
    private Difficulty myDifficulty;
    /** The number of pillars collected in the dungeon */
    private int myPillarCount;
    /** A random number generator */
    private Random random = new Random();

    private static final long serialVersionUID = 1L;

    /**
     * Dungeon constructor. Initializes a new dungeon.
     * 
     * @param width  the width of the dungeon
     * @param height the height of the dungeon
     */
    public Dungeon(final int theWidth, final int theHeight, final Difficulty theDifficulty) {
        myWidth = theWidth;
        myHeight = theHeight;
        myDifficulty = theDifficulty;
        myPillarCount = 0;
    }

    /**
     * Uses a DungeonBuilder to initialize the dungeon.
     */
    public final void init() {
        if (myDungeon != null) {
            return;
        }

        myDungeon = new DungeonBuilder()
                .setDifficulty(myDifficulty)
                .setWidth(myWidth)
                .setHeight(myHeight)
                .build();

        Monster[] monsters = GameState.getInstance().getMonsters();
        if (monsters == null || monsters.length == 0) {
            monsters = populateMonsters();
        }

        // Populate dungeon
        placePillars();
        placeItems();
        placeMonsters(monsters);
    }

    /**
     * Check if the location is valid
     * 
     * @param theX the x coordinate
     * @param theY the y coordinate
     * @return true if the location is valid
     */
    public boolean isValidLocation(final int theX, final int theY) {
        return myDungeon != null && theX >= 0 && theX < myWidth && theY >= 0 && theY < myHeight;
    }

    /**
     * Check if there's a valid connection from the current position to the new room
     * inthe given direction.
     * 
     * @param thePos       the position of the current room
     * @param theDirection the direction to check
     * 
     * @return true if the current room an new room are connected
     */
    public boolean isConnected(final int theX, final int theY, final Direction theDirection) {
        final int newX = theDirection.calculateNewX(theX);
        final int newY = theDirection.calculateNewY(theY);

        // Check bounds first
        if (!isValidLocation(newX, newY)) {
            return false;
        }

        final Room currentRoom = myDungeon[theX][theY];
        final Room newRoom = myDungeon[newX][newY];

        if (currentRoom == null || newRoom == null) {
            return false;
        }

        // Check if both rooms have doors connecting them
        final boolean currentRoomConnects = currentRoom.getDoors()[theDirection.ordinal()];
        final boolean newRoomConnects = newRoom.getDoors()[theDirection.getOpposite().ordinal()];

        return currentRoomConnects && newRoomConnects;
    }

    /** Places pillars in the dungeon at dead ends */
    private void placePillars() {
        List<int[]> deadEnds = new ArrayList<>();

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                Room room = getRoom(x, y);
                if (room == null || room == getStartRoom()) {
                    continue;
                }

                int connections = 0;
                for (Direction direction : Direction.values()) {
                    if (isConnected(x, y, direction)) {
                        connections++;
                    }
                }

                if (connections == 1) {
                    deadEnds.add(new int[] { x, y });
                }
            }
        }

        if (deadEnds.size() < 4) {
            throw new IllegalStateException("Not enough dead ends to place all pillars!");
        }

        Collections.shuffle(deadEnds, random);

        PillarOfOO.PillarType[] types = PillarOfOO.PillarType.values();

        for (int i = 0; i < types.length; i++) {
            int[] coords = deadEnds.get(i);
            Room room = getRoom(coords[0], coords[1]);

            room.setItem(new PillarOfOO(types[i]));
        }
    }

    /** Places items in the dungeon */
    private void placeItems() {
        for (int i = 0; i < getWidth(); i++) {
            for (int j = 0; j < getHeight(); j++) {
                Room room = getRoom(i, j);

                if (random.nextInt(100) > 5 || room == null || room == getStartRoom()) {
                    continue;
                }

                if (room.getItem() != null) {
                    continue;
                }

                room.setItem(getRandomItem());
            }
        }
    }

    /**
     * Populate the dungeon with monsters based on the difficulty
     * 
     * @return the monsters
     */
    private Monster[] populateMonsters() {
        if (myDungeon == null) {
            throw new IllegalStateException("Dungeon not initialized");
        }

        int monsterCount = 0;

        switch (getDifficulty()) {
            case EASY -> monsterCount = 5;
            case MEDIUM -> monsterCount = 10;
            case HARD -> monsterCount = 15;
            default -> throw new IllegalStateException("Unexpected difficulty: " + getDifficulty());
        }

        final char monsterTypes[] = new char[] { 'O', 'G', 'S' };
        
        Monster monsters[] = new Monster[monsterCount];
        //Creating monster
        for (int i = 0; i < monsterCount; i++) {
            char type = monsterTypes[i % monsterTypes.length];
            String name = "";
            switch (type) {
                case 'O' -> name = OGRE_NAMES[random.nextInt(5)];
                case 'S' -> name = SKELETON_NAMES[random.nextInt(5)];
                case 'G' -> name = GOBLIN_NAMES[random.nextInt(5)];
                default -> throw new IllegalStateException("Unexpected monster" + type);
            }
            
            monsters[i] = MonsterFactory.createMonster(type, name);
        }

        return monsters;
    }

    /**
     * Places monsters randomly throughout the dungeon
     *
     * @param theMonsters the monsters to place
     */
    private void placeMonsters(final Monster[] theMonsters) {
        int index = 0;
        while (index < theMonsters.length) {
            Room room = getRandomRoom();

            if (room == getStartRoom()) {
                continue;
            }

            room.setMonster(theMonsters[index]);

            index++;
        }
    }

    /**
     * Get the width of the dungeon
     * 
     * @return the width
     */
    public int getWidth() {
        return myWidth;
    }

    /**
     * Get the height of the dungeon
     * 
     * @return the height
     */
    public int getHeight() {
        return myHeight;
    }

    /**
     * Get the difficulty of the dungeon
     * 
     * @return the difficulty
     */
    public Difficulty getDifficulty() {
        return myDifficulty;
    }

    /**
     * Get the dungeon
     * 
     * @return the dungeon
     */
    public Room[][] getDungeon() {
        return myDungeon;
    }

    /**
     * Get the room at the given coordinates
     * 
     * @param theX the x coordinate
     * @param theY the y coordinate
     * @return the room at the coordinates
     */
    public Room getRoom(final int theX, final int theY) {
        return myDungeon[theX][theY];
    }

    /**
     * Pick a random room from the dungeon
     * 
     * @return the random room
     */
    public final Room getRandomRoom() {
        int x = random.nextInt(myWidth);
        int y = random.nextInt(myHeight);

        final Room room = myDungeon[x][y];

        return room;
    }

    /**
     * Get the start room of the dungeon
     * 
     * @return the start room
     */
    public final Room getStartRoom() {
        return myDungeon[0][0];
    }

    /** The number of pillars collected in the dungeon */
    public int getPillarCount() {
        return myPillarCount;
    }

    /** Set the number of pillars collected in the dungeon */
    public void collectPillar() {
        myPillarCount += 1;
    }

    public void addExit() {
        Room room = getStartRoom();
        if (room != null) {
            room.setItem(new Exit());
        }
    }

    /**
     * Get a random item from a list of items
     *
     * @return the random item
     */
    private Item getRandomItem() {
        int itemType = random.nextInt(3);
        Item item = null;
        // TODO: Add more items
        switch (itemType) {
            case 0 -> item = new HealingPotion();
            case 1 -> item = new Bomb();
            case 2 -> item = new AttackPotion();
            default -> throw new IllegalStateException("Unexpected itemType: " + itemType);
        }

        return item;
    }

    /**
     * The string representation of the dungeon. Which is the accumulation of
     * the string representation of each room. IF the room is null, the string
     * "X" is used. Each row is separated by a newline.
     * 
     * @return the symbol
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < myWidth; i++) {
            for (int j = 0; j < myHeight; j++) {
                final Room room = myDungeon[i][j];
                if (room != null) {
                    sb.append(room.toString());
                } else {
                    sb.append("X");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }

}
