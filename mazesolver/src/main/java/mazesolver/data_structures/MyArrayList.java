package mazesolver.data_structures;

import java.util.Iterator;

/**
 *
 * @author Marko Vainio
 * @param <Item>
 */
public class MyArrayList<Item> implements Iterable<Item> {

    private Item[] table;
    private int size;

    public MyArrayList(int initialSize) {
        if (initialSize > 0) {
            table = (Item[]) new Object[initialSize];
        } else {
            throw new ArrayIndexOutOfBoundsException("Size must above zero.");
        }
    }

    public MyArrayList() {
        this(10);
    }

    public void doubleSize() {
        Item[] newTable = (Item[]) new Object[table.length * 2];
        for (int i = 0; i < table.length; i++) {
            newTable[i] = table[i];
        }
        table = newTable;
        size = 0;
    }

    public void add(Item item) {
        table[size] = item;
        size++;
    }

    public Item get(int index) {
        if (index >= 0 && index <= size) {
            Item item = table[index];
            return item;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

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
        Iterator iterator = new Iterator<Item>() {
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

}
