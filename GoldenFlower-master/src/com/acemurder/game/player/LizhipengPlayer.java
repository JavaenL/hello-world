package com.acemurder.game.player;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

import java.util.Collections;
import java.util.List;

public class LizhipengPlayer implements Player {
    public String getName() {
        return "李治朋";
    }

    public String getStuNum() {
        return "2017213315";
    }

    public void onGameStart(Manager manager, int totalPlayer) {

    }

    public int bet(final int time, final int round, final int lastPerson, final int moneyOnDesk,
                   final int moneyYouNeedToPayLeast,
                   List<Poker> pokers) {
        Collections.sort(pokers);
        if (isSameColor(pokers))
            return (int)(Math.pow(2 +round / 10f,round+1)*100);
        if ((isSameColorStraight(pokers) || isSamePoint(pokers)))
            return 3*moneyYouNeedToPayLeast;
        if (isPair(pokers))
            return (int) (Math.pow(1.69,round+1)*100);

        if (isStraight(pokers))
            return (int)(Math.pow(1.99,round+1)*100);

        return 100;
    }

    public void onResult(int time, boolean isWin, List<Poker> pokers) {
    }

    private boolean isSameColor(List<Poker> handCards) {
        return handCards.get(0).getSuit() == handCards.get(1).getSuit() &&
                handCards.get(1).getSuit() == handCards.get(2).getSuit();
    }

    private boolean isPair(List<Poker> handCards) {
        return handCards.get(0).getPoint().getNum() == handCards.get(1).getPoint().getNum()
                || handCards.get(1).getPoint().getNum() == handCards.get(2).getPoint().getNum()
                || handCards.get(0).getPoint().getNum() == handCards.get(2).getPoint().getNum();
    }

    private boolean isStraight(List<Poker> handCards) {
        Collections.sort(handCards);
        return Math.abs(handCards.get(0).getPoint().getNum() - handCards.get(1).getPoint().getNum()) == 1
                && Math.abs(handCards.get(1).getPoint().getNum() - handCards.get(2).getPoint().getNum()) == 1;

    }

    private boolean isSameColorStraight(List<Poker> handCards) {
        return isSameColor(handCards) && isStraight(handCards);
    }

    private boolean isSamePoint(List<Poker> handCards) {
        return handCards.get(0).getPoint() == handCards.get(1).getPoint()
                && handCards.get(2).getPoint() == handCards.get(1).getPoint();

    }

}
