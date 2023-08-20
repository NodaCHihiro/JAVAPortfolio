package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tramp {
    /**
     * トランプのマークと数字を文字列で定義する
     */
    public String spade1 = "♠1";
    public String spade2 = "♠2";
    public String spade3 = "♠3";
    public String spade4 = "♠4";
    public String spade5 = "♠5";
    public String spade6 = "♠6";
    public String spade7 = "♠7";
    public String spade8 = "♠8";
    public String spade9 = "♠9";
    public String spade10 = "♠10";
    public String spade11 = "♠11";
    public String spade12 = "♠12";
    public String spade13 = "♠13";

    public String heart1 = "♥1";
    public String heart2 = "♥2";
    public String heart3 = "♥3";
    public String heart4 = "♥4";
    public String heart5 = "♥5";
    public String heart6 = "♥6";
    public String heart7 = "♥7";
    public String heart8 = "♥8";
    public String heart9 = "♥9";
    public String heart10 = "♥10";
    public String heart11 = "♥11";
    public String heart12 = "♥12";
    public String heart13 = "♥13";

    public String diamond1 = "♦1";
    public String diamond2 = "♦2";
    public String diamond3 = "♦3";
    public String diamond4 = "♦4";
    public String diamond5 = "♦5";
    public String diamond6 = "♦6";
    public String diamond7 = "♦7";
    public String diamond8 = "♦8";
    public String diamond9 = "♦9";
    public String diamond10 = "♦10";
    public String diamond11 = "♦11";
    public String diamond12 = "♦12";
    public String diamond13 = "♦13";

    public String clover1 = "♣1";
    public String clover2 = "♣2";
    public String clover3 = "♣3";
    public String clover4 = "♣4";
    public String clover5 = "♣5";
    public String clover6 = "♣6";
    public String clover7 = "♣7";
    public String clover8 = "♣8";
    public String clover9 = "♣9";
    public String clover10 = "♣10";
    public String clover11 = "♣11";
    public String clover12 = "♣12";
    public String clover13 = "♣13";

//    public String joker1 = "Joker1";
//    public String joker2 = "Joker2";

    public Tramp() {

    }

    public String[] getPlayingCards() {
        List<String> playingCards = new ArrayList<>(Arrays.asList(
                spade1,
                spade2,
                spade3, 
                spade4, 
                spade5, 
                spade6, 
                spade7, 
                spade8, 
                spade9, 
                spade10, 
                spade11, 
                spade12, 
                spade13,
                heart1, 
                heart2, 
                heart3, 
                heart4, 
                heart5, 
                heart6, 
                heart7, 
                heart8, 
                heart9, 
                heart10, 
                heart11, 
                heart12, 
                heart13,
                diamond1, 
                diamond2, 
                diamond3, 
                diamond4, 
                diamond5, 
                diamond6, 
                diamond7, 
                diamond8, 
                diamond9, 
                diamond10, 
                diamond11, 
                diamond12, 
                diamond13,
                clover1, 
                clover2, 
                clover3, 
                clover4, 
                clover5, 
                clover6, 
                clover7, 
                clover8, 
                clover9, 
                clover10, 
                clover11, 
                clover12, 
                clover13
 //       		 joker1,
//               joker2              
        ));


        Collections.shuffle(playingCards);

        return playingCards.toArray(new String[0]);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Tramp().getPlayingCards()));
    }
}
