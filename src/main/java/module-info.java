module team5 {
    requires javafx.controls;
    requires javafx.fxml;

    opens team5 to javafx.fxml;
    exports team5;
}
