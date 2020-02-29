package com.algos.mitch;

import org.junit.Before;
import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

/* For primitives:
int[] myIntArray = new int[3];
int[] myIntArray = {1, 2, 3};
int[] myIntArray = new int[]{1, 2, 3};

String[] myStringArray = new String[3];
String[] myStringArray = {"a", "b", "c"};
String[] myStringArray = new String[]{"a", "b", "c"};
* */

public class OddOrEvenJavaTest {
    OddOrEvenJava subject;
    int[] inputArray;

    @Before
    public void setUp() throws Exception {
        subject = new OddOrEvenJava();
    }

    @Test
    public void oddOrEvenSum_success_shouldReturnStringValueOfOddOrEvenBasedOffSum() {
        int[] inputArray;
        inputArray = new int[]{1,3,5,7};

        String actual = subject.oddOrEvenSum(inputArray);

        String expected = "even";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void oddOrEvenSum_failure_shouldReturnStringValueOfOddOrEvenBasedOffSum() {
        int[] inputArray;
        inputArray = new int[]{2,3,5,7};

        String actual = subject.oddOrEvenSum(inputArray);

        String expected = "odd";
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void checkEvenOr_success_shouldReturnTrueIfCountIsEven() {
        int inputCount = 16;

        boolean actual = subject.checkEven(inputCount);

        assertThat(actual).isTrue();
    }

    @Test
    public void checkEvenOr_failure_shouldReturnFalseIfCountIsOdd() {
        int inputCount = 17;

        boolean actual = subject.checkEven(inputCount);

        assertThat(actual).isFalse();
    }
}