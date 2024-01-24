package Tests;

import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class testforQuestion {

    Question q1;
    Question q2;
    Question q3;

    @BeforeEach
    void runbefore() {
        q1 = new Question("What is the capital of Canada?", "Ottawa", 1);
        q2 = new Question("Which city is UBC at", "Vancouver", 2);
        q3 = new Question("", "", 0);
    }

    @Test
    public void testgetCorrectanswer(){
        assertEquals("Ottawa", q1.getCorrectanswer());
        assertEquals("Vancouver", q2.getCorrectanswer());
        assertEquals("", q3.getCorrectanswer());
    }

    @Test
    public void testgetQuestion(){
        assertEquals("What is the capital of Canada?", q1.getQuestion());
        assertEquals("Which city is UBC at", q2.getQuestion());
        assertEquals("", q3.getQuestion());
    }

    @Test
    public void testcheckCorrect(){
        assertTrue(q1.checkcorrect("Ottawa", "Ottawa"));
        assertFalse(q1.checkcorrect("Ottaw", "Ottawa"));
        assertFalse(q1.checkcorrect("oTTAWA", "Ottawa"));
    }
}



