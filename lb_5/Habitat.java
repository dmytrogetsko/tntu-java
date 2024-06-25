import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Habitat implements LoggerInterface {
    private String name;
    private List<Mammal> mammals;
    private static final String LOG_FILE = "Habitat.log";

    public Habitat(String name) {
        this.name = name;
        this.mammals = new ArrayList<>();
        log("Created Habitat instance: " + this, "message");
    }

    public void addMammal(Mammal mammal) {
        mammals.add(mammal);
        log("Added Mammal: " + mammal, "message");
    }

    @Override
    public String toString() {
        return "Habitat{name='" + name + "', mammals=" + mammals + "}";
    }
}