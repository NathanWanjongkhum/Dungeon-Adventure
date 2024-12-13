package team5.game.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseHandler is a class that handles the database connection. This is to
 * avoid instances of the connection, fetching, or setting being handled
 * improperly
 */
public class DatabaseHandler {
    /** The database URL */
    public static final String URL = "jdbc:sqlite:my.db";

    /** Close the connection to the database */
    public static void close() {
        try {
            DriverManager.getConnection("jdbc:sqlite:my.db").close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Serialize the database
     * 
     * @throws IOException
     */
    public static void serialize(final Object theObject) throws IOException {
        Path path = Path.of("my.db");

        // Create the file if it doesn't exist
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        try (final FileOutputStream file = new FileOutputStream(path.toString());
                final ObjectOutputStream out = new ObjectOutputStream(file)) {
            out.writeObject(theObject);
        }
    }

    /**
     * Deserialize the database
     * 
     * @return the deserialized object
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object deserialize() throws IOException, ClassNotFoundException {
        Path path = Path.of("my.db");

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Database file not found");
        }

        try (FileInputStream file = new FileInputStream(path.toString());
                ObjectInputStream in = new ObjectInputStream(file)) {

            Object object = in.readObject();

            return object;
        }
    }

    /**
     * Select all the monsters from the database
     *
     * @return an array of monsters
     */
    public static Monster[] getMonsters() {
        List<Monster> monsters = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL);
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM monsters")) {

            while (resultSet.next()) {
                monsters.add(MonsterFactory.createMonster(
                        resultSet.getString("type").charAt(0),
                        resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.err.println("Failed to load monster database: " + e.getMessage());
        }

        return monsters.toArray(new Monster[0]);
    }
}