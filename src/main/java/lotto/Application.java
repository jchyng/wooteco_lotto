package lotto;

import lotto.controller.LottoGameManager;
import lotto.service.PrizeCalculator;

public class Application {
    public static void main(String[] args) {
        LottoGameManager lottoGameManager = new LottoGameManager(new PrizeCalculator());

        lottoGameManager.play();
    }
}
