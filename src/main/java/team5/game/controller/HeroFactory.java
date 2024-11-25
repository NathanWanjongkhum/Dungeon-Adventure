package team5.game.controller;

import team5.game.model.Archer;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.model.Mage;
import team5.game.model.Priestess;
import team5.game.model.Warrior;

/**
 * The Hero Factory to create heroes
 * 
 * @author
 * @version
 */
public class HeroFactory {
    /**
     * Takes a string and returns the hero chosen because of the string.
     * 
     * @param theHero the string of the hero
     * @return a new hero object of selected hero
     */
    public static Hero createHero(final String theHero) {
        final String name = GameState.getInstance().getName();

        Hero hero = null;
        switch (theHero) {
            case "Archer" -> hero = new Archer(name);
            case "Mage" -> hero = new Mage(name);
            case "Priestess" -> hero = new Priestess(name);
            default -> hero = new Warrior(name);
        }

        return hero;
    }
}
