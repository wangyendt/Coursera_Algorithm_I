import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int len;
    private DeLinkedList first;
    private DeLinkedList last;

    private class DeLinkedList {
        private Item item;
        private DeLinkedList prev;
        private DeLinkedList next;
    }

    // construct an empty deque
    public Deque() {
        len = 0;
        first = last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return len == 0;
    }

    // return the number of items on the deque
    public int size() {
        return len;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        DeLinkedList oldFirst = first;
        first = new DeLinkedList();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) {
            last = first;
        } else {
            oldFirst.prev = first;
        }
        len++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        DeLinkedList oldLast = last;
        last = new DeLinkedList();
        last.item = item;
        last.prev = oldLast;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
        len++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            DeLinkedList oldFirst = first;
            first = first.next;
            Item item = oldFirst.item;
            oldFirst = null;
            len--;
            return item;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            DeLinkedList oldLast = last;
            last = last.prev;
            Item item = oldLast.item;
            oldLast = null;
            len--;
            return item;
        }
    }

    private class ListIterator implements Iterator<Item> {

        public Item next() {
            if (isEmpty()) throw new java.util.NoSuchElementException();

            DeLinkedList current = first;
            Item item = current.item;
            current = current.next;
            return item;

        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        public boolean hasNext() {

            return len > 0;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        StdOut.println(deque.removeLast());
    }

}