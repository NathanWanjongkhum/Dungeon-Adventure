package team5.game.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import team5.game.App;
import team5.game.controller.Battle;
import team5.game.model.AttackPotion;
import team5.game.model.Bomb;
import team5.game.model.DungeonCharacter;
import team5.game.model.GameState;
import team5.game.model.HealingPotion;
import team5.game.model.Hero;
import team5.game.model.Monster;
import team5.game.model.Ogre;

public class BattleController implements Initializable {
    @FXML
    private AnchorPane myBackground;

    @FXML
    private AnchorPane myScene;

    @FXML
    private HBox myControls;

    @FXML
    private Label myHPLevel;

    @FXML
    private ProgressBar myHeroBar;

    @FXML
    private ImageView myHeroImage;

    @FXML
    private TextArea myLog;

    @FXML
    private ProgressBar myMonsterBar;

    @FXML
    private Label myMonsterHPLevel;

    @FXML
    private ImageView myMonsterImage;

    @FXML
    private Label myMonsterName;

    @FXML
    private Label myName;
    @FXML
    private Button myNext;
    @FXML
    private Button myAttack;
    @FXML
    private Button mySpecial;
    @FXML
    private Button myItem;
    @FXML
    private Button myRetreat;

    private Stage myStage;
    private Battle myBattle;
    private Hero myHero;
    private Monster myMonster;
    private Tooltip myHeroTooltip;
    private Tooltip myMonsterTooltip;
    private ImageView myHeroEffects;
    private ImageView myMonsterEffects;
    private Tooltip myHeroEffectTooltip;
    private Tooltip myMonsterEffectTooltip;

    @Override
    public void initialize(URL theURL, ResourceBundle theResource) {
        // Dungeon class would get hero and monster so hp would carry over
        myHero = GameState.getInstance().getHero();
        myMonster = new Ogre("Og");

        AttackPotion potion = new AttackPotion();
        AttackPotion potion2 = new AttackPotion();
        Bomb bomb = new Bomb();
        HealingPotion heal = new HealingPotion();
        myHero.getInventory().addItem(heal);
        myHero.getInventory().addItem(bomb);
        myHero.getInventory().addItem(potion);
        myHero.getInventory().addItem(potion);
        myHero.getInventory().addItem(potion2);
        
        //CustomBackground code
        BackgroundImage back = App.getBackgroundImage("battle background");
        myBackground.setBackground(new Background(back));
        // myBackground.setStyle();

        myControls.setStyle("-fx-background-color: black; -fx-effect: innershadow(gaussian, #66524d, 7, 0.9, 0, 0)");
        // myControls.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, null)));
        // BackgroundImage ui = App.getBackgroundImage("battle background2");
        // myControls.setBackground(new Background(ui));

        myBattle = new Battle(myHero, myMonster);
        myHeroTooltip = new Tooltip(myHero.getStats());
        myMonsterTooltip = new Tooltip(myMonster.getStats());
        initDungeonCharacter();
        initImages();
        initStatusIndication();
        setHP();
        myLog.appendText("Battles had started with " + myMonster.getName() + "\n");
    }
    private void initDungeonCharacter() {
        myName.setText(myHero.getName());
        myHeroBar.setStyle("-fx-accent: green");
        // myHeroStatus.setFont(Font.font("High Tower Text", 12));

        myMonsterName.setText(myMonster.getName());
        myMonsterBar.setStyle("-fx-accent: green");
        // myMonsterStatus.setFont(Font.font("High Tower Text", 12));
    }

    private void initImages() {
        initImages(myHeroImage, myHero, myHeroTooltip);
        initImages(myMonsterImage, myMonster, myMonsterTooltip);
        myMonsterImage.setScaleX(-1);
    }

    private void initImages(final ImageView theView, final DungeonCharacter theCharacter, Tooltip theTooltip) {
        theView.setImage(theCharacter.getImage());
        theTooltip.setStyle("-fx-font-size: 15; -fx-text-fill: white;" +
                            "-fx-font-family: 'High Tower Text'");
        Tooltip.install(theView, theTooltip);

    }
    private void initStatusIndication() {
        myHeroEffects = new ImageView();
        myMonsterEffects = new ImageView();
        myHeroEffectTooltip = new Tooltip();
        myMonsterEffectTooltip = new Tooltip();
        setTooltipTheme(myHeroEffectTooltip);
        setTooltipTheme(myMonsterEffectTooltip);
    }
    private void setTooltipTheme(final Tooltip theTooltip) {
        theTooltip.setStyle("-fx-font-size: 15; -fx-text-fill: white;" +
                            "-fx-font-family: 'High Tower Text'");
        theTooltip.setShowDelay(Duration.seconds(0.5));
    }

    private void setHP() {
        setCharHP(myHero, myHPLevel, myHeroBar);
        setCharHP(myMonster, myMonsterHPLevel, myMonsterBar);
    }

