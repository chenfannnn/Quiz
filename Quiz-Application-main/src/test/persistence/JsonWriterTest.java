package persistence;


import model.ListOfQuestions;
import model.Question;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ListOfQuestions loq = new ListOfQuestions("my question bank");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ListOfQuestions loq = new ListOfQuestions("My question bank");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyloq.json");
            writer.open();
            writer.write(loq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyloq.json");
            loq = reader.read();
            assertEquals("My question bank", loq.getName());
            assertEquals(0, loq.getListOfQuestions().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ListOfQuestions loq = new ListOfQuestions("my question bank");
            loq.addQuestion(new Question("1+1", "2", 1));
            loq.addQuestion(new Question("2+2","4",3));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralloq.json");
            writer.open();
            writer.write(loq);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralloq.json");
            loq = reader.read();
            assertEquals("my question bank", loq.getName());
            List<Question> ques = loq.getListOfQuestions();
            assertEquals(2, ques.size());
            checkQuestion("1+1", "2", 1, ques.get(0));
            checkQuestion("2+2","4",3, ques.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}