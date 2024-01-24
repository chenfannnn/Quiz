package Tests;

import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;



/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Event a;
    private Event c;
    private Date d;
    private String z;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e = new Event("x");  // (1)
        a = new Event("x");

        c = new Event("Sensor open at doo");
        z = "plz";
        d = Calendar.getInstance().getTime();

    }

    @Test
    public void testEvent() {
        assertEquals("x", e.getDescription());
        assertEquals(d, e.getDate());
    }

    @Test
    public void testequalsnull() {
        assertFalse(e.equals(null));
    }
    @Test
    public void testequalsnotsamesubject() {
        assertFalse(e.equals(d));
        assertFalse(c.equals("123"));
    }

    @Test
    public void testequalsnotsame() {
        assertFalse(e.equals(c));
    }



    @Test
    public void testequalscorrect() {
        assertFalse(e.equals(z));
        assertEquals(a,e);
        assertTrue(a.equals(e));
        assertFalse(e.equals(z));

        Event l = new Event("x");
        assertFalse(l.equals(e));
        assertEquals(l,e);
    }

    @Test
    public void testHashCode() {
        assertNotEquals(e.hashCode(), c.hashCode());
        assertEquals(a.hashCode(), e.hashCode());
    }




    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "x", e.toString());
    }
}
