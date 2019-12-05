package app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BruteForcer {
    private int lowerLimit;
    private int upperLimit;
    private int counter1 = 0;
    private int counter2 = 0;
    private List<Integer> rule1CompliantPasscodes;

    private List<Integer> rule2CompliantPasscodes;

    public BruteForcer(int lowerLimit, int upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        rule1CompliantPasscodes = new ArrayList<>();
        rule2CompliantPasscodes = new ArrayList<>();
    }
    /**
     * Rules:
     * 1) At least 1 double digit
     * 2) Only ascending digits
     */
    public void findCompliantPasswords() {
        IntStream.rangeClosed(lowerLimit, upperLimit).forEach(this::match);
        rule1CompliantPasscodes.forEach(this::matchPart2);
    }

    private void matchPart2(int i) {
        String password = String.valueOf(i);
        char[] digits = password.toCharArray();
        boolean hasTriple = false;
        boolean hasDifferentDouble = false;
        int tripleValue = -1;
        for (int j = 0; j < digits.length; j++) {
            if (j == digits.length -1) {
                break;
            }
            // Check for triple digits
            if (digits[j]==digits[j+1]) {
                if (j + 2 < digits.length) {
                    if (digits[j] == digits[j + 2]) {
                        hasTriple = true;
                        tripleValue = digits[j];
                    }
                }
                if (digits[j] != tripleValue) {
                    hasDifferentDouble = true;
                }
            }
        }
        if (!hasTriple || hasDifferentDouble) {
            counter2++;
            rule2CompliantPasscodes.add(i);
        }
    }

    private void match(int i) {
        // Check for double digits
        String password = String.valueOf(i);
        char[] digits = password.toCharArray();
        boolean hasDouble = false;
        boolean ascending = true;
        for (int j = 0; j < digits.length; j++) {
            if (j == digits.length -1) {
                break;
            }
            // Check for double digits
            if (digits[j] == digits[j + 1]) {
                hasDouble = true;
            }
            // Test of ascendicity (?)
            int o = digits[j];
            int p = digits[j + 1];
            if (o - p > 0) {
                ascending = false;
            }
        }
        if (hasDouble && ascending) {
            counter1++;
            rule1CompliantPasscodes.add(i);
        }
    }

    public List<Integer> getRule1CompliantPasscodes() {
        return rule1CompliantPasscodes;
    }

    public List<Integer> getRule2CompliantPasscodes() {
        return rule2CompliantPasscodes;
    }

    public int getCounter1() {
        return counter1;
    }

    public int getCounter2() {
        return counter2;
    }
}
