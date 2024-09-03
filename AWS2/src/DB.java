import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    // MySQL接続情報
    private static final String URL = "jdbc:mysql://localhost:3306/mysql"; // データベースのURL
    private static final String USERNAME = "root"; // データベースのユーザー名
    private static final String PASSWORD = "admin123"; // データベースのパスワード

    public static void main(String[] args) {
        // MySQLへの接続
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            System.out.println("MySQLに接続しました。");

            // SQLクエリの実行
            try (Statement statement = connection.createStatement()) {
                String sql = "SELECT * FROM jyanken";
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    // 結果の処理
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String date = resultSet.getString("DATE");
                        String outcome = resultSet.getString("outcome");
                        // データを処理するコードをここに書く
                        System.out.println("ID: " + id + ", DATE: " + date + ", OUTCOME: " + outcome);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQLへの接続に失敗しました。");
            e.printStackTrace();
        }
    }
}
