/**
 * Page object contains the memory address, the memory operation, a used-last counter for LRU, and a hasBeenWritten
 * flag for write-to-disk testing
 */
class Page
{
    private long address;
    private String operation;
    private int eventsSinceUsed;
    private boolean hasBeenWritten;

    /**
     * Creates a new Page
     *
     * @param address Memory Address as String to be Converted into Long
     * @param operation Memory Operation (R/W)
     */
    public Page(String address, String operation)
    {
        this.address = Long.parseLong(address, 16);
        this.operation = operation;
        this.eventsSinceUsed = 0;
        this.hasBeenWritten = operation.equals("W");
    }

    /**
     * Returns the Address of the Page
     * @return Address of the Page
     */
    public long getAddress()
    {
        return this.address;
    }

    /**
     * Returns if the Page Operation is a Write
     * @return True if the Page Operation is a Write. False if the Page Operation is a Read
     */
    public boolean isWrite()
    {
        return this.operation.equals("W");
    }

    /**
     * Returns the Number of Events since the Page was Last Used
     * @return Number of Events since the Page was Last Used
     */
    public int getEventsSinceUsed()
    {
        return this.eventsSinceUsed;
    }

    /**
     * Sets the Number of Events since the Page was Last Used Back to 0
     */
    public void resetEventsSinceLastUsed()
    {
        this.eventsSinceUsed = 0;
    }

    /**
     * Returns True if the Page has been Written to while in Memory - Equivalent to the Page being Dirty.
     * @return True if the Page has been Written to while in Memory
     */
    public boolean hasBeenWritten()
    {
        return this.hasBeenWritten;
    }

    /**
     * Flags the Page as having been Written to while in Memory - Equivalent to the Page being Dirty
     */
    public void hasBeenWritten(boolean has)
    {
        this.hasBeenWritten = has;
    }

    /**
     * Adds 1 to the Number of Events Since Last Used
     */
    public void incrementEventsSinceUsed()
    {
        this.eventsSinceUsed++;
    }
}