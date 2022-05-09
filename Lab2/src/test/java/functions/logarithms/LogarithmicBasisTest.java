package functions.logarithms;

import exceptions.LogarithmicArgumentException;
import exceptions.TooBigPrecisionException;
import logs.CsvLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LogarithmicBasisTest {

    private LogarithmicBasis logarithmicBasis;
    private static final double epsilon = 0.01;

    @BeforeEach
    public void setup() throws TooBigPrecisionException {
        logarithmicBasis = new LogarithmicBasis(epsilon);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.12, 1.41, 0.51, 0.25578, 2, 3, 5, 10})
    public void checkRandomNumbersInDefinitionScope(double x) throws LogarithmicArgumentException{
        assertEquals(Math.log(x), logarithmicBasis.ln(x), epsilon);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-12, -134.41, -41.51, -239.25578})
    public void checkRandomNegatives(double x) {
        Exception actualException = assertThrows(LogarithmicArgumentException.class, () -> logarithmicBasis.ln(x));

        String expectedMessage = "Argument should be positive";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.01, -0.00001, -0.00000001, -0.000000000000001, 0})
    public void checkNegativesCloseToZero(double x) {
        Exception actualException = assertThrows(LogarithmicArgumentException.class, () -> logarithmicBasis.ln(x));

        String expectedMessage = "Argument should be positive";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00000000000000001})
    public void checkTooBigPrecisionException(double epsilon){
        Exception actualException = assertThrows(TooBigPrecisionException.class, () -> new LogarithmicBasis(epsilon));

        String expectedMessage = "Precision should be maximum 0.1^15";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @AfterAll
    public static void logLn() throws TooBigPrecisionException {
        List<double[]> results = new ArrayList<>();
        LogarithmicBasis logarithmicBasis1 = new LogarithmicBasis(epsilon);

        for (double i = 0.1; i < 10; i += 0.1){
            try {
                results.add(new double[]{i, logarithmicBasis1.ln(i)});
            } catch (LogarithmicArgumentException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/ln.csv", results);
        logger.log();
    }
}