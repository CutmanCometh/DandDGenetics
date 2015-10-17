package danddgenetics;

import java.util.Random;

public abstract class Gene
{
    private static final Random rng = new Random();
    
    public static final double ODDS_OF_RANDOMLY_GETTING_MULTIPLIER = .01;
    
    public static final int MAX_RANDOM_SCALAR_VALUE = 20;
    
    public enum Sex {MALE, FEMALE}
    
    public final int value;
	
    public Gene(int value)
    {
    	this.value = value;
    }
    
    public static Gene getRandomGene()
    {
        if(rng.nextDouble() * 100 <= 100.0 * Gene.ODDS_OF_RANDOMLY_GETTING_MULTIPLIER)
            return getRandomMultiplierGene();
        else
            return getRandomValueGene();
    }

    private static ScalarGene getRandomValueGene()
    {
        return new ScalarGene(rng.nextInt(Gene.MAX_RANDOM_SCALAR_VALUE) + 1);
    }
    
    private static MultiplierGene getRandomMultiplierGene()//gives an even chance of a 1 or 2 multiplier
    {
        int multiplier;
        
        if(rng.nextBoolean())
            multiplier = 1;
        else
            multiplier = 2;
        
        return new MultiplierGene(multiplier);
    }
    
    public static Sex getRandomSex()
    {
        return rng.nextBoolean() ? Sex.MALE : Sex.FEMALE;
    }
    
    public static void main(String[] args)
    {
        Gene[] randomGenes = new Gene[1000];
        int scalarGenes = 0;
        int multiplierGenes = 0;
        
        for(Gene gene : randomGenes)
        {
            gene = getRandomGene();
            if(gene instanceof MultiplierGene)
                multiplierGenes ++;
            else
                scalarGenes ++;
        }
        
        System.out.println("Multiplier genes:\t" + multiplierGenes);
        System.out.println("Scalar genes:\t" + scalarGenes);
    }
}
