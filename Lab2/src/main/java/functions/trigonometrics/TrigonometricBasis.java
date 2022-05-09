package functions.trigonometrics;

import exceptions.TooBigPrecisionException;

public class TrigonometricBasis {

    private final double EPSILON;
    private static final double NULL_BOUND = 0.000000000000001;

    public TrigonometricBasis(double epsilon) throws TooBigPrecisionException {
        if (epsilon < NULL_BOUND){
            throw new TooBigPrecisionException("Precision should be maximum 0.1^15");
        }
        this.EPSILON = epsilon;
    }

    public double sin(double x){
        double result = x;
        double current_epsilon = Math.abs(x);

        int i = 1;
        while (current_epsilon >= EPSILON){
            double step_result = Math.pow(-1, i) * Math.pow(x, 2 * i + 1);
            for (int j = 2; j <= 2 * i + 1; ++j) {
                step_result /= j;
            }
            current_epsilon = Math.abs(step_result);
            result += step_result;
            ++i;
        }

        return result;
    }

}
