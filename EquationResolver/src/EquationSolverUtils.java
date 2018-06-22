public class EquationSolverUtils {

    public static double round(double value, int places)throws IllegalArgumentException{
        if(places < 0) throw new IllegalArgumentException("Number of places to round can not be a negative");

        long factor =(long) Math.pow(10, places);

        value = value * factor;

        long rounded = Math.round(value);

        return (double)rounded / factor;
    }
}
