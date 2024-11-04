package team5.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Circle;

public class Input implements Initializable{
    @FXML
    private Circle myCircle;
    private double x;
    private double y;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }

    @FXML
    public void up(ActionEvent event) {
        up();
    }
    @FXML
    public void down(ActionEvent event) {
        down();
    }

    @FXML
    public void left(ActionEvent event) {
        left();
    }

    @FXML
    public void right(ActionEvent event) {
        right();
    }
    
    public void up() {
        myCircle.setCenterY(y-=5);
    }
    public void down() {
        myCircle.setCenterY(y+=5);
    }
    public void left() {
        myCircle.setCenterX(x-=5);
    }
    public void right() {
        myCircle.setCenterX(x+=5);
    }

}
