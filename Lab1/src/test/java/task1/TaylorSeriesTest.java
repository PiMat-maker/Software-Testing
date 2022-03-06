package task1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TaylorSeriesTest{

    private TaylorSeries nTaylorSeries;
    private final double precision = 0.01;

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI - 0.06, Math.PI - 0.15, Math.PI + 0.06, Math.PI + 0.15})
    public void checkCloseToNegativeOneCos(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 + 0.06, Math.PI / 2 + 0.15, -(Math.PI / 2 + 0.06), -(Math.PI / 2 + 0.15)})
    public void checkCloseToZeroCosLeftPart(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2 - 0.06, Math.PI / 2 - 0.15, -(Math.PI / 2 - 0.06), -(Math.PI / 2 - 0.15)})
    public void checkCloseToZeroCosRightPart(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.06, 0.15, -0.06, -0.15})
    public void checkCloseToPositiveOneCos(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI, Math.PI + 0.00000001,  Math.PI - 0.00000001})
    public void checkPi(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI / 2, Math.PI / 2 + 0.00000001,  Math.PI / 2 - 0.00000001,
            -Math.PI / 2, -Math.PI / 2 + 0.00000001, -Math.PI / 2 - 0.00000001})
    public void checkZeroCos(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.00000001, -0.00000001})
    public void checkZero(double angle) throws SmallNException {
        nTaylorSeries = new TaylorSeries(1200);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
        nTaylorSeries = new TaylorSeries(10);
        assertEquals(Math.cos(angle), nTaylorSeries.cos(angle), precision);
    }

    @ParameterizedTest
    @ValueSource(ints = {-5, 2})
    public void checkSmallNException(int n){
        nTaylorSeries = new TaylorSeries(n);
        Exception actualException = assertThrows(SmallNException.class, () -> nTaylorSeries.cos(0));

        String expectedMessage = "N should be more than 2";
        assertTrue(actualException.getMessage().contains(expectedMessage));
    }
}
