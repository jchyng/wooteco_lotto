package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.domain.lotto.Lottos;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConsumerTest {

    @DisplayName("구매 금액을 저장한다.")
    @Test
    void savePurchaseAmount() {
        //When
        Purchase purchase = new Purchase(5000);
        //Then
        assertThat(purchase.getPurchaseAmount()).isEqualTo(5000);
    }

    @DisplayName("구매 금액이 1000으로 나누어 떨어지지 않는다면 예외가 발생한다.")
    @Test
    void savePurchaseAmountByNotDivided() {
        assertThatThrownBy(() -> new Purchase(5001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 1000원 단위로 입력해주세요.");
    }

    @DisplayName("구매한 금액의 1000원당 한 개의 로또를 생성한다.")
    @Test
    void createLottos() {
        //When
        Lottos lottos = new Lottos(new Purchase(3000));
        //Then
        assertThat(lottos.getLottos().size()).isEqualTo(3);
    }

}
