package com.algos.mitch;

/*
Task:
Given a list of numbers, determine whether the sum of its elements is odd or even.
*/
public class OddOrEvenJava {

    public OddOrEvenJava() { }

    public String oddOrEvenSum(int[] numberArray) {
        int count = 0;

        for (int value : numberArray) {
            count += value;
        }

        if (checkEven(count)) {
            return "even";
        } else {
            return "odd";
        }
    }

    public boolean checkEven(int count) {
        return count % 2 == 0;
    }
}
