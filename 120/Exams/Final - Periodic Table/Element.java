
/**
 * Description
 * 
 * @author Nick Alexander
 * @version December 9, 2013
 */

import java.io.*;

public class Element implements Serializable, Comparable
{
    private String abbreviation;
    private String name;
    private double atomicMass;
    
    public Element(String abbreviation, String name, double atomicMass)
    {
        this.abbreviation = abbreviation;
        this.name = name;
        this.atomicMass = atomicMass;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String toString()
    {
        return this.abbreviation +" - " + this.name + ", Atomic Mass = " + this.atomicMass;
    }
    
    public int compareTo(Object o)
    {
        Element tmp = (Element) o;
        return this.name.compareTo(tmp.getName());
    }
}
