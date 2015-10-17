/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danddgenetics;

/**
 *
 * @author CutmanCometh
 */
public class Person
{
    private Genome mothersGenome, fathersGenome, myGenome;
    
    /**
     * Gets a person with random genetics. More technically, gets a person with random parents, then creates his genetics.
     */
    public Person()
    {
        this(new Genome(), new  Genome());
    }
    
    public Person(Genome father, Genome mother)
    {
        mothersGenome = mother;
        fathersGenome = father;
        
        initMyGenes();
    }
    
    private void initMyGenes()
    {
        myGenome = new Genome(fathersGenome, mothersGenome);
    }
    
    public int strength()
    {
        return myGenome.strength.value;
    }
    
    public int dexterity()
    {
        return myGenome.dexterity.value;
    }
    
    public int constitution()
    {
        return myGenome.constitution.value;
    }
    
    public int intelligence()
    {
        return myGenome.intelligence.value;
    }
    
    public int wisdom()
    {
        return myGenome.wisdom.value;
    }
    
    public int charisma()
    {
        return myGenome.charisma.value;
    }
}
