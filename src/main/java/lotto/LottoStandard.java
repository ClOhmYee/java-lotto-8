package lotto;

// RANK(number of matching numbers, bonus number matching, winning prize)
public enum LottoStandard {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int numberMatches;
    private final boolean bonusMatch;
    private final int prize;

    LottoStandard(int numberMatches, boolean bonusMatch, int prize) {
        this.numberMatches = numberMatches;
        this.bonusMatch = bonusMatch;
        this.prize = prize;
    }

    public static LottoStandard findMatches(int numberMatches, boolean bonusMatch) {
        if (numberMatches == 6) {
            return FIRST;
        }

        if (numberMatches == 5 && bonusMatch) {
            return SECOND;
        }

        if (numberMatches == 5) {
            return THIRD;
        }

        if (numberMatches == 4) {
            return FOURTH;
        }

        if (numberMatches == 3) {
            return FIFTH;
        }

        return NONE;
    }
}
