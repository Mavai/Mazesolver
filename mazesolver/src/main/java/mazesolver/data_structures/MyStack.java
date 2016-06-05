package mazesolver.data_structures;

/**
 * Provides implementation for a "first in first out" type datastructure using
 * generic arrays. The array grows automatically if limit is reached.
 *
 * @author Marko Vainio
 * @param <Item> Defines the type of items stored in the stack.
 */
public class MyStack<Item> {

    private Item[] table;
    private int top;

    /**
     * Constructor which uses 10 as the default size of the stack.
     */
    public MyStack() {
        this(10);
    }

    /**
     * Constructor for MyStack with size parameter.
     *
     * @param initialSize Size of the stack.
     */
    public MyStack(int initialSize) {
        table = (Item[]) new Object[initialSize];
        top = -1;
    }

    /**
     * Returns the first item of the stack without removing it.
     *
     * @return First item in the stack.
     */
    public Item peek() {
        if (top == -1) {
            return null;
        }
        return table[top];
    }

    /**
     * Removes and returns the first item in the stack.
     *
     * @return Returns the first item in the stack.
     */
    public Item pop() {
        if (top == -1) {
            return null;
        }
        Item delete = table[top];
        top--;
        return delete;
    }

    /**
     * Adds item to the front of the stack.
     *
     * @param add Item to be added to the stack.
     */
    public void push(Item add) {
        top++;
        if (top >= table.length) {
            doubleSize();
        }
        table[top] = add;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return Returns true if the stack is empty.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Doubles the stack's size.
     */
    public void doubleSize() {
        Item[] newTable = (Item[]) new Object[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            newTable[i] = table[i];
        }
        table = newTable;
    }
    
    public int arraySize() {
        return table.length;
    }

}
