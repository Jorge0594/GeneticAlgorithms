import java.util.ArrayList;
import java.util.List;

public class Population {

    private final int POPULATION_SIZE = 10;

    private List<Individual> individuals;
    private int fittest;

    public Population() {
        individuals = new ArrayList<>(POPULATION_SIZE);
        createPopulation();
    }

    public Individual getBestIndividual() {
        int maxFit = 0;
        int maxFitIndex = 0;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (individuals.get(i).getFitness() > maxFit) {
                maxFit = individuals.get(i).getFitness();
                maxFitIndex = i;
            }
        }

        return individuals.get(maxFitIndex);
    }

    public Individual getSecondBetterIndividual() {
        int maxIndex = 0;
        int secondIndex = 0;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (individuals.get(i).getFitness() > individuals.get(maxIndex).getFitness()) {
                secondIndex = maxIndex;
                maxIndex = i;
            }
        }

        return individuals.get(secondIndex);
    }

    public int getLeastIndex() {
        int minFit = Integer.MAX_VALUE;
        int minFitIndex = 0;

        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (individuals.get(i).getFitness() < minFit) {
                minFitIndex = i;
            }
        }

        return minFitIndex;
    }

    public void calculateFitness() {
        for (Individual individual : individuals) {
            individual.calculateFitness();
        }

        getBestIndividual();
    }

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public void setIndividuals(List<Individual> individuals) {
        this.individuals = individuals;
    }

    public int getFittest() {
        return fittest;
    }

    public void setFittest(int fittest) {
        this.fittest = fittest;
    }


    private void createPopulation() {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            individuals.add(new Individual());
        }
    }
}
