package lotto.domain;


import java.util.List;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.Lottos;

public class Consumer {
    private final Purchase purchase;
    private final Lottos lottos;


    public Consumer(Purchase purchase, Lottos lottos) {
        this.purchase = purchase;
        this.lottos = lottos;
    }

    public int getPurchaseAmount() {
        return purchase.getPurchaseAmount();
    }

    public List<Lotto> getLottos() {
        return lottos.getLottos();
    }

}
