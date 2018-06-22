public class MainEcuationSolver {


    public static void main(String[] args) {
        final int N_EQUATIONS = 3;
        final int N_VARIABLES = 3;
        double[][] equation = new double[N_EQUATIONS][N_VARIABLES];

        equation[0][0] = 3;
        equation[0][1] = 8;
        equation[0][2] = 2;
        equation[1][0] = 1;
        equation[1][1] = -2;
        equation[1][2] = 4;
        equation[2][0] = -5;
        equation[2][1] = 3;
        equation[2][2] = 11;


        double[] solutions = new double[N_EQUATIONS];

        solutions[0] = 25;
        solutions[1] = 12;
        solutions[1] = 4;

        SystemEquation systemEquation = new SystemEquation(N_EQUATIONS, N_VARIABLES);

        try {
            systemEquation.setEquationTerms(equation);
            systemEquation.setSolutions(solutions);
        } catch (Exception e) {
            e.printStackTrace();
        }

        EquationSolver solver = new EquationSolver(systemEquation);

        solver.startEquationResolver();


    }

}
