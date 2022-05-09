package systems;

import exceptions.LogarithmicArgumentException;
import exceptions.LogarithmicBaseException;
import exceptions.TooBigPrecisionException;
import functions.logarithms.Logarithmic;
import functions.logarithms.LogarithmicBasis;
import functions.trigonometrics.Trigonometric;
import functions.trigonometrics.TrigonometricBasis;
import logs.CsvLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SystemOfFunctionTest {

    private SystemOfFunctions systemOfFunctions;
    private static final double EPSILON = 0.01;

    @BeforeEach
    public void setup() throws TooBigPrecisionException{
        LogarithmicBasis logarithmicBasis = new LogarithmicBasis(EPSILON);
        Logarithmic logarithmic = new Logarithmic(logarithmicBasis);
        TrigonometricBasis trigonometricBasis = new TrigonometricBasis(EPSILON);
        Trigonometric trigonometric = new Trigonometric(trigonometricBasis);
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

    @AfterAll
    public static void logSystemOfFunctionResult() throws TooBigPrecisionException {
        List<double[]> results = new ArrayList<>();
        SystemOfFunctions system = new SystemOfFunctions(new Logarithmic(new LogarithmicBasis(EPSILON)),
                new Trigonometric(new TrigonometricBasis(EPSILON)));

        for (double i = -5; i < 5; i += 0.1){
            try {
                results.add(new double[]{i, system.countSystem(i)});
            } catch (LogarithmicBaseException | LogarithmicArgumentException e) {
                e.printStackTrace();
            }
        }

        CsvLogger logger = new CsvLogger("csv_output/system_result.csv", results);
        logger.log();
    }
}
