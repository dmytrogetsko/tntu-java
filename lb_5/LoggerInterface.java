import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public interface LoggerInterface {
    String LOG_FILE = "";

    default void log(String message, String messageType) {
        if (!isLoggingEnabled()) return;

        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String user = System.getProperty("user.name");
            writer.write(String.format("%s %s %s %s%n", timeStamp, user, message, messageType));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static boolean isLoggingEnabled() {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream("lab4.properties")) {
            properties.load(input);
            return "on".equalsIgnoreCase(properties.getProperty("logging"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}