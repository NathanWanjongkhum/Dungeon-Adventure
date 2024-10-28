package team5.game.model;

public class Hero extends AbstractDungeonCharacter {
    protected Hero(String theName, int theHealth, int theDamage, int theSpeed) {
        super(theName, theHealth, theDamage, theSpeed);
    }

    enum Directions {
        UP, DOWN, LEFT, RIGHT
    }
}