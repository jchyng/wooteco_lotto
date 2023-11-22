package lotto.controller;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import lotto.domain.Consumer;
import lotto.domain.Prize;
import lotto.domain.Winning;
import lotto.domain.lotto.Lottos;
import lotto.domain.Purchase;
import lotto.service.PrizeCalculator;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoGameManager {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final PrizeCalculator prizeCalculator;

    public LottoGameManager(PrizeCalculator prizeCalculator) {
        this.prizeCalculator = prizeCalculator;
    }

    public void play() {
        Consumer consumer = createConsumerWithInputAmount();
        outputView.printLottos(consumer.getLottos());

        Winning winning = createWinningNumber();

        printTotalPrizeAndRevenue(consumer, winning);
    }

    private Consumer createConsumerWithInputAmount() {
        return doWorkUntilComplete(() -> {
            int purchaseAmount = inputView.enterPurchaseAmount();
            Purchase purchase = new Purchase(purchaseAmount);

            return new Consumer(purchase, new Lottos(purchase));
        });
    }

    private Winning createWinningNumber() {
        return doWorkUntilComplete(() -> {
            List<Integer> numbers = inputView.enterWinningNumber();
            int bonusNumber = inputView.enterBonusNumber();

            return new Winning(numbers, bonusNumber);
        });
    }

    private void printTotalPrizeAndRevenue(Consumer consumer, Winning winning) {
        Map<Prize, Integer> totalPrize = prizeCalculator.calcTotalPrize(consumer.getLottos(), winning);
        outputView.printTotalPrize(totalPrize);

        double revenueRate = prizeCalculator.calcRevenueRate(totalPrize, consumer.getPurchaseAmount());
        outputView.printRevenueRate(revenueRate);
    }

    private <T> T doWorkUntilComplete(Supplier<T> work) {
        while (true) {
            try {
                return work.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
