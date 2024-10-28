module team5.game {
    requires javafx.controls;
    requires javafx.fxml;

    opens team5.game to javafx.fxml;

    exports team5.game;
}
