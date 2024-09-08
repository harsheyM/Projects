public class NumberList
{
    private Integer[] list;
    private int size;

    NumberList()
    {
        list = new Integer[2];
        size = 0;
    }
    int size()
    {
        return this.size;
    }
    boolean isEmpty()
    {
        if (this.size == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public String toString()
    {
        String returnV = "[";
        for(int i = 0; i < size; i++)
        {
            if (i != size-1)
            {
               returnV += (list[i] + ", ");
            }
            else
            {
                returnV += list[i];
            }
        }
        return (returnV + "]");
    }

    private void doubleCapacity()
    {
        Integer[] listNew = new Integer[list.length*2];
        for (int i = 0; i < size; i++)
        {
            listNew[i] = list[i];
        }
        this.list = listNew;
    }

    void add(int index, Integer val)
    {
        if (index >= 0 && index <= size) {
            if (list.length >= size) {
                doubleCapacity();
            }
            for (int i = size; i > index; i--)
            {
    
                   list[i] = list[i-1];

            }
            list[index] = val;
            size++;
        }
        else
        {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        
    }
    
    boolean add(Integer element)
    {
        add(size, element);
        return true;
    }
    
    Integer get(int index)
    {
        if (index >= 0 || index <= size)
        {
            return list[index];
        }
        else
        {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
    }
    
    Integer set(int index, Integer val)
    {
        if (index >= 0 || index <= size)
        {
            Integer hold = list[index];
            list[index] = val;
            return hold;
        }
        else
        {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
    }
    
    Integer remove(int index)
    {
        if (index >= 0 && index <= size) {
            int a = list[index];
            for (int i = index; i <= size; i++)
            {
                   list[i] = list[i + 1];
            }
            size--;
            return a;
        }
        else
        {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
    }
}