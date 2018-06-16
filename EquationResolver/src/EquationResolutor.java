import java.util.Random;

public class EquationResolutor {

    private final int POPULATION_SIZE = 10;

	private SelectionAlgorithm selection;
	private SystemEquation systemEquation;
    private Individual[] population;
	
	public EquationResolutor(int nVariables, int nEquations){
	    selection = new SelectionAlgorithm();
		systemEquation = new SystemEquation(nVariables, nEquations);
		population = initializePopulation(nVariables);
	}
	
	private Individual[] initializePopulation(int nVariables){
		Individual[] population = new Individual[POPULATION_SIZE];
		for(int i = 0; i < POPULATION_SIZE; i++){
			Individual ind = new Individual(nVariables);
			population[i] = ind;
		}
		
		return population;
	}

	private void crossover(Individual parent1, Individual parent2){
	    Random rnd = new Random();
	    int crossoverPoint = rnd.nextInt(parent1.getGenome().length);
        double genomeAux;
        for (int i = crossoverPoint; i < parent1.getGenome().length ; i++) {
            genomeAux = parent1.getGenome()[i];
            parent1.getGenome()[i] = parent2.getGenome()[i];
            parent2.getGenome()[i] = genomeAux;
        }
    }

    private void mutation(Individual ind){
	    Random rnd = new Random();
	    int mutationPoint = rnd.nextInt(ind.getGenome().length);


    }

}
