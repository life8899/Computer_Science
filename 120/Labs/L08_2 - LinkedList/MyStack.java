
/**
 * Use a LinkedList to implement self-made Stack
 * 
 * @author Nick Alexander
 * @version 10/16/13
 */

import java.util.LinkedList;

public class MyStack
{
    private LinkedList<Character> list;
    
    public MyStack()
    {
        list = new LinkedList<Character>();
    }
    
    public void push(char inChar)
    {
        list.addFirst(inChar);
    }
    
    public char pop()
    {
        return list.removeFirst();
    }
    
    public char peek()
    {
        return list.peek();
    }
    
    public boolean empty()
    {
        if (list.size() != 0)
            return false;
        else
            return true;
    }
}
