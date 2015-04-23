
/**
 * Self-implemented, Revised Stack Class storing Generic Classes
 * 
 * @author Nick Alexander
 * @version 10/21/13
 */

public class MUStack<E>
{
    private Node topNode;
    private class Node
    {
        private E data;
        private Node nextNode;
        private Node(E data)
        {
            this.data = data;
        }
    }
    
    public MUStack()
    {
        topNode = null;
    }
    
    public boolean empty()
    {
        if (topNode == null)
            return true;
        return false;    
    }
    
    public void push(E data)
    {
        Node newNode = new Node(data);
        newNode.nextNode = topNode;
        topNode = newNode;
    }
    
    public E pop()
    {
        if (empty())
            return null;
        E tmp = topNode.data;
        topNode = topNode.nextNode;
        return tmp;
    }
    
    public E peek()
    {
        if (empty())
            return null;
        return topNode.data;    
    }
}
