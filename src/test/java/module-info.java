module team5.game {
    requires javafx.controls;
    requires javafx.fxml;

    // IDE may produce error "[dependency] cannot be resolved to a module"
    // however these are required and do not cause any errors in build
    requires org.junit.jupiter.api;
    requires org.slf4j;
    requires org.xerial.sqlitejdbc;

    opens team5.game to javafx.fxml, org.junit.jupiter.api;
    opens team5.game.view to javafx.fxml;

    exports team5.game;
    exports team5.game.controller;
    exports team5.game.model;
    exports team5.game.view;
}
