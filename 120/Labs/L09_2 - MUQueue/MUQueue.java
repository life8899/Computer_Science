
/**
 * Creates a Queue using LinkedLists
 * 
 * @author Nick Alexander
 * @version 10/23/13
 */

import java.util.LinkedList;

public class MUQueue
{
    private Node topNode;
    private class Node
    {
        private String data;
        private Node nextNode;
        private Node(String data)
        {
            this.data = data;
        }
    }
    
    public MUQueue()
    {
        topNode = null;
    }
    
    public boolean empty()
    {
        if (topNode == null)
            return true;
        return false;    
    }
    
    public void enqueue(String data)
    {
        Node newNode = new Node(data);
        if (empty())
        {
            topNode = newNode;
            return;
        }
        Node tmpNode = topNode;
        while (tmpNode.nextNode != null)
        {
            tmpNode = tmpNode.nextNode;
        }
        tmpNode.nextNode = newNode;
    }
    
    public String dequeue()
    {
        if (empty())
            return null;
        String tmp = topNode.data;
        topNode = topNode.nextNode;
        return tmp;
    }
    
    public String peek()
    {
        if (empty())
            return null;
        return topNode.data;    
    }
}
