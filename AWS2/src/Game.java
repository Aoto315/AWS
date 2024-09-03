import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Game {
	// データベース接続情報
	private static final String DB_URL = "jdbc:mysql://localhost:3306/mysql"; // データベースのURL
	private static final String DB_USER = "root"; // データベースのユーザー名
	private static final String DB_PASSWORD = "admin123"; // データベースのパスワード
	private static final Logger LOGGER = LogManager.getLogger(Game.class);

	public static void play() {
		// 結果の変数を用意
		int result = 0;

		// Scannerのインスタンス化
		Scanner scanner = new Scanner(System.in);

		do {
			// メッセージの表示
			LOGGER.info("勝負する手を数字で入力してください。0.グー、1.チョキ、2.パー");
//			System.out.println("勝負する手を数字で入力してください。0.グー、1.チョキ、2.パー");

			if (!scanner.hasNextInt()) {
				System.out.println("無効な入力です。数値を入力してください。");
				scanner.next(); // 不正な入力を消費
				continue;
			}

			// ユーザーの手を読み取る
			int userHands = scanner.nextInt();

			// 入力チェック
			while (userHands < 0 || userHands > 2) {
				System.out.println("無効な値です。");
				System.out.println("勝負する手を数字で入力してください。0.グー、1.チョキ、2.パー");
				userHands = scanner.nextInt();
			}

			// PCのじゃんけんの手を準備
			Random rand = new Random();
			int computerHands = rand.nextInt(3);

			// 結果判定
			result = (userHands - computerHands + 3) % 3;
			String winOrLose;
			if (result == 2) {
				winOrLose = "あなたの勝ちです！";
			} else if (result == 1) {
				winOrLose = "あなたの負けです";
			} else {
				winOrLose = "あいこです";
			}

			// 判定結果の表示
			String[] hands = { "グー", "チョキ", "パー" };
			System.out.println("Player: " + hands[userHands] + ", Computer: "
					+ hands[computerHands]);
			System.out.println("結果：" + winOrLose);

			// データベースに結果を保存
			saveResultToDatabase(winOrLose);

			// あいこの場合は繰り返す
		} while (result == 0);

		// scanner.close();
	}

	private static void saveResultToDatabase(String winOrLose) {
		String query = "INSERT INTO jyanken (date, outcome) VALUES (?, ?)";

		// 現在の日付を取得
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = dateFormat.format(new Date());

		try (Connection connection
				= DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement preparedStatement
						= connection.prepareStatement(query)) {

			preparedStatement.setString(1, currentDate);
			preparedStatement.setString(2, winOrLose);

			int rowsAffected = preparedStatement.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("結果がデータベースに保存されました。");
			}

		} catch (SQLException e) {
			System.out.println("データベースへの保存に失敗しました。");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		play();
	}
}
