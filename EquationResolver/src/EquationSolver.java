import java.util.Random;

public class EquationSolver {

	private SelectionAlgorithm selection;
	private SystemEquation systemEquation;
    private Individual[] population;
	
	EquationSolver(SystemEquation systemEquation){
	    this.selection = new SelectionAlgorithm();
		this.systemEquation = systemEquation ;
		this.population = initializePopulation(this.systemEquation.getNumOfVariables());
	}

	public void startEquationResolver(){
        int generation = 1;
        System.out.println("========================INITIALIZE THE RESOLUTION=====================");
        Random rnd = new Random();
        while(selection.getBestIndividual(population).getFitness() > 0.05 && generation < 1000000){

            if(rnd.nextInt(10) < 9){
                Individual parent1 = population[selection.tournamentSelection(population)];
                Individual parent2 = population[selection.tournamentSelection(population)];

                while(parent2.equals(parent1)){
                    parent2 = population[selection.tournamentSelection(population)];
                }

                crossover(parent1, parent2);

                if(rnd.nextInt(100) < 4){
                    //For now only mutate the first child
                    mutation(parent1);
                }
            }

            System.out.println("BEST INIDIVIDUAL IN GENERATION " + generation + " IS: " + selection.getBestIndividual(population));

            generation++;
        }

        System.out.println("SOLUTION HAS BEEN FOUND IN GENERATION " + generation + " WITH THE INDIVIDUAL: " + selection.getBestIndividual(population));
    }

    public SystemEquation getSystemEquation(){
	    return systemEquation;
    }
	
	private Individual[] initializePopulation(int nVariables){
		Individual[] population = new Individual[EquationSolverConstants.POPULATION_SIZE];
		for(int i = 0; i < EquationSolverConstants.POPULATION_SIZE; i++){
			Individual ind = new Individual(nVariables);
			population[i] = ind;
			systemEquation.calculateFitness(ind);
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

        systemEquation.calculateFitness(parent1);
        systemEquation.calculateFitness(parent2);
    }

    private void mutation(Individual ind){
	    Random rnd = new Random();
	    int mutationPoint = rnd.nextInt(ind.getGenome().length);

        ind.getGenome()[mutationPoint] = ind.getRandomValue(-10, 10);

        systemEquation.calculateFitness(ind);

    }

}
