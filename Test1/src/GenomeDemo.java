import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Random;

public class GenomeDemo {

    private Individual fittest;
    private Individual secondFit;
    private Population population;
    private Random random;

    public GenomeDemo(){
        random = new Random();
        population = new Population();
    }

    public void start(){
        int numGeneration = 0;
        System.out.println("Starting the genome demo...");

        while(population.getBestIndividual().getFitness() < 5){
            numGeneration++;

            individualsSelection();

            crossover();

            if(random.nextInt(20) < 5){
                mutate();
            }

            addBestOffspring();

            population.calculateFitness();

            System.out.println("Generation: " + numGeneration + " Fittest: " + population.getBestIndividual());
        }

        System.out.println("Finish the geenome demo");
    }

    private void mutate(){
        int indexMutate = random.nextInt(Individual.getGenesSize());

        for (int i = 0; i < indexMutate; i++) {
            if(fittest.getGenes()[i] == 0){
                fittest.getGenes()[i] = 1;
            } else {
                fittest.getGenes()[i] = 0;
            }
        }

        indexMutate = random.nextInt(Individual.getGenesSize());

        for (int i = 0; i < indexMutate; i++) {
            if(secondFit.getGenes()[i] == 0){
                secondFit.getGenes()[i] = 1;
            } else {
                secondFit.getGenes()[i] = 0;
            }
        }
    }

    private void individualsSelection(){
        fittest = population.getBestIndividual();
        secondFit = population.getSecondBetterIndividual();
    }

    private void crossover(){
        int crossPoint = random.nextInt(Individual.getGenesSize());

        for (int i = crossPoint; i < Individual.getGenesSize(); i++) {
            int aux = fittest.getGenes()[i];

            fittest.getGenes()[i] = secondFit.getGenes()[i];
            secondFit.getGenes()[i] = aux;

        }
    }

    private void addBestOffspring(){
        int leastIndex = population.getLeastIndex();

        if(fittest.compareTo(secondFit) >= 0){
            population.getIndividuals().set(leastIndex, fittest);
        } else {
            population.getIndividuals().set(leastIndex, secondFit);
        }
    }
}
