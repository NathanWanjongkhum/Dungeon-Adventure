package team5.game.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;

public class Input implements Initializable{
    
    private BooleanProperty wPressed = new SimpleBooleanProperty();
    private BooleanProperty aPressed = new SimpleBooleanProperty();
    private BooleanProperty sPressed = new SimpleBooleanProperty();
    private BooleanProperty dPressed = new SimpleBooleanProperty();

    private BooleanBinding keyPressed = wPressed.or(aPressed).or(sPressed).or(dPressed);


    @FXML
    private Circle myCircle;
    @FXML
    private AnchorPane scene;
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
