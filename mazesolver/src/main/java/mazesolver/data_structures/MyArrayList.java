package mazesolver.data_structures;

import java.util.Iterator;

/**
 * Offers implementation for an ArraList.
 *
 * @author Marko Vainio
 * @param <Item> Type of items which are stored in the list.
 */
public class MyArrayList<Item> implements Iterable<Item> {

    private Item[] table;
    private int size;

    /**
     * Constructor where the user can set custom size for the list.
     *
     * @param initialSize Size of the list.
     */
    @SuppressWarnings("unchecked")
	public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            table = (Item[]) new Object[initialSize];
        } else {
            throw new ArrayIndexOutOfBoundsException("Size must above zero.");
        }
    }

    /**
     * Constructor where the default size is 10.
     */
    public MyArrayList() {
        this(10);
    }

    /**
     * Doubles the size of the array.
     */
    @SuppressWarnings("unchecked")
	public void doubleSize() {
        Item[] newTable = (Item[]) new Object[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            newTable[i] = table[i];
        }
        table = newTable;
    }

    /**
     * Adds an item to the list.
     *
     * @param item Item to be added.
     */
    public void add(Item item) {
        if (size >= table.length) {
            doubleSize();
        }
        table[size] = item;
        size++;
    }

    /**
     * Returns an item from the specified index.
     *
     * @param index Index of the item.
     * @return Item from the index.
     */
    public Item get(int index) {
        if (index >= 0 && index <= size) {
            Item item = table[index];
            return item;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Removes item from the specified index.
     *
     * @param index Index from where the item will be removed.
     * @return Returns item that was removed.
     */
    public Item remove(int index) {
        if (index >= 0 && index < size) {
            Item item = table[index];
            table[index] = null;
            while (index < size - 1) {
                table[index] = table[index + 1];
                table[index + 1] = null;
                index++;
            }
            size--;
            return item;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<Item> iterator() {
        Iterator<Item> iterator = new Iterator<Item>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor < size;
            }

            @Override
            public Item next() {
                return table[cursor++];
            }
        };
        return iterator;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public int tableSize() {
        return table.length;
    }

}
