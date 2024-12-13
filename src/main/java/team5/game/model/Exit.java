package team5.game.model;

import java.io.Serializable;

public class Exit implements Item, Serializable {
    private static final long serialVersionUID = 1L;
    private int myCount;


    protected Exit() {
        myCount = 1;
    }
    protected Exit(final Exit theOther) {
        this.myCount = theOther.getCount();
    }
    @Override
    public int getCount() {
        return myCount;
    }
    @Override
    public void setCount(final int theCount) {
        myCount = theCount;
    }
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
    @Override
    public boolean isConsumable() {
        return false;
    }
    @Override
    public boolean isPillar() {
        return false;
    }
    @Override
    public String toString() {
        return "E";
    }
    @Override
    public Exit copy() {
        return new Exit(this);
    }
}
