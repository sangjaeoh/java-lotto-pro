package lotto.view;

import lotto.domain.*;

public class LottoInput {

    private LottoInput() {
    }

    public static int inputMoneyAndAvailableToPurchaseCount() {
        try {
            String input = InputConsole.inputMoneyForPurchaseLotto();
            Money money = new Money(input);
            return LottoCalculator.availableToPurchaseCount(money);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputMoneyAndAvailableToPurchaseCount();
        }
    }

    public static WinningLotto inputWinningLotto() {
        try {
            Lotto lotto = inputLotto();
            LottoNumber bonusLottoNumber = inputBonusLottoNumber(lotto);
            return new WinningLotto(lotto);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputWinningLotto();
        }
    }

    private static Lotto inputLotto() {
        try {
            String input = InputConsole.inputWinningLottoNumbers();
            return new Lotto(input);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputLotto();
        }
    }

    private static LottoNumber inputBonusLottoNumber(Lotto lotto) {
        try {
            String input = InputConsole.inputBonusLottoNumber();
            return LottoNumber.of(input);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputBonusLottoNumber(lotto);
        }
    }


}
