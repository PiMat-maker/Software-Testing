package functions.trigonometrics;

import exceptions.TooBigPrecisionException;
import logs.CsvLogger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrigonometricBasisTest {

    public void checker(double angle, double epsilon) throws TooBigPrecisionException {
        TrigonometricBasis trigonometricBasis = new TrigonometricBasis(epsilon);
        assertEquals(Math.sin(angle), trigonometricBasis.sin(angle), epsilon);
    }

    @ParameterizedTest
    @ValueSource(doubles = {- Math.PI / 2 - 0.06, - Math.PI / 2 - 0.15,  - Math.PI / 2 + 0.06, - Math.PI / 2 + 0.15})
    public void checkCloseToNegativeOneSin(double angle) throws TooBigPrecisionException {
        checker(angle, 0.01);
        checker(angle, 0.00000000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.06, -0.15, Math.PI + 0.06, Math.PI + 0.15})
    public void checkCloseToZeroSinBottom(double angle) throws TooBigPrecisionException {
        checker(angle, 0.01);
        checker(angle, 0.00000000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.06, 0.15, Math.PI - 0.06, Math.PI - 0.15})
    public void checkCloseToZeroSinPositives(double angle) throws TooBigPrecisionException {
        checker(angle, 0.01);
        checker(angle, 0.00000000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 - 0.06, Math.PI / 2 - 0.15,  Math.PI / 2 + 0.06, Math.PI / 2 + 0.15})
    public void checkCloseToPositiveOneSin(double angle) throws TooBigPrecisionException {
        checker(angle, 0.01);
        checker(angle, 0.00000000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Math.PI / 2 + 0.00000001,  Math.PI / 2 - 0.00000001})
    public void checkHalfPi(double angle) throws TooBigPrecisionException {
        checker(angle, 0.01);
        checker(angle, 0.00000000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.00000001,  - 0.00000001,
            Math.PI, Math.PI + 0.00000001, Math.PI - 0.00000001})
    public void checkZeroSin(double angle) throws TooBigPrecisionException {
        checker(angle, 0.01);
        checker(angle, 0.00000000001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.00000000000000001})
    public void checkTooBigPrecisionException(double epsilon){
        Exception actualException = assertThrows(TooBigPrecisionException.class, () -> new TrigonometricBasis(epsilon));

        String expectedMessage = "Precision should be maximum 0.1^15";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }

    @AfterAll
    public static void logSin() throws TooBigPrecisionException {
        List<double[]> results = new ArrayList<>();
        TrigonometricBasis trigonometricBasis = new TrigonometricBasis(0.01);

        for (double i = 0; i < 7.5; i += 0.1){
            results.add(new double[]{i, trigonometricBasis.sin(i)});
        }

        CsvLogger logger = new CsvLogger("csv_output/sin.csv", results);
        logger.log();
    }
}
