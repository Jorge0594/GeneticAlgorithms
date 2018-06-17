import java.util.Arrays;
import java.util.Objects;

public class Individual implements Comparable<Individual> {

    private double[] genome;
    private double fitness;

    Individual(int genomeSize) {
        genome = new double[genomeSize];
        initializeGenome(EquationSolverConstants.LOW_GENOME_RANGE, EquationSolverConstants.HIGH_GENOME_RANGE);
    }

    public double[] getGenome() {
        return genome;
    }

    public void setGenome(double[] genome) {
        this.genome = genome;
    }

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getRandomValue(double lowRange, double highRange){
        return Math.random() * (highRange - lowRange) + lowRange;
    }

    private void initializeGenome(double lowRange, double highRange) {
        for (int i = 0; i < genome.length; i++) {
            genome[i] =getRandomValue(lowRange, highRange);
        }
    }

    @Override
    public String toString() {
        return "Individual{" +
                "solutions=" + Arrays.toString(genome) +
                ", fitness=" + fitness +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Individual that = (Individual) o;
        return Double.compare(that.fitness, fitness) == 0 &&
                Arrays.equals(genome, that.genome);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(fitness);
        result = 31 * result + Arrays.hashCode(genome);
        return result;
    }

    @Override
    public int compareTo(Individual ind) {
        if (this.getFitness() > ind.getFitness()) return 1;
        if (this.getFitness() < ind.getFitness()) return -1;
        return 0;
    }
}
