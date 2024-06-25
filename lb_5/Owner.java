import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Owner implements LoggerInterface {
    private String name;
    private static final String LOG_FILE = "Owner.log";

    public Owner(String name) {
        this.name = name;
        log("Created Owner instance: " + this, "message");
    }

    @Override
    public String toString() {
        return "Owner{name='" + name + "'}";
    }
}