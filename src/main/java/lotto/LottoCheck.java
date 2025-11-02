package lotto;

public class LottoCheck {
    private int userIndex;
    private int winIndex;

    public LottoStandard checkWinning(Lotto user, LottoWinning win) {
        userIndex = 0;
        winIndex = 0;
        int mainMatches = findMainMatches(user, win);
        boolean bonusMatch = isBonusMatching(user, win);

        return LottoStandard.findMatches(mainMatches, bonusMatch);
    }

    private boolean isMatching(int userNumber, int winNumber) {
        return userNumber == winNumber;
    }

    private void nextStep(Lotto user, LottoWinning win) {
        int currentUserValue = user.getNumbers().get(userIndex);
        int currentWinValue = win.getWinningNumbers().get(winIndex);

        if (currentUserValue > currentWinValue) { winIndex++; }
        if (currentUserValue < currentWinValue) { userIndex++; }
        if (currentUserValue == currentWinValue) {
            winIndex++;
            userIndex++;
        }
    }

    private int findMainMatches(Lotto user, LottoWinning win) {
        int matches = 0;

        while (0 <= userIndex && userIndex < 6 && 0 <= winIndex && winIndex < 6) {
            if (isMatching(user.getNumbers().get(userIndex), win.getWinningNumbers().get(winIndex))) {
                matches++;
            }

            nextStep(user, win);
        }

        return matches;
    }

    private boolean isBonusMatching(Lotto user, LottoWinning win) {
        return user.getNumbers().contains(win.getBonusNumber());
    }
}
