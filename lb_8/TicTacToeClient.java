import javax.websocket.*;
import java.net.URI;
import java.util.Scanner;

@ClientEndpoint
public class TicTacToeClient {
    private static Object waitLock = new Object();

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
        if (message.length() == 9) {
            displayBoard(message);
        }
        synchronized (waitLock) {
            waitLock.notifyAll();
        }
    }

    private static void displayBoard(String board) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println();
            System.out.print(board.charAt(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        String uri = "ws://localhost:8080/tictactoe";
        System.out.println("Connecting to " + uri);

        try {
            Session session = container.connectToServer(TicTacToeClient.class, URI.create(uri));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                synchronized (waitLock) {
                    waitLock.wait();
                }
                System.out.println("Enter position (0-8): ");
                String position = scanner.nextLine();
                session.getBasicRemote().sendText(position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
