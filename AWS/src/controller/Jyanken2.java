package controller;

// ランダム関数とScannerを使う為のインポート
import java.util.Random;
import java.util.Scanner;

public class Jyanken2 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		// じゃんけんゲームを作る

		Scanner scanner = new Scanner(System.in);

		// 説明文
		System.out.println("勝負する手を数字で入力してください。1.グー、2.チョキ、3.パー");

		// 入力された数字を変数Playerに格納
		int player = Integer.parseInt(scanner.nextLine());

		// コンピュータ用に乱数を生成し変数computerに格納
		//0~2数字がランダムで生成される
		//1~3で入力してもらいたかったためプラス１をした
		Random rand = new Random();
		int computer = rand.nextInt(3);

		//Playerが入力した数字をswitch文で振り分け
		//if文でComputerがランダムで出した数字によって分岐させ
		//PlayerとComputerが出した手と勝敗を表示
		switch (player) {
			// playerがグーを出した場合
			case 1:
				if (computer == 0) {
					System.out.println("Player:グー　vs Computer:グー");
					System.out.println("あいこです");
				} else if (computer == 1) {
					System.out.println("Player:グー　vs Computer:チョキ");
					System.out.println("あなたの勝ちです");
				} else if (computer == 2) {
					System.out.println("Player:グー　vs Computer:パー");
					System.out.println("あなたの負けです");
				}
				break;
			// playerがチョキを出した場合
			case 2:
				if (computer == 0) {
					System.out.println("Player:チョキ　vs Computer:グー");
					System.out.println("あなたの負けです");
				} else if (computer == 1) {
					System.out.println("Player:チョキ　vs Computer:チョキ");
					System.out.println("あいこです");
				} else if (computer == 2) {
					System.out.println("Player:チョキ　vs Computer:パー");
					System.out.println("あなたの勝ちです");
				}
				break;
			// playerがパーを出した場合
			case 3:
				if (computer == 0) {
					System.out.println("Player:パー　vs Computer:グー");
					System.out.println("あなたの勝ちです");
				} else if (computer == 1) {
					System.out.println("Player:パー　vs Computer:チョキ");
					System.out.println("あなたの負けです");
				} else if (computer == 2) {
					System.out.println("Player:パー　vs Computer:パー");
					System.out.println("あいこです");
				}
				break;
		}

	}

}
