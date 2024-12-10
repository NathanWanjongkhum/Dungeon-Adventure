package team5.game.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
public class HelpController {

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

    @FXML
    private void initialize() {
        Monster goblin = MonsterFactory.createMonster('G', "Random Goblin Name");
        Monster skeleton = MonsterFactory.createMonster('S', "Random Skeleton Name");
        Monster ogre = MonsterFactory.createMonster('O', "Random Ogre Name");
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
