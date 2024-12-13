package team5.game.model;

import java.io.IOException;
import java.io.Serializable;

/**
 * GameState is a class that represents the game state. It holds the dungeon,
 * the hero, and the monsters. It also holds the cheats and battles flags. The
 * GameState is a singleton class. It is used to save and load the game state.
 * Shares state across the application.
 */
public class GameState implements Serializable {
    /** The single instance of this class */
    private static GameState instance;
    /** The dungeon */
    private Dungeon myDungeon;
    /** The monster in the dungeon */
    private Monster[] myMonsters;
    /** The Hero selected */
    private Hero myHero;
    /** The hero's name */
    private String myName;
    /** Indicator for cheats */
    private boolean myCheats;
    /** Indicator for battles */
    private boolean myBattling;

    /** The serial version UID for serialization */
    private static final long serialVersionUID = 1L;

    /**
     * The singleton pattern that returns the instance of this class
     * 
     * @return the instance of this class
     */
    public static GameState getInstance() {
        if (instance == null) {
            instance = new GameState();
        }

        return instance;
    }

    /** Private constructor that does nothing */
    private GameState() {
    }

    /**
     * Saves the game to a file. Serializes the game state; hero, monsters, and
     * dungeon.
     */
    public static void saveGame() {
        try {
            DatabaseHandler.serialize(getInstance());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save game: " + e.getMessage());
        }
    }

    /**
     * Loads the game from a file. Deserializes the game state; hero, monsters,
     * and dungeon.
     *
     * @param theFilename
     * @return
     */
    public static GameState loadGame() {
        try {
            instance = (GameState) DatabaseHandler.deserialize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load game: " + e.getMessage());
        }

        return instance;

    }

    /**
     * Gets the dungeon instance
     * 
     * @return the dungeon instance
     */
    public Dungeon getDungeon() {
        if (instance.myDungeon == null) {
            throw new IllegalStateException("Dungeon not initialized");
        }

        return instance.myDungeon;
    }

    /**
     * Sets the dungeon instance
     * 
     * @param theDungeon the dungeon instance
     */
    public void setDungeon(Dungeon theDungeon) {
        instance.myDungeon = theDungeon;
    }

    /**
     * Gets the name the player chooses
     * 
     * @return a string of the name
     */
    public String getName() {
        if (instance.myName == null) {
            throw new IllegalStateException("Name not initialized");
        }

        return instance.myName;
    }

    /**
     * Sets the name to the player's choice
     * 
     * @param theName the string of the name
     */
    public void setName(final String theName) {
        instance.myName = theName;
    }

    /**
     * Gets the hero the player chooses
     * 
     * @return the hero that was chosen
     */
    public Hero getHero() {
        if (instance.myHero == null) {
            throw new IllegalStateException("Hero not initialized");
        }

        return instance.myHero;
    }

    /**
     * Sets the hero to the player's choice
     * 
     * @param theHero the hero object
     */
    public void setHero(final Hero theHero) {
        instance.myHero = theHero;
    }

    /**
     * Gets the monsters. If no monsters are found, populate the dungeon with
     * monsters.
     * 
     * @return a array of monsters
     */
    public Monster[] getMonsters() {
        return instance.myMonsters;
    }

    /**
     * Sets the monsters to the player's choice
     * 
     * @param theMonsters the array of monsters
     */
    public void setMonsters(Monster[] theMonsters) {
        instance.myMonsters = theMonsters;
    }

    /**
     * Gets the cheats flag
     * 
     * @return true if cheats are enabled, false otherwise
     */
    public boolean isCheats() {
        return myCheats;
    }

    /**
     * Sets the cheats flag
     * 
     * @param theCheats the cheats flag
     */
    public void setCheats(final boolean theCheats) {
        myCheats = theCheats;
    }

    /**
     * Gets the battles flag
     * 
     * @return true if battles are enabled, false otherwise
     */
    public boolean isBattling() {
        return myBattling;
    }

    /**
     * Sets the battles flag
     * 
     * @param theBattling the battles flag
     */
    public void setBattling(final boolean theBattling) {
        myBattling = theBattling;
    }
}
