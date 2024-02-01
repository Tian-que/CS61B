package flik;

import org.junit.Test;
import static org.junit.Assert.*;

public class FlikTest {

    @Test
    public void flikEqTest() {
        assertTrue(Flik.isSameNumber(10, 10));
        assertTrue(Flik.isSameNumber(128, 128));
    }
}
