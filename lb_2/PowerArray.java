import java.util.Random;

public class PowerArray {

    /**
     * Main method to execute the program.
     * @param args Command line arguments where the first argument is the size of the array.
     */
    public static void main(String[] args)
    {
        if (args.length != 1) {
            System.out.println("Invalid argument. Correct: java PowerArray <array_size>");
            return;
        }

        int arraySize;
        try {
            arraySize = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer for the array size.");
            return;
        }

        double[] array = generateRandomArray(arraySize);
        printArray("Original array: ", array);

        for (int i = 0; i < array.length; i += 2) {
            array[i] = Math.pow(array[i], 5);
        }

        printArray("Modified array: ", array);
    }

    /**
     * Generates an array of given size with random values between 0 (inclusive) and 1 (exclusive).
     * @param size The size of the array to generate.
     * @return An array of random double values.
     */
    public static double[] generateRandomArray(int size)
    {
        Random random = new Random();
        double[] array = new double[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextDouble();
        }

        return array;
    }

    /**
     * Prints the array to the console with a given message.
     * @param message The message to print before the array.
     * @param array The array to print.
     */
    public static void printArray(String message, double[] array)
    {
        System.out.print(message);

        for (double value : array) {
            System.out.printf("%.5f ", value);
        }

        System.out.println();
    }
}
