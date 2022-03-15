/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 15, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size;
    private int head;
    private int tail;

    private Item[] store;

    // construct an empty randomized queue
    public RandomizedQueue() {
        store = (Item[]) new Object[1];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
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

    // remove and return a random item
    public Item dequeue() {
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

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new java.util.NoSuchElementException();

        int index;
        if (size == 1)
            index = head;
        else
            index = StdRandom.uniform(head, tail + 1);

        return store[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int current = 0;
        private int[] index = new int[size()];

        private RandomizedQueueIterator() {
            for (int i = 0; i < index.length; i++) {
                index[i] = head + i;
            }
            StdRandom.shuffle(index);
        }

        public boolean hasNext() {
            return !isEmpty() && current < index.length;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException();

            return store[index[current++]];
        }
    }

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

    // private void outDeque() {
    //     for (int i = 0; i < store.length; i++) {
    //         StdOut.printf("%s ", store[i]);
    //     }
    //     StdOut.printf(": (%s-%s-%s)=%s%n",
    //                   head, size, store.length - tail - 1, store.length);
    // }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        queue.enqueue(15);
        StdOut.printf("Added to queue. Size queue: %s%n", queue.size());

        queue.enqueue(16);
        StdOut.printf("Added to queue. Size queue: %s%n", queue.size());

        StdOut.printf("Random item: %s%n", queue.sample());

        StdOut.printf("Removed from queue: %s. ", queue.dequeue());
        StdOut.printf("Size deque: %s%n", queue.size());

        StdOut.printf("Removed from queue: %s. ", queue.dequeue());
        StdOut.printf("Size deque: %s%n", queue.size());

        for (int i = 0; i < 150; i++) {
            int r = StdRandom.uniform(0, 100);
            if (r < 50)
                queue.enqueue(r);
            else if (!queue.isEmpty()) {
                queue.dequeue();
            }
        }

        StdOut.print("Random iterator: ");
        for (int i : queue)
            StdOut.printf("%s ", i);
        StdOut.println();
    }

}
