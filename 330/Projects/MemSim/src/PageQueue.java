/**
 * Data Structure that Adheres to the First-in, First-Out Policy
 */
class PageQueue
{
    private class Node
    {
        private Page data;
        private Node nextNode;

        public Node(Page data)
        {
            this.data = data;
            this.nextNode = null;
        }
    }

    private Node head;

    /**
     * Creates an Empty PageQueue
     */
    public PageQueue()
    {
        this.head = null;
    }

    /**
     * Adds a Page to the Back of the Queue
     * @param data Page to be Queued
     */
    public void enqueue(Page data)
    {
        if (this.head == null) {
            this.head = new Node(data);
        } else {
            Node iterNode = this.head;
            while (iterNode.nextNode != null) {
                iterNode = iterNode.nextNode;
            }
            iterNode.nextNode = new Node(data);
        }
    }

    /**
     * Removes the Page from the Front of the Queue
     * @return Page in the Front of the Queue
     */
    public Page dequeue()
    {
        if (this.head == null) {
            return null;
        }
        Page firstPage = this.head.data;
        this.head = this.head.nextNode;
        return firstPage;
    }
}