import java.util.List;

public class SystemEquation {

    private double[][] equationTerms;
    private double[] solutions;

    public SystemEquation(int nVariables, int nEquations) {
        equationTerms = new double[nVariables][nEquations];
        solutions = new double[nEquations];
    }

    public void setSolutions(List<Double> values) throws ArrayIndexOutOfBoundsException {
        if (values.size() == solutions.length) {
            for (int i = 0; i < values.size(); i++) {
                solutions[i] = values.get(i);
            }
        } else {
            throw new ArrayIndexOutOfBoundsException("The number of solutions provided dont equals with the number of solutions");
        }
    }

    public void setSolutions(double[] values) throws ArrayIndexOutOfBoundsException {
        if (values.length == solutions.length) {
            solutions = values;
        } else {
            throw new ArrayIndexOutOfBoundsException("The number of solutions provided dont equals with the number of solutions");
        }
    }

    public void setEquationTerms(double[][] terms) throws ArrayIndexOutOfBoundsException {
        if (equationTerms.length == terms.length && equationTerms[0].length == terms[0].length) {
            equationTerms = terms;
        } else {
            throw new ArrayIndexOutOfBoundsException("The number of equation terms provided don't equals with the number of solutions");
        }
    }

    public void calculateFitness(Individual ind){

        double fitness = 0;
        double diference;
        for (int i = 0; i < equationTerms.length; i++) {
            diference = 0;
            for (int j = 0; j < equationTerms[i].length; j++) {
                diference+= (equationTerms[i][j] * ind.getGenome()[j]);
            }
            fitness+= Math.abs(solutions[i] - diference);
        }

        ind.setFitness(fitness);
    }
}
