package pl.graczyk;

import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OnpTest extends TestCase {

    @Test
    @DisplayName("Basic expression")
    public void testCalcOnp() {
        Onp onpCalculator = new Onp("2+2");
        String expectedOnpResult = "2 2 + ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }

    @Test
    @DisplayName("Expression with brackets")
    public void testCalcOnp2() {
        Onp onpCalculator = new Onp("(1+2)*3)");
        String expectedOnpResult = "1 2 + 3 * ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }
    @Test
    @DisplayName("Casual expression")
    public void testCalcOnp3() {
        Onp onpCalculator = new Onp("(1+2)*3+4*1");
        String expectedOnpResult = "1 2 + 3 * 4 1 * + ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }
    @Test
    @DisplayName("Multidigit numbers")
    public void testCalcOnp4() {
        Onp onpCalculator = new Onp("(11+2)*333+4*12");
        String expectedOnpResult = "11 2 + 333 * 4 12 * + ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }
    @Test
    @DisplayName("Negative numbers")
    public void testCalcOnp5() {
        Onp onpCalculator = new Onp("-1*-3+4*1");
        String expectedOnpResult = "-1 -3 * 4 1 * + ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }
    @Test
    @DisplayName("Negative numbers and brackets")
    public void testCalcOnp6() {
        Onp onpCalculator = new Onp("(-1+-2)*3+4*1");
        String expectedOnpResult = "-1 -2 + 3 * 4 1 * + ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }
    @Test
    @DisplayName("Negative and multidigit numbers and brackets")
    public void testCalcOnp7() {
        Onp onpCalculator = new Onp("(-11+2)*333+4*-12");
        String expectedOnpResult = "-11 2 + 333 * 4 -12 * + ";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }

}
