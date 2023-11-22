package lotto.domain.lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto extends LottoRule {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        this.numbers = sortNumbers(numbers);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }


    private List<Integer> sortNumbers(List<Integer> numbers){
        List<Integer> sortedNumber = new ArrayList<>(numbers);
        Collections.sort(sortedNumber);

        return sortedNumber;
    }
}
