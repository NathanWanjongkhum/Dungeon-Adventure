package team5.game.view;

import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import team5.game.App;
import team5.game.DatabaseHandler;
import team5.game.model.Direction;
import team5.game.model.Dungeon;
import team5.game.model.GameState;
import team5.game.model.Hero;
import team5.game.model.Monster;
import team5.game.model.Room;

public class DungeonController {
    /** The original size of the tiles sprite */
    private final int ORIGINAL_TILE_SIZE = 16;

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

    /** The graphics context for drawing on the canvas */
    private GraphicsContext gc;

    public DungeonController() {
        setScale(1);
        setZoom(1);
    }

    @FXML
    private void initialize() throws ClassNotFoundException, IOException {
        myDungeon = GameState.getInstance().getDungeon();
        myHero = GameState.getInstance().getHero();

        // Initializes the dungeon
        myDungeon.init();

        // Places the hero in the dungeon
        myHero.setX(0);
        myHero.setY(0);

        // Initializes the monsters
        DatabaseHandler.init();

        Monster[] monsters = null;

        try {
            monsters = (Monster[]) DatabaseHandler.deserialize();
        } catch (FileNotFoundException e) {
            System.err.println("Database file not found: " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load monster database: " + e.getMessage());
        }

        DatabaseHandler.close();

        // Initializes the canvas
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

                // Debug: Draw tile outline
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

    private void handleKeyPressed(final KeyEvent theEvent) {
        try {
            switch (theEvent.getCode()) {
                case ESCAPE -> App.setRoot("DungeonSetting");
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

    private void tryMove(final Direction theDirection) {
        final boolean isConnected = myDungeon.isConnected(myHero.getX(), myHero.getY(), theDirection);

        if (isConnected) {
            myHero.moveTo(theDirection);
            render();
        }
    }
}
