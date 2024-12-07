package team5.game.view;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import team5.game.App;
import team5.game.model.Consumable;
import team5.game.model.Direction;
import team5.game.model.Dungeon;
import team5.game.model.Exit;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.model.Inventory;
import team5.game.model.Item;
import team5.game.model.Monster;
import team5.game.model.PillarOfOO;
import team5.game.model.Room;

public class DungeonController {
    /** The original size of the tiles sprite */
    private static final int ORIGINAL_TILE_SIZE = 32;

    /** The dungeon maze */
    private static Dungeon myDungeon;
    /** The player character */
    private static Hero myHero;

    /** The tile scaling factor */
    private int myScale;
    /** The final tile size */
    private int myTileSize;
    /** The max rows visible on the screen */
    private int myMaxScreenRows;
    /** The max cols visible on the screen */
    private int myMaxScreenCols;

    /** What the game is drawn on */
    @FXML
    private Canvas gameCanvas;
    /** The base pane */

    /** The graphics context for drawing on the canvas */
    private GraphicsContext gc;
    /** Determine if keyboard inputs should be counted */
    //This is a band aid fix
    private boolean myEnable;
    @FXML
    private Label myAttackPotion;

    @FXML
    private Label myBomb;

    @FXML
    private Label myHPLabel;

    @FXML
    private Label myHealingPotion;

    @FXML
    private ProgressBar myHeroBar;

    @FXML
    private Label myHeroName;

    @FXML
    private TextArea myPickUp;

    @FXML
    private Text myAbstraction;

    @FXML
    private Text myEncapsulation;

    @FXML
    private Text myInheritance;

    @FXML
    private Text myPolymorphism;


    private Inventory myInventory;

    public DungeonController() {
        // Set up the dungeon and hero
        myDungeon = GameState.getInstance().getDungeon();
        myHero = GameState.getInstance().getHero();

        myInventory = myHero.getInventory();

        // Initializes the dungeon
        myDungeon.init();

        // Places the hero in the dungeon
        myHero.setX(myHero.getX());
        myHero.setY(myHero.getY());

        // Set up the zoom and scale
        setScale(1);
        // For demo and debugging purposes zoom out to reveal the entire maze
        if (GameState.getInstance().isCheats()) {
            setZoom(getMaxZoom());
        } else {
            setZoom(1);
        }


        GameState.saveGame();
        myEnable = true;
    }

    @FXML
    private void initialize() {
        // Initializes the canvas
        disablePillars();
        setNoItems();
        heroGUISetup();
        initializeCanvas();
        render();
    }

