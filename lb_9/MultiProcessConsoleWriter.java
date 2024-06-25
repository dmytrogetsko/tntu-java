public class MultiProcessConsoleWriter {
    public static void main(String[] args) {
        Thread writer1 = new Thread(new ConsoleWordWriter("Thread1", new String[]{"apple", "banana", "cherry"}));
        Thread writer2 = new Thread(new ConsoleWordWriter("Thread2", new String[]{"dog", "elephant", "frog"}));
        Thread writer3 = new Thread(new ConsoleWordWriter("Thread3", new String[]{"grape", "hippo", "iguana"}));

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

        System.out.println("Вивід завершено.");
    }

    static class ConsoleWordWriter implements Runnable {
        private final String threadName;
        private final String[] words;

        public ConsoleWordWriter(String threadName, String[] words) {
            this.threadName = threadName;
            this.words = words;
        }

        @Override
        public void run() {
            for (String word : words) {
                System.out.println(threadName + ": " + word);
                try {
                    Thread.sleep(100); // Імітація затримки
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
