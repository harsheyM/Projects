public class MinHeap {
    Integer[] heap;
    int size;
    int lastIndex = 1;
    static final int DEFAULT_CAPACITY = 9;

    public MinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MinHeap(int capacity) {
        heap = new Integer[capacity + 1];
        size = 0;
    }

    int getSize() {
        return size;
    }

    boolean isEmpty() {
        return this.size == 0;
    }

    int peekMinimum() {
        return heap[1];
    }

    int getLeftChildIndex(int index) {
        return 2 * index;
    }

    int getRightChildIndex(int index) {
        return 2 * index + 1;
    }

    int getParentIndex(int index) {
        return index / 2;
    }

    private void doubleCapacity() {
        Integer[] heapNew = new Integer[size * 2 + 1];
        for (int i = 1; i < heap.length; i++) {
            heapNew[i] = heap[i];
        }
        this.heap = heapNew;
    }

    void insert(int value) {

        if (size + 1 >= heap.length)
        {
            doubleCapacity();
        }
        heap[size + 1] = value;
        bubbleUp(size + 1);
        size++;


    }
    private void bubbleUp(int index) {
        if (index > 1 && heap[getParentIndex(index)] > heap[index])
        {
            mix(index, getParentIndex(index));
            bubbleUp(getParentIndex(index));
        }
    }
    private void mix(int parent, int child)
    {
        Integer temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    int popMinimum() {
        int min = peekMinimum();
        heap[1] = heap[size];
        size--;
        siftDown(1);
        return min;
    }

    private void siftDown(int index) {
        if (index * 2 > size)
        {
            return;
        }
        if (getRightChildIndex(index) > size)
        {
            if (heap[getLeftChildIndex(index)] < heap[index])
            {
                mix(index, getLeftChildIndex(index));
            }
        }
        else if (heap[index] > heap[getLeftChildIndex(index)] || heap[index] > heap[getRightChildIndex(index)])
        {
            if (heap[getLeftChildIndex(index)] < heap[getRightChildIndex(index)])
            {
                mix(index, getLeftChildIndex(index));
                siftDown(getLeftChildIndex(index));
            }
            else
            {
                mix(index, getRightChildIndex(index));
                siftDown(getRightChildIndex(index));
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 1; i <= getSize(); i++)
            output += heap[i] + ", ";
        return output.substring(0, output.lastIndexOf(","));
//lazily truncate last comma
    }

    /**
     * method borrowed
     * with minor modifications
     * from the internet somewhere, for printing
     */
    public void display() {
        int nBlanks = 32, itemsPerRow = 1, column = 0, j = 1;
        String dots = "...............................";
        System.out.println(dots + dots);
        while (j <= this.getSize()) {
            if (column == 0)
                for (int k = 0; k < nBlanks; k++)
                    System.out.print(' ');
            System.out.print((heap[j] == null) ? "" : heap[j]);
            if (++column == itemsPerRow) {
                nBlanks /= 2;
                itemsPerRow *= 2;
                column = 0;
                System.out.println();
            } else
                for (int k = 0; k < nBlanks * 2 - 2; k++)
                    System.out.print(' ');
            j++;
        }
        System.out.println("\n" + dots + dots);

    }
}