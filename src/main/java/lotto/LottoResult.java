package lotto;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class LottoResult {
    private final List<Lotto> userLottos;
    private final LottoWinning winningLotto;

    public LottoResult(List<Lotto> userLottos, LottoWinning winningLotto) {
        if (userLottos == null) {
            throw new IllegalArgumentException("[ERROR] 로또 리스트는 null일 수 없습니다.");
        }
        if (winningLotto == null) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 null일 수 없습니다.");
        }

        this.userLottos = userLottos;
        this.winningLotto = winningLotto;
    }

    public Map<LottoStandard, Integer> allResult() {
        Map<LottoStandard, Integer> resultCount = new HashMap<>();
        for (Lotto lotto : userLottos) {
            LottoStandard result = LottoCheck.checkWinning(lotto, winningLotto);
            resultCount.put(result, resultCount.getOrDefault(result, 0) + 1);
        }

        return resultCount;
    }
}
