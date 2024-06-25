import java.io.*;
import java.util.*;

public class LoggerUtil {

    public static void displaySystemInfo() {
        try (BufferedReader reader = new BufferedReader(new FileReader("system_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listLogFiles() {
        File currentDir = new File(".");
        File[] logFiles = currentDir.listFiles((dir, name) -> name.endsWith(".log"));
        if (logFiles != null) {
            for (File logFile : logFiles) {
                System.out.println(logFile.getName());
            }
        }
    }

    public static void printLog(String className, boolean ascending) {
        String logFile = className + ".log";
        File file = new File(logFile);

        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Log file created: " + logFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(logFile))) {
            List<String> logLines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                logLines.add(line);
            }
            if (!ascending) {
                Collections.reverse(logLines);
            }
            for (String logLine : logLines) {
                System.out.println(logLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}