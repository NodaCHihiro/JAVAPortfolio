package Controller;

import java.util.List;

import Model.Dealer;
import Model.Player;
import Model.Tramp;

public class BlackJack extends Tramp {

	private Player[] player;
	private Dealer dealer;
	Tramp tarmp = new Tramp();

	/**
	 * プレイヤー、ディーラーのインスタンス生成
	 * 
	 * @param playerNumber
	 */
	private BlackJack(int playerNumber) {
		player = new Player[playerNumber];
		for (int i = 0; i < playerNumber; i++) {
			player[i] = new Player();
		}
		dealer = new Dealer();
	}

	/**
	 * 引数numとして受け取ったトランプカードの数値を
	 * BlackJackの点数計算用の値に変換する
	 *  
	 * @param trampCard トランプカードの数値
	 * @return トランプカードの点数計算用の値
	 */
	public static int toPoint(String trampCard) {
		
		// トランプの数字だけを抽出する
		String TrumpNum = trimStart(trampCard);
		int num = Integer.parseInt(TrumpNum);
		
		
		// 絵札の情報を10に変換する
		if (num == 11 || num == 12 || num == 13) {
			num = 10;
		}

		return num;
	}

	/**
	 * 引数listとして受け取った手札のカード情報を
	 * BlackJackの合計用の数値として変換する
	 * 
	 * @param list　トランプカードの手札のカード情報
	 * @return　　トランプカードの数値の合計
	 */
	private static int sumPoint(List<String> list) {
		int sum = 0;

		for (int i = 0; i < list.size(); i++) {
			sum = sum + toPoint(list.get(i));
		}

		return sum;
	}

	/*
	 * 先頭文字削除
	 */
	public static String trimStart(String target) {
		return (target.substring(1));
	}
}