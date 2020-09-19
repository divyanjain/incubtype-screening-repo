package com.incubyte.assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private static final Pattern DELIMITER_PATTERN = Pattern.compile("((//(\\[?(.+)\\]?)*\n)?)((.+|\n)*)");

    private List<Integer> parseInputString(String numbers) throws StringCalculatorException {
        String delimiterString = ",";
        String inputString = "";

        Matcher delimiterMatcher = DELIMITER_PATTERN.matcher(numbers);
        if (delimiterMatcher.find()) {
            String delimiterGroup = delimiterMatcher.group(3);
            if (delimiterGroup != null && !delimiterGroup.isEmpty()) {
                delimiterString = delimiterString + "|"
                        + (delimiterGroup.replace("][", "|").replace("[", "").replace("]", ""));
            }

            inputString = delimiterMatcher.group(5);
            Pattern p = Pattern.compile(".+([" + delimiterString + "][(\r\n)|(\n)])");
            Matcher m = p.matcher(inputString);
            if (m.find()) {
                throw new StringCalculatorException("New line character not allowed at the end.");
            }
        }

        return parseInputString(inputString, delimiterString);
    }

    private List<Integer> parseInputString(String inputString, String pipeSeparatedDelimiters)
            throws StringCalculatorException {
        String[] inputArray = inputString.split("[\n|" + pipeSeparatedDelimiters + "]");
        StringBuilder negativeNumbers = new StringBuilder();
        List<Integer> list = new ArrayList<Integer>();
        for (String input : inputArray) {
            if (input.isEmpty()) {
                continue;
            }
            try {
                int number = Integer.parseInt(input);
                if (number < 0) {
                    negativeNumbers.append((negativeNumbers.toString().isEmpty() ? "" : " , "));
                    negativeNumbers.append(number);
                } else if (number > 1000) {
                    System.out.println("Ignoring numbers greater than 1000");
                } else {
                    list.add(number);
                }
            } catch (NumberFormatException ex) {
                throw new StringCalculatorException(ex);
            }
        }

        if (!negativeNumbers.toString().isEmpty()) {
            throw new StringCalculatorException("negatives not allowed - [" + negativeNumbers + "]");
        }

        return list;
    }
}
