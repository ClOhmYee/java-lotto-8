package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class LottoCheckTest {
    private LottoCheck lottoCheck = new LottoCheck();

    @DisplayName("번호가 6개 모두 일치하면 1등을 반환한다")
    @Test
    void 번호_6개_일치하면_1등을_반환한다() {
        List<Integer> userNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(userNumbers);
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        
        Lotto userLotto = new Lotto(userNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        LottoStandard result = lottoCheck.checkWinning(userLotto, winning);

        assertThat(result).isEqualTo(LottoStandard.FIRST);
    }

    @DisplayName("번호가 5개 일치하고 보너스 번호도 일치하면 2등을 반환한다")
    @Test
    void 번호_5개_일치하고_보너스_일치하면_2등을_반환한다() {
        List<Integer> userNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 10));
        Collections.sort(userNumbers);
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        
        Lotto userLotto = new Lotto(userNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 10);

        LottoStandard result = lottoCheck.checkWinning(userLotto, winning);

        assertThat(result).isEqualTo(LottoStandard.SECOND);
    }

    @DisplayName("번호가 일치하지만 보너스 번호는 일치하지 않으면 3등을 반환한다")
    @Test
    void 번호_5개_일치하지만_보너스_불일치하면_3등을_반환한다() {
        List<Integer> userNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 10));
        Collections.sort(userNumbers);
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        
        Lotto userLotto = new Lotto(userNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        LottoStandard result = lottoCheck.checkWinning(userLotto, winning);

        assertThat(result).isEqualTo(LottoStandard.THIRD);
    }

    @DisplayName("번호가 4개개 일치하면 4등을 반환한다")
    @Test
    void 번호_4개_일치하면_4등을_반환한다() {
        List<Integer> userNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 9, 10));
        Collections.sort(userNumbers);
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        
        Lotto userLotto = new Lotto(userNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        LottoStandard result = lottoCheck.checkWinning(userLotto, winning);

        assertThat(result).isEqualTo(LottoStandard.FOURTH);
    }

    @DisplayName("번호가 3개 일치하면 5등을 반환한다")
    @Test
    void 번호_3개_일치하면_5등을_반환한다() {
        List<Integer> userNumbers = new ArrayList<>(List.of(1, 2, 3, 8, 9, 10));
        Collections.sort(userNumbers);
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        
        Lotto userLotto = new Lotto(userNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        LottoStandard result = lottoCheck.checkWinning(userLotto, winning);

        assertThat(result).isEqualTo(LottoStandard.FIFTH);
    }

    @DisplayName("번호가 2개 이하로 일치하면 NONE을 반환한다")
    @Test
    void 번호_2개_이하_일치하면_NONE을_반환한다() {
        List<Integer> userNumbers1 = new ArrayList<>(List.of(1, 2, 8, 9, 10, 11));
        Collections.sort(userNumbers1);
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        
        Lotto userLotto1 = new Lotto(userNumbers1);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        LottoStandard result1 = lottoCheck.checkWinning(userLotto1, winning);

        assertThat(result1).isEqualTo(LottoStandard.NONE);

        List<Integer> userNumbers2 = new ArrayList<>(List.of(1, 8, 9, 10, 11, 12));
        Collections.sort(userNumbers2);
        Lotto userLotto2 = new Lotto(userNumbers2);

        LottoStandard result2 = lottoCheck.checkWinning(userLotto2, winning);

        assertThat(result2).isEqualTo(LottoStandard.NONE);

        List<Integer> userNumbers3 = new ArrayList<>(List.of(7, 8, 9, 10, 11, 12));
        Collections.sort(userNumbers3);
        Lotto userLotto3 = new Lotto(userNumbers3);

        LottoStandard result3 = lottoCheck.checkWinning(userLotto3, winning);

        assertThat(result3).isEqualTo(LottoStandard.NONE);
    }

    @DisplayName("큰 숫자 범위에서의 테스트트")
    @Test
    void 큰_숫자_범위_테스트() {
        List<Integer> userNumbers = new ArrayList<>(List.of(40, 41, 42, 43, 44, 45));
        Collections.sort(userNumbers);
        List<Integer> winningNumbers = new ArrayList<>(List.of(40, 41, 42, 43, 44, 1));
        Collections.sort(winningNumbers);
        
        Lotto userLotto = new Lotto(userNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 45);

        LottoStandard result = lottoCheck.checkWinning(userLotto, winning);

        assertThat(result).isEqualTo(LottoStandard.SECOND);
    }
}
