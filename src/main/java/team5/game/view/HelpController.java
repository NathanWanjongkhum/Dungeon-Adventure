package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import team5.game.App;
import team5.game.controller.MonsterFactory;
import team5.game.model.Monster;
/**
 * The GUI Controller for HelpScene
 * 
 * @author Holden Tsang
 * @version December 5 2024
 */
public class HelpController implements Initializable {

    @FXML
    /** The menu container */
    private VBox myMenu;
    @FXML
    /** The goblin description */
    private Label myGoblinDesciption;
    @FXML
    /** The Ogre description */
    private Label myOgreDescription;

    @FXML
    /** The skeleton description */
    private Label mySkeletonDesciption;

    @Override
    public void initialize(final URL theURL, final ResourceBundle theResource) {
        Monster goblin = MonsterFactory.createMonster('G', "temp");
        Monster skeleton = MonsterFactory.createMonster('S', "temp");
        Monster ogre = MonsterFactory.createMonster('O', "temp");
        myGoblinDesciption.setText(goblin.getStats());
        mySkeletonDesciption.setText(skeleton.getStats());
        myOgreDescription.setText(ogre.getStats());
    }
    @FXML
    /**
     * The exit button action
     * 
     * @param theEvent the button press
     */
    private void exit(final ActionEvent theEvent) throws IOException {
        App.setPopUpRoot("Settings");
    }

}
