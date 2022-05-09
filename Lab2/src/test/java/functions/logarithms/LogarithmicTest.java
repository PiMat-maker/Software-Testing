package functions.logarithms;

import exceptions.LogarithmicArgumentException;
import exceptions.LogarithmicBaseException;
import exceptions.TooBigPrecisionException;
import logs.CsvLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogarithmicTest {

    private Logarithmic logarithmic;
    private static final double EPSILON = 0.01;

    @BeforeEach
    public void setup() throws TooBigPrecisionException {
        LogarithmicBasis logarithmicBasis = new LogarithmicBasis(EPSILON);
        logarithmic = new Logarithmic(logarithmicBasis);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.12, 1.41, 0.51, 0.25578, 2, 3, 5, 10})
    public void checkRandomNumbersInDefinitionScopeLogFunction(double x) throws LogarithmicArgumentException, LogarithmicBaseException {
        assertEquals(Math.log(x) / Math.log(2), logarithmic.log(2, x), EPSILON);
        assertEquals(Math.log(x) / Math.log(3), logarithmic.log(3, x), EPSILON);
        assertEquals(Math.log(x) / Math.log(10), logarithmic.log(10, x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-12, -134.41, -41.51, -239.25578})
    public void checkRandomNegativesLogFunction(double x) {
        Exception actualException = assertThrows(LogarithmicArgumentException.class, () -> logarithmic.log(2, x));

        String expectedMessage = "Argument should be positive";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -0.00001, -0.00000001, -0.000000000000001, 0})
    public void checkNegativesCloseToZeroLogFunction(double x) {
        Exception actualException = assertThrows(LogarithmicArgumentException.class, () -> logarithmic.log(2, x));

        String expectedMessage = "Argument should be positive";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-3, -1, 0, 1})
    public void checkWrongBasesLogFunction(double base){
        Exception actualException = assertThrows(LogarithmicBaseException.class, () -> logarithmic.log(base, 2));

        String expectedMessage = "Base should be not positive or equal to 1";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.12, 1.41, 0.51, 0.25578, 2, 3, 5, 10})
    public void checkRandomNumbersInDefinitionScopeLnFunction(double x) throws LogarithmicArgumentException {
        assertEquals(Math.log(x), logarithmic.ln(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-12, -134.41, -41.51, -239.25578})
    public void checkRandomNegativesLnFunction(double x) {
        Exception actualException = assertThrows(LogarithmicArgumentException.class, () -> logarithmic.ln(x));

        String expectedMessage = "Argument should be positive";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -0.00001, -0.00000001, -0.000000000000001, 0})
    public void checkNegativesCloseToZeroLnFunction(double x) {
        Exception actualException = assertThrows(LogarithmicArgumentException.class, () -> logarithmic.ln(x));

        String expectedMessage = "Argument should be positive";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @AfterAll
    static void logLogarithm() throws TooBigPrecisionException {
        List<double[]> results = new ArrayList<>();
        Logarithmic logarithmic1 = new Logarithmic(new LogarithmicBasis(EPSILON));
        double base = 4;

        for (double i = 0.1; i < 10; i += 0.1){
            try {
                results.add(new double[]{i, logarithmic1.log(base, i)});
            } catch (LogarithmicArgumentException | LogarithmicBaseException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/logarithm.csv", results);
        logger.log();
    }
}
