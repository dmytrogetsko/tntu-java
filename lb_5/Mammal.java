import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Mammal implements LoggerInterface {
    private String name;
    private int age;
    private Owner owner;
    private static final String LOG_FILE = "Mammal.log";

    public Mammal(String name, int age) {
        this.name = name;
        this.age = age;
        log("Created Mammal instance: " + this, "message");
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        log("Set owner: " + owner, "message");
    }

    @Override
    public String toString() {
        return "Mammal{name='" + name + "', age=" + age + "}";
    }
}
