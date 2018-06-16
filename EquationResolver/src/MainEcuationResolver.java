import java.util.Random;

public class MainEcuationResolver {


    public static void main(String[] args) {
        final int N_EQUATIONS = 2;
        final int N_VARIABLES = 2;
        double[][] equation = new double[N_EQUATIONS][N_VARIABLES];

        equation[0][0] = 2;
        equation[0][1] = 3;
        equation[1][0] = 1;
        equation[1][1] = 1;

        double[] solutions = new double[N_EQUATIONS];

        solutions[0] = 5;
        solutions[1] = 2;

        SystemEquation systemEquation = new SystemEquation(N_EQUATIONS, N_VARIABLES);

        try {
            systemEquation.setEquationTerms(equation);
            systemEquation.setSolutions(solutions);
        }catch (Exception e){
            e.printStackTrace();
        }

        EquationResolutor resolutor = new EquationResolutor(systemEquation);

        resolutor.startEquationResolver();

    }

}
