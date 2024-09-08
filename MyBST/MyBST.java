public class MyBST {
    BSTNode root;

    int size()
    {
        return sizeHelp(root);
    }
    private int sizeHelp(BSTNode node)
    {
        if (node == null)
        {
            return 0;
        }
        else
        {
            return 1 + sizeHelp(node.left) + sizeHelp(node.right);
        }
    }

    void insert(Integer n)
    {
        BSTNode node = root;
        if (root == null)
        {
            root = new BSTNode(n);
        }
        else
        {
            insertHelp(n, root);
        }
    }
    private void insertHelp(Integer n, BSTNode node)
    {
        if (node.left == null && (n < node.val))
        {
            node.left = new BSTNode(n);;
        }
        else if (node.right == null && (n > node.val))
        {
            node.right = new BSTNode(n);
        }
        else
        {
            if (n < node.val)
            {
                insertHelp(n, node.left);
            }
            else
            {
                insertHelp(n, node.right);
            }
        }
    }
    
    boolean contains(Integer n)
    {
        return containsHelp(n, root);
    }
    private boolean containsHelp(Integer n, BSTNode node)
    {
        if (node.left == null || node.right == null)
        {
            return false;
        }
        else if (n == node.left.val || n == node.right.val)
        {
            return true;
        }
        else
        {
            if (n < node.val)
            {
                return containsHelp(n, node.left);
            }
            else
            {
                return containsHelp(n, node.right);
            }
        }
    }

    Integer getMax()
    {
        BSTNode node = root;
        if (node == null)
        {
            return null;
        }
        else
        {
            while (node.right != null)
            {
                node = node.right;
            }
        }
        return node.val;
    }

    Integer getMin()
    {
        BSTNode node = root;
        if (node == null)
        {
            return null;
        }
        else
        {
            while (node.left != null)
            {
                node = node.left;
            }
        }
        return node.val;
    }

    void inOrder()
    {
        inOrderHelper(root);
    }
    private void inOrderHelper(BSTNode node)
    {
       if (node == null)
       {
           return;
       }
       else
       {
           inOrderHelper(node.left);
           System.out.print(node.val + " ");
           inOrderHelper(node.right);
       }
    }
    
    void delete(Integer n)
    {
       deleteHelper(root, null, n); 
    }
    private void deleteHelper(BSTNode node, BSTNode prev, Integer n)
    {
        if (node == null)
        {
            return;
        }
        if (node.val > n)
        {
            deleteHelper(node.left, node, n);
        }
        else if (node.val < n)
        {
            deleteHelper(node.right, node, n);
        }
        else if (node.val == n)
        {
            if (node.left == null && node.right == null)
            {
                if (prev.left == node)
                {
                    prev.left = null;
                }
                else
                {
                    prev.right = null;
                }
            }
            else if (node.left == null || node.right == null)
            {
                if (prev.left == node)
                {
                    if (node.left == null)
                    {
                        prev.left = node.left;
                    }
                    else
                    {
                        prev.left = node.right;
                    }
                }
                else
                {
                    if (node.right == null)
                    {
                        prev.right = node.left;
                    }
                    else
                    {
                        prev.right = node.right;
                    }
                }
            }
            else
            {
                BSTNode prevRep = node.right;
                if (prevRep.left == null)
                {
                    node.right = prevRep.right;
                    node.val = prevRep.val;
                    return;
                }
                while (prevRep.left != null && prevRep.left.left != null)
                {
                    prevRep = prevRep.left;
                }
                node.val = prevRep.left.val;
                prevRep.left = prevRep.left.right;
            }
        }
    }
    void print()
    {
        printHelp(root, 0);
    }
    private void printHelp(BSTNode node, int dep)
    {
        if (node == null)
        {
            return;
        }
        printHelp(node.right, dep + 1);
        for (int i = 0; i < dep; i++)
        {
            System.out.print("    ");
        }
        System.out.println(node.val);
        printHelp(node.left, dep + 1);
    }
    private class BSTNode
    {
        Integer val;
        BSTNode left, right;

        public BSTNode(Integer val)
        {
            this.val = val;
            left = right = null;
        }
        @Override
        public String toString()
        {
            return "" + this.val;
        }
    }
}