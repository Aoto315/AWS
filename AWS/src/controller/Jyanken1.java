package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
// ランダム関数とScannerを使う為のインポート
import java.util.Random;
import java.util.Scanner;

public class Jyanken1 {

	public static void main(String[] args) {

		// じゃんけんゲームを作る
		// 勝率を計算
		int Win = 0;
		int Draw = 0;
		int Lose = 0;
		// ゲームの繰り返しを制御する(trueだと繰り返し)
		boolean playAgain = true;

		// Scannerクラス
		Scanner scanner = new Scanner(System.in);
		Random rand = new Random();

		// ユーザーがプレイを希望する限りゲームを繰り返す
		while (playAgain) {
			// 入力を促す説明文
			System.out.println("勝負する手を数字で入力してください。1.グー、2.チョキ、3.パー");

			// 入力された数字を変数Playerに格納
			int Player = Integer.parseInt(scanner.nextLine());

			// Playerが1か2か3以外の数字を入力した場合じゃんけんゲームはPlayできない
			if (Player == 1 || Player == 2 || Player == 3) {

				// コンピュータ用に乱数を生成し変数computerに格納
				// 0~2数字がランダムで生成される
				int computer = rand.nextInt(3);

				// Playerが入力した数字をswitch文で振り分け
				// if文でComputerがランダムで出した数字によって分岐させ
				// PlayerとComputerが出した手と勝敗を表示
				switch (Player) {
					// playerがグーを出した場合
					case 1:
						if (computer == 0) {
							System.out.println("Player:グー　vs Computer:グー");
							System.out.println("あいこです");
							Draw = Draw + 1;
						} else if (computer == 1) {
							System.out.println("Player:グー　vs Computer:チョキ");
							System.out.println("あなたの勝ちです");
							Win = Win + 1;
						} else if (computer == 2) {
							System.out.println("Player:グー　vs Computer:パー");
							System.out.println("あなたの負けです");
							Lose = Lose + 1;
						}
						break;
					// playerがチョキを出した場合
					case 2:
						if (computer == 0) {
							System.out.println("Player:チョキ　vs Computer:グー");
							System.out.println("あなたの負けです");
							Lose = Lose + 1;
						} else if (computer == 1) {
							System.out.println(
									"Player:チョキ　vs Computer:チョキ");
							System.out.println("あいこです");
							Draw = Draw + 1;
						} else if (computer == 2) {
							System.out.println("Player:チョキ　vs Computer:パー");
							System.out.println("あなたの勝ちです");
							Win = Win + 1;
						}
						break;
					// playerがパーを出した場合
					case 3:
						if (computer == 0) {
							System.out.println("Player:パー　vs Computer:グー");
							System.out.println("あなたの勝ちです");
							Win = Win + 1;
						} else if (computer == 1) {
							System.out.println("Player:パー　vs Computer:チョキ");
							System.out.println("あなたの負けです");
							Lose = Lose + 1;
						} else if (computer == 2) {
							System.out.println("Player:パー　vs Computer:パー");
							System.out.println("あいこです");
							Draw = Draw + 1;
						}
						break;
				}
				System.out.println("--------------------------------");
				// 勝敗表示
				System.out.println(
						"勝率　勝ち:" + Win + "　｜　あいこ:" + Draw + "　｜　負け:"
								+ Lose);
				System.out.println("");

				System.out.println("もう一度勝負しますか？　1.はい / 2.いいえ");
				int answer = Integer.parseInt(scanner.nextLine());
				if (answer == 2) { // Playerが「いいえ」を選んだ場合
					playAgain = false; // 繰り返しを終了する
				}
			}
		}
		// スキャナーを閉じる
		scanner.close();
		// 終了メッセージ
		System.out.println("じゃんけんゲームを終了します。");

		// 対戦結果記録
		try {
			// テキストを書き込みたいファイルを対象にしてFileクラスのオブジェクトを作成
			File file = new File("C:\\AWS\\kadai1\\shouritu.txt");
			// オブジェクトを引数としてFileWriterクラスのオブジェクトを作成
			FileWriter filewriter = new FileWriter(file);

			filewriter.write(
					"勝率　勝ち:" + Win + "　｜　あいこ:" + Draw + "　｜　負け:" + Lose);
			// fileを閉じる
			filewriter.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
