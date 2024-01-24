package ui;



import model.ListOfQuestions;
import model.Question;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class App {

    private int points;
    protected ListOfQuestions questionBank;
    private static final String JSON_STORE = "./data/workroom.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    //EFFECTS: initial quizapp with points being 0, blank questionbank, and runs the quiz application

    public App() throws FileNotFoundException {
        this.points = 0;
        questionBank = new ListOfQuestions("my question bank");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runQuiz() throws FileNotFoundException {
        boolean keepGoing = true;
        String inputString;

        while (keepGoing) {
            showMenu();
            Scanner scanner = new Scanner(System.in);
            inputString = scanner.nextLine();
            if (inputString.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(inputString);
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: processes user command

    private void processCommand(String command) throws ArithmeticException {
        if (command.equals("s")) {
            startQuiz();
        } else if (command.equals("i")) {
            insertquestionfromuser();
        } else if (command.equals("d")) {
            removequestionfromuser();
        } else if (command.equals("q")) {
            System.out.println("Have a good rest of your day");
        } else if (command.equals("sq")) {
            showquestionbank();
        } else if (command.equals("sa")) {
            showanswers();
        } else if (command.equals("save")) {
            saveQuestionBank();
        } else if (command.equals("load")) {
            loadQuestionBank();
        } else {
            System.out.println("Value entered is not valid");
        }
    }

    public ListOfQuestions getQuestionBank() {
        return questionBank;
    }

    // MODIFIES: this
    // EFFECTS: user can insert questions to questionbank
    public void insertquestionfromuser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your question");
        String q = scanner.nextLine();
        if (!(questionBank.isquestioninbank(q))) {
            System.out.println("Please enter the corresponding answer for the question you have inserted");
            String a = scanner.nextLine();

            System.out.println("Please assign points from scale 1-10 for answering this question correctly "
                    + "(integer only)");
            int p = 0;

            try {
                p = scanner.nextInt();
                if (!(p < 1 || p > 10)) {
                    Question newq = new Question(q, a, p);
                    questionBank.addQuestion(newq);
                } else {
                    System.out.println("Integer input is not within the bound 1-10");
                }
            } catch (RuntimeException e) {
                System.out.println("Please enter integer");
            }

        }
    }
    // MODIFIES: this
    // EFFECTS: user can remove questions to questionbank

    public void removequestionfromuser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the question you want to remove");
        String qremove = scanner.nextLine();
        questionBank.removeQuestionstring(qremove);
        System.out.println("The chosen question has been removed");

    }

    // EFFECTS: Display the questions in the questionbank
    public void showquestionbank() {
        if (questionBank.getListOfQuestions().isEmpty()) {
            System.out.println("There are no questions inside the question bank");
        } else {
            for (Question q : questionBank.getListOfQuestions()) {
                int pnum = questionBank.getListOfQuestions().indexOf(q) + 1;
                String extra = ". ";
                System.out.println(pnum + extra + q.getQuestion());
            }
        }
    }

    // EFFECTS: Display the answers in the questionbank

    public void showanswers() {
        if (questionBank.getListOfQuestions().isEmpty()) {
            System.out.println("There are no questions inside the question bank");
        } else {
            for (Question q : questionBank.getListOfQuestions()) {
                int pnum = questionBank.getListOfQuestions().indexOf(q) + 1;
                String extra = ". ";
                System.out.println(pnum + extra + q.getCorrectanswer());
            }
        }
    }


    // EFFECTS: Shows the menu to user
    public void showMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> Start Quiz");
        System.out.println("\ti -> Insert New Question");
        System.out.println("\td -> Delete Question");
        System.out.println("\tq -> Quit");
        System.out.println("\tsq -> Show All Questions in Question Bank");
        System.out.println("\tsa -> Show All Answers in Question Bank");
        System.out.println("\tsave -> save question bank to file");
        System.out.println("\tload -> load question bank from file");
    }


    // EFFECTS: starts the quiz for the inputted questions
    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        for (Question q: questionBank.getListOfQuestions()) {
            System.out.println(q.getQuestion());
            System.out.println("Please insert your answer: ");
            String inputanswer = scanner.nextLine();

            if (inputanswer.equals(q.getCorrectanswer())) {
                System.out.println("Congrats");
                points += q.getAssignedpoints();
                System.out.println("Total Points: " + this.points);
            } else {
                System.out.println("Incorrect");
                System.out.println("Total Points: " + this.points);
            }
        }
        System.out.println("You have finished the quiz, your total score is " + this.points);
    }

    // EFFECTS: saves the questionbank to file
    public void saveQuestionBank() {
        try {
            jsonWriter.open();
            jsonWriter.write(questionBank);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads questionbank from file
    public void loadQuestionBank() {
        try {
            questionBank = jsonReader.read();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


}
