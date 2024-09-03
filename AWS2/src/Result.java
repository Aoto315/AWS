import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Result {
	// データベース接続情報
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "admin123";

	public static void showMatchRecord() {
		System.out.println("1~2で番号を入力してください");
		System.out.println("1.過去10回の対戦成績を表示する,2.月別の対戦戦績を表示する");

		// Scannerのインスタンス化
        Scanner scanner = new Scanner(System.in);
        // 数字を読み取る
        int userResult = scanner.nextInt();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (userResult == 1) {
                // 過去10回の記録を取得
                String past10Query = "SELECT * FROM jyanken ORDER BY date DESC LIMIT 10";
                try (PreparedStatement statement = connection.prepareStatement(past10Query);
                     ResultSet resultSet = statement.executeQuery()) {
                    System.out.println("過去10回の対戦成績:");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("ID");
                        String date = resultSet.getString("date");
                        String outcome = resultSet.getString("outcome");
                        System.out.println("ID: " + id + ", Date: " + date + ", Outcome: " + outcome);
                    }
                }
            } else if (userResult == 2) {
                // 月別の対戦成績を取得
                System.out.println("見たい月の数字を2桁になるように入力してください (01: 1月, 02: 2月, ..., 12: 12月)");
                int month = scanner.nextInt();

                String monthlyQuery = "SELECT DATE_FORMAT(date, '%Y-%m') AS month, "
                                     + "COUNT(*) AS count, "
                                     + "SUM(CASE WHEN outcome = 'あなたの勝ちです！' THEN 1 ELSE 0 END) AS wins, "
                                     + "SUM(CASE WHEN outcome = 'あなたの負けです' THEN 1 ELSE 0 END) AS losses, "
                                     + "SUM(CASE WHEN outcome = 'あいこです' THEN 1 ELSE 0 END) AS draws "
                                     + "FROM jyanken "
                                     + "WHERE MONTH(date) = ? "
                                     + "GROUP BY month "
                                     + "ORDER BY month DESC";

                try (PreparedStatement statement = connection.prepareStatement(monthlyQuery)) {
                    statement.setInt(1, month);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        System.out.println("月別の対戦成績:");
                        while (resultSet.next()) {
                            String resultMonth = resultSet.getString("month");
                            int count = resultSet.getInt("count");
                            int wins = resultSet.getInt("wins");
                            int losses = resultSet.getInt("losses");
                            int draws = resultSet.getInt("draws");
                            System.out.println("Month: " + resultMonth + ", Total Games: " + count +
                                    ", Wins: " + wins + ", Losses: " + losses + ", Draws: " + draws);
                        }
                    }
                }
            } else {
                System.out.println("無効な選択です。");
            }
        } catch (SQLException e) {
            System.out.println("データベースへのアクセスに失敗しました。");
            e.printStackTrace();
        } finally {
//            scanner.close();
        }
    }

    public static void main(String[] args) {
        showMatchRecord();
    }
}