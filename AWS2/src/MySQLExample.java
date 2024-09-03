import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLExample {
    public static void main(String[] args) {
        // MySQL接続情報
        String url = "jdbc:mysql://localhost:3306/mysql"; // データベースのURL
        String username = "root"; // データベースのユーザー名
        String password = "admin123"; // データベースのパスワード

        // MySQLへの接続
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
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
                        System.out.println("ID: " + id + ", DATE: " + date + ", OUTCOME:" + outcome);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("MySQLへの接続に失敗しました。");
            e.printStackTrace();
        }
    }
}
