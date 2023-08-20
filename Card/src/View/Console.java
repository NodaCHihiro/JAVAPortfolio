package View;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import Controller.BlackJack;
import Model.Human;
import Model.Tramp;

/**
 * Consoleクラス：BlackJackのMVCモデルのViewにあたる
 *
 */
public class Console {

	// プレイヤー人数
	private static final List<String> playerHandCards = new ArrayList<String>();

	private static int numPlayers;

	// 山札カードの情報
	private static String[] deckCards;

	// 利用し終わったカード
	private static Set<String> usedCards;

	// ゲームをプレイしている人の情報
	private static List<Human> humans;

	/**
	 * 開始のアナウンス
	 */
	public static void startGame(Scanner scanner) {

		// ゲーム開始の宣言をする
		System.out.println("ブラックジャックゲームを始めます");

		// ゲーム終了フラグ
		boolean playAgainGameFlag = true;

		while (playAgainGameFlag) {

			setPlayerNumber();

			inGame();

			playAgainGameFlag = isPlayAgainGame(scanner);
			if (playAgainGameFlag) {
				resetGame();
			}
		}

	}

	/**
	 * 対戦人数、ゲーム開始準備の生成
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		startGame(scanner);
	}

	/**
	 * 対戦人数の設定をする
	 */
	public static void setPlayerNumber() {
		System.out.println("対戦人数を入力してください。");

		Scanner scan = new Scanner(System.in);
		numPlayers = scan.nextInt();
		scan.nextLine();

		System.out.println("ディーラーを含めた" + (numPlayers + 1) + "人で対戦します");
		System.out.println("トランプカードを2枚ずつ配ります。2枚のカードの数字を合計してください。");
	}

