import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Disk implements Comparable<Disk> {
    private int storage;
    ArrayList<Integer> files = new ArrayList<Integer>();
    private int id;
    static int num;
    public Disk()
    {
        storage = 0;
        id = num;
        num++;
    }
    public int id()
    {
        return id;
    }
    public void addFile(int size)
    {
        files.add(size);
        storage += size;
    }
    public boolean canStore(int size)
    {
        return 1_000_000 - storage - size >= 0;
    }
    public int spaceLeft()
    {
        return 1_000_000 - storage;
    }
    @Override
    public int compareTo(Disk other)
    {
        return storage - other.storage;
    }

    @Override
    public String toString()
    {
        String str = "\t" + String.format("%1$" + (num + "").length() + "s",id + "") + " " + String.format("%1$7s",(1000000 - storage) + "") + ":";
      if (files.size() <= 100) 
         for (Integer file : files)
            str = str + " " + file;
      return str;
    }
}