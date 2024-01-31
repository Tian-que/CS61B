package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] arrays;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        arrays = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        nextLast = size;
        nextFirst = a.length - 1;
        arrays = a;
    }

    public void addFirst(T x) {
        if (size == arrays.length) {
            resize((int) (size * 2));
        }
        size += 1;
        arrays[nextFirst] = x;
        nextFirst = (nextFirst - 1 + arrays.length) % arrays.length;
    }

    public void addLast(T x) {
        if (size == arrays.length) {
            resize((int) (size * 2));
        }
        size += 1;
        arrays[nextLast] = x;
        nextLast = (nextLast + 1) % arrays.length;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size - 1; i++) {
            System.out.print(arrays[i] + " ");
        }
        System.out.print(arrays[size - 1]);
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        if (size <= arrays.length * 0.25 && size >= 16) {
            resize((int) (size * 2));
        }

        size -= 1;
        nextFirst = (nextFirst + 1) % arrays.length;
        return arrays[nextFirst];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size <= arrays.length * 0.25 && size >= 16) {
            resize((int) (size * 2));
        }
        size -= 1;
        nextLast = (nextLast - 1 + arrays.length) % arrays.length;
        return arrays[nextLast];
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return arrays[(index + nextFirst + 1) % arrays.length];
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (this.getClass() == o.getClass()) {
            ArrayDeque<T> ld = (ArrayDeque<T>) o;
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

    private class ADIterator implements Iterator<T> {
        private int now;

        ADIterator() {
            now = 0;
        }

        @Override
        public boolean hasNext() {
            return size() != now;
        }

        @Override
        public T next() {
            T retValue = get(now);
            now += 1;
            return retValue;
        }
    }

    public Iterator<T> iterator() {
        return new ADIterator();
    }
}
