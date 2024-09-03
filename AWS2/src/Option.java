import java.util.Scanner;

public class Option {
	public static void select() {
		Scanner scanner = new Scanner(System.in);

		while (true) { // 無限ループでメニューを表示し続ける
			System.out.println("1~3で番号を入力してください");
			System.out.println("1.じゃんけんをする,2.対戦戦績を表示する,3.終了する");

			int option = scanner.nextInt();

			switch (option) {
				case 1:
					Game.play();
					break;
				case 2:
					Result.showMatchRecord();
					break;
				case 3:
					System.out.println("プログラムを終了します");
					scanner.close();
					return; // プログラムを終了
				default:
					System.out.println("入力した数値は無効です。もう一度入力してください。");
					break;
			}
		}
	}
	public static void main(String[] args) {
        select(); // メニュー選択を開始
    }
}

