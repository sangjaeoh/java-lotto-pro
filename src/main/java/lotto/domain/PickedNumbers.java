package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class PickedNumbers {

    public static final int SIZE = 6;

    private static final int NUMBER_NOT_CONTAINED = 0;
    private static final int NUMBER_CONTAINED = 1;
    private static final String DELIMITER = ",";

    private final List<Number> pickedNumbers;

    public PickedNumbers(List<Number> pickedNumbers) {
        validateSize(pickedNumbers);
        validateDuplicates(pickedNumbers);
        this.pickedNumbers = new ArrayList<>(pickedNumbers);
    }

    public static PickedNumbers of(final String winningNumbers) {
        return Arrays.stream(winningNumbers.split(DELIMITER))
            .map(Number::new)
            .collect(Collectors.collectingAndThen(Collectors.toList(), PickedNumbers::new));
    }

    private void validateSize(final List<Number> numbers) {
        if (numbers.size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 6개의 숫자로 생성할 수 있습니다.");
        }
    }

    private void validateDuplicates(final List<Number> numbers) {
        if (new HashSet<>(numbers).size() != SIZE) {
            throw new IllegalArgumentException("로또 번호는 중복 선택할 수 없습니다.");
        }
    }

    public int size() {
        return pickedNumbers.size();
    }

    public int count(final PickedNumbers other) {
        int numberOfMatchingNumbers = 0;
        for (int i = 0; i < pickedNumbers.size(); i++) {
            numberOfMatchingNumbers += countHelper(other.pickedNumbers.get(i));
        }
        return numberOfMatchingNumbers;
    }

    private int countHelper(final Number number) {
        return contains(number) ? NUMBER_CONTAINED : NUMBER_NOT_CONTAINED;
    }

    public boolean contains(final Number number) {
        return pickedNumbers.contains(number);
    }

    public String asString() {
        return pickedNumbers
            .stream()
            .map(Number::getValue)
            .collect(Collectors.toList())
            .toString();
    }
}