package systems;

import exceptions.LogarithmicArgumentException;
import exceptions.LogarithmicBaseException;
import functions.logarithms.Logarithmic;
import functions.trigonometrics.Trigonometric;

public class SystemOfFunctions {

    private final Logarithmic LOGARITHMIC;
    private final Trigonometric TRIGONOMETRIC;

    public SystemOfFunctions(Logarithmic logarithmic, Trigonometric trigonometric){
        this.LOGARITHMIC = logarithmic;
        this.TRIGONOMETRIC = trigonometric;
    }

    public double countSystem(double x) throws LogarithmicArgumentException, LogarithmicBaseException, ArithmeticException {
        return x <= 0 ? TRIGONOMETRIC.sin(x) : (((LOGARITHMIC.log(10, x) / LOGARITHMIC.ln(x) / LOGARITHMIC.log(2, x)) +
                LOGARITHMIC.log(3, x) + LOGARITHMIC.ln(x)) + LOGARITHMIC.log(10, x)) + 2 * LOGARITHMIC.log(3, x);
    }

}
