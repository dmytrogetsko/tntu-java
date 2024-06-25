import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class MultiProcessFileWriter {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final String FILE_PATH = "output.txt";

    public static void main(String[] args) {
        Thread writer1 = new Thread(new WordWriter("Thread1", new String[]{"apple", "banana", "cherry"}));
        Thread writer2 = new Thread(new WordWriter("Thread2", new String[]{"dog", "elephant", "frog"}));
        Thread writer3 = new Thread(new WordWriter("Thread3", new String[]{"grape", "hippo", "iguana"}));

        writer1.start();
        writer2.start();
        writer3.start();

        try {
            writer1.join();
            writer2.join();
            writer3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Запис завершено. Перевірте файл " + FILE_PATH);
    }

    static class WordWriter implements Runnable {
        private final String threadName;
        private final String[] words;

        public WordWriter(String threadName, String[] words) {
            this.threadName = threadName;
            this.words = words;
        }

        @Override
        public void run() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                for (String word : words) {
                    lock.lock();
                    try {
                        writer.write(threadName + ": " + word);
                        writer.newLine();
                    } finally {
                        lock.unlock();
                    }
                    Thread.sleep(100);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
