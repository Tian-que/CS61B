package tester;

import static org.junit.Assert.*;

import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {

    @Test
    public void RandomStudentADTest() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            double ctrl = StdRandom.uniform();
            if (ctrl < 0.2) {
                sad.addFirst(i);
                ads.addFirst(i);
                sb.append("addFirst(").append(i).append(")").append('\n');
            } else if (ctrl < 0.4) {
                sad.addLast(i);
                ads.addLast(i);
                sb.append("addLast(").append(i).append(")").append('\n');
            } else if (ctrl < 0.6) {
                if (!(sad.isEmpty() && ads.isEmpty())) {
                    sb.append("removeFirst(").append(")").append('\n');
                    assertEquals(sb.toString().strip(), sad.removeFirst(), ads.removeFirst());
                }
            } else if (ctrl < 0.8) {
                if (!(sad.isEmpty() && ads.isEmpty())) {
                    sb.append("removeLast(").append(")").append('\n');
                    assertEquals(sb.toString().strip(), sad.removeLast(), ads.removeLast());
                }
            } else {
                sb.append("isEmpty(").append(")").append('\n');
                assertEquals(sb.toString().strip(), sad.isEmpty(), ads.isEmpty());
            }
        }
    }
}
