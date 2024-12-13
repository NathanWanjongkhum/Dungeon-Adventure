package team5.game.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.view.App;

public class HeroViewerController {
    /** The HP label */
    @FXML
    private Label myHP;

    /** The HP bar */
    @FXML
    private ProgressBar myHPBar;

    /** The hero image */
    @FXML
    private ImageView myHeroImage;

    /** The hero name label */
    @FXML
    private Label myHeroName;

    /** The hero type label */
    @FXML
    private Label myHeroType;

    /** The stats label */
    @FXML
    private Label myStats;

    /** The hero */
    private Hero myHero;

    @FXML
    /**
     * Initializes the hero viewer controller. Sets the image, name, type, and
     * stats.
     */
    private void initialize() {
        myHero = GameState.getInstance().getHero();
        // Sets image
        myHeroImage.setImage(myHero.getImage());
        setText();
        setHP();
    }

    /**
     * Sets the HP of the hero
     * 
     * @param theEvent the action event
     */
    private void setHP() {
        myHP.setText("HP " + myHero.getHealth() + "/" + myHero.getMaxHealth());
        final double hp = (double) myHero.getHealth() / myHero.getMaxHealth();
        myHPBar.setProgress(hp);
        if (hp < 0.25) {
            myHPBar.setStyle("-fx-accent: red;");
        } else if (hp < 0.5) {
            myHPBar.setStyle("-fx-accent: yellow;");
        } else {
            myHPBar.setStyle("-fx-accent: green");
        }
    }

    /**
     * Sets the text of the hero name, type, and stats
     */
    private void setText() {
        myHeroName.setText(myHero.getName());
        myHeroType.setText(myHero.getClass().getSimpleName());
        myStats.setText(myHero.getStats());
    }

    /**
     * Closes the pop up
     * 
     * @param theEvent the action event
     */
    @FXML
    private void cancel(final ActionEvent theEvent) {
        App.closePopUp();
    }
}
