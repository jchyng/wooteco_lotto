package lotto.domain;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoRule;
import lotto.exception.ExceptionMessage;

public class Winning extends LottoRule {
    private final List<Integer> numbers;
    private final int bonusNumber;

    public Winning(List<Integer> numbers, int bonusNumber) {
        validate(numbers);
        validateBonus(numbers, bonusNumber);

        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    public int getMatchNumberCount(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();

        return numbers.stream()
                .filter(this.numbers::contains)
                .collect(Collectors.toList())
                .size();
    }

    public boolean isContainBonusNumber(Lotto lotto) {
        return lotto.getNumbers().contains(bonusNumber);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    private void validateBonus(List<Integer> numbers, int bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            ExceptionMessage.BONUS_NUMBER_DUPLICATE.throwException();
        }
        if (isOutOfRange(bonusNumber)) {
            ExceptionMessage.LOTTO_OUT_OF_RANGE.throwException();
        }
    }

}
