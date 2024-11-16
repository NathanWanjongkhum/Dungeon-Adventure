package team5.game.model;

/**
 * Direction is an enum that represents the cardinal directions.
 */
public enum Direction {
    /** The cardinal directions */
    NORTH(0, -1),
    EAST(1, 0),
    SOUTH(0, 1),
    WEST(-1, 0);

    /** The x coordinate direction */
    private final int dx;
    /** The y coordinate direction */
    private final int dy;

    /** Direction constructor */
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Get the x coordinate direction
     * 
     * @return the x coordinate direction
     */
    public int getDx() {
        return dx;
    }

    /**
     * Get the y coordinate direction
     * 
     * @return the y coordinate direction
     */
    public int getDy() {
        return dy;
    }

    /**
     * Get the opposite direction
     * 
     * @return the opposite direction
     */
    public Direction getOpposite() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case WEST:
                return EAST;
            case EAST:
                return WEST;
            default:
                throw new IllegalStateException("Unknown direction");
        }
    }

    /**
     * Calculate the new x coordinate
     * 
     * @param currentX the current x coordinate
     * 
     * @return the new x coordinate
     */
    public int calculateNewX(int currentX) {
        return currentX + dx;
    }

    /**
     * Calculate the new y coordinate
     * 
     * @param currentY the current y coordinate
     * @return the new y coordinate
     */
    public int calculateNewY(int currentY) {
        return currentY + dy;
    }
}