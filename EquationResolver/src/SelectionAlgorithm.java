import java.util.Random;

//CURRENTLY ONLY WORKS FOR TOURNAMENT SELECTION
public class SelectionAlgorithm {

    SelectionAlgorithm() {
    }

    public int tournamentSelection(Individual[] individuals) {
        Random rand = new Random();
        int tournamentSize = (EquationSolverConstants.POPULATION_SIZE / 2);
        Individual[] tournament = new Individual[tournamentSize];

        for (int i = 0; i < tournamentSize; i++) {
            tournament[i] = individuals[rand.nextInt(individuals.length)];
        }

        return getBestIndividualIndex(tournament);
    }

	public Individual getBestIndividual(Individual[] group){
	    return group[getBestIndividualIndex(group)];
    }

    private int getBestIndividualIndex(Individual[] group) {
        int index = 0;
        for (int i = 0; i < group.length; i++) {
            if (group[i].getFitness() < group[index].getFitness()) {
                index = i;
            }
        }
        return index;
    }
}
