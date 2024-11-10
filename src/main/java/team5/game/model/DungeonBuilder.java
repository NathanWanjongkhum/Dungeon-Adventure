package team5.game.model;

import team5.game.model.Dungeon.Difficulty;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

final class DungeonBuilder {
    private Room[][] myDungeon;
    private int myWidth;
    private int myHeight;
    private Difficulty myDifficulty;

    protected DungeonBuilder() {
    }

    protected final DungeonBuilder setWidth(final int theWidth) {
        myWidth = theWidth;
        return this;
    }

    protected final DungeonBuilder setHeight(final int theHeight) {
        myHeight = theHeight;
        return this;
    }

    protected final DungeonBuilder setDifficulty(final Difficulty theDifficulty) {
        myDifficulty = theDifficulty;
        return this;
    }

    protected final Room[][] build() {
        myDungeon = new Room[myWidth][myHeight];

        // Initialize all rooms first
        for (int x = 0; x < myWidth; x++) {
            for (int y = 0; y < myHeight; y++) {
                myDungeon[x][y] = new Room();
            }
        }

        // Start from center for better distribution
        SimpleEntry<Integer, Integer> startPos = new SimpleEntry<>(myWidth / 2, myHeight / 2);
        generateMaze(startPos);

        return myDungeon;
    }

    private final void generateMaze(final SimpleEntry<Integer, Integer> startPos) {
        Stack<SimpleEntry<Integer, Integer>> stack = new Stack<>();
        boolean[][] visited = new boolean[myWidth][myHeight];

        stack.push(startPos);
        visited[startPos.getKey()][startPos.getValue()] = true;

        while (!stack.isEmpty()) {
            SimpleEntry<Integer, Integer> current = stack.peek();
            List<Direction> unvisitedNeighbors = getUnvisitedNeighbors(current, visited);

            if (unvisitedNeighbors.isEmpty()) {
                stack.pop();
            } else {
                Direction randomDir = unvisitedNeighbors.get(
                        (int) (Math.random() * unvisitedNeighbors.size()));

                SimpleEntry<Integer, Integer> next = new SimpleEntry<>(
                        randomDir.calculateNewX(current.getKey()),
                        randomDir.calculateNewY(current.getValue()));

                // Carve passage between current and next
                Room currentRoom = myDungeon[current.getKey()][current.getValue()];
                Room nextRoom = myDungeon[next.getKey()][next.getValue()];

                carveEdge(randomDir, nextRoom, currentRoom);

                visited[next.getKey()][next.getValue()] = true;
                stack.push(next);
            }
        }
    }

    private final List<Direction> getUnvisitedNeighbors(
            SimpleEntry<Integer, Integer> pos,
            boolean[][] visited) {
        List<Direction> unvisited = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            int newX = dir.calculateNewX(pos.getKey());
            int newY = dir.calculateNewY(pos.getValue());

            if (isValidLocation(newX, newY) && !visited[newX][newY]) {
                unvisited.add(dir);
            }
        }

        Collections.shuffle(unvisited);
        return unvisited;
    }

    private final void carveEdge(final Direction theDirection,
            final Room newRoom,
            final Room previousRoom) {
        previousRoom.addDoor(theDirection);
        newRoom.addDoor(theDirection.getOpposite());
    }

    private final boolean isValidLocation(final int theX, final int theY) {
        return theX >= 0 && theX < myWidth && theY >= 0 && theY < myHeight;
    }

    public final Room getRoom(final SimpleEntry<Integer, Integer> theCoordinates) {
        int theX = theCoordinates.getKey();
        int theY = theCoordinates.getValue();
        return myDungeon[theX][theY];
    }
}