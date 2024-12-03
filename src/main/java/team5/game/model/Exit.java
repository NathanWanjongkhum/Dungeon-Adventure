package team5.game.model;

import java.io.Serializable;

public class Exit implements Item, Serializable {
    private static final long serialVersionUID = 1L;

    public Exit() {
    }

    @Override
    public String toString() {
        return "E";
    }
}
