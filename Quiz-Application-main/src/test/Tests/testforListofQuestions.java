package Tests;

import model.ListOfQuestions;
import model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;

public class testforListofQuestions {

    ListOfQuestions loq;

    @BeforeEach
    void runbefore() {
        loq = new ListOfQuestions("hi");
    }
    @Test
    void testaddQuestion(){
        Question q1 = new Question("What is the capital of Canada?", "Ottawa", 1);
        Question q2 = new Question("Which city is UBC at?", "Vancouver", 2);
        Question q3 = new Question("Who is the prime minister of Canada?", "Justin Trudeau", 3);
        Question q4 = new Question("Who owns Tesla?","Elon Musk", 1);

        loq.getListOfQuestions().size();


        assertEquals(0, loq.getListOfQuestions().size());
        loq.addQuestion(q1);
        assertEquals(q1, loq.getListOfQuestions().get(0));
        assertEquals(1, loq.getListOfQuestions().size());

        loq.addQuestion(q2);
        loq.addQuestion(q3);
        loq.addQuestion(q4);
        loq.addQuestion(q1);
        assertEquals(q2, loq.getListOfQuestions().get(1));
        assertEquals(q3, loq.getListOfQuestions().get(2));
        assertEquals(q4, loq.getListOfQuestions().get(3));
        assertEquals(q1, loq.getListOfQuestions().get(4));
        assertEquals(5, loq.getListOfQuestions().size());
    }

    @Test
    void testremoveQuestioncons(){
        Question q1 = new Question("What is the capital of Canada?", "Ottawa", 1);
        Question q2 = new Question("Which city is UBC at?", "Vancouver", 2);
        Question q3 = new Question("Who is the prime minister of Canada?", "Justin Trudeau", 3);
        Question q4 = new Question("Who owns Tesla?","Elon Musk", 1);



        loq.addQuestion(q1);
        loq.addQuestion(q2);
        loq.addQuestion(q3);
        loq.addQuestion(q4);
        assertEquals(4, loq.getListOfQuestions().size());

        loq.removeQuestioncons(q1);
        assertEquals(3, loq.getListOfQuestions().size());
        assertEquals(q2, loq.getListOfQuestions().get(0));

        loq.removeQuestioncons(q2);
        loq.removeQuestioncons(q3);

        assertEquals(1, loq.getListOfQuestions().size());
        assertEquals(q4, loq.getListOfQuestions().get(0));

        loq.removeQuestioncons(q4);
        assertEquals(0, loq.getListOfQuestions().size());
        assertTrue(loq.getListOfQuestions().isEmpty());

    }
    @Test
    void testremoveQuestionstring(){
        Question q1 = new Question("What is the capital of Canada?", "Ottawa", 1);
        Question q2 = new Question("Which city is UBC at?", "Vancouver", 2);
        Question q3 = new Question("Who is the prime minister of Canada?", "Justin Trudeau", 3);
        Question q4 = new Question("Who owns Tesla?","Elon Musk", 1);



        loq.addQuestion(q1);
        loq.addQuestion(q2);

        loq.removeQuestionstring("What is the capital of Canada?");
        assertEquals(1, loq.getListOfQuestions().size());
        assertEquals(q2, loq.getListOfQuestions().get(0)); loq.removeQuestionstring("Who is the prime minister of Canada?");
        assertEquals(1, loq.getListOfQuestions().size());
        assertEquals(q2, loq.getListOfQuestions().get(0));



    }
    @Test
    void testisquestioninbank(){
        Question q1 = new Question("What is the capital of Canada?", "Ottawa", 1);
        Question q2 = new Question("Which city is UBC at?", "Vancouver", 2);
        Question q3 = new Question("Who is the prime minister of Canada?", "Justin Trudeau", 3);
        Question q4 = new Question("Who owns Tesla?","Elon Musk", 1);



        loq.addQuestion(q1);
        loq.addQuestion(q2);
        loq.addQuestion(q1);

        assertTrue(loq.isquestioninbank("What is the capital of Canada?"));
        assertFalse(loq.isquestioninbank("Who is the prime minister of Canada?"));
    }

}
