package lotto.view;

import java.util.List;
import java.util.Map;
import lotto.domain.Consumer;
import lotto.domain.Prize;
import lotto.domain.lotto.Lotto;

public class OutputView {

    public void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        lottos.forEach(System.out::println);
        System.out.println();
    }

    public void printTotalPrize(Map<Prize, Integer> totalPrize) {
        StringBuilder sb = new StringBuilder();

        sb.append("당첨 통계\n"
                + "---\n");
        totalPrize.keySet().forEach(prize -> {
            if(prize != Prize.FAIL){
                sb.append(prize.getMessage());
                sb.append(" - " + totalPrize.get(prize) + "개\n");
            }
        });

        System.out.println(sb);
    }

    public void printRevenueRate(double revenueRate) {
        String result = String.format("총 수익률은 %,.1f%%입니다.", revenueRate);
        System.out.println(result);
    }
}
