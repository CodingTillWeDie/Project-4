/** An interface for the Heap. */
public interface MaxHeapInterface
{
    /** Adds a new entry to the heap. Uses an up-heap operation
     @param newEntry  An object to be added to the heap. */
    public void add(int newEntry);

    /** Removes and returns this heap's root entry. Uses a down-heap operation.
     @return  The root of the heap. */
    public int removeMax();

    /** Returns this heap's root entry.
     @return  The root of the heap. */
    public int getMax();

    /** Detects whether this stack is empty.
     @return  True if the stack is empty. */
    public boolean isEmpty();

    /** Gets the size of this heap.
     @return  The value of lastIndex. */
    public int getSize();

    /** Removes all entries from this heap. */
    public void clear();

    /** Gets an array of ints from the heap.
     @return  The array of ints heap. */
    public int[] getHeap();

    /** Gets the number of swaps done in heap creation.
     @return  The number of swaps. */
    public int getNumSwaps();
} // end of "MaxHeapInterface"
