package integration;

import exceptions.LogarithmicArgumentException;
import exceptions.LogarithmicBaseException;
import functions.logarithms.Logarithmic;
import functions.logarithms.LogarithmicBasis;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IntegrationLogarithmicTest {

    private static Logarithmic logarithmic;
    private final double EPSILON = 0.01;

    @BeforeAll
    static void setup(){
        LogarithmicBasis logarithmicBasis = mock(LogarithmicBasis.class);
        double x = 5;

        try{
            when(logarithmicBasis.ln(10)).thenReturn(2.30258509);
            when(logarithmicBasis.ln(x)).thenReturn(1.60943791);
            when(logarithmicBasis.ln(2)).thenReturn(0.693147181);
            when(logarithmicBasis.ln(3)).thenReturn(1.09861229);

        } catch (LogarithmicArgumentException ex){
            ex.printStackTrace();
        }

        logarithmic = new Logarithmic(logarithmicBasis);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 3, 5, 10})
    public void checkLog(double x) throws LogarithmicArgumentException, LogarithmicBaseException {
        assertEquals(Math.log(x) / Math.log(2), logarithmic.log(2, x), EPSILON);
        assertEquals(Math.log(x) / Math.log(3), logarithmic.log(3, x), EPSILON);
        assertEquals(Math.log(x) / Math.log(5), logarithmic.log(5, x), EPSILON);
        assertEquals(Math.log(x) / Math.log(10), logarithmic.log(10, x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {2, 3, 5, 10})
    public void checkLn(double x) throws LogarithmicArgumentException {
        assertEquals(Math.log(x), logarithmic.ln(x), EPSILON);
    }
}
