package team5.game.model;

public class Priestess extends Hero {
    protected Priestess(String theName) {
        super(theName, 100, 20, 25);
    }

    void heal(int theHeal) {
        setHealth(getHealth() + theHeal);
    }
}