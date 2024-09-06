import java.util.Scanner;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Option {

	private static final Logger LOGGER = LogManager.getLogger(Option.class);

	public static void select() {
		Scanner scanner = new Scanner(System.in);

		while (true) { // 無限ループでメニューを表示し続ける
			LOGGER.info("1~3で番号を入力してください");
			LOGGER.info("1.じゃんけんをする,2.対戦戦績を表示する,3.終了する");

			int option = scanner.nextInt();

			//入力された数字によって処理を行う
			switch (option) {
				case 1:
					Game.play();
					break;
				case 2:
					Result.showMatchRecord();
					break;
				case 3:
					LOGGER.info("プログラムを終了します");
					scanner.close();
					return; // プログラムを終了
				default:
					LOGGER.info("入力した数値は無効です。もう一度入力してください。");
					break;
			}
		}
	}
}
