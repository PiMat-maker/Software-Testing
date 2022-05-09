package integration;

import exceptions.LogarithmicArgumentException;
import exceptions.LogarithmicBaseException;
import functions.logarithms.Logarithmic;
import functions.trigonometrics.Trigonometric;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import systems.SystemOfFunctions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationSystemTest {

    private static SystemOfFunctions systemOfFunctions;
    private static final double EPSILON = 0.01;

    @BeforeAll
    static void setup(){
        Logarithmic logarithmic = mock(Logarithmic.class);

        try{
            when(logarithmic.ln(10)).thenReturn(2.30258509);
            when(logarithmic.ln(2)).thenReturn(0.693147181);
            when(logarithmic.ln(3)).thenReturn(1.09861229);
            when(logarithmic.ln(12.4)).thenReturn(2.517696472611);
            when(logarithmic.ln(51)).thenReturn(3.9318256327243);
            when(logarithmic.ln(41)).thenReturn(3.7135720667043);
            when(logarithmic.ln(4)).thenReturn(1.3862943611199);
            when(logarithmic.log(2, 12.4)).thenReturn(3.632268);
            when(logarithmic.log(3, 12.4)).thenReturn(2.291706);
            when(logarithmic.log(10, 12.4)).thenReturn(1.093422);
            when(logarithmic.log(2, 51)).thenReturn(5.672425);
            when(logarithmic.log(3, 51)).thenReturn(3.578902);
            when(logarithmic.log(10, 51)).thenReturn(1.70757);
            when(logarithmic.log(2, 41)).thenReturn(5.357552);
            when(logarithmic.log(3, 41)).thenReturn(3.380239);
            when(logarithmic.log(10, 41)).thenReturn(1.612784);
            when(logarithmic.log(2, 4)).thenReturn(2.0);
            when(logarithmic.log(3, 4)).thenReturn(1.26186);
            when(logarithmic.log(10, 4)).thenReturn(0.60206);
        } catch(LogarithmicArgumentException | LogarithmicBaseException ex){
            ex.printStackTrace();
        }

        Trigonometric trigonometric = mock(Trigonometric.class);

        when(trigonometric.sin(0)).thenReturn(0.0);
        when(trigonometric.sin(Math.PI)).thenReturn(0.0);
        when(trigonometric.sin(Math.PI / 2)).thenReturn(1.0);
        when(trigonometric.sin(-Math.PI / 2)).thenReturn(-1.0);
        when(trigonometric.sin(-Math.PI / 4)).thenReturn(-0.7071);

        when(trigonometric.sin(Math.PI / 8)).thenReturn(0.382683432);
        when(trigonometric.sin(Math.PI / 6)).thenReturn(0.5);
        when(trigonometric.sin(-Math.PI / 12)).thenReturn(-0.258819045);

        when(trigonometric.sin(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(trigonometric.sin(-Math.PI / 3)).thenReturn(-Math.sqrt(3) / 2);
        systemOfFunctions = new SystemOfFunctions(logarithmic, trigonometric);
    }

    public double countLog(double base, double x){
        return Math.log(x) / Math.log(base);
    }

    public double countExpectedValue(double x){
        return x <= 0 ? Math.sin(x) : (((countLog(10, x) / Math.log(x) / countLog(2, x)) +
                countLog(3, x) + Math.log(x)) + countLog(10, x)) + countLog(3, Math.pow(x, 2));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI / 2, -Math.PI / 4, -Math.PI / 3, -Math.PI / 12})
    public void checkNegativeX(double x) throws LogarithmicArgumentException, LogarithmicBaseException {
        assertEquals(countExpectedValue(x), systemOfFunctions.countSystem(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {12.4, 51, 41, 4})
    public void checkPositiveX(double x) throws LogarithmicArgumentException, LogarithmicBaseException {
        assertEquals(countExpectedValue(x), systemOfFunctions.countSystem(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1})
    public void checkNan(double x) throws LogarithmicArgumentException, LogarithmicBaseException {
        assertTrue(Double.isNaN(systemOfFunctions.countSystem(x)));
    }
}
