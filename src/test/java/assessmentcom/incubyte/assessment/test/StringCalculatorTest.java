package assessmentcom.incubyte.assessment.test;

import static org.junit.Assert.assertEquals;
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
        try {
            calculator.add("invalidString");
        } catch (Exception e) {
            assertTrue(e.getCause() instanceof NumberFormatException);
            e.printStackTrace();
        }
    }

}
