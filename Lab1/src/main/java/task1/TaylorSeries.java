package task1;

public class TaylorSeries {

    private final int N;

    public TaylorSeries(int n){
        this.N = n;
    }

    public double cos(double angle) throws SmallNException{
        double result = 0;


        try {
            if (N < 3){
                throw new SmallNException("N should be more than 2");
            }
            for (int i = 0; i < N; ++i) {
                if (i > 100) throw new BigNException("Too big N");
                double step_result = Math.pow(-1, i) * Math.pow(angle, 2 * i);
                for (int j = 2; j <= 2 * i; ++j) {
                    step_result /= j;
                }
                result += step_result;
            }
        } catch(BigNException ex){
            System.out.println("Could be smaller precision");
            return result;
        }

        return result;
    }
}
