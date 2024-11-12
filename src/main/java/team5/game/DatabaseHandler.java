package team5.game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    /**
     * Serialize the database
     * 
     * @throws IOException
     */
    public static void serialize(final Object theObject) throws IOException {
        Path path = Path.of("my.db");

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Database file not found");
        }

        try (final FileOutputStream file = new FileOutputStream(path.toString());
                final ObjectOutputStream out = new ObjectOutputStream(file);) {
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

            if (object == null) {
                throw new IOException("Read null object from database");
            }

            return object;
        }
    }
}