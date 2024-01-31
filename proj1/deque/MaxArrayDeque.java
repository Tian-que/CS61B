package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{

    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T m = get(0);
        for (int i = 0; i < size(); i++) {
            if (c.compare(m ,get(i)) > 0){
                m = get(i);
            }
        }
        return m;
    }
}
