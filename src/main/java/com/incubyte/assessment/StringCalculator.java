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

    private List<Integer> parseInputString(String numbers) throws StringCalculatorException {

        List<Integer> list = new ArrayList<Integer>();
        String delimiterString = ",";
        String inputString = "";

        Pattern delimiterPattern = Pattern.compile("((//(.+)\n)?)((.+|\n)*)");
        Matcher delimiterMatcher = delimiterPattern.matcher(numbers);
        if (delimiterMatcher.find()) {
            String delimiterGroup = delimiterMatcher.group(3);
            if (delimiterGroup != null && !delimiterGroup.isEmpty()) {
                delimiterString = delimiterString + "|" + delimiterGroup;
            }

            inputString = delimiterMatcher.group(4);
            Pattern p = Pattern.compile(".+([" + delimiterString + "][(\r\n)|(\n)])");
            Matcher m = p.matcher(inputString);
            if (m.find()) {
                throw new StringCalculatorException("New line character not allowed at the end.");
            }
        }

        String[] inputArray = inputString.split("[\n|" + delimiterString + "]");
        if (inputArray[inputArray.length - 1].isEmpty()) {
            throw new StringCalculatorException("New line character not allowed at the end.");
        }
        for (String input : inputArray) {
            if (input.isEmpty()) {
                list.add(0);
                continue;
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
