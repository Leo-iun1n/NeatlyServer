package com.geeyao.neatly.compare.simple;

import com.geeyao.neatly.compare.CardSet;
import com.geeyao.neatly.compare.PokerDefine;
import com.geeyao.neatly.logic.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断连队，对子依小大到分别存于第2，3位，单张存于第一位
 */
public class LiangDui extends CardSet {

    int typeVal = PokerDefine.LIANG_DUI << PLACE_6;

    @Override
    public int getWeight(List<Card> cardList) {
        if (cardList.size() != CARD_COUNT_5) return PokerDefine.NULL;
        List<Card> duiList = new ArrayList<>();
        for (int i = 0; i < cardList.size() - 1; i++) {
            if (cardList.get(i + 1).getNumber() == cardList.get(i).getNumber()) {
                duiList.add(cardList.get(i));
            }
        }
        if (duiList.size() < 2) {
            return PokerDefine.NULL;
        }
        Card danCard = null;//单张牌
        for (int i = 0; i < cardList.size(); i++) {
            int num = cardList.get(i).getNumber();
            if (num != duiList.get(0).getNumber() && num != duiList.get(1).getNumber()) {
                danCard = cardList.get(i);
                break;
            }
        }
        if (danCard == null) {
            return PokerDefine.NULL;
        }
        duiList = sortCardByEndA(duiList);
        duiList.add(0, danCard);
        return typeVal +getCardListWeight(duiList);
    }
}
