//Harshitha Mahesh
//3A

public class Lock
{
    private boolean locked;
    
    public Lock()
    {
        locked = true;
    }
    void open()
    {
        locked = false;
    }
    void close()
    {
        locked = true;
    }
    boolean isLocked()
    {
        return locked;
    }
    public String toString()
    {
        if (locked == false)
        {
             return "Lock is open";
        }
        else
        {
             return "Lock is closed";
        }
    }
}