package heap;

import java.util.ArrayList;

/**
 * Abstract Heap Data Type for Priority Queues:  
 * -# < 0 < +# < A < Z < a < z
 * 
 * @author Nick Alexander
 * @version 4/11/14
 */
public abstract class Heap<E>
{
	
	protected int ARRAY_SIZE;
	
	protected ArrayList<E> array;
	
	/**
	 * Creates a Heap with Without Priority
	 */
	public Heap()
	{
		this.array = new ArrayList<E>(ARRAY_SIZE);
	}
	
	/**
	 * Adds Generic Element E to Heap
	 * 
	 * @param data Generic Element to Add
	 */
	public abstract void enqueue(E data);
	
	/**
	 * Removes and Returns Priority Element from Heap
	 * 
	 * @return Priority Element
	 */
	public abstract E dequeue();
	
	/**
	 * Prints Entire Heap Sequentially based on ArrayList Index
	 */
	public void printAll()
	{
		if (array.isEmpty())
			System.out.println("Empty Heap");
		else
		{
			for (E e : array)
			{
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Returns Size of the Heap
	 * 
	 * @return Size of the Heap
	 */
	public int size()
	{
		return array.size();
	}
	
	/**
	 * Returns If Heap is Empty
	 * @return If Heap is Empty
	 */
	public boolean isEmpty()
	{
		return array.size() == 0;
	}
	
	/**
	 * Returns Left Child Index of Given Parent Index
	 * 
	 * @param parentIndex Index of Parent
	 * @return Index of Parent's Left Child
	 */
	protected int getLeftChildIndex(int parentIndex)
	{
		return (2 * parentIndex) + 1;
	}

	/**
	 * Returns Right Child Index of Given Parent Index
	 * @param parentIndex Index of Parent
	 * @return Index of Parent's Right Child
	 */
	protected int getRightChildIndex(int parentIndex)
	{
		return (2 * parentIndex) + 2;
	}
	
	/**
	 * Returns Index of Parent Given Index of a Child
	 * 
	 * @param childIndex Index of Child of whom to Find Index of Parent
	 * @return Index of Parent
	 */
	protected int getParentIndex(int childIndex)
	{
		return (int)Math.floor((childIndex - 1) / 2);
	}

	/**
	 * Swaps Parent and Child Positions
	 * 
	 * @param parent
	 * @param child
	 */
	protected void swap(int parent, int child)
	{
		E tmp = array.get(parent);
		array.set(parent, array.get(child));
		array.set(child, tmp);
	}
}
