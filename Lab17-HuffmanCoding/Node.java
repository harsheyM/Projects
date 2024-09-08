public class Node implements Comparable<Node>
{
    int weight;
    int cur;
    Node left, right;
    public Node(int weight)
    {
        this.weight = weight;
        this.cur = -1;
    }
    public Node(int count, int weight)
    {
        this.cur = cur;
        this.weight = weight;
    }
    public Node(Node num1, Node num2)
    {
        this(num1.weight + num2.weight);
        this.left = num1;
        this.right = num2;
    }
    public Node combine(Node num)
    {
        return new Node(this, num);
    }
    
    public int compareTo(Node o) {
        return weight - o.weight;
    }
    
    public boolean isLeaf()
    {
        return left == right && right == null;
    }
    public String toString()
    {
        if (isLeaf())
        {
            return "" + (char) cur;
        }
        {
            return weight + "";
        }
    }
}

