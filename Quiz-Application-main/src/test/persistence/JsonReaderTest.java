package persistence;


import model.ListOfQuestions;
import model.Question;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfQuestions loq = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyloq.json");
        try {
            ListOfQuestions loq = reader.read();
            assertEquals("my question bank", loq.getName());
            assertEquals(0, loq.getListOfQuestions().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralloq.json");
        try {
            ListOfQuestions loq = reader.read();
            assertEquals("my question bank", loq.getName());
            List<Question> qs = loq.getListOfQuestions();
            assertEquals(2, qs.size());
            checkQuestion("1+1", "2", 1, qs.get(0));
            checkQuestion("2+2", "4",3, qs.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}