import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String URL = "jdbc:mysql://localhost:3306/LibraryDb";
    private static final String USER = "dima";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            System.out.println("Connected to the database.");

            while (true) {
                System.out.println("Choose an option:");
                System.out.println("1. Add author");
                System.out.println("2. Add genre");
                System.out.println("3. Add book");
                System.out.println("4. View all authors");
                System.out.println("5. View all genres");
                System.out.println("6. View all books");
                System.out.println("7. Update book quantity");
                System.out.println("8. Delete book");
                System.out.println("9. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addAuthor(connection, scanner);
                        break;
                    case 2:
                        addGenre(connection, scanner);
                        break;
                    case 3:
                        addBook(connection, scanner);
                        break;
                    case 4:
                        viewAllAuthors(connection);
                        break;
                    case 5:
                        viewAllGenres(connection);
                        break;
                    case 6:
                        viewAllBooks(connection);
                        break;
                    case 7:
                        updateBookQuantity(connection, scanner);
                        break;
                    case 8:
                        deleteBook(connection, scanner);
                        break;
                    case 9:
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addAuthor(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter author name:");
        String name = scanner.nextLine();
        String sql = "INSERT INTO authors (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Author added.");
        }
    }

    private static void addGenre(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter genre name:");
        String name = scanner.nextLine();
        String sql = "INSERT INTO genres (name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.executeUpdate();
            System.out.println("Genre added.");
        }
    }

    private static void addBook(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        System.out.println("Enter book year:");
        int year = scanner.nextInt();
        System.out.println("Enter book quantity:");
        int quantity = scanner.nextInt();
        System.out.println("Enter author ID:");
        int authorId = scanner.nextInt();
        System.out.println("Enter genre ID:");
        int genreId = scanner.nextInt();

        String sql = "INSERT INTO books (title, year, quantity, author_id, genre_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setInt(2, year);
            statement.setInt(3, quantity);
            statement.setInt(4, authorId);
            statement.setInt(5, genreId);
            statement.executeUpdate();
            System.out.println("Book added.");
        }
    }

    private static void viewAllAuthors(Connection connection) throws SQLException {
        String sql = "SELECT * FROM authors";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }
    }

    private static void viewAllGenres(Connection connection) throws SQLException {
        String sql = "SELECT * FROM genres";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }
    }

    private static void viewAllBooks(Connection connection) throws SQLException {
        String sql = "SELECT books.id, books.title, books.year, books.quantity, authors.name AS author, genres.name AS genre " +
                "FROM books " +
                "JOIN authors ON books.author_id = authors.id " +
                "JOIN genres ON books.genre_id = genres.id";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                int year = resultSet.getInt("year");
                int quantity = resultSet.getInt("quantity");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                System.out.println("ID: " + id + ", Title: " + title + ", Year: " + year + ", Quantity: " + quantity + ", Author: " + author + ", Genre: " + genre);
            }
        }
    }

    private static void updateBookQuantity(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter book ID:");
        int id = scanner.nextInt();
        System.out.println("Enter new quantity:");
        int quantity = scanner.nextInt();

        String sql = "UPDATE books SET quantity = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Book quantity updated.");
        }
    }

    private static void deleteBook(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Enter book ID:");
        int id = scanner.nextInt();

        String sql = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Book deleted.");
        }
    }
}
