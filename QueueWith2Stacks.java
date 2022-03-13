/* *****************************************************************************
 *  Name:              Searhei
 *  Coursera User ID:  123456
 *  Last modified:     March 13, 2022
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QueueWith2Stacks {
    private Stack<Integer> pushStack = new Stack<>();
    private Stack<Integer> popStack = new Stack<>();

    public void enqueue(int item) {
        pushStack.push(item);
    }

    public int dequeue() {
        if (popStack.isEmpty())
            while (!pushStack.isEmpty())
                popStack.push(pushStack.pop());

        if (popStack.isEmpty())
            throw new IndexOutOfBoundsException();

        return popStack.pop();
    }

    public boolean isEmpty() {
        return (pushStack.isEmpty() && popStack.isEmpty());
    }

    public static void main(String[] args) {
        QueueWith2Stacks q = new QueueWith2Stacks();

        while (!StdIn.isEmpty())
            q.enqueue(StdIn.readInt());

        while (!q.isEmpty())
            StdOut.printf("%d ", q.dequeue());

        StdOut.println();
    }
}
