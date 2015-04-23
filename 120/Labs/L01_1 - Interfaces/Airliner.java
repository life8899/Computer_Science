/**
 * Airliner class implementing Autopilot
 * 
 * @author Nick Alexander
 * @version 8/28/13
 */



public class Airliner implements Autopilot
{
    private double maxAltitude, airspeed, altitude, heading;
    
    public Airliner(double inMaxAltitude)
    {
        maxAltitude = inMaxAltitude;
    }
    
    public void setAirspeed(double inAirspeed)
    {
        airspeed = inAirspeed;
    }
    
    public void setAltitude(double inAltitude)
    {
        altitude = inAltitude;
    }
    
    public void setHeading(double inHeading)
    {
        heading = inHeading;
    }
}   
