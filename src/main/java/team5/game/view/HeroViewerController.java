package team5.game.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import team5.game.App;
import team5.game.model.GameState;
import team5.game.model.Hero;

public class HeroViewerController {
    @FXML
    private Label myHP;

    @FXML
    private ProgressBar myHPBar;

    @FXML
    private ImageView myHeroImage;

    @FXML
    private Label myHeroName;

    @FXML
    private Label myHeroType;

    @FXML
    private Label myStats;

    private Hero myHero;

    @FXML
    private void initialize() {
        myHero = GameState.getInstance().getHero();
        //Sets image
        myHeroImage.setImage(myHero.getImage());
        setText();
        setHP();
        
    }
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
    private void setText() {
        myHeroName.setText(myHero.getName());
        myHeroType.setText(myHero.getClass().getSimpleName());
        myStats.setText(myHero.getStats());
    }
    @FXML
    private void cancel(final ActionEvent theEvent) {
        App.closePopUp();
    }
}
