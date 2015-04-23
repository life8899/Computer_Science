/**
 * Creates an Interface for Autopilot
 * 
 * @author Nick Alexander
 * @version 8/28/13
 */

public interface Autopilot
{
    final double MAXAIRSPEED = 550;

    public void setAirspeed(double inAirspeed);
    public void setAltitude(double inAltitude);
    public void setHeading(double inHeading);
}