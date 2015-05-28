public class BinarySearchTree<E extends Comparable<E>>
{
    private class Node
    {
        private E data;
        private Node left;
        private Node right;
        
        public Node(E data)
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }
        
        public Node(E data, Node left, Node right)
        {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
    
    private E data;
    private Node root;
    
    public BinarySearchTree()
    {
        root = null;
    }
    
    private boolean added;
    private Node add(Node root, E data)
    {
        if (root == null)
        {
            added = true;
            return new Node(data);
        }
        else if (data.compareTo(root.data) == 0)
        {
            added = false;
            return root;
        }
        else if (data.compareTo(root.data) < 0)
        {
            root.left = add(root.left, data);
            return root;
        }
        else
        {
            root.right = add(root.right, data);
            return root;
        }
    }
    
    public boolean add(E data)
    {
        root = add(root, data);
        return added;
    }
    
    private E find(Node root, E target)
    {
        if (root == null)
            return null;
        else if (target.compareTo(root.data) == 0)
            return root.data;
        else if (target.compareTo(root.data) < 0)
            return find(root.left, target);
        else
            return find(root.right, target);
    }
    
    public E find(E target)
    {
        return this.find(this.root, target);
    }
    
    private E findLargestChild(Node parent)
    {
        if (parent.right.right == null)
        {
            E returnValue = parent.right.data;
            parent.right = parent.right.left;
            return returnValue;
        }
        else
        {
            return findLargestChild(parent.right);
        }
    }
    
    public E delete(E target)
    {
        root = delete(root, target);
        return deleted;
    }
    
    private E deleted;
    private Node delete(Node root, E target)
    {
        if (root == null)
        {
            deleted = null;
            return root;
        }
        
        if (target.compareTo(root.data) < 0)
        {
            root.left = delete(root.left, target);
            return root;
        }
        else if (target.compareTo(root.data) > 0)
        {
            root.right = delete(root.right, target);
            return root;
        }
        else
        {
            deleted = root.data;
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            else
            {
                if (root.left.right == null)
                {
                    root.data = root.left.data;
                    root.left = root.left.left;
                    return root;
                }
                else
                {
                    root.data = findLargestChild(root.left);
                    return root;
                }
            }
        }
    }
    
    private void inOrderTraverse(Node root)
    {
        Node local = root;
        if (local == null)
            return;
        inOrderTraverse(local.left);
        System.out.println(local.data);
        inOrderTraverse(local.right);
    }
    
    public void traverseInOrder()
    {
        inOrderTraverse(this.root);
    }
}