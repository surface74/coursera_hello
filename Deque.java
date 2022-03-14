/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 14, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // number of items
    private int size;
    private int head;
    private int tail;
    private int minCapacity;

    private Item[] store;

    // construct an empty deque
    public Deque(int capacity) {
        store = (Item[]) new Object[capacity];
        size = 0;
        head = capacity / 2;
        tail = head;
        minCapacity = capacity;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == 0) {
            store[head] = item;
            size++;
            StdOut.printf("(head %s, tail %s)", head, tail);
            return;
        }
        if (head == 0) {
            resizeHead(size);
        }
        store[--head] = item;
        size++;
        StdOut.printf("(head %s, tail %s)", head, tail);
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size == 0) {
            store[tail] = item;
            size++;
            StdOut.printf("(head %s, tail %s)", head, tail);
            return;
        }
        if (tail == store.length - 1) {
            resizeTail(size);
        }
        store[++tail] = item;
        size++;
        StdOut.printf("(head %s, tail %s)", head, tail);
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        if (size)
            Item item = store[head];
        store[head++] = null;
        size--;

        if (size <= head / 4)
            resizeHead();
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item item = store[tail];
        store[tail--] = null;
        size--;
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int current = head;

        public boolean hasNext() {
            return (size > 0) && (current < tail);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            return store[current++];
        }
    }

    private void outDeque() {
        for (int i = 0; i < store.length; i++) {
            StdOut.printf("%s ", store[i]);
        }
        StdOut.println();
    }

    private void resizeHead(int newSize) {
        Item[] newDeque = (Item[]) new Object[newSize + store.length];
        for (int i = 0; i < size; i++) {
            newDeque[i + newSize] = store[i];
        }
        store = newDeque;
        head = newSize;
        tail = newSize + size - 1;
        StdOut.printf("(resizeHead to %s, head %s, tail %s)",
                      store.length, head, tail);
    }

    private void resizeTail(int newSize) {
        Item[] newDeque = (Item[]) new Object[newSize + store.length];
        for (int i = 0; i < store.length; i++) {
            newDeque[i] = store[i];
        }
        store = newDeque;
        StdOut.printf("(resizeTail to %s, head %s, tail %s)",
                      store.length, head, tail);
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>(20);

        while (!StdIn.isEmpty()) {
            int value = StdIn.readInt();
            System.out.printf("%s ", value);
            deque.addFirst(value);
            System.out.printf(" items: %s%n", deque.size());
        }
        StdOut.println();

        deque.outDeque();
    }

}
