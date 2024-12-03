package team5.game.model;

import java.io.Serializable;

public class Exit implements Item, Serializable {
    private static final long serialVersionUID = 1L;
    private int myCount;


    public Exit() {
        myCount = 1;
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
    public String toString() {
        return "E";
    }
}
