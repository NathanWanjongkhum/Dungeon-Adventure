package team5.game.controller;

import team5.game.model.Hero;

public class Choices {
    private static Choices instance = new Choices();
    private String myName;
    private Hero myHero;

    private Choices() {

    }
    public String getName() {
        return myName;
    }
    public Hero getHero() {
        return myHero;
    }   
    public void setName(String theName) {
        myName = theName;
    }
    public void setHero(Hero theHero) {
        myHero = theHero;
    }
    public static Choices getChoices() {
        return instance;
    }
}
