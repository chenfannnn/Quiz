package persistence;

import model.ListOfQuestions;
import model.Question;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.stream.Stream;



public class JsonReader {
    private String source;


    // EFFECTS: constructs reader to read from source files
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfQuestions from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfQuestions read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfQuestions(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses listofquestions from JSON object and returns it
    private ListOfQuestions parseListOfQuestions(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ListOfQuestions loq = new ListOfQuestions(name);
        addQuestions(loq, jsonObject);
        return loq;
    }



    // MODIFIES: loq
    // EFFECTS: parses questions from JSON object and adds them to listofquestions
    private void addQuestions(ListOfQuestions loq, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("questions");
        for (Object json : jsonArray) {
            JSONObject nextquestions = (JSONObject) json;
            addQuestion(loq, nextquestions);
        }

    }


    // MODIFIES: loq
    // EFFECTS: parses question from JSON object and adds it to listofquestions
    private void addQuestion(ListOfQuestions loq, JSONObject jsonObject) {
        String question = jsonObject.getString("question");
        String correctanswer = jsonObject.getString("correctanswer");
        int assignedpoints = jsonObject.getInt("assignedpoints");
        Question q = new Question(question, correctanswer, assignedpoints);
        loq.addQuestion(q);
    }


}
