package lotto.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;
import lotto.domain.Prize;
import lotto.domain.Winning;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PrizeCalculatorTest {
    private PrizeCalculator prizeCalculator = new PrizeCalculator();

    @DisplayName("일치하는 로또 번호와 보너스 여부에 따른 상금 결과를 반환한다.")
    @Test
    void getPrizeByMatchNumbers() {
        //Given
        int matchCount = 5;
        boolean isMatchBonus = true;
        //When
        Prize prize = Prize.getPrize(matchCount, isMatchBonus);
        //Then
        assertThat(prize).isEqualTo(Prize.FIVE_MATCH_BONUS);
    }


    @DisplayName("당첨 통계를 계산한다.")
    @Test
    void calcTotalPrize() {
        //Given
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1,2,3,4,5,8)),
                new Lotto(List.of(1,2,3,4,5,7))
        );
        Winning winning = new Winning(List.of(1,2,3,4,5,6), 7);
        //When
        Map<Prize, Integer> totalPrize = prizeCalculator.calcTotalPrize(lottos, winning);
        //Then
        assertThat(totalPrize.get(Prize.FIVE_MATCH)).isEqualTo(1);
        assertThat(totalPrize.get(Prize.FIVE_MATCH_BONUS)).isEqualTo(1);
    }

    @DisplayName("수익률을 계산한다.")
    @Test
    void calcRevenueRate() {
        //Given
        int purchaseAmount = 3000;
        Map<Prize, Integer> totalPrize = Prize.initTotalPrizeMap();
        totalPrize.put(Prize.FOUR_MATCH, 1);
        //When
        double revenueRate = prizeCalculator.calcRevenueRate(totalPrize, purchaseAmount);
        //Then
        assertThat(revenueRate).isEqualTo(1666.7);
    }
}
