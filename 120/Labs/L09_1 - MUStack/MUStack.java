
/**
 * Self-implemented Stack Class storing Characters
 * 
 * @author Nick Alexander
 * @version 10/21/13
 */

public class MUStack
{
    private Node topNode;
    private class Node
    {
        private Character data;
        private Node nextNode;
        private Node(Character data)
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
    
    public void push(Character data)
    {
        Node newNode = new Node(data);
        newNode.nextNode = topNode;
        topNode = newNode;
    }
    
    public Character pop()
    {
        if (empty())
            return null;
        Character tmp = topNode.data;
        topNode = topNode.nextNode;
        return tmp;
    }
    
    public Character peek()
    {
        if (empty())
            return null;
        return topNode.data;    
    }
}
