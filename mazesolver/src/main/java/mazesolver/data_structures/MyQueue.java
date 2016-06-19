package mazesolver.data_structures;

import java.util.Arrays;

/**
 * Offers implementation for an ArrayDeque. The queue's size is doubled whenever
 * an addition to a full queue is made.
 *
 * @author Marko Vainio
 * @param <Item>
 */
public class MyQueue<Item> {

    private Item[] table;
    private int head;
    private int tail;

    /**
     * Constructor where the user can set a size for the queue.
     *
     * @param initialSize
     */
    public MyQueue(int initialSize) {
        head = 0;
        tail = 0;
        if (initialSize > 0) {
            table = (Item[]) new Object[initialSize];
        } else {
            throw new ArrayIndexOutOfBoundsException("Size must above zero.");
        }
    }

    /**
     * Constructor where the queue's default size is 10.
     */
    public MyQueue() {
        this(10);
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return Returns true is the queue is empty.
     */
    public boolean isEmpty() {
        return head == tail;
    }

    /**
     * Checks whether the queue is full.
     *
     * @return Returns true is the queue is full.
     */
    public boolean isFull() {
        int tailnext = tail + 1;
        if (tailnext == table.length) {
            tailnext = 0;
        }
        return tailnext == head;
    }

    /**
     * Doubles the array's size.
     */
    public void doubleSize() {
        int start = 0;
        int max = Math.min(tail, table.length - 1);
        Item[] newTable = (Item[]) new Object[table.length * 2];
        for (int i = head; i < max; i++) {
            newTable[start] = table[i];
            start++;
        }
        if (head > tail) {
            for (int i = 0; i < tail; i++) {
                newTable[start] = table[i];
                start++;
            }
        }
        head = 0;
        tail = start;
        table = newTable;
    }

    /**
     * Adds an item to the tail of the queue.
     *
     * @param item Item to be added to the queue.
     */
    public void add(Item item) {
        if (isFull()) {
            doubleSize();
        }
        table[tail] = item;
        tail++;
        if (tail == table.length) {
            tail = 0;
        }
    }

    /**
     * Removes and returns an item from the head of the queue.
     *
     * @return Returns an item from the head of the queue.
     */
    public Item poll() {
        Item deleted = table[head];
        head++;
        if (head == table.length) {
            head = 0;
        }
        return deleted;
    }

    /**
     * Returns but doesn't remove the head of the queue.
     *
     * @return Returns but doesn't remove the head of the queue.
     */
    public Item peek() {
        return table[head];
    }

    /**
     * Makes a copy of the queue.
     *
     * @return Returns a copy of the queue.
     */
    public MyQueue<Item> clone() {
        MyQueue<Item> newQueue = new MyQueue<>(table.length);
        newQueue.setTable(Arrays.copyOf(table, table.length));
        newQueue.setHead(head);
        newQueue.setTail(tail);
        return newQueue;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setTable(Item[] table) {
        this.table = table;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public int tableSize() {
        return table.length;
    }
    
    

}
