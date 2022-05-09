package functions.trigonometrics;

public class Trigonometric {

    private final TrigonometricBasis trigonometricBasis;

    public Trigonometric(TrigonometricBasis trigonometricBasis){
        this.trigonometricBasis = trigonometricBasis;
    }

    public double sin(double x){

        return trigonometricBasis.sin(x);
    }
}