module team5.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    requires java.sql;

    opens team5.game to javafx.fxml;
    opens team5.game.view to javafx.fxml;

    exports team5.game;
}
