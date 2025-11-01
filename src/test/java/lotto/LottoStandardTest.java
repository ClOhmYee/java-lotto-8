package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LottoStandardTest {

    @DisplayName("6개 번호가 일치하면 1등을 반환한다")
    @Test
    void 일치하는_번호가_6개면_1등을_반환한다() {
        LottoStandard result = LottoStandard.findMatches(6, false);
        assertThat(result).isEqualTo(LottoStandard.FIRST);
    }

    @DisplayName("5개 번호 일치하고 보너스 번호도 일치하면 2등을 반환한다")
    @Test
    void 일치하는_번호가_5개이고_보너스_일치하면_2등을_반환한다() {
        LottoStandard result = LottoStandard.findMatches(5, true);
        assertThat(result).isEqualTo(LottoStandard.SECOND);
    }

    @DisplayName("5개 번호 일치하지만 보너스 번호는 일치하지 않으면 3등을 반환한다")
    @Test
    void 일치하는_번호가_5개이고_보너스_불일치하면_3등을_반환한다() {
        LottoStandard result = LottoStandard.findMatches(5, false);
        assertThat(result).isEqualTo(LottoStandard.THIRD);
    }

    @DisplayName("4개 번호가 일치하면 4등을 반환한다")
    @Test
    void 일치하는_번호가_4개면_4등을_반환한다() {
        LottoStandard result = LottoStandard.findMatches(4, false);
        assertThat(result).isEqualTo(LottoStandard.FOURTH);
    }

    @DisplayName("3개 번호가 일치하면 5등을 반환한다")
    @Test
    void 일치하는_번호가_3개면_5등을_반환한다() {
        LottoStandard result = LottoStandard.findMatches(3, false);
        assertThat(result).isEqualTo(LottoStandard.FIFTH);
    }

    @DisplayName("2개 이하 번호가 일치하면 NONE을 반환한다")
    @Test
    void 일치하는_번호가_2개_이하면_NONE을_반환한다() {
        LottoStandard result1 = LottoStandard.findMatches(2, false);
        LottoStandard result2 = LottoStandard.findMatches(1, false);
        LottoStandard result3 = LottoStandard.findMatches(0, false);

        assertThat(result1).isEqualTo(LottoStandard.NONE);
        assertThat(result2).isEqualTo(LottoStandard.NONE);
        assertThat(result3).isEqualTo(LottoStandard.NONE);
    }

    @DisplayName("각 등수의 당첨 조건과 상금이 올바르게 설정되어 있다")
    @Test
    void 각_등수의_당첨_조건과_상금이_올바르다() {
        assertThat(LottoStandard.findMatches(6, false)).isEqualTo(LottoStandard.FIRST);
        assertThat(LottoStandard.findMatches(5, true)).isEqualTo(LottoStandard.SECOND);
        assertThat(LottoStandard.findMatches(5, false)).isEqualTo(LottoStandard.THIRD);
        assertThat(LottoStandard.findMatches(4, false)).isEqualTo(LottoStandard.FOURTH);
        assertThat(LottoStandard.findMatches(3, false)).isEqualTo(LottoStandard.FIFTH);
        assertThat(LottoStandard.findMatches(2, false)).isEqualTo(LottoStandard.NONE);
        assertThat(LottoStandard.findMatches(1, false)).isEqualTo(LottoStandard.NONE);
        assertThat(LottoStandard.findMatches(0, false)).isEqualTo(LottoStandard.NONE);
    }
}
