package heap;

/**
 * Heap with Maximum Value Priority
 * 
 * @author Nick Alexander
 * @version 4/11/14
 */
public class MaxHeap <E extends Comparable<E>> extends Heap<E>
{
	
	/**
	 * Creates a Heap with Maximum Value Priority
	 */
	public MaxHeap()
	{
		super();
	}
	
	@Override
	public void enqueue(E data)
	{
		array.add(data);
		int child = array.size() - 1;
		int parent = getParentIndex(child);
		
		while (parent >= 0 && array.get(parent).compareTo(array.get(child)) < 0)
		{
			swap(parent, child);
			child = parent;
			parent = getParentIndex(child);
		}
	}
	
	@Override
	public E dequeue()
	{
		if (this.isEmpty())
			return null;
		int parent = 0;
		E returnVal = array.get(parent);
		int last = array.size() - 1;
		array.set(parent, array.get(last));
		array.remove(last);
		while (true)
		{
			int leftChild = getLeftChildIndex(parent);
			int rightChild = getRightChildIndex(parent);
			int minChild = leftChild;
			if (leftChild >= array.size())
				break;
			else if (rightChild < array.size() && array.get(leftChild).compareTo(array.get(rightChild)) < 0)
			{
				minChild = rightChild;	
			}
			
			if (array.get(parent).compareTo(array.get(minChild)) < 0)
			{
				swap(parent, minChild);
				parent = minChild;
			}
			else
				break;
				
		}
		return returnVal;
	}
}
