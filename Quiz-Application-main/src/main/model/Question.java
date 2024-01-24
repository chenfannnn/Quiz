package model;

import org.json.JSONObject;
import persistence.Writable;





public class Question implements Writable {
    private final String question;
    private final String correctanswer;
    private final int assignedpoints;

    //EFFECTS: constructs a question with user input of question correct answer and assignedpoint

    public Question(String question, String correctanswer, int assignedpoints) {
        this.question = question;
        this.correctanswer = correctanswer;
        this.assignedpoints = assignedpoints;

    }

    public String getCorrectanswer() {
        return this.correctanswer;
    }


    public String getQuestion() {
        return this.question;
    }

    public int getAssignedpoints() {
        return this.assignedpoints;
    }

    //EFFECTS: return true if the two statements are the same otherwise false
    //Logs the event that a new question is shown, answer is correct or not and total points has increased
    public boolean checkcorrect(String inputanswer, String correctanswer) {
        EventLog.getInstance().logEvent(new Event("New question is shown in Start Quiz "));
        if (inputanswer.equals(correctanswer)) {
            EventLog.getInstance().logEvent(new Event("Total points for the quiz has increased, "
                    + "and answer input is correct "));
            return true;
        } else {
            EventLog.getInstance().logEvent(new Event("Answer input is wrong "));
            return false;
        }
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("question", question);
        json.put("correctanswer", correctanswer);
        json.put("assignedpoints", assignedpoints);
        return json;
    }
}
