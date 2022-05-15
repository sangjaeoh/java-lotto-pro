package lotto.controller;

import static lotto.domain.Money.LOTTO_TICKET_PRICE;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.LottoNumbers;
import lotto.domain.PurchasedLottoTickets;
import lotto.domain.LottoVendingMachine;
import lotto.domain.LottoWinningResults;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.enums.LottoRank;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final LottoVendingMachine vendingMachine;

    public LottoController() {
        this.vendingMachine = new LottoVendingMachine();
    }

    public void run() {
        PurchasedLottoTickets lottoTickets = buyLottoTickets();

        int purchasedTicketsCount = lottoTickets.purchasedTicketsCount();

        reportingLottoTicketsInformation(lottoTickets, purchasedTicketsCount);

        LottoWinningResults winningResults = checkWinningLotto(lottoTickets);

        reportingLottoResult(purchasedTicketsCount, winningResults);

    }

    private void reportingLottoTicketsInformation(PurchasedLottoTickets lottoTickets, int purchasedTicketsCount) {
        OutputView.printPurchasedTicketsCount(purchasedTicketsCount);
        OutputView.printTicketsNumbers(lottoTickets.toString());
    }

    private void reportingLottoResult(int purchasedTicketsCount, LottoWinningResults winningResults) {
        List<LottoRank> prizedRanks = LottoRank.getPrizedRanks();

        prizedRanks.sort(Collections.reverseOrder());

        OutputView.printTotalString();
        for (LottoRank prizedRank : prizedRanks) {
            printWinningStatistics(winningResults, prizedRank);
        }

        int usedMoney = purchasedTicketsCount * LOTTO_TICKET_PRICE;
        OutputView.printTotalProfitRate(winningResults.profitRate(Money.from(usedMoney)));
    }

    private void printWinningStatistics(LottoWinningResults winningResults, LottoRank prizedRank) {
        OutputView.printTotalWinningCount(
                prizedRank.getCountOfMatch(),
                prizedRank.getWinningMoney(),
                winningResults.winingRankCount(prizedRank));
    }

    private LottoWinningResults checkWinningLotto(PurchasedLottoTickets lottoTickets) {
        List<Integer> winningLottoNumbers = InputView.inputWinningLottoNumbers();
        WinningLotto winningLotto = WinningLotto.from(LottoNumbers.from(winningLottoNumbers));
        List<LottoNumbers> lottoNumbers = lottoTickets.getLottoNumbers();
        List<LottoRank> ranks = lottoNumbers.stream().
                map(ln -> winningLotto.match(ln)).
                collect(Collectors.toList());
        return LottoWinningResults.from(ranks);
    }

    private PurchasedLottoTickets buyLottoTickets() {
        Money inputMoney = InputView.inputMoney();
        return vendingMachine.purchase(inputMoney);
    }
}