package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        Node prev;
        T item;
        Node next;

        Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node oldFirst = sentinel.next;
        Node newNode = new Node(sentinel, item, oldFirst);
        sentinel.next = newNode;
        oldFirst.prev = newNode;
        size += 1;
    }

    public void addLast(T item) {
        Node oldLast = sentinel.prev;
        Node newNode = new Node(oldLast, item, sentinel);
        oldLast.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
        T returnItem = first.item;
        Node second = first.next;
        sentinel.next = second;
        second.prev = sentinel;
        size -= 1;
        return returnItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = sentinel.prev;
        T returnItem = last.item;
        Node secondToLast = last.prev;
        secondToLast.next = sentinel;
        sentinel.prev = secondToLast;
        size -= 1;
        return returnItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, index - 1);
    }


    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private Node p;
        LinkedListDequeIterator() {
            p = sentinel.next;
        }
        public boolean hasNext() {
            return p != sentinel;
        }
        public T next() {
            T returnItem = p.item;
            p = p.next;
            return returnItem;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> other = (Deque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++){
            if (!(this.get(i).equals(other.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
