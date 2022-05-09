package functions.logarithms;

import exceptions.LogarithmicArgumentException;
import exceptions.TooBigPrecisionException;

public class LogarithmicBasis {

    private final double EPSILON;
    private static final double NULL_BOUND = 0.000000000000001;

    public LogarithmicBasis(double epsilon) throws TooBigPrecisionException {
        if (epsilon < NULL_BOUND){
            throw new TooBigPrecisionException("Precision should be maximum 0.1^15");
        }
        this.EPSILON = epsilon;
    }

    public double ln(double x) throws LogarithmicArgumentException {
        if (x <= 0){
            throw new LogarithmicArgumentException("Argument should be positive");
        }

        double result = (x - 1) / (x + 1);
        double current_epsilon = 1;

        int i = 3;
        while (2 * current_epsilon >= EPSILON * 0.001){
            double step_result = Math.pow((x - 1) / (x + 1), i) / i;
            current_epsilon = Math.abs(step_result);
            result += step_result;
            i += 2;
        }

        return 2 * result;
    }
}
