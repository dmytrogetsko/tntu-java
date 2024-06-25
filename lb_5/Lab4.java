import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Lab4 {

    public static void main(String[] args) {
        if (args.length == 0 || "help".equalsIgnoreCase(args[0])) {
            printHelp();
        } else if ("system_info".equalsIgnoreCase(args[0])) {
            LoggerUtil.displaySystemInfo();
        } else if ("list".equalsIgnoreCase(args[0])) {
            LoggerUtil.listLogFiles();
        } else if ("printlog".equalsIgnoreCase(args[0])) {
            if (args.length < 3) {
                System.out.println("Usage: java Lab4 printlog <ClassName> <0/1>");
            } else {
                String className = args[1];
                boolean ascending = "0".equals(args[2]);
                LoggerUtil.printLog(className, ascending);
            }
        } else {
            System.out.println("Unknown command.");
            printHelp();
        }
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("java Lab4 system_info");
        System.out.println("java Lab4 list");
        System.out.println("java Lab4 printlog <ClassName> <0/1>");
        System.out.println("java Lab4 help");
    }

    static {
        logSystemInfo();
    }

    private static void logSystemInfo() {
        try (FileWriter writer = new FileWriter("system_info.txt", true)) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String osName = System.getProperty("os.name");
            String osArch = System.getProperty("os.arch");
            String osVersion = System.getProperty("os.version");
            String javaVersion = System.getProperty("java.version");
            String javaVendor = System.getProperty("java.vendor");
            String user = System.getProperty("user.name");

            writer.write(String.format("%s OS: %s %s %s Java: %s (%s) User: %s%n",
                    timeStamp, osName, osArch, osVersion, javaVersion, javaVendor, user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}