package lotto.domain;

import lotto.domain.lotto.LottoPrice;
import lotto.exception.ExceptionMessage;

public class Purchase {
    private final int purchaseAmount;

    public Purchase(int purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    public int getNumberOfLotto(){
        return purchaseAmount / LottoPrice.THOUSAND_WON.getPrice();
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    private void validate(int purchaseAmount){
        int price = LottoPrice.THOUSAND_WON.getPrice();
        if(purchaseAmount % price != 0){
            ExceptionMessage.PURCHASE_AMOUNT_NOT_DIVIDED.throwException();
        }
    }
}
