import java.util.Scanner;

public class StringScanner {

    /**
     * Main method to execute the program.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a string:");
        String input = scanner.nextLine();

        String[] words = input.split("\\s+");

        for (String word : words) {
            System.out.println(word + " (" + word.length() + ")");
        }

        scanner.close();
    }
}
