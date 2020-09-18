package com.incubyte.assessment;

public class StringCalculator {

    public int add(String numbers) throws StringCalculatorException {
        int sum = 0;
        if (numbers == null || numbers.isEmpty()) {
            return sum;
        }

        String[] inputArray = numbers.split(",");
        
        if(inputArray.length>2) {
            throw new StringCalculatorException("Input length is restricted to max 2 comma separated numbers");
        }
        for (String input : inputArray) {
            if (!input.isEmpty()) {
                try {
                    sum += Integer.parseInt(input);
                } catch (NumberFormatException ex) {
                    throw new StringCalculatorException(ex);
                }

            }
        }
        return sum;
    }
}
