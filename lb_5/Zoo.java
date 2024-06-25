import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Zoo implements LoggerInterface {
    private String name;
    private List<Mammal> mammals;
    private static final String LOG_FILE = "Zoo.log";

    public Zoo(String name) {
        this.name = name;
        this.mammals = new ArrayList<>();
        log("Created Zoo instance: " + this, "message");
    }

    public void addMammal(Mammal mammal) {
        mammals.add(mammal);
        log("Added Mammal: " + mammal, "message");
    }

    @Override
    public String toString() {
        return "Zoo{name='" + name + "', mammals=" + mammals + "}";
    }
}