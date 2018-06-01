import java.util.Arrays;
import java.util.Random;

public class Individual implements Comparable {

    private static final int GENES_SIZE = 5;

    private int fitness;
    private int[] genes = new int[GENES_SIZE];

    public Individual() {
        generateGenes();
        calculateFitness();
    }

    public static int getGenesSize() {
        return GENES_SIZE;
    }

    public int getFitness() {
        return fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }

    public int[] getGenes() {
        return genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public void calculateFitness() {
        fitness = 0;
        for (int i = 0; i < genes.length; i++) {
            if(genes[i] == 1) fitness++;
        }
    }

    private void generateGenes() {
        Random rnd = new Random();
        for (int i = 0; i < GENES_SIZE; i++) {
            genes[i] = Math.abs(rnd.nextInt() % 2);
        }
    }

    @Override
    public String toString() {
        return "Individual{" +
                "fitness=" + fitness +
                ", genes=" + Arrays.toString(genes) +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Individual other = (Individual)o;
        if (this.getFitness() > other.getFitness()) return 1;
        if (this.getFitness() < other.getFitness()) return -1;

        return 0;
    }
}
