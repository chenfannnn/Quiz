package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;




public class ListOfQuestions implements Writable {
    private ArrayList<Question> listOfQuestions;
    private String name;

    // EFFECTS: constructs an empty arraylist of question
    public ListOfQuestions(String name) {
        this.listOfQuestions = new ArrayList<Question>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Question> getListOfQuestions() {
        return this.listOfQuestions;
    }



    //REQIURES: question
    //MODIFIES: this
    //EFFECTS: add the question to the listofQuestions
    //Logs the event that a question is added
    public void addQuestion(Question question) {
        this.listOfQuestions.add(question);
        EventLog.getInstance().logEvent(new Event("Question is added "));
    }

    //REQIURES: question
    //MODIFIES: this
    //EFFECTS: remove the question to the listofQuestions
    //Logs the event that a question is removed
    public void removeQuestioncons(Question question) {
        this.listOfQuestions.remove(question);
        EventLog.getInstance().logEvent(new Event("Question is removed "));
    }

    //REQIURES: The String version of the quesiton
    //MODIFIES: this
    //EFFECTS: remove the question to the listofQuestions
    //Logs the event that a question is removed
    public void removeQuestionstring(String question) {
        for (Question q : this.listOfQuestions) {
            if (q.getQuestion().equals(question)) {
                EventLog.getInstance().logEvent(new Event("Question is removed "));
                this.listOfQuestions.remove(q);
            }
        }

    }


    //REQIURES: The String version of the quesiton
    //EFFECTS: produce true if the input question is in the bank false if not
    public boolean isquestioninbank(String ques) {
        for (Question q : getListOfQuestions()) {
            if (q.getQuestion().equals(ques)) {
                return true;
            }

        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("questions", listofqsToJson());
        return json;
    }

    // EFFECTS: returns questions in listofquestions as a JSON array
    private JSONArray listofqsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Question q : listOfQuestions) {
            jsonArray.put(q.toJson());
        }

        return jsonArray;
    }
}






