package team5.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

import team5.game.model.Monster;

public class DatabaseHandler {
    /** The connection to the database */
    public static void init() {
        String url = "jdbc:sqlite:my.db";

        try (var conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                var meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /** Close the connection to the database */
    public static void close() {
        try {
            DriverManager.getConnection("jdbc:sqlite:my.db").close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /** Serialize the database */
    public static void serialize(final Object theObject) {
        try {
            Path path = Path.of("my.db");
            FileOutputStream file = new FileOutputStream(path.toString());
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(theObject);

            out.close();
            file.close();

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Deserialize the database
     * 
     * @return the deserialized object
     */
    public static Monster[] deserialize() {
        try {
            Path path = Path.of("my.db");
            FileInputStream file = new FileInputStream(path.toString());
            ObjectInputStream in = new ObjectInputStream(file);
            Monster[] object = (Monster[]) in.readObject();

            in.close();
            file.close();

            return object;
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}