
public class MyLinkedList {
    private ListNode head;
    private ListNode tail;
    private int size;

    MyLinkedList()
    {
        this.head = null;
        this.tail = null;
        size = 0;
    }
    MyLinkedList(int val)
    {
        this.head = new ListNode(val);
        tail = head;
        size = 1;
    }


    //3
    void add(int newVal)
    {
        ListNode temp = this.head;
        if (this.head == null)
        {
             this.head = new ListNode(newVal);
        }
        else
        {
            while (temp != null)
            {
                if (temp.next == null)
                {
                    temp.next = new ListNode(newVal);
                    tail = temp.next;
                    break;

                }
                temp = temp.next;
            }
        }
        size++;
    }

    //4
    boolean contains(int target)
    {
        ListNode temp = this.head;
        boolean contains = false;
        while (temp != null)
        {
            if (temp.getVal() == target)
            {
                contains = true;
            }
            temp = temp.next;
        }
        return contains;
    }

    //5
    int get(int index)
    {
        int value = 0;
        ListNode temp = this.head;
        if (index >= size())
        {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        int count = 0;
        while (temp != null)
        {
            if (count == index)
            {
                value = temp.getVal();
            }
            count++;
            temp = temp.next;
        }
        return value;
    }

    //6
    int indexOf(int target)
    {
        int count = 0;
        int index = 0;
        boolean indexF = false;
        ListNode temp = this.head;
        while (temp != null)
        {
            if(temp.getVal() == target)
            {
                index = count;
                indexF = true;
                break;
            }
            count++;
            temp = temp.next;
        }
        if (indexF)
        {
            return index;
        }
        else
        {
            return -1;
        }
    }

    //7
    void set(int newVal, int index)
    {
        ListNode temp = this.head;
        int count = 0;
        if (index >= size())
        {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        while(temp != null)
        {
            if (count == index)
            {
                temp.setVal(newVal);
            }
            count++;
            temp = temp.next;
        }
    }

    //8
    int size()
    {
        ListNode temp = this.head;
        int size = 0;
        while (temp != null)
        {
            size++;
            temp = temp.next;
        }
        return this.size;
    }

    int sizeRecursive(ListNode current)
    {
        if (current == null)
        {
            return 0;            
        }
        return 1 + sizeRecursive(current.next);
    }

    //9
    boolean isEmpty()
    {
        return (size() == 0);
    }

    //10
    int remove(int index)
    {
        if (index >= size() || index < 0)
        {
            throw new IndexOutOfBoundsException("Index is Out of Bounds!");
        }
        int value = 0;
        int count = 0;
        ListNode newV = this.head;
        ListNode temp = this.head;
        while (temp != null)
        {
            if (count == index)
            {
                value = temp.getVal();
            }
            count++;
            temp = temp.next;

        }
        count = 0;
        if (index != 0) {
            while (newV != null) {
                if (count + 1 == index) {
                    newV.next = newV.next.next;
                    break;
                }
                newV = newV.next;
                count++;
            }
        }
        else
        {
            head = newV.next;
        }
        size--;
        return value;
    }

    //11
    void add(int newVal, int index)
    {
        if (index > size() || index < 0)
        {
            throw new IndexOutOfBoundsException("Index is Out of Bounds!");
        }
        size++;
        ListNode temp = this.head;
        ListNode newV = new ListNode(newVal);
        int count = 0;
        if (index != 0 && index < size()) {
            while (count < index - 1)
            {
                count++;
                temp = temp.next;
            }
            newV.next = temp.next;
            temp.next = newV;
        }
        else if (index == 0)
        {
            newV.next = head;
            head = newV;
        }
        else if (index == size - 1)
        {
            tail.next = newV;
            tail = tail.next;
        }
        else if (head == null)
        {
            head = newV;
            tail = head;
        }
    }

    //12
    @Override
    public String toString()
    {
        ListNode temp = this.head;
        String total = "[";
        while (temp != null)
        {
            if (temp.next == null)
            {
                total += temp;
            }
            else {
                total += (temp + ", ");
            }
            temp = temp.next;
        }
        return total + "]";
    }

    private class ListNode
    {
        int val;
        ListNode next;

        public ListNode(int val) { this.val = val; }

        public void setVal(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
        @Override
        public String toString()
        { return "" + this.val; }

    }
    
}