    /**
     * Initialize the canvas.
     */
    private void initializeCanvas() {
        gc = gameCanvas.getGraphicsContext2D();
        gameCanvas.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(this::handleKeyPressed);
            }
        });
    }

    /**
     * Render the dungeon
     */
    void render() {
        if (GameState.getInstance() == null) {
            System.err.println("Game state not initialized");
            return;
        }
        if (myDungeon == null) {
            System.err.println("Dungeon not initialized");
            return;
        }
        if (myHero == null) {
            System.err.println("Hero not initialized");
            return;
        }

        gc.clearRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Debug border
        gc.setStroke(Color.BLACK);
        gc.strokeRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());

        // Get hero's position (currently center of dungeon)
        final int heroWorldX = myHero.getX();
        final int heroWorldY = myHero.getY();

        // Calculate viewport bounds
        final int startX = Math.max(0, heroWorldX - myMaxScreenCols / 2);
        final int startY = Math.max(0, heroWorldY - myMaxScreenRows / 2);
        final int endX = Math.min(myDungeon.getWidth(), startX + myMaxScreenCols);
        final int endY = Math.min(myDungeon.getHeight(), startY + myMaxScreenRows);

        // Calculate offset to center the viewport
        final double offsetX = (gameCanvas.getWidth() - (myMaxScreenCols * myTileSize) / 2.0) / 2.0;
        final double offsetY = (gameCanvas.getHeight() - (myMaxScreenRows * myTileSize) / 2.0) / 2.0;

        // Render visible portion of the dungeon
        for (int x = startX; x < endX; x++) {
            for (int y = startY; y < endY; y++) {
                // Convert world coordinates to screen coordinates
                final double screenX = offsetX + (x - startX) * myTileSize;
                final double screenY = offsetY + (y - startY) * myTileSize;

                drawTile(x, y, screenX, screenY);
            }
        }

        // Render hero at center
        final double heroScreenX = offsetX + (heroWorldX - startX) * myTileSize;
        final double heroScreenY = offsetY + (heroWorldY - startY) * myTileSize;
        drawHero(heroScreenX, heroScreenY);
    }

    /**
     * Draw the tile at the specified screen coordinates. If the tile is null,
     * draw a floor tile. For each wall, draw a line if the door is not open.
     * 
     * @param theWorldX  the world x coordinate
     * @param theWorldY  the world y coordinate
     * @param theScreenX the screen x coordinate
     * @param theScreenY the screen y coordinate
     */
    private void drawTile(final int theWorldX, final int theWorldY,
            final double theScreenX, final double theScreenY) {
        Room room = myDungeon.getRoom(theWorldX, theWorldY);

        if (room == null) {
            gc.setFill(Color.DARKGRAY);
            gc.fillRect(theScreenX, theScreenY, myTileSize, myTileSize);
            return;
        }

        // Draw floor
        gc.setFill(Color.WHITE);
        gc.fillRect(theScreenX, theScreenY, myTileSize, myTileSize);

        if (room.getItem() != null) {
            drawItem(room.getItem(), theScreenX, theScreenY);
        }
        if (room.getMonster() != null) {
            drawMonster(room.getMonster(), theScreenX, theScreenY);
        }

        boolean[] doors = room.getDoors();

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        // Draw walls based on door configuration
        // Draw walls where there are no doors

        // North wall
        if (!doors[0]) {
            gc.strokeLine(theScreenX, theScreenY,
                    theScreenX + myTileSize, theScreenY);
        }
        // East wall
        if (!doors[1]) {
            gc.strokeLine(theScreenX + myTileSize, theScreenY,
                    theScreenX + myTileSize, theScreenY + myTileSize);
        }
        // South wall
        if (!doors[2]) {
            gc.strokeLine(theScreenX, theScreenY + myTileSize,
                    theScreenX + myTileSize, theScreenY + myTileSize);
        }
        // West wall
        if (!doors[3]) {
            gc.strokeLine(theScreenX, theScreenY,
                    theScreenX, theScreenY + myTileSize);
        }
    }

    private void drawItem(final Item theItem, final double theScreenX, final double theScreenY) {
        switch (theItem.getClass().getName()) {
            case "team5.game.model.Exit" -> gc.setFill(Color.BLACK);
            case "team5.game.model.HealingPotion" -> gc.setFill(Color.LAVENDER);
            case "team5.game.model.Bomb" -> gc.setFill(Color.GRAY);
            case "team5.game.model.PillarOfOO" -> gc.setFill(Color.YELLOW);
            default -> System.err.println("Unknown item: " + theItem.getClass().getName());
        }

        gc.fillOval(theScreenX, theScreenY, myTileSize, myTileSize);
    }

    private void drawMonster(final Monster theMonster, final double theScreenX, final double theScreenY) {
        gc.setFill(Color.RED);
        gc.fillOval(theScreenX, theScreenY, myTileSize, myTileSize);
    }

    /**
     * Get the maximum zoom level
     * 
     * @return the maximum zoom level
     */
    private int getMaxZoom() {
        if (myDungeon == null) {
            return 1;
        }
        return (int) Math.ceil(Math.sqrt(myDungeon.getWidth() * myDungeon.getHeight()));
    }

    /**
     * Draw the hero at the specified screen coordinates
     * 
     * @param theHeroScreenX where the hero is on the screen horizontally
     * @param theHeroScreenY where the hero is on the screen vertically
     */
    private void drawHero(final double theHeroScreenX, final double theHeroScreenY) {
        gc.setFill(Color.GREEN);
        gc.fillOval(theHeroScreenX, theHeroScreenY, myTileSize, myTileSize);
    }

    /**
     * Update the screen size
     */
    private void updateScreen() {
        if (myScale == 0 || myMaxScreenRows == 0 || myMaxScreenCols == 0) {
            return;
        }

        myTileSize = ORIGINAL_TILE_SIZE * myScale;
    }

    /**
     * Set the scale. Update the screen size.
     * 
     * @param theScale the new scale
     */
    private void setScale(final int theScale) {
        myScale = theScale;
        updateScreen();
    }

    /**
     * Set the visible cell distance from the player. Update the screen size.
     * 
     * @param theZoomFactor the new visible cell distance
     */
    public void setZoom(final int theZoomFactor) {
        myMaxScreenRows = (theZoomFactor * 2) + 1;
        myMaxScreenCols = (theZoomFactor * 2) + 1;
        updateScreen();
    }

    /**
     * Handle key pressed events
     * 
     * @param theEvent the key event
     */
    private void handleKeyPressed(final KeyEvent theEvent) {
        try {
            switch (theEvent.getCode()) {
                case ESCAPE -> escapeSettings();
                case W, UP -> tryMove(Direction.NORTH);
                case S, DOWN -> tryMove(Direction.SOUTH);
                case A, LEFT -> tryMove(Direction.WEST);
                case D, RIGHT -> tryMove(Direction.EAST);
                default -> {
                }
            }
        } catch (Exception e) {
            System.err.println("Error handling key press: " + e.getMessage());
        }
    }
    //I think the keyevents also effected the battle scene so was thinking it would also 
    private void escapeSettings() throws IOException {
        if (myEnable) {
            App.createPopUpScene("Settings");
        }
    }

    /**
     * Try to move the hero in the given direction
     * 
     * @param theDirection the direction to move
     * @throws IOException if the screen can't be loaded
     */
    private void tryMove(final Direction theDirection) throws IOException {
        if (!myDungeon.isConnected(myHero.getX(), myHero.getY(), theDirection)) {
            return;
        }
        myHero.moveTo(theDirection);
        myHero.setDirection(theDirection);
        Room currentRoom = myDungeon.getRoom(myHero.getX(), myHero.getY());

        handleRoomItem(currentRoom);
        handleRoomMonster(currentRoom);

        render();
    }

    /**
     * Handle the item in the room
     * 
     * @param room the room
     * @throws IOException if the screen can't be loaded
     */
    private void handleRoomItem(Room room) throws IOException {
        Item item = room.getItem();
        if (item == null) {
            return;
        }

        if (item instanceof PillarOfOO) {
            handlePillarOfOO(item);
        } else if (item instanceof Exit) {
            App.setRoot("EndScene");
            myEnable = false;
        } else {
            myHero.getInventory().addItem(item);
        }
        setItems();
        room.removeItem();
    }

    /**
     * When collecting a pillar of OO, add it to the count until all pillars are
     * collected. Then add an exit to the dungeon and zoom out to reveal the entire
     * maze so the player can easily find it.
     */
    private void handlePillarOfOO(final Item theItem) {
        myDungeon.collectPillar();
        myHero.getInventory().addItem(theItem);
        if (myDungeon.getPillarCount() == 4) {
            myDungeon.addExit();
            setZoom(getMaxZoom());
        }
    }

    /**
     * Handle the monster in the room
     * 
     * @param room the room
     * @throws IOException if the screen can't be loaded
     */
    private void handleRoomMonster(Room room) throws IOException {
        if (room.getMonster() == null) {
            return;
        }
        App.setRoot("BattleScene");
        myEnable = false;
        //Possibly remov monster
        // loadScene("BattleScene");
    }
    private void heroGUISetup() {
        myHeroName.setText(myHero.getName());
        setHP();
        setItems();
    }
    private void setHP() {
        final double hp = myHero.getHealth() / myHero.getMaxHealth();
        final String character = "HP " + myHero.getHealth() + "/" + myHero.getMaxHealth();
        myHPLabel.setText(character);
        myHeroBar.setProgress(hp);
        if (hp < 0.25) {
            myHeroBar.setStyle("-fx-accent: red;");
        } else if (hp < 0.5) {
            myHeroBar.setStyle("-fx-accent: yellow;");
        } else {
            myHeroBar.setStyle("-fx-accent: green");
        }
    }
    private void setItems() {
        int index = 0;
        if (!myInventory.isEmpty()) {
            for (Item c: myInventory.getItems()) {
                if (c != null && c.isPillar()) {
                    setPillar(index);
                } else if (c != null && c.isConsumable()) {
                    setConsumable(index);
                }
                index++;
            }
        }
    }
    private void setPillar(final int theIndex) {
        PillarOfOO item = ((PillarOfOO)myInventory.getItem(theIndex));
        switch(item.getPillar().name()) {
            case "ABSTRACTION":
                myAbstraction.setVisible(true);
                break;
            case "ENCAPSULATION": 
                myEncapsulation.setVisible(true);
                break;
            case "INHERITANCE": 
                myInheritance.setVisible(true);
                break;
            case "POLYMORPHISM":
                myPolymorphism.setVisible(true);
                break;
            default:
                break;
        }
    }
    private void setConsumable(final int theIndex) {
        Consumable item = ((Consumable)myInventory.getItem(theIndex));
        switch(item.getName()) {
            case "AttackPotion":
                myAttackPotion.setText("x" + item.getCount());
                break;
            case "HealingPotion":
                myHealingPotion.setText("x" + item.getCount());
                break;
            case "Bomb":
                myBomb.setText("x" + item.getCount());
                break;
            default:
                break;
        }
    }
    private void disablePillars() {
        myAbstraction.setVisible(false);
        myEncapsulation.setVisible(false);
        myInheritance.setVisible(false);
        myPolymorphism.setVisible(false);
    }
    private void setNoItems() {
        myAttackPotion.setText("x0");
        myHealingPotion.setText("x0");
        myBomb.setText("x0");
    }
}
