import java.util.Random;

//CURRENTLY ONLY WORKS FOR TOURNAMENT SELECTION
public class SelectionAlgorithm {

    //private Individual[] individuals;
    //private double sumFitness;
    private final int TOURNAMENT_SIZE = 5;

    public SelectionAlgorithm() {

    }

	/*public Individual roulleteSelection() {
		double numSelect = generateRandom() * sumFitness;

		for (Individual ind : individuals) {
			if (numSelect - ind.getFitness() <= 0) {
				return ind;
			}
		}

		return individuals[individuals.length - 1];
	}*/

    public int tournamentSelection(Individual[] individuals) {
        Random rand = new Random();
        Individual[] tournament = new Individual[TOURNAMENT_SIZE];
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            tournament[i] = individuals[rand.nextInt(individuals.length)];
        }

        return getBestIndividualIndex(tournament);
    }

	/*private void calculateSum() {
		sumFitness = 0;
		for (Individual ind : individuals) {
			sumFitness += ind.getFitness();
		}
	}

	private double generateRandom() {
		double num = new Random().nextDouble();
		return num;
	}*/

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
