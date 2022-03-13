/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class StackWithMax {
    private class Node {
        private double value;
        private double maxValue;

        private Node(double data, double maxData) {
            value = data;
            maxValue = maxData;
        }
    }

    private Stack<Node> stack = new Stack<>();

    public double pop() {
        if (stack.isEmpty())
            throw new IndexOutOfBoundsException();

        return stack.pop().value;
    }

    public void push(double value) {
        double max;
        if (!isEmpty()) {
            Node node = stack.pop();
            max = Math.max(value, node.maxValue);
            stack.push(node);
        }
        else {
            max = value;
        }
        stack.push(new Node(value, max));
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public double getMax() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();

        Node node = stack.pop();
        stack.push(node);
        return node.maxValue;
    }

    public static void main(String[] args) {
        StackWithMax s = new StackWithMax();

        while (!StdIn.isEmpty())
            s.push(StdIn.readInt());

        while (!s.isEmpty())
            StdOut.printf("max %s, value %s%n", s.getMax(), s.pop());
    }
}
