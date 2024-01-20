package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> base = new AListNoResizing<>();
        BuggyAList<Integer> bad = new BuggyAList<>();

        for (int i = 0; i < 3; i++) {
            base.addLast(i);
            bad.addLast(i);
        }

        for (int i = 0; i < 3; i++) {
            assertEquals(base.removeLast(), bad.removeLast());
        }
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                assertEquals(size, B.size());
            } else if (operationNumber == 2) {
                if (L.size() == 0) {
                    continue;
                }
                Integer val = L.getLast();
                assertEquals(val, B.getLast());
            } else if (operationNumber == 3) {
                if (L.size() == 0) {
                    continue;
                }
                Integer val = L.removeLast();
                assertEquals(val, B.removeLast());
            }
        }
    }
}
