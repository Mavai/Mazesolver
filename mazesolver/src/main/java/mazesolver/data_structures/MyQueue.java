
package mazesolver.data_structures;

import java.util.Arrays;

/**
 *
 * @author Marko Vainio
 * @param <Item>
 */
public class MyQueue<Item> {
    private Item[] table;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
	public MyQueue(int initialSize) {
        head = 0;
        tail = 0;
        if (initialSize > 0) {
            table = (Item[]) new Object[initialSize];
        } else {
            throw new ArrayIndexOutOfBoundsException("Size must above zero.");
        }
    }

    public MyQueue() {
        this(10);
    }
    
    public boolean isEmpty() {
        return head == tail;
    }
    
    public boolean isFull() {
        int tailnext = tail + 1;
        if (tailnext == table.length) {
            tailnext = 0;
        }
        return tailnext == head;
    }
    
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
    
    public Item poll() {
        Item deleted = table[head];
        head++;
        if (head == table.length) {
            head = 0;
        }
        return deleted;
    }
    
    public Item peek() {
        return table[head];
    }
    
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
    
    
    
    
    
    
    
}
