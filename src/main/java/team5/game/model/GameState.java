package team5.game.model;

public class GameState {
    /** The single instance of this class */
    private static GameState instance;
    /** The dungeon */
    private Dungeon myDungeon;
    /** The hero's name */
    private String myName;
    /** The Hero selected */
    private Hero myHero;

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
     * Gets the dungeon instance
     * 
     * @return the dungeon instance
     */
    public Dungeon getDungeon() {
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
        return instance.myName;
    }

    /**
     * Gets the hero the player chooses
     * 
     * @return the hero that was chosen
     */
    public Hero getHero() {
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
     * Sets the name to the player's choice
     * 
     * @param theName the string of the name
     */
    public void setName(final String theName) {
        instance.myName = theName;
    }
}
