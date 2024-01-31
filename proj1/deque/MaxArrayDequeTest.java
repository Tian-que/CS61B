package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class MaxArrayDequeTest {

    @Test
    public void myTest() {
        MaxArrayDeque<Integer> lld1 = new MaxArrayDeque<>(Integer::compareTo);
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);

        assertEquals("", (int) lld1.max(), 2);
        assertEquals("", (int) lld1.max(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        }), 0);
    }
}
