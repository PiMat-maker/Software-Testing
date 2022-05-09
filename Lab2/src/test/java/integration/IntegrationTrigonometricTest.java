package integration;

import functions.trigonometrics.Trigonometric;
import functions.trigonometrics.TrigonometricBasis;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTrigonometricTest {

    private static Trigonometric trigonometric;

    @BeforeAll
    static void setup(){
        TrigonometricBasis trigonometricBasis = mock(TrigonometricBasis.class);

        when(trigonometricBasis.sin(0)).thenReturn(0.0);
        when(trigonometricBasis.sin(Math.PI)).thenReturn(0.0);
        when(trigonometricBasis.sin(Math.PI / 2)).thenReturn(1.0);
        when(trigonometricBasis.sin(-Math.PI / 2)).thenReturn(-1.0);
        when(trigonometricBasis.sin(-Math.PI / 4)).thenReturn(-0.7071);

        when(trigonometricBasis.sin(Math.PI / 8)).thenReturn(0.382683432);
        when(trigonometricBasis.sin(Math.PI / 6)).thenReturn(0.5);
        when(trigonometricBasis.sin(Math.PI / 12)).thenReturn(0.258819045);

        when(trigonometricBasis.sin(Math.PI / 4)).thenReturn(Math.sqrt(2) / 2);
        when(trigonometricBasis.sin(Math.PI / 3)).thenReturn(Math.sqrt(3) / 2);

        trigonometric = new Trigonometric(trigonometricBasis);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI / 4, -Math.PI / 2, Math.PI / 2, Math.PI / 6})
    public void checkSin(double angle) {
        double epsilon = 0.01;
        assertEquals(Math.sin(angle), trigonometric.sin(angle), epsilon);
    }
}
