import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//CURRENTLY ONLY WORKS FOR TOURNAMENT SELECTION
public class SelectionAlgorithm {

    SelectionAlgorithm() {
    }

    public Individual tournamentSelection(List<Individual> individuals) {
        Random rnd = new Random();

        Individual parent1 = individuals.get(rnd.nextInt(individuals.size()));
        Individual parent2 = individuals.get(rnd.nextInt(individuals.size()));

        return parent1.getFitness() > parent2.getFitness() ? parent1 : parent2;
    }


    public Individual getBestIndividual(List<Individual> group) {
        Individual individualAux = group.get(0);

        for (Individual individual : group) {
            if (individualAux.getFitness() > individual.getFitness()) {
                individualAux = individual;
            }
        }

        return individualAux;
    }

    public List<Individual> elitism(List<Individual> population, int nIndividuals) {
        List<Individual> elitismGroup = new ArrayList<>(nIndividuals);
        Individual individualAux;

        for (int i = 0; i < nIndividuals; i++) {
            individualAux = getBestIndividual(population);
            population.remove(individualAux);
            elitismGroup.add(individualAux);
        }

        return elitismGroup;
    }
}
