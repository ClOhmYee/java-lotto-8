package lotto;

import java.util.Collections;
import java.util.List;

public class LottoWinning {
    private final List<Integer> winningNumbers;
    private final int bonus;

    public LottoWinning(List<Integer> winningNumbers, int bonus) {
        validate(winningNumbers, bonus);
        this.winningNumbers = winningNumbers;
        this.bonus = bonus;
    }

    private void validate(List<Integer> winningNumbers, int bonus) {
        if (winningNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 6개여야 합니다.");
        }
        if (winningNumbers.stream().distinct().count() != 6 || winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 중복될 수 없습니다.");
        }
        if (winningNumbers.get(0) < 1 || winningNumbers.get(5) > 45) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 1이상 45 이하여야 합니다.");
        }
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1이상 45 이하여야 합니다.");
        }
    }

    public List<Integer> getWinningNumbers() {
        return Collections.unmodifiableList(winningNumbers);
    }

    public int getBonusNumber() { return bonus;}
}
