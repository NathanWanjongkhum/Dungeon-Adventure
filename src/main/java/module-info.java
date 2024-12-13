module team5.game {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;

    opens team5.game.controller to javafx.fxml;
    opens team5.game.view to javafx.fxml;

    exports team5.game.controller;
    exports team5.game.model;
    exports team5.game.view;
}
