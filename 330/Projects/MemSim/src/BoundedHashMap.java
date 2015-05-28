import java.util.HashMap;

/**
 * HashMap with a Bounded Number of Keys
 */
public class BoundedHashMap<K, V> extends HashMap<K, V>
{
    private int capacity;

    /**
     * Creates a New BoundedHashMap with Given Capacity
     * @param capacity Capacity of the HashMap
     */
    public BoundedHashMap(int capacity)
    {
        super();
        this.capacity = capacity;
    }

    /**
     * Associates key with value if the map is not filled to capacity
     * @param key Key
     * @param value Value
     * @return True if key was mapped to value. False if the map is filled to capacity
     */
    public boolean add(K key, V value)
    {
        if (this.size() == capacity) {
            return false;
        }
        this.put(key ,value);
        return true;
    }

    /**
     * Returns True if the BoundedHashMap Capacity has been Met
     * @return True if the BoundedHashMap Capacity has been Met
     */
    public boolean isFull()
    {
        return this.size() == capacity;
    }

    /**
     * Returns the Total Number of Keys that the BoundedHashMap is Permitted to Contain
     * @return Total Number of Keys Permitted
     */
    public int getCapacity()
    {
        return this.capacity;
    }
}