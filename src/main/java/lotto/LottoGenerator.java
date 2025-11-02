package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LottoGenerator {

    public static Lotto generate() {
        List<Integer> numbers = generateNumbers();

        return new Lotto(numbers);
    }

    private static List<Integer> generateNumbers() {
        List<Integer> numbers = new ArrayList<>(Randoms.pickUniqueNumbersInRange(1, 45, 6));
        Collections.sort(numbers);

        return numbers;
    }
}
