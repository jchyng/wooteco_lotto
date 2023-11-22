package lotto.service;

import java.util.List;
import java.util.Map;
import lotto.domain.Prize;
import lotto.domain.Winning;
import lotto.domain.lotto.Lotto;

public class PrizeCalculator {

    public Map<Prize, Integer> calcTotalPrize(List<Lotto> lottos, Winning winning) {
        Map<Prize, Integer> totalPrize = Prize.initTotalPrizeMap();

        for (Lotto lotto : lottos) {
            int matchCount = winning.getMatchNumberCount(lotto);
            boolean isMatchBonus = winning.isContainBonusNumber(lotto);

            Prize prize = Prize.getPrize(matchCount, isMatchBonus);

            totalPrize.put(prize, totalPrize.get(prize) + 1);
        }
        return totalPrize;
    }

    public double calcRevenueRate(Map<Prize, Integer> totalPrize, int purchaseAmount) {
        int totalPrizeAmount = getPrizeAmount(totalPrize);
        double revenueRate = (double) totalPrizeAmount / purchaseAmount * 100;

        return Math.round(revenueRate * 10) / 10.0;
    }

    private int getPrizeAmount(Map<Prize, Integer> totalPrize) {
        return totalPrize.entrySet().stream()
                .filter(entry -> entry.getValue() >= 1)
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
