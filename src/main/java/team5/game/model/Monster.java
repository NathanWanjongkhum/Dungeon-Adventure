package team5.game.model;

import java.io.Serializable;

public class Monster extends AbstractDungeonCharacter implements Serializable {
    /** The serialVersionUID */
    private static final long serialVersionUID = 1L;

    protected Monster(String theName, int theHealth, int theDamage, int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
    }
}