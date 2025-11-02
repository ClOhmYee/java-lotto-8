package lotto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {
    private static final int LOTTO_PRICE = 1000;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void run() {
        int money = inputView.inputMoney();
        List<Lotto> lottos = generateLottos(money);
        LottoWinning winningLotto = inputWinningLotto();
        outputView.printPurchasedLottos(lottos);

        LottoResult lottoResult = new LottoResult(lottos, winningLotto);
        Map<LottoStandard, Integer> results = lottoResult.allResult();
        outputView.printResult(results);

        double profitRate = calculateProfitRate(results, money);
        outputView.printProfitRate(profitRate);
    }

    private LottoWinning inputWinningLotto() {
        List<Integer> winningNumbers = inputView.inputWinningNumbers();
        int bonusNumber = inputView.inputBonusNumber();

        return new LottoWinning(winningNumbers, bonusNumber);
    }

    private List<Lotto> generateLottos(int money) {
        int lottoCount = moneyToAmount(money);
        List<Lotto> lottos = new ArrayList<>();

        for (int i = 0; i < lottoCount; i++) {
            lottos.add(LottoGenerator.generate());
        }

        return lottos;
    }

    private int moneyToAmount(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }

        return money / LOTTO_PRICE;
    }

    private double calculateProfitRate(Map<LottoStandard, Integer> results, int money) {
        long totalPrize = 0;

        for (Map.Entry<LottoStandard, Integer> entry : results.entrySet()) {
            totalPrize += (long) entry.getKey().getPrize() * entry.getValue();
        }

        return (double) totalPrize / money * 100;
    }

    public static void main(String[] args) {
        try {
            new Application().run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
