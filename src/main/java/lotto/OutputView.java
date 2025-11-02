package lotto;

import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPurchasedLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers().toString());
        }
    }

    public void printResult(Map<LottoStandard, Integer> results) {
        LottoStandard[] order = {
                LottoStandard.FIFTH,
                LottoStandard.FOURTH,
                LottoStandard.THIRD,
                LottoStandard.SECOND,
                LottoStandard.FIRST
        };

        for (LottoStandard rank : order) {
            int count = results.getOrDefault(rank, 0);
            String matchInfo = getMatchInfo(rank);
            System.out.println(matchInfo + " - " + count + "개");
        }
    }

    public void printProfitRate(double profitRate) {
        System.out.println("총 수익률은 " + String.format("%,.1f", profitRate) + "%입니다.");
    }

    private String getMatchInfo(LottoStandard rank) {
        if (rank == LottoStandard.SECOND) {
            return "5개 일치, 보너스 볼 일치 (" + String.format("%,d", rank.getPrize()) + "원)";
        }

        return rank.getNumberMatches() + "개 일치 (" + String.format("%,d", rank.getPrize()) + "원)";
    }

}
