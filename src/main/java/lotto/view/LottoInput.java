package lotto.view;

import lotto.domain.*;

public class LottoInput {

    private LottoInput() {
    }

    public static LottoCoin inputMoneyAndAvailableToPurchaseCoin() {
        try {
            String input = InputConsole.inputMoneyForPurchaseLotto();
            Money money = Money.of(input);
            return LottoCoin.of(money);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputMoneyAndAvailableToPurchaseCoin();
        }
    }

    public static WinningLotto inputWinningLotto() {
        try {
            Lotto lotto = inputLotto();
            LottoNumber bonusLottoNumber = inputBonusLottoNumber();
            return WinningLotto.of(lotto, bonusLottoNumber);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputWinningLotto();
        }
    }

    private static Lotto inputLotto() {
        try {
            String input = InputConsole.inputWinningLottoNumbers();
            return Lotto.of(input);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputLotto();
        }
    }

    private static LottoNumber inputBonusLottoNumber() {
        try {
            String input = InputConsole.inputBonusLottoNumber();
            return LottoNumber.of(input);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(e.getMessage());
            return inputBonusLottoNumber();
        }
    }

    public static LottoCoin inputBuyManuallyNumber(LottoCoin lottoCoin) {
        try {
            int input = Integer.parseInt(InputConsole.inputBuyManuallyNumber());
            if (input == 0) {
                return LottoCoin.empty();
            }
            return lottoCoin.pop(input);
        } catch (IllegalArgumentException e) {
            OutputConsole.out(String.format("0 ~ %d의 숫자만 입력 가능합니다.", lottoCoin.size()));
            return inputBuyManuallyNumber(lottoCoin);
        }
    }


}
