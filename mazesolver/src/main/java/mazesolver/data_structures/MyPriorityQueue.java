package mazesolver.data_structures;

/**
 * Offers implementation for a minimum priority queue.
 *
 * @author Marko Vainio
 * @param <Item> Type of items which are stored in the queue. Items must
 * implement the Comparable interface.
 */
public class MyPriorityQueue<Item extends Comparable<Item>> {

    private Item[] table;
    private int size;

    /**
     * Constructor where the user can set custom size for the priorityqueue.
     *
     * @param initialSize
     */
    @SuppressWarnings("unchecked")
	public MyPriorityQueue(int initialSize) {
        table = (Item[]) new Comparable[initialSize];
        size = 0;
    }

    /**
     * Constructor where the default size for the priority queue is 10.
     */
    public MyPriorityQueue() {
        this(10);
    }

    /**
     * Returns index of this index's parent.
     *
     * @param index Current index
     * @return Index of this index's parent.
     */
    public int parent(int index) {
        return index / 2;
    }

    /**
     * Returns index of this index's left child.
     *
     * @param index Current index.
     * @return Index of this index's left child.
     */
    public int left(int index) {
        return 2 * index;
    }

    /**
     * Returns index of this index's right child.
     *
     * @param index Current index.
     * @return Index of this index's right child.
     */
    public int right(int index) {
        return 2 * index + 1;
    }

    /**
     * Orders the array so that it follows the rules of a priority queue.
     *
     * @param index Index from which the ordering begins.
     */
    public void heapify(int index) {
        int left = left(index);
        int right = right(index);
        int largest;
        Item item;
        if (right <= size) {
            if (table[left].compareTo(table[right]) == -1) {
                largest = left;
            } else {
                largest = right;
            }
            if (table[index].compareTo(table[largest]) == 1) {
                item = table[index];
                table[index] = table[largest];
                table[largest] = item;
                heapify(largest);
            }
        } else if (left == size && table[index].compareTo(table[left]) == 1) {
            item = table[index];
            table[index] = table[left];
            table[left] = item;
        }
    }

    /**
     * Returns and removes the first item in the queue e.g the item of smallest
     * value.
     *
     * @return The first item in the queue e.g the item of smallest value.
     */
    public Item poll() {
        Item item = table[0];
        table[0] = table[size - 1];
        size--;
        heapify(0);
        return item;
    }

    /**
     * Adds an item to the correct place in the queue. If the queue is full its
     * size is doubled.
     *
     * @param item Item to be added.
     */
    public void add(Item item) {
        if (size >= table.length) {
            doubleSize();
        }
        int index = size;
        while (index > 0 && table[parent(index)].compareTo(item) == 1) {
            table[index] = table[parent(index)];
            index = parent(index);
        }
        size++;
        table[index] = item;
    }

    /**
     * Doubles the size of the array.
     */
    @SuppressWarnings("unchecked")
	public void doubleSize() {
        Item[] newTable = (Item[]) new Comparable[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            newTable[i] = table[i];
        }
        table = newTable;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return Returns true if queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns but doesnt remove the first item in the queue.
     *
     * @return The first item in the queue e.g the item of smallest value.
     */
    public Item peek() {
        return table[0];
    }

    /**
     * Returns size of the array.
     *
     * @return Returns size of the array.
     */
    public int tableSize() {
        return table.length;
    }

}
