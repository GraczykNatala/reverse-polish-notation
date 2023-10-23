package pl.graczyk;

import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OnpTest extends TestCase {

    @Test
    @DisplayName("Basic expression")
    public void testCalcOnp() {
        Onp onpCalculator = new Onp("2+2");
        String expectedOnpResult = "22+";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }

    @Test
    @DisplayName("Expression with brackets")
    public void testCalcOnp2() {
        Onp onpCalculator = new Onp("(1+2)*3)");
        String expectedOnpResult = "12+3*";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }
    @Test
    @DisplayName("Casual expression")
    public void testCalcOnp3() {
        Onp onpCalculator = new Onp("(1+2)*3+4*1");
        String expectedOnpResult = "12+3*41*+";
        String result = onpCalculator.calcOnp();
        assertEquals(expectedOnpResult, result);
    }

}
