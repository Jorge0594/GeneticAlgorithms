public class Individual implements Comparable<Individual> {

	private double[] genome;
	private double fitness;

	public Individual(int genomeSize) {
		genome = new double[genomeSize];
		initialiceGenome(-10, 10);
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

	private void initialiceGenome(double lowRange, double highRange) {
		for (int i = 0; i < genome.length; i++) {
			genome[i] = Math.random() * (highRange + lowRange) - lowRange;
		}
	}

	@Override
	public int compareTo(Individual ind) {
		if (this.getFitness() > ind.getFitness())return 1;
		if (this.getFitness() < ind.getFitness())return -1;
		return 0;
	}
}
