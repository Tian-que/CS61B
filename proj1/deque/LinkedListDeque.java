package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class IntNode {
        private T item;
        private IntNode next;
        private IntNode prev;

        IntNode(T i, IntNode next, IntNode prev) {
            item = i;
            this.next = next;
            this.prev = prev;
        }
    }

    private IntNode sentinel;
    private IntNode endSentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        endSentinel = new IntNode(null, null, null);
        endSentinel.prev = sentinel;
        sentinel.next = endSentinel;
        size = 0;
    }

    public void addFirst(T x) {
        IntNode oldFirst = sentinel.next;
        sentinel.next = new IntNode(x, oldFirst, sentinel);
        oldFirst.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        IntNode oldLast = endSentinel.prev;
        endSentinel.prev = new IntNode(item, endSentinel, oldLast);
        oldLast.next = endSentinel.prev;
        size += 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode p = sentinel;
        for (int i = 0; i < size - 1; i++) {
            p = p.next;
            System.out.print(p.item + " ");
        }
        System.out.print(p.next.item);
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        IntNode firstNode = sentinel.next;
        sentinel.next = firstNode.next;
        sentinel.next.prev = sentinel;
        return firstNode.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        IntNode lastNode = endSentinel.prev;
        endSentinel.prev = lastNode.prev;
        endSentinel.prev.next = endSentinel;
        return lastNode.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        IntNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (o instanceof Deque) {
            Deque<T> ld = (Deque<T>) o;
            if (size != ld.size()) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (!get(i).equals(ld.get(i))) {
                    return false;
                }
            }

            return true;

        }
        return false;
    }

    private class LLDIterator implements Iterator<T> {
        private int now;
        private IntNode iterSentinel;

        LLDIterator() {
            now = 0;
            iterSentinel = sentinel;
        }

        @Override
        public boolean hasNext() {
            return size() != now;
        }

        @Override
        public T next() {
            iterSentinel = iterSentinel.next;
            now += 1;
            return iterSentinel.item;
        }
    }

    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }

    private T getRecursive(int index, IntNode p) {
        if (p.next == sentinel) {
            return null;
        }
        p = p.next;
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p);
    }
}
