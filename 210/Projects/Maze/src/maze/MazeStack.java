package maze;

/**
 * Module 2
 * 
 * Stack to Track Maze Traversal
 * 
 * @author Eli Cabarrus
 * @author Nicholas Alexander
 * 
 * @version 2/21/14 - Created
 */

public class MazeStack<E>
{
    private Node topNode;
    
    private class Node
    {
        private E data;
        private Node next;
        public Node(E data)
        {
            this.data = data;
        }
    }
    
    public MazeStack()
    {
        topNode = null;
    }
    
    public boolean empty()
    {
        if (this.topNode == null)
            return true;
        return false;    
    }
    
    public void push(E data)
    {
        Node newNode = new Node(data);
        newNode.next = topNode;
        topNode = newNode;
    }
    
    public E pop()
    {
        if (this.empty())
            return null;
        E tmp = topNode.data;
        topNode = topNode.next;
        return tmp;
    }
    
    public E peek()
    {
        if (this.empty())
            return null;
        return topNode.data;    
    }
}
