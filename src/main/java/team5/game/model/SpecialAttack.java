package team5.game.model;

public class SpecialAttack {
    private int myTurns;
    private int myDamage;

    public SpecialAttack() {
        myTurns = 1;
        myDamage = 0;
    }
    public SpecialAttack(int theTurns, int theDamage) {
        myTurns = theTurns;
        myDamage = theDamage;
    }
    public int getTurns() {
        return myTurns;
    }
    public int getDamage() {
        return myDamage;
    }
    public void setTurns(final int theTurns) {
        myTurns = theTurns;
    }
    public void setDamage(final int theDamage) {
        myDamage = theDamage;
    }
}
