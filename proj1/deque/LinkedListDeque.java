package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
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
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new IntNode(null, null, null);
        sentinel.next = new IntNode(x, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = size + 1;
    }

    public void addFirst(T x) {
        IntNode oldFirst = sentinel.next;
        sentinel.next = new IntNode(x, oldFirst, sentinel);
        oldFirst.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item) {
        IntNode oldLast = sentinel.prev;
        sentinel.prev = new IntNode(item, sentinel, oldLast);
        oldLast.next = sentinel.prev;
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
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return firstNode.item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        IntNode lastNode = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return lastNode.item;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        IntNode p = sentinel;
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

        if (this.getClass() == o.getClass()) {
            LinkedListDeque<T> ld = (LinkedListDeque<T>) o;
            if (size != ld.size()) {
                return false;
            }

            for (int i = 0; i < size; i++) {
                if (get(i) != ld.get(i)) {
                    return false;
                }
            }

            return true;

        }
        return false;
    }

    public class LLDIterator implements Iterator<T> {
        private int now;
        private IntNode iterSentinel;

        public LLDIterator() {
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
        return getRecursive(index - 1, p.next);
    }
}
