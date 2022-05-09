package functions.logarithms;

import exceptions.LogarithmicArgumentException;
import exceptions.LogarithmicBaseException;

public class Logarithmic {

    private final LogarithmicBasis logarithmicBasis;

    public Logarithmic(LogarithmicBasis logarithmicBasis){
        this.logarithmicBasis = logarithmicBasis;
    }

    public double log(double base, double x) throws LogarithmicBaseException, LogarithmicArgumentException {
        if (base <= 0 || base == 1){
            throw new LogarithmicBaseException("Base should be not positive or equal to 1");
        }

        if (x <= 0){
            throw new LogarithmicArgumentException("Argument should be positive");
        }

        return logarithmicBasis.ln(x) / logarithmicBasis.ln(base);
    }

    public double ln(double x) throws LogarithmicArgumentException {
        if (x <= 0){
            throw new LogarithmicArgumentException("Argument should be positive");
        }

        return logarithmicBasis.ln(x);
    }
}
