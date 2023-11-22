package lotto.domain.lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.domain.Purchase;

public class Lottos {
    private final List<Lotto> lottos;

    public Lottos(Purchase purchase) {
        this.lottos = createLottos(purchase);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }

    private List<Lotto> createLottos(Purchase purchase) {
        List<Lotto> lottos = new ArrayList<>();
        int lottoCount = purchase.getNumberOfLotto();

        for (int i = 0; i < lottoCount; i++) {
            List<Integer> numbers = generateRandomNumber();
            lottos.add(new Lotto(numbers));
        }
        return lottos;
    }

    private List<Integer> generateRandomNumber(){
        return Randoms.pickUniqueNumbersInRange(
                LottoRule.MIN_RANGE, LottoRule.MAX_RANGE, LottoRule.LENGTH);
    }
}
