public final class MaxHeap<T extends Comparable<? super T>>
        implements MaxHeapInterface<T>
{
    private T[] heap; //Array of heap entries
    private int lastIndex; //Index of last entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public MaxHeap() {
        this(DEFAULT_CAPACITY); //Call next constructor
    } //end default constructor

    public MaxHeap(int initialCapacity) {
        if(initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else
            checkCapacity(initialCapacity);
        

        //The cast is safe because the array contains all null entries
        @SuppressWarnings("unchecked")
        T[] tempHeap = (T[]) new Comparable[initialCapacity + 1];
        heap = tempHeap;
        lastIndex = 0;
        initialized = true;
    } //end constructor

    public MaxHeap(T[] entries) {
        this(entries.length); //call another constructor
        assert initialized = true;

        //copy given array to data field
        for(int index = 0; index < entries.length; index++)
            heap[index + 1] = entries[index];
        //create heap
        for(int rootIndex = lastIndex / 2; rootIndex > 0; rootIndex--)
            reheap(rootIndex);
    } //end constructor

    private void reheap(int rootIndex) {
        boolean done = false;
        T orphan = heap[rootIndex];
        int leftChildIndex = 2 * rootIndex;

        while(!done && (leftChildIndex <= lastIndex)) {
            int largerChildIndex = leftChildIndex; //assume larger
            int rightChildIndex = leftChildIndex + 1;
            if ((rightChildIndex <= lastIndex) && heap[rightChildIndex].compareTo(heap[largerChildIndex]) > 0) {
                largerChildIndex = rightChildIndex;
            } //end if

            if(orphan.compareTo(heap[largerChildIndex]) < 0) {
                heap[rootIndex] = heap[largerChildIndex];
                rootIndex = largerChildIndex;
                leftChildIndex = 2 * rootIndex;
            }
            else
                done = true;
        } //end while
        heap[rootIndex] = orphan;
    } //end reheap
    public void add(T newEntry) {
        checkInitialization(); //ensure initialization of data fields
        int newIndex = lastIndex + 1;
        int parentIndex = newIndex / 2;
        while((parentIndex > 0) && newEntry.compareTo(heap[parentIndex]) > 0) {
            heap[newIndex] = heap[parentIndex];
            newIndex = parentIndex;
            parentIndex = newIndex / 2;
        } //end while
        heap[newIndex] = newEntry;
        lastIndex++;
        ensureCapacity();
    } //end add

    public T removeMax() {
        checkInitialization(); //ensure initialization of data fields
        T root = null;

        if(!isEmpty()) {
            root = heap[1]; //return value
            heap[1] = heap[lastIndex]; //form a semiheap
            lastIndex--; //decrease size
            reheap(1); //Transform to a heap
        } //end if
        return root;
    } //end removeMax

    public T getMax() {
        checkInitialization();
        T root = null;
        if(!isEmpty())
            root = heap[1];
        return root;
    } //end getMax

    public boolean isEmpty() {
        return lastIndex < 1;
    } //end isEmpty

    public int getSize() {
        return lastIndex;
    } //end getSize

    public void clear() {
        checkInitialization();
        while(lastIndex > -1) {
            heap[lastIndex] = null;
            lastIndex--;
        } //end while
        lastIndex = 0;
    } //end clear
    private int checkCapacity(int initialCapacity) { //this is not given so idk if i did it right
        return heap.length;
    } //end checkCapacity
    private void checkInitialization() { //this is not given so idk if i did it right
        if (!initialized)
            throw new SecurityException
                    ("ArrayBag object is not initialized "
                            + "properly.");
    } //end checkInitialization
    private int ensureCapacity() { //this is not given so idk if i did it right
        return DEFAULT_CAPACITY;
    } //end ensureCapacity
} //end MaxHeap.java

