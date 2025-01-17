package assessmentcom.incubyte.assessment.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.incubyte.assessment.StringCalculator;
import com.incubyte.assessment.StringCalculatorException;

public class StringCalculatorTest {
    StringCalculator calculator;

    @Before
    public void setUp() throws Exception {
        calculator = new StringCalculator();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEmptyString() {
        try {
            int sum = calculator.add("");
            assertEquals(0, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNullString() {
        try {
            int sum = calculator.add(null);
            assertEquals(0, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommaSeparatedTwoIntegerString() {
        try {
            int sum = calculator.add("1,2");
            assertEquals(3, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommaSeparatedOneIntegerString() {
        try {
            int sum = calculator.add("1");
            assertEquals(1, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOneIntegerAndCommaString() {
        try {
            int sum = calculator.add("1,");
            assertEquals(1, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommaAndOneIntegerString() {
        try {
            int sum = calculator.add(",1");
            assertEquals(1, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInvalidString() {
        Exception ex = null;
        try {
            calculator.add("invalidString");
        } catch (Exception e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().equals("java.lang.NumberFormatException: For input string: \"invalidString\""));
    }

    // Commenting below. The restrictions for 2 numbers is not needed anymore as of
    // Req#2
    /*
     * @Test public void testCommaSeparatedThreeIntegerString() { try { int sum =
     * calculator.add("1,2,3"); } catch (StringCalculatorException e) {
     * assertTrue(e.getMessage().
     * equals("Input length is restricted to max 2 comma separated numbers")); } }
     */

    @Test
    public void testCommaSeparatedThreeIntegerString() {
        try {
            int sum = calculator.add("1,2,3");
            assertEquals(6, sum);
        } catch (StringCalculatorException e) {
        }
    }

    @Test
    public void testCommaSeparatedNumbersWithNewLine() {
        try {
            int sum = calculator.add("1\n2,3");
            assertEquals(6, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCommaSeparatedNumbersWithNewLineAndEmptyString() {
        Exception ex = null;
        try {
            calculator.add("1,\n");
        } catch (StringCalculatorException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().equals("New line character not allowed at the end."));
    }
    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimiter() {
        try {
            int sum = calculator.add("//;\n1,2\n3;4");
            assertEquals(10, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimiterWithVaryingLength() {
        try {
            int sum = calculator.add("//[**]\n1,2\n3**4");
            assertEquals(10, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimitersWithVaryingLengths() {
        try {
            int sum = calculator.add("//[**][;][::]\n1,2\n3**4;7::8\n5");
            assertEquals(30, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimiterAndEmptyString() {
        Exception ex = null;
        try {
            calculator.add("//;\n1,2\n3;4;\n");
        } catch (StringCalculatorException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().equals("New line character not allowed at the end."));
    }
    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimiterAndNegativeNumber() {
        Exception ex = null;
        try {
            calculator.add("//;\n1,2\n3;4;-1");
        } catch (StringCalculatorException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().equals("negatives not allowed - [-1]"));
    }
    

    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimiterAndNegativeNumbers() {
        Exception ex = null;
        try {
            calculator.add("//;\n1,2\n3;4;-1;8;-4;6");
        } catch (StringCalculatorException e) {
            ex = e;
        }
        assertNotNull(ex);
        assertTrue(ex.getMessage().equals("negatives not allowed - [-1 , -4]"));
    }
    
    @Test
    public void testNumbersSeperatedByNewLineAndNonCommaDelimiterAndLargeNumbers() {
        try {
            int sum=calculator.add("//;\n1,2\n3;4;8;6000;6");
            assertEquals(24, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testMultipleDelimiters() {
        try {
            int sum=calculator.add("//[*][%]\n1*2%3");
            assertEquals(6, sum);
        } catch (StringCalculatorException e) {
            e.printStackTrace();
        }
    }

}
