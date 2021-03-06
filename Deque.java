/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 14, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // number of items
    private int size;
    private int head;
    private int tail;

    private Item[] store;

    // construct an empty deque
    public Deque() {
        store = (Item[]) new Object[1];
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
        if (size > 0) {
            if (head == 0) {
                resizeHead(2 * size + store.length - tail - 1);
            }
            --head;
        }
        store[head] = item;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();
        if (size > 0) {
            if (tail == store.length - 1) {
                resizeTail(size + store.length);
            }
            ++tail;
        }
        store[tail] = item;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        if (size <= head / 2) {
            resizeHead(2 * size + store.length - tail - 1);
        }
        Item item = store[head];
        size--;
        store[head] = null;
        if (size > 0)
            head++;
        return item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        Item item = store[tail];
        size--;
        store[tail] = null;
        if (size > 0)
            tail--;
        if (size > 1 && size <= (store.length - tail - 1) / 2)
            resizeTail(tail + size + 1);
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private int current = head;

        public boolean hasNext() {
            return !isEmpty() && current <= tail;
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

    // private void outDeque() {
    //     for (int i = 0; i < store.length; i++) {
    //         StdOut.printf("%s ", store[i]);
    //     }
    //     StdOut.printf(": (%s-%s-%s)=%s%n",
    //                   head, size, store.length - tail - 1, store.length);
    // }

    private void resizeHead(int newSize) {
        int minSpace = size + 1;
        if (newSize < minSpace)
            throw new IndexOutOfBoundsException();

        Item[] newDeque = (Item[]) new Object[newSize];

        int freeAtHead = (newSize > store.length) ?
                         newSize - store.length :
                         newSize + head - store.length;
        for (int i = 0; i < size; i++) {
            newDeque[i + freeAtHead] = store[i + head];
        }

        store = newDeque;
        head = freeAtHead;
        tail = head + size - 1;
    }

    private void resizeTail(int newSize) {
        int minSpace = size + 1;
        if (newSize < minSpace)
            throw new IndexOutOfBoundsException();

        Item[] newDeque = (Item[]) new Object[newSize];
        for (int i = 0; i < tail + 1; i++) {
            newDeque[i] = store[i];
        }
        store = newDeque;
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();

        deque.addFirst(15);
        StdOut.printf("Added to head. Size deque: %s%n", deque.size());

        deque.addFirst(16);
        StdOut.printf("Added to tail. Size deque: %s%n", deque.size());

        StdOut.printf("Removed from tail: %s. ", deque.removeLast());
        StdOut.printf("Size deque: %s%n", deque.size());

        StdOut.printf("Removed from head: %s. ", deque.removeFirst());
        StdOut.printf("Size deque: %s%n", deque.size());

        for (int i = 0; i < 150; i++) {
            int r = StdRandom.uniform(0, 100);
            if (r < 25)
                deque.addFirst(r);
            else {
                if (r < 51)
                    deque.addLast(r);
                else {
                    if (!deque.isEmpty()) {
                        if (r < 76)
                            deque.removeFirst();
                        else
                            deque.removeLast();
                    }
                }
            }
        }
        StdOut.print("Random add/remove result: ");
        for (int i : deque)
            StdOut.printf("%s ", i);
        StdOut.println();
        // while (!StdIn.isEmpty()) {
        //     int value = StdIn.readInt();
        //     // System.out.printf("%s ", value);
        //     deque.addLast(value);
        //     // System.out.printf(" items: %s, size %s%n", deque.size(), deque.getLength());
        // }
        // StdOut.println();
    }

}
