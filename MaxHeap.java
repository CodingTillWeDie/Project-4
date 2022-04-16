/** Class to create max heaps using both the sequential and optimal methods. */
public final class MaxHeap implements MaxHeapInterface
{
    private int[] heap; // Array of heap entries
    private int lastIndex; // Index of last entry
    private boolean initialized = false;
    private int numSwaps = 0;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap()
    {
        this(DEFAULT_CAPACITY); // Call next constructor
    } // end of default constructor

    public MaxHeap(int initialCapacity)
    {
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity + 1);

        int[] tempHeap = new int[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    } // end of type constructor #1

    public MaxHeap(int[] entries)
    {
        this(entries.length); // call the other type constructor.
        lastIndex = entries.length;
        assert initialized = true;

        // copy given array to data field.
        for (int index = 0; index < entries.length; index++)
        {
            heap[index + 1] = entries[index];
        }

        // create a max-heap.
        for (int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
        {
            reheap(rootIndex);
        }
    } // end of type constructor #2

    /** The smart way of creating a heap
     @param rootIndex  The index of the root. */
    public void reheap(int rootIndex)
    {
        boolean done = false;
        int orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done && (leftChildIndex <= lastIndex))
        {
            int largerChildIndex = leftChildIndex; // assume that left child is larger.
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex)
                    && (heap[rightChildIndex] > heap[largerChildIndex]))
            {
                largerChildIndex = rightChildIndex; // right child is larger than left child.
            } // end if

            if (orphan < heap[largerChildIndex])
            {
                heap[rootIndex] = heap[largerChildIndex];
                // increment the number of swaps.
                numSwaps++;
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;

        } // end while

        heap[rootIndex] = orphan;
    } // end of reheap

    public void add(int newEntry)
    {
        checkInitialization(); // ensure initialization of data fields.
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;

        // perform up-heap operation in order to add an entry.
        while ((parentIndex > 0) && newEntry > heap[parentIndex])
        {
            heap[newIndex] = heap[parentIndex];
            numSwaps++;
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } // end while

        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } // end of add

    public int removeMax()
    {
        checkInitialization(); // ensure initialization of data fields.
        int root = -1;

        if (!isEmpty())
        {
            root = heap[1]; // return value
            heap[1] = heap[lastIndex]; // form a semi-heap.
            lastIndex--; // decrease size.
            reheap(1); // Transform to a heap.
        } // end if

        return root;
    } // end of removeMax

    public int getMax()
    {
        checkInitialization(); // ensure initialization of data fields.
        int root = -1;

        if (!isEmpty())
            root = heap[1];

        return root;
    } // end of getMax

    public boolean isEmpty()
    {
        return lastIndex < 1;
    } // end of isEmpty

    public int getSize()
    {
        return lastIndex;
    } // end of getSize

    public int getNumSwaps()
    {
        return numSwaps;
    }

    public void clear()
    {
        checkInitialization(); // ensure initialization of data fields.

        heap = null;
        lastIndex = 0;

    } // end of clear

    public int[] getHeap()
    {
        return heap;
    }

    private void checkCapacity(int initialCapacity)
    {
        if (initialCapacity > MAX_CAPACITY)
        {
            throw new IllegalStateException("Attempted to create an array "
                    + "which exceeds the allowed maximum capacity of "
                    + MAX_CAPACITY + "...");
        }
    } // end of checkCapacity

    private void checkInitialization()
    {
        // check to see if MaxHeap object is initialized.
        if (!initialized)
            throw new SecurityException("MaxHeap object is corrupted...");
    } // end of checkInitialization

    private void ensureCapacity()
    {
        // check to see if the number of entries in the heap
        // array has exceeded the maximum capacity.
        checkCapacity(lastIndex);
    } // end of ensureCapacity

} // end of "MaxHeap.java"
