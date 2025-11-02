package lotto;

public class LottoCheck {
    public static LottoStandard checkWinning(Lotto user, LottoWinning win) {
        int mainMatches = findMainMatches(user, win);
        boolean bonusMatch = isBonusMatching(user, win);

        return LottoStandard.findMatches(mainMatches, bonusMatch);
    }

    private static boolean isMatching(int userNumber, int winNumber) {
        return userNumber == winNumber;
    }

    private static void nextStep(Lotto user, LottoWinning win, int[] indices) {
        int currentUserValue = user.getNumbers().get(indices[0]);
        int currentWinValue = win.getWinningNumbers().get(indices[1]);

        if (currentUserValue > currentWinValue) { 
            indices[1]++; 
        }
        if (currentUserValue < currentWinValue) { 
            indices[0]++; 
        }
        if (currentUserValue == currentWinValue) {
            indices[1]++;
            indices[0]++;
        }
    }

    private static int findMainMatches(Lotto user, LottoWinning win) {
        int matches = 0;
        int[] indices = {0, 0};

        while (0 <= indices[0] && indices[0] < 6 && 0 <= indices[1] && indices[1] < 6) {
            if (isMatching(user.getNumbers().get(indices[0]), win.getWinningNumbers().get(indices[1]))) {
                matches++;
            }

            nextStep(user, win, indices);
        }

        return matches;
    }

    private static boolean isBonusMatching(Lotto user, LottoWinning win) {
        return user.getNumbers().contains(win.getBonusNumber());
    }
}
