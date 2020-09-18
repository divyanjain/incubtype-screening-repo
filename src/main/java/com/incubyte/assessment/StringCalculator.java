package com.incubyte.assessment;

import java.util.ArrayList;
import java.util.List;

public class StringCalculator {

    public int add(String numbers) throws StringCalculatorException {
        int sum = 0;
        if (numbers == null || numbers.isEmpty()) {
            return sum;
        }

        List<Integer> list = new ArrayList<Integer>();

        list.addAll(parseInputString(numbers));

        for (Integer item : list) {
            sum += item;
        }
        return sum;
    }

    private List<Integer> parseInputString(String numbers) throws StringCalculatorException {
        List<Integer> list = new ArrayList<Integer>();
        if (numbers.matches(".*\\n")) {
            throw new StringCalculatorException("New line character not allowed at the end.");
        }
        String[] inputArray = numbers.split("[\n|,]");

        for (String input : inputArray) {
            if (input.isEmpty()) {
                list.add(0);
            }
            try {
                list.add(Integer.parseInt(input));
            } catch (NumberFormatException ex) {
                throw new StringCalculatorException(ex);
            }

        }
        return list;
    }
}
