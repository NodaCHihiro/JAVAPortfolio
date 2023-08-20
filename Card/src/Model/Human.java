package Model;

import java.util.ArrayList;
import java.util.List;

public class Human {


  /**
   * プレイヤーの名前、手札、勝敗をインスタンス
   */
  private String name;
  private static List<String> handCards = new ArrayList<String>();
  private int battleResult;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getHandCards() {
    return handCards;
  }


  public void setHandCards(List<String> handCards) {
    Human.handCards = handCards;
  }

  public void drawCard() {

  }

  public int getBattleResult() {
    return battleResult;
  }

  public void setBattleResult(int battleResult) {
    this.battleResult = battleResult;
  }

  // 手札にカードを追加するメソッド
  public void addCardToHand(String card) {
    handCards.add(card);
  }

  // 手札を引くメソッド（カードを手札に追加）
  public void drawCard(String card) {
    addCardToHand(card);
  }


}
