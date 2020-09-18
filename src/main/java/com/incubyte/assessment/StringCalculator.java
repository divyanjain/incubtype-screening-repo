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

        try {
            list.addAll(parseInputString(numbers));
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Integer item : list) {
            sum += item;
        }
        return sum;
    }

    private List<Integer> parseInputString(String numbers) throws StringCalculatorException {
        // Commenting below. The restrictions for 2 numbers is not needed anymore as of
        // Req#2
        /*
         * if(inputArray.length>2) { throw new
         * StringCalculatorException("Input length is restricted to max 2 comma separated numbers"
         * ); }
         */

        List<Integer> list = new ArrayList<Integer>();
        String[] inputArray = numbers.split("[\n|,]");

        for (String input : inputArray) {
            if (input.isEmpty()) {
                throw new StringCalculatorException("Empty strings not allowed");
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
