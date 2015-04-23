/**
 * @author Nick Alexander
 * @version 4-20-2015
 *
 * The RandNumGen class generates a pseudo-random integer with a period of roughly 2^64.
 */
public class RandNumGen
{
	private final long modValue = (long)Math.pow(2, 5);

	private long multiplier;
	private long lastRandom;
	private long bitPad;

	/**
	 * Generates a Pseudo-Random Integer
	 */
	public RandNumGen()
	{
		this.multiplier = System.currentTimeMillis();
		this.lastRandom = System.nanoTime();
		this.bitPad = (long)Math.pow(2, 32) - 1;
	}

	/**
	 * Generates an integer between Integer.MIN_VALUE and Integer.MAX_VALUE
	 * @return An integer between Integer.MIN_VALUE and Integer.MAX_VALUE
	 */
	public int randomInt()
	{
		this.lastRandom = (this.multiplier * (this.lastRandom & this.bitPad) + (this.lastRandom >>> modValue));
        int lastRandomAsInt = (int)this.lastRandom;
        if (lastRandomAsInt < 0) {
            lastRandomAsInt = -lastRandomAsInt;
        }
        return lastRandomAsInt;
	}

	/**
	 * Generates an integer between 0 and the upper bound
	 * @param upperBound Highest possible value for the random integer
	 * @return Random integer between 0 and the upper bound
	 */
    public int randomIntWithRange(int upperBound)
	{
        this.lastRandom = (this.multiplier * (this.lastRandom & this.bitPad) + (this.lastRandom >>> modValue));
        int lastRandomAsInt = (int)this.lastRandom;
        if (lastRandomAsInt < 0) {
            lastRandomAsInt = -lastRandomAsInt;
        }
        return lastRandomAsInt % upperBound;
    }
}