import java.util.PriorityQueue;
import java.util.HashMap;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class HuffmanTree {
    public static int EOF = 256;
    PriorityQueue<Node> tree;
    HashMap<Integer, char[]> characterBits;
    
    public HuffmanTree(int[] count)
    {
        tree = new PriorityQueue<>();
        characterBits = new HashMap<>();
        
        for (int i = 0; i < count.length; i++)
        {
            if (count[i] > 0)
            {
                tree.offer(new Node(i, count[i]));
            }
            tree.offer(new Node(EOF, 1));
            makeTree();
            genCharBitsMap();
        }
    }
    public void makeTree()
    {
        while (tree.size() > 1)
        {
            tree.offer(tree.poll().combine(tree.poll()));
        }
    }
    public void addNode(int cur, String next)
    {
        Node current = tree.peek();
        for (char letter : next.toCharArray())
        {
            if (letter == '0')
            {
                if (current.left == null)
                {
                    current.left = new Node(current.weight);
                }
                else
                {
                    current = current.left;
                }
            }
            else
            {
                if (current.right == null)
                {
                    current.right = new Node(current.weight);
                }
                else
                {
                    current = current.right;
                }
            }
        }
        current.cur = cur;
    }
    public HuffmanTree(String codeFile)
    {
        tree = new PriorityQueue<>();
        characterBits = new HashMap<>();
        tree.offer(new Node(0));
        try {
            Scanner console = new Scanner(new File(codeFile));
            while (console.hasNext())
            {
                int num = console.nextInt();
                console.nextLine();
                String next = console.next();
                characterBits.put(num, next.toCharArray());
                addNode(num, next);
            }
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    
    public void encode(BitOutputStream out, String file)
    {
        try {
            BufferedReader console = new BufferedReader(new FileReader(file));
            char[] cur;
            while (console.ready())
            {
                cur = characterBits.get((int) console.read());
                for (int i = 0; i < cur.length; i++)
                {
                    out.writeBit(cur[i] - 48);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        for (char bit : characterBits.get(EOF))
        {
            if (bit == '0')
            {
                out.writeBit(0);
            }
            else
            {
                out.writeBit(1);
            }
        }
        out.close();
    }
    private void genCharBitsMap() {
        genCharBitsMap(tree.peek(),"");
    }

     private void genCharBitsMap(Node now, String path) {
        if (now == null) { return; }
        genCharBitsMap(now.left,path + "0");
        if (now.isLeaf())
        {
            characterBits.put(now.cur,path.toCharArray());
                }
        genCharBitsMap(now.right, path + "1");
    }
    
    public void decode(BitInputStream in, String file)
    {
        Node current = tree.peek();
        StringBuilder str = new StringBuilder("");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            while (true)
            {
                if (current.isLeaf())
                {
                    if (current.cur == EOF)
                    {
                        break;
                        }
                        str.append((char) current.cur);
                        current = tree.peek();
                    }
                    int bit = in.readBit();
                    if (bit == -1)
                    {
                        break;
                    }
                    current = bit == 0 ? current.left : current.right;
                }
                writer.write(str.toString());
                writer.close();
           } catch (IOException e)
           {
               e.printStackTrace();
           }
 }
 public void printTree()
 {
     TreePrinter.printTree(tree.peek());
 }
 public void write(String file)
 {
     try {
         FileWriter writer = new FileWriter(file);
         writer.write(writeTree(tree.peek(), ""));
         writer.close();
     } catch (IOException e)
     {
         e.printStackTrace();
     }
 }
 public String writeTree(Node current, String path)
{
    if (current == null)
    {
        return "";
    }
    String str = writeTree(current.left, path + "0");
    if (current.isLeaf())
    {
        str = str + current.cur + "\n" + path + "\n";
    }
    str = str + writeTree(current.right, path + "1");
    return str;
}
 
}