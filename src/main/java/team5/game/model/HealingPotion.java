package team5.game.model;

public class HealingPotion extends AbstractConsumables {
    private static final int HEALTH_RESTORE = 20;
    private int myHealthRestore;
    
    /**
     * Default constructor for healing potion
     */
    public HealingPotion() {
        super();
        myHealthRestore = HEALTH_RESTORE;
    }
    /**
     * Gets the amount of health a potion retores
     * 
     * @return the amount fo health restored
     */
    public int getHealthRestore() {
        return myHealthRestore;
    }
    /**
     * Sets the amount of health a potion heals 
     * 
     * @param theHealthRestore the health restored
     */
    public void setHealthRestore(int theHealthRestore) {
        myHealthRestore = theHealthRestore;
    }
    @Override
    public int useItem(DungeonCharacter theCharacter) {
        theCharacter.heal(getHealthRestore());
        return getHealthRestore();
    }
    @Override
    public String getDescription() {
        return String.format("Heals %s hp when used", myHealthRestore);
    }
}
