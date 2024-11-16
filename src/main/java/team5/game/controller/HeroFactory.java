package team5.game.controller;

import team5.game.model.Archer;
import team5.game.model.Hero;
import team5.game.model.Mage;
import team5.game.model.Priestess;
import team5.game.model.Warrior;

public class HeroFactory {
    public static Hero createHero(String theHero) {
        switch(theHero) {
            case "Archer":
                return new Archer(Choices.getChoices().getName());
            case "Mage":
                return new Mage(Choices.getChoices().getName());
            case "Priestess":
                return new Priestess(Choices.getChoices().getName());
            default:
                return new Warrior(Choices.getChoices().getName());
        }
    }
}
