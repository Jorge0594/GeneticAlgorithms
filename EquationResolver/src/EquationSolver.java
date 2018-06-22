import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EquationSolver {

    private SelectionAlgorithm selection;
    private SystemEquation systemEquation;
    private List<Individual> population;

    EquationSolver(SystemEquation systemEquation) {
        this.selection = new SelectionAlgorithm();
        this.systemEquation = systemEquation;
        initializePopulation(this.systemEquation.getNumOfVariables());

        population.forEach(System.out::println);
    }

    public void startEquationResolver() {
        int generation = 1;
        System.out.println("========================INITIALIZE THE RESOLUTION=====================");
        List<Individual> temporaryPopulation;

        while (selection.getBestIndividual(population).getFitness() > 0.5 && generation < 1000000) {
            temporaryPopulation = new ArrayList<>(EquationSolverConstants.POPULATION_SIZE);
            temporaryPopulation.addAll(selection.elitism(population, 2));

            while (temporaryPopulation.size() < EquationSolverConstants.POPULATION_SIZE) {
                selectCrossOver(temporaryPopulation);
            }

            population = temporaryPopulation;
            generation++;

            //System.out.println("BEST INIDIVIDUAL IN GENERATION " + generation + " IS: " + selection.getBestIndividual(population));
        }
        System.out.println("SOLUTION HAS BEEN FOUND IN GENERATION " + generation + " WITH THE INDIVIDUAL: " + selection.getBestIndividual(population));
    }

    public SystemEquation getSystemEquation() {
        return systemEquation;
    }

    private void initializePopulation(int nVariables) {
        population = new ArrayList<>(EquationSolverConstants.POPULATION_SIZE);
        Individual individualAux;
        for (int i = 0; i < EquationSolverConstants.POPULATION_SIZE; i++) {
            individualAux = new Individual(nVariables);
            systemEquation.calculateFitness(individualAux);
            population.add(individualAux);
        }
    }

    private void crossover(Individual parent1, Individual parent2) {
        Random rnd = new Random();
        int crossoverPoint = rnd.nextInt(parent1.getGenome().length);
        double genomeAux;
        for (int i = crossoverPoint; i < parent1.getGenome().length; i++) {
            genomeAux = parent1.getGenome()[i];
            parent1.getGenome()[i] = parent2.getGenome()[i];
            parent2.getGenome()[i] = genomeAux;
        }

        systemEquation.calculateFitness(parent1);
        systemEquation.calculateFitness(parent2);
    }

    private void mutation(Individual ind) {
        Random rnd = new Random();
        int mutationPoint = rnd.nextInt(ind.getGenome().length);

        ind.getGenome()[mutationPoint] = EquationSolverUtils.round(ind.getRandomValue(-10, 10), EquationSolverConstants.N_DECIMALS);

        systemEquation.calculateFitness(ind);

    }

    private void individualToMutateSelection(Individual ind1, Individual ind2) {
        Random rnd = new Random();

        switch (rnd.nextInt(2)) {
            case 0:
                mutation(ind1);
                break;
            case 1:
                mutation(ind2);
                break;
        }
    }

    private void selectCrossOver(List<Individual> temporaryPopulation) {
        Random rnd = new Random();
        if (population.size() == 0) {
            return;
        }

        if (population.size() >= 2) {
            Individual parent1 = selection.tournamentSelection(population);
            Individual parent2 = selection.tournamentSelection(population);

            population.remove(parent1);
            population.remove(parent2);

            temporaryPopulation.add(parent1);
            temporaryPopulation.add(parent2);

            if(rnd.nextInt(100) < 90) {
                crossover(parent1, parent2);

                if (rnd.nextInt(100) < 2) {
                    individualToMutateSelection(parent1, parent2);
                }
            }
        } else {
            temporaryPopulation.add(population.get(0));
            population.remove(population.get(0));
        }
    }

}
