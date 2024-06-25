import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ServerEndpoint("/tictactoe")
public class TicTacToeServer {
    private static List<Session> sessions = new ArrayList<>();
    private static char[] board = new char[9];
    private static char currentPlayer = 'X';

    static {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
        try {
            session.getBasicRemote().sendText("Welcome to Tic Tac Toe!");
            sendBoard(session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        int position = Integer.parseInt(message);
        if (position >= 0 && position < 9 && board[position] == ' ') {
            board[position] = currentPlayer;
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            broadcastBoard();
            if (checkWin()) {
                broadcastMessage("Player " + board[position] + " wins!");
                resetBoard();
            } else if (isBoardFull()) {
                broadcastMessage("It's a draw!");
                resetBoard();
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    private void broadcastBoard() {
        for (Session session : sessions) {
            sendBoard(session);
        }
    }

    private void broadcastMessage(String message) {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendBoard(Session session) {
        try {
            session.getBasicRemote().sendText(new String(board));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checkWin() {
        int[][] winPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
                {0, 4, 8}, {2, 4, 6}             // Diagonals
        };

        for (int[] wp : winPositions) {
            if (board[wp[0]] != ' ' && board[wp[0]] == board[wp[1]] && board[wp[1]] == board[wp[2]]) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (char c : board) {
            if (c == ' ') {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int i = 0; i < 9; i++) {
            board[i] = ' ';
        }
        currentPlayer = 'X';
        broadcastBoard();
    }
}
