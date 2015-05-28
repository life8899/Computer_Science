/**
 * Creates an Ordered Linked List of a Generic Class
 * 
 * @author Nick Alexander
 * @version 11/1/13
 */

public class OrderedLinkedList<E extends Comparable<E>>
{
    private Node topNode;
    
    /**
     * Creates a Node for the Linked List
     * Stores Data E for Generic Data Type
     */
    private class Node
    {
        private E data;
        private Node nextNode;
        
        /**
         * Instantiates a Node
         * @param Node's Generic Data
         */
        public Node(E data)
        {
            this.data = data;
            this.nextNode = null;
        }
    }
    
    /**
     * Instantiates a new OrderedLinkedList
     */
    public OrderedLinkedList()
    {
        topNode = null;
    }
    
    /**
     * Returns Empty Status
     * @return Whether or Not the List is Empty
     */
    public boolean empty()
    {
        if (topNode != null)
            return false;
        return true;
    }
    
    /**
     * Adds a Piece of Data to the List
     * @param data Data for the Node
     */
    public void add(E data)
    {
        Node newNode = new Node(data);
        Node currentNode;
        Node priorNode;
        
        //Case 0 - Empty
        if (empty())
        {
            topNode = newNode;
            System.out.println("String Added!\n");
            return;
        }
        
        //Case 1 - Already Exists
        boolean done = false;
        currentNode = topNode;
        while (!done)
        {
            if (newNode.data.compareTo(currentNode.data) == 0)
            {
                System.out.println("String Already Exists in List!\n");
                return;
            }
            if (currentNode.nextNode == null)
                done = true;
            currentNode = currentNode.nextNode;
        }
        
        //Case 2 - First Node
        if (newNode.data.compareTo(topNode.data) < 0)
        {
            newNode.nextNode = topNode;
            topNode = newNode;
            System.out.println("String Added!\n");
            return;
        }
        
        //Case 3 - Last Node
        //Case 4 - In Between
        else if (newNode.data.compareTo(topNode.data) > 0)
        {
            currentNode = topNode;
            priorNode = topNode;
            while (newNode.data.compareTo(currentNode.data) > 0)
            {
                if (currentNode.nextNode == null)
                {
                    currentNode.nextNode = newNode;
                    System.out.println("String Added!\n");
                    return;
                }
                priorNode = currentNode;
                currentNode = currentNode.nextNode;
            }
            priorNode.nextNode = newNode;
            newNode.nextNode = currentNode;
            System.out.println("String Added!\n");
            return;
        }
    }
    
    /**
     * Removes a Piece of Data from the List
     * @param data Data to Find and Remove
     * @return Whether or Not Data was Removed
     */
    public boolean remove(E data)
    {
        //Case 0 - List is Empty
        if (empty())
        {
            System.out.println("String Does Not Exist in List!\n");
            return false;
        }
        
        //Case 1 - Node is First in List
        if (data.compareTo(topNode.data) == 0)
        {
            topNode = topNode.nextNode;
            System.out.println("String Removed!\n");
            return true;
        }
        
        Node currentNode = topNode;
        Node priorNode = topNode;
        if (data.compareTo(currentNode.data) > 0)
        {
            while (data.compareTo(currentNode.data) != 0)
            {
                if (currentNode.nextNode == null)
                {
                    System.out.println("String Does Not Exists in List!\n");
                    return false;
                }
                priorNode = currentNode;
                currentNode = currentNode.nextNode;
            }
            priorNode.nextNode = currentNode.nextNode;
            System.out.println("String Removed!\n");
        }
        return true;
    }
    
    /**
     * Returns a String Representation of the List
     * @return List in String Format
     */
    public String toString()
    {
        String text = "";
        Node currentNode = topNode;
        while (currentNode != null)
        {
            if (currentNode == topNode)
                text = currentNode.data.toString();
            else    
                text =  text + " --> " + currentNode.data.toString();
            currentNode = currentNode.nextNode;
        }
        return text;
    }
}
