package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoResultTest {

    @DisplayName("같은 등수의 로또가 여러 개일 때 올바르게 합산한다")
    @Test
    void 같은_등수의_로또가_여러_개일_때_올바르게_합산한다() {
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        List<Lotto> userLottos = new ArrayList<>();
        List<Integer> lotto1 = new ArrayList<>(List.of(1, 2, 3, 8, 9, 10));
        Collections.sort(lotto1);
        List<Integer> lotto2 = new ArrayList<>(List.of(1, 2, 3, 11, 12, 13));
        Collections.sort(lotto2);
        List<Integer> lotto3 = new ArrayList<>(List.of(1, 2, 3, 14, 15, 16));
        Collections.sort(lotto3);

        userLottos.add(new Lotto(lotto1));
        userLottos.add(new Lotto(lotto2));
        userLottos.add(new Lotto(lotto3));

        LottoResult lottoResult = new LottoResult(userLottos, winning);
        Map<LottoStandard, Integer> results = lottoResult.allResult();

        assertThat(results.get(LottoStandard.FIFTH)).isEqualTo(3);
    }

    @DisplayName("모든 등수가 포함된 경우 올바르게 합산한다")
    @Test
    void 모든_등수가_포함된_경우_올바르게_합산한다() {
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 10);

        List<Lotto> userLottos = new ArrayList<>();
        List<Integer> first = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(first);
        List<Integer> second = new ArrayList<>(List.of(1, 2, 3, 4, 5, 10));
        Collections.sort(second);
        List<Integer> third = new ArrayList<>(List.of(1, 2, 3, 4, 5, 7));
        Collections.sort(third);
        List<Integer> fourth = new ArrayList<>(List.of(1, 2, 3, 4, 9, 11));
        Collections.sort(fourth);
        List<Integer> fifth = new ArrayList<>(List.of(1, 2, 3, 12, 13, 14));
        Collections.sort(fifth);

        userLottos.add(new Lotto(first));
        userLottos.add(new Lotto(second));
        userLottos.add(new Lotto(third));
        userLottos.add(new Lotto(fourth));
        userLottos.add(new Lotto(fifth));

        LottoResult lottoResult = new LottoResult(userLottos, winning);
        Map<LottoStandard, Integer> results = lottoResult.allResult();

        assertThat(results.get(LottoStandard.FIRST)).isEqualTo(1);
        assertThat(results.get(LottoStandard.SECOND)).isEqualTo(1);
        assertThat(results.get(LottoStandard.THIRD)).isEqualTo(1);
        assertThat(results.get(LottoStandard.FOURTH)).isEqualTo(1);
        assertThat(results.get(LottoStandard.FIFTH)).isEqualTo(1);
    }

    @DisplayName("낙첨 로또들만 있는 경우 NONE만 카운트된다")
    @Test
    void 낙첨_로또들만_있는_경우_NONE만_카운트된다() {
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        List<Lotto> userLottos = new ArrayList<>();
        List<Integer> lotto1 = new ArrayList<>(List.of(10, 11, 12, 13, 14, 15));
        Collections.sort(lotto1);
        List<Integer> lotto2 = new ArrayList<>(List.of(20, 21, 22, 23, 24, 25));
        Collections.sort(lotto2);

        userLottos.add(new Lotto(lotto1));
        userLottos.add(new Lotto(lotto2));

        LottoResult lottoResult = new LottoResult(userLottos, winning);
        Map<LottoStandard, Integer> results = lottoResult.allResult();

        assertThat(results.getOrDefault(LottoStandard.FIRST, 0)).isEqualTo(0);
        assertThat(results.getOrDefault(LottoStandard.SECOND, 0)).isEqualTo(0);
        assertThat(results.getOrDefault(LottoStandard.THIRD, 0)).isEqualTo(0);
        assertThat(results.getOrDefault(LottoStandard.FOURTH, 0)).isEqualTo(0);
        assertThat(results.getOrDefault(LottoStandard.FIFTH, 0)).isEqualTo(0);
    }

    @DisplayName("로또 리스트가 null이면 예외가 발생한다")
    @Test
    void 로또_리스트가_null이면_예외가_발생한다() {
        List<Integer> winningNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(winningNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 7);

        assertThatThrownBy(() -> new LottoResult(null, winning))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 로또 리스트는 null일 수 없습니다.");
    }

    @DisplayName("당첨 번호가 null이면 예외가 발생한다")
    @Test
    void 당첨_번호가_null이면_예외가_발생한다() {
        List<Lotto> userLottos = new ArrayList<>();
        List<Integer> lottoNumbers = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        Collections.sort(lottoNumbers);
        userLottos.add(new Lotto(lottoNumbers));

        assertThatThrownBy(() -> new LottoResult(userLottos, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 null일 수 없습니다.");
    }

    @DisplayName("큰 숫자 범위의 로또들도 올바르게 합산한다")
    @Test
    void 큰_숫자_범위의_로또들도_올바르게_합산한다() {
        List<Integer> winningNumbers = new ArrayList<>(List.of(40, 41, 42, 43, 44, 1));
        Collections.sort(winningNumbers);
        LottoWinning winning = new LottoWinning(winningNumbers, 45);

        List<Lotto> userLottos = new ArrayList<>();
        List<Integer> lotto1 = new ArrayList<>(List.of(40, 41, 42, 43, 44, 45));
        Collections.sort(lotto1);
        List<Integer> lotto2 = new ArrayList<>(List.of(40, 41, 42, 43, 44, 2));
        Collections.sort(lotto2);

        userLottos.add(new Lotto(lotto1));
        userLottos.add(new Lotto(lotto2));

        LottoResult lottoResult = new LottoResult(userLottos, winning);
        Map<LottoStandard, Integer> results = lottoResult.allResult();

        assertThat(results.get(LottoStandard.SECOND)).isEqualTo(1);
        assertThat(results.get(LottoStandard.THIRD)).isEqualTo(1);
    }
}
