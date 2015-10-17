package danddgenetics;

import java.util.Random;



public class Genome
{
    private static final Random rng = new Random();

    public final Gene strength, dexterity, constitution, intelligence, wisdom, charisma;
    public final Gene.Sex sex;

    private Genome(Gene strength, Gene dexterity, Gene constitution, Gene intelligence, Gene wisdom, Gene charisma, Gene.Sex sex)
    {
        this.strength = strength;
        this.dexterity = dexterity;
        this.constitution = constitution;
        this.intelligence = intelligence;
        this.wisdom = wisdom;
        this.charisma = charisma;
        this.sex = sex;
    }
    
    /**
     * Creates a genome by combining the genomes of the parents according to the algorithm from "what if?"
     * @param father
     * @param mother 
     */
    public Genome(Genome father, Genome mother)
    {
        strength = getGeneFromCombiningParents(father.strength, mother.strength);
        dexterity = getGeneFromCombiningParents(father.dexterity, mother.dexterity);
        constitution = getGeneFromCombiningParents(father.constitution, mother.constitution);
        intelligence = getGeneFromCombiningParents(father.intelligence, mother.intelligence);
        wisdom = getGeneFromCombiningParents(father.wisdom, mother.wisdom);
        charisma = getGeneFromCombiningParents(father.charisma, mother.charisma);
        sex = Gene.getRandomSex();
    }
    
    /**
     * Gets a random genome
     */
    public Genome()
    {
        strength = Gene.getRandomGene();
        dexterity = Gene.getRandomGene();
        constitution = Gene.getRandomGene();
        intelligence = Gene.getRandomGene();
        wisdom = Gene.getRandomGene();
        charisma = Gene.getRandomGene();
        sex = Gene.getRandomSex();
    }
    
    /**
     * This implements the algorithm described in "what if?" for determining one's own genes based upon those of one's parents
     * @param father
     * @param mother
     * @return 
     */
    private static Gene getGeneFromCombiningParents(Gene father, Gene mother)
    {
        if(father instanceof ScalarGene && mother instanceof ScalarGene)
        {
            //return the greater of the two scalar values
            if(father.value > mother.value)
                return new ScalarGene(father.value);
            else
                return new ScalarGene(mother.value);
        }
        else if(father instanceof MultiplierGene && mother instanceof MultiplierGene)
        {
            //BAD LUCK
            return new ScalarGene(1);
        }
        else
        {
            //one is a scalar and one is a multiplier
            return  new ScalarGene(mother.value * father.value);
        }
    }
    
    /**
     * This produces a random set of genes by taking either the value for the mother or the father for each gene. Whether the mother or the father is chosen is random.
     * @param father
     * @param mother
     * @return 
     */
    public static Genome getRandomSampleOfGenesForReproduction(Genome father, Genome mother)
    {
        Gene strength = rng.nextBoolean() ? father.strength : mother.strength;
        Gene dexterity = rng.nextBoolean() ? father.dexterity : mother.dexterity;
        Gene constitution = rng.nextBoolean() ? father.constitution : mother.constitution;
        Gene intelligence = rng.nextBoolean() ? father.intelligence : mother.intelligence;
        Gene wisdom = rng.nextBoolean() ? father.wisdom : mother.wisdom;
        Gene charisma = rng.nextBoolean() ? father.charisma : mother.charisma;
        
        return new Genome(strength, dexterity, constitution, intelligence, wisdom, charisma, null);//okay to pass null value to sex because this sex is not dependent on parents sex
    }
}