    private void setCharHP(final DungeonCharacter theCharacter, final Label theLabel, final ProgressBar theBar) {
        final String character = "HP " + theCharacter.getHealth() + "/" + theCharacter.getMaxHealth();
        theLabel.setText(character);
        final double hp = (double) theCharacter.getHealth() / theCharacter.getMaxHealth();
        setHPBar(theBar, hp);
    }

    private void setHPBar(final ProgressBar theBar, final double theHP) {
        theBar.setProgress(theHP);
        if (theHP < 0.25) {
            theBar.setStyle("-fx-accent: red;");
        } else if (theHP < 0.5) {
            theBar.setStyle("-fx-accent: yellow;");
        } else {
            theBar.setStyle("-fx-accent: green");
        }
    }
    @FXML
    void attack(ActionEvent event) {
        attackAction();
    }

    private void attackAction() {
        myBattle.battle("attack");
        setHP();
        displayText();
        if (myBattle.isOver()) {
            over();
        }
    }

    @FXML
    void useSpecialAttack(ActionEvent event) {
        specialAction();
    }

    private void specialAction() {
        if (myHero.getSpecialAttack().getTurns() > 0) {
            setBattleButtons(false);
            mySpecial.setDisable(false);
        } else {
            setBattleButtons(true);
        }
        myBattle.battle("special");
        setHP();
        displayText();
        if (myBattle.isOver()) {
            over();
        }
    }
    private Parent loadFXML(String theFXML) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/team5/game/" + theFXML + ".fxml"));
        return fxmlLoader.load();
     }
    @FXML
    void item(ActionEvent event) throws IOException {
        myStage = new Stage();
        myStage.setScene(new Scene(loadFXML("ItemBag")));
        myStage.initModality(Modality.APPLICATION_MODAL);
        myStage.initOwner(myItem.getScene().getWindow());
        myStage.showAndWait();
        if (GameState.getInstance().getHero().isConUsed()) {
            useItem();
        }
    }
    private void useItem() {
        myBattle.setConsumable(GameState.getInstance().getHero().useConsumable());
        myBattle.battle("item");
        setHP();
        displayText();
        if (myBattle.isOver()) {
            over();
        }
    }
    @FXML
    void myHome(ActionEvent event) throws IOException {
        App.setRoot("StartScreen");
    }

    @FXML
    void retreat(ActionEvent event) throws IOException {
        App.setRoot("HeroSelection");
    }

    @FXML
    void endBattle(ActionEvent event) throws IOException {
        if (myBattle.isOver() && myHero.getHealth() == 0) {
            App.setRoot("EndScene");//Still need to make 
        } else {
            App.setRoot("DungeonScene");
        }
    }
    @FXML
    void showRules(ActionEvent event) throws IOException {
        // myStage = new Stage();
        // myStage.setScene(new Scene(loadFXML("")));
        // myStage.initModality(Modality.APPLICATION_MODAL);
        // myStage.initOwner(myItem.getScene().getWindow());
        // myStage.showAndWait();
    }

    private void displayText() {
        myLog.appendText(myBattle.actionPerformed());
        displayEffect(myHero, myHeroTooltip, myHPLevel, myHeroEffectTooltip, myHeroEffects);
        displayEffect(myMonster, myMonsterTooltip, myMonsterHPLevel, myMonsterEffectTooltip, myMonsterEffects);
    }

    private void displayEffect(final DungeonCharacter theCharacter, final Tooltip theTooltip, 
                                final Label theLabel, final Tooltip theStatus, final ImageView theIcon) {
        if (theCharacter.getStatusEffects().hasEffect()) {
            theTooltip.setText(theCharacter.getStats() + "\n" + theCharacter.getStatusEffects().toString());
            
            theStatus.setText(theCharacter.getStatusEffects().toString());
            theLabel.setTooltip(theStatus);
            Image icon = null;
            //Adding image to label depending on status
            if (theCharacter.getStatusEffects().hasMultipleStatus()) {
                 icon = getImage("bothStatus");
            } else if (theCharacter.getStatusEffects().isVulnerable()) {
                icon = getImage("debuff");
            } else {
                icon = getImage("buff");
            }
            theIcon.setImage(icon);
            theLabel.setGraphic(theIcon);
        } else {
            theTooltip.setText(theCharacter.getStats());
            theLabel.setGraphic(null);
            theLabel.setTooltip(null);
        }
    }

    private Image getImage(final String theImageName) {
        final StringBuilder builder = new StringBuilder();
        builder.append("/team5/game/");
        builder.append(theImageName);
        builder.append(".png");
        Image temp = new Image(getClass().getResource(builder.toString()).toString());
        return temp;
    }

    private void over() {
        setBattleButtons(false);
        setNextButton(true);
    }

    private void setBattleButtons(boolean theBoolean) {
        myAttack.setDisable(!theBoolean);
        mySpecial.setDisable(!theBoolean);
        myItem.setDisable(!theBoolean);
        myRetreat.setDisable(!theBoolean);

    }

    private void setNextButton(boolean theBoolean) {
        myNext.setVisible(theBoolean);
        myNext.setDisable(!theBoolean);
    }

}