	/**
	 * ゲーム開始する プレイヤーはコンソール画面に入力した数値分を表示させる
	 */
	public static void inGame() {

		// プレイヤー・ディーラーの手札リストを生成
		Tramp cards = new Tramp();
		// ゲームで使用するカードデッキを生成する
		deckCards = cards.getPlayingCards();
		usedCards = new HashSet<>();

		List<String> dealerHandCards = new ArrayList<>();
		List<List<String>> playerHandCardsList = new ArrayList<>();

		// ディーラーがカードを2枚引く
		for (int i = 0; i < 2; i++) {
			dealerHandCards.add(deckCards[i]);
			usedCards.add(deckCards[i]);
		}

		// プレイヤーがカードを2枚引く
		for (int i = 0; i < numPlayers; i++) {
			List<String> playerHandCards = new ArrayList<>();
			for (int j = 0; j < 2; j++) {
				String card = drawCard();
				playerHandCards.add(card);
				usedCards.add(card);
			}
			playerHandCardsList.add(playerHandCards);
		}

		int dealerPoint = sumPoint(dealerHandCards);

		System.out.println("ディーラーのカード: " + String.join(", ", dealerHandCards));
		System.out.println("ディーラーの現在のポイントは" + dealerPoint + "です。");
		System.out.println(" ");

		// プレイヤーごとにカードとポイントを表示
		for (int i = 0; i < numPlayers; i++) {
			List<String> playerHandCards = playerHandCardsList.get(i);
			int playerPoint = sumPoint(playerHandCards);

			// コンソールにカードとポイントを表示
			System.out.println("プレイヤー" + (i + 1) + "のカード: " + String.join(", ", playerHandCards));
			System.out.println("プレイヤー" + (i + 1) + "の現在のポイントは" + playerPoint + "です。");
			System.out.println("");

			while (playerPoint < 21) {
				Scanner scan = new Scanner(System.in);
				System.out.println("プレイヤー" + (i + 1) + "はカードを追加しますか（YES(y) or NO(n))");
				String str = scan.nextLine();

				if (str.equalsIgnoreCase("y")) {
					String card = drawCard();
					playerHandCards.add(card);
					usedCards.add(card);
					playerPoint = sumPoint(playerHandCards);
					System.out.println("プレイヤー" + (i + 1) + "の新しいカード: " + card);
					System.out.println("プレイヤー" + (i + 1) + "の現在のポイントは" + playerPoint + "です。");

					if (playerPoint > 21) {
						System.out.println("プレイヤー" + (i + 1) + "はバーストしました");
						break;
					}
				} else if (str.equalsIgnoreCase("n")) {
					System.out.println("プレイヤー" + (i + 1) + "は終了です");
					break;
				} else {
					System.out.println("「" + str + "」は不正な値です。入力しなおしてください");
					continue;
				}
			}
		}

		// ディーラーがカードを引く
		while (dealerPoint < 17) {
			String card = drawCard();
			dealerHandCards.add(card);
			usedCards.add(card);
			dealerPoint = sumPoint(dealerHandCards);
			System.out.println("ディーラーがカードを引きました: " + card);
			System.out.println("ディーラーの現在のポイントは" + dealerPoint + "です。");
			System.out.println("");

			if (dealerPoint > 21) {
				System.out.println("ディーラーはバーストしました");
				break;
			}
		}

		// 最終結果の表示
		System.out.println("ディーラーの最終手札: " + String.join(", ", dealerHandCards));
		System.out.println("ディーラーの最終ポイントは" + dealerPoint + "です。");
		System.out.println("");

		for (int i = 0; i < numPlayers; i++) {
			List<String> playerHandCards = playerHandCardsList.get(i);
			int playerPoint = sumPoint(playerHandCards);
			System.out.println("プレイヤー" + (i + 1) + "の最終手札: " + String.join(", ", playerHandCards));
			System.out.println("プレイヤー" + (i + 1) + "の最終ポイントは" + playerPoint + "です。");
			System.out.println("");
		}

		// 勝者の判定
		int maxPoint = 0;
		int winnerIndex = -1; // -1 represents no winner

		if (dealerPoint <= 21 && dealerPoint > maxPoint) {
			maxPoint = dealerPoint;
			winnerIndex = 0; // 0 represents the dealer
		}

		for (int i = 0; i < numPlayers; i++) {
			List<String> playerHandCards = playerHandCardsList.get(i);
			int playerPoint = sumPoint(playerHandCards);

			if (playerPoint <= 21 && playerPoint > maxPoint) {
				maxPoint = playerPoint;
				winnerIndex = i + 1; // Player index starts from 1
			}
		}

		if (maxPoint == 0) {
			System.out.println("全員がバーストしました。引き分けです。");
		} else if (winnerIndex == 0) {
			System.out.println("ディーラーは、最終" + dealerPoint + "になりました。");
			System.out.println("");
			System.out.println("ディーラーの勝利です。");
		} else {
			System.out.println("プレイヤー" + winnerIndex + "が最終" + maxPoint + "になりました。");
			System.out.println("");
			System.out.println("プレイヤー" + winnerIndex + "の勝利です。");
		}
	}

	/**
	 * 手札の情報から合計値を算出する
	 * 
	 * @param handCards 手札のリスト
	 * @return 合計ポイント
	 */
	private static int sumPoint(List<String> handCards) {
		int sum = 0;

		for (String card : handCards) {
			sum = sum + BlackJack.toPoint(card);
		}
		return sum;
	}

	/**
	 * 未使用のカードをランダムに引く
	 * 
	 * @return 引かれたカード
	 */
	private static String drawCard() {
		int index = (int) (Math.random() * deckCards.length);
		String card = deckCards[index];

		while (usedCards.contains(card)) {
			index = (int) (Math.random() * deckCards.length);
			card = deckCards[index];
		}

		return card;
	}

	/**
	  * ゲームを再度プレイするかユーザーに確認する
	  *
	  * @param scannerコマンド入力で選択する
	  * @return再度プレイするかの選択結果
	  */
	public static boolean isPlayAgainGame(Scanner scanner) {
		while (true) {
			System.out.println("もう一度プレイしますか。（YES(y) or NO(n))");
			String str = scanner.nextLine();

			if (str.equalsIgnoreCase("y")) {
				return true;
			} else if (str.equalsIgnoreCase("n")) {
				System.out.println("ゲームを終了します。");
				System.out.println("");
				return false;
			} else {
				System.out.println("「" + str + "」は不正な値です。入力しなおしてください");
				continue;
			}
		}
	}

	/**
	 * 再度プレイするにあたりトランプのリセットを宣言する
	 */
	public static void resetGame() {
		System.out.println("トランプをリセットします。");
		System.out.println("");

	}

}
