package ui;

import model.Event;
import model.EventLog;
import model.Question;
import ui.imagerelated.Questionhasbeenaddedimage;
import ui.imagerelated.SuccessfulPage;
import ui.imagerelated.Unsuccessfulpage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;


public class MainMenuGUI extends JFrame {
    private static final String STATUS = "Please Make Your Selection";
    private JLabel statusLabel;
    private JPanel buttonPanel = new JPanel(new GridLayout(9, 2, 10, 10));
    protected App app = new App();

    //all fields for insert question:
    private static String STATUSiq = "Please input the appropriate values";
    private JLabel statusLabeliq;
    private JPanel buttonPaneliq = new JPanel(new GridLayout(4, 2, 10, 10));

    private JTextField questioninput;
    private JTextField answerinput;
    private JTextField scoreinput;
    private JButton submitB;

    private JFrame insertqframe;
    private int count;

    //startquiz fields
    private int points = 0;
    private JLabel statusLabelsq = new JLabel("Please insert your answer: ");
    private JLabel pointsLabel = new JLabel("Total Points: " + points);
    private JLabel questionlabel = new JLabel();
    private JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

    private List<JTextField> useranswer = new ArrayList<>();
    private JButton submitBsq;
    private int qnum = 0;
    private JFrame startqframe;

    //delete question fields
    private JFrame deleteqframe;
    private JTextField deleteqinput;

    // EFFECTS: Start the ui for the entire application
    public MainMenuGUI() throws FileNotFoundException {
        super("My QUIZ");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        statusLabel = new JLabel(STATUS);
        add(statusLabel, BorderLayout.NORTH);

        startQuiz();
        insertNewQuestion();
        deleteQuestion();
        showQuestion();
        showAnswer();
        showPoints();
        save();
        load();
        quit();
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setVisible(true);


    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and makes a popup window that runs the quiz
    public void startQuiz() {
        JButton startQuizButton = new JButton("StartQuiz");
        startQuizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startQuizInterface();
            }
        });
        buttonPanel.add(startQuizButton);
    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and makes a popup window that inserts new question
    public void insertNewQuestion() {
        JButton insertNewQuestionButton = new JButton("Insert New Question");
        insertNewQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (count == 0) {
                    insertQuestionInterface();
                    count++;
                } else {
                    insertqframe.setVisible(true);
                }
            }
        });
        buttonPanel.add(insertNewQuestionButton);

    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and makes a popup window that deletes question
    public void deleteQuestion() {
        JButton deleteQuestionButton = new JButton("Delete Question");

        deleteQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteQfram();
            }
        });
        buttonPanel.add(deleteQuestionButton);
    }

    //MODIFIES: this
    //EFFECTS: Makes a frame for the delete question popup window
    public void deleteQfram() {
        deleteqframe = new JFrame("Delete Question");
        deleteqframe.setDefaultCloseOperation(HIDE_ON_CLOSE);

        submitBfordelete();

        JLabel qlabel = new JLabel("Please enter the question you want to remove");
        deleteqframe.add(qlabel,BorderLayout.NORTH);

        deleteqframe.pack();
        deleteqframe.setVisible(true);

    }

    //MODIFIES: this, app
    //EFFECTS: removes the question from the app question bank with the string from the text field
    public void deletefunciton() {
        try {
            String deleteqinpuutstring = deleteqinput.getText();
            app.questionBank.removeQuestionstring(deleteqinpuutstring);
        } catch (ConcurrentModificationException e) {
            //caught
        }
    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the delete button pop up window, and when clicked execute the delete question task
    public void submitBfordelete() {
        deleteqinput = new JTextField(50);
        deleteqframe.add(deleteqinput,BorderLayout.WEST);
        JButton deletesubmitB = new JButton("Submit");
        deletesubmitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deletefunciton();
            }
        });
        deleteqframe.add(new JLabel());
        deleteqframe.add(deletesubmitB, BorderLayout.SOUTH);
    }


    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and makes a popup window that shows all question
    public void showQuestion() {
        JButton showQuestionButton = new JButton("Show All Questions in Question Bank");

        showQuestionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                qbankqshower();
            }
        });
        buttonPanel.add(showQuestionButton);

    }

    //MODIFIES: this
    //EFFECTS: insert a new frame in pop up window and shows all question in app question bank
    public void qbankqshower() {
        JFrame showqframe = new JFrame("All Question in Bank");
        showqframe.setTitle("All Question in Bank");
        showqframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        showqframe.setSize(300, 200);

        int numquestion = 1;

        List<String> qsinstring = new ArrayList<>();

        for (Question q : app.questionBank.getListOfQuestions()) {

            String qinstring = q.getQuestion();
            qsinstring.add("Question" + String.valueOf(numquestion) + " : " + qinstring);
            numquestion++;
        }
        JLabel qlabel = new JLabel(qsinstring.toString());
        showqframe.add(qlabel);
        showqframe.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and makes a popup window that shows all answers
    public void showAnswer() {
        JButton showAnswerButton = new JButton("Show All Answers in Question Bank");

        showAnswerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                qbankashower();
            }
        });
        buttonPanel.add(showAnswerButton);
    }

    //MODIFIES: this
    //EFFECTS: insert a new frame in pop up window and shows all answers in app question bank
    public void qbankashower() {
        JFrame showqframe = new JFrame("All Answers in Bank");
        showqframe.setTitle("All Answers in Bank");
        showqframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        showqframe.setSize(300, 200);

        int numquestion = 1;

        List<String> qsinstring = new ArrayList<>();

        for (Question q : app.questionBank.getListOfQuestions()) {

            String qinstring = q.getCorrectanswer();
            qsinstring.add("Answer" + String.valueOf(numquestion) + " : " + qinstring);
            numquestion++;
        }
        JLabel qlabel = new JLabel(qsinstring.toString());
        showqframe.add(qlabel);
        showqframe.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and makes a popup window that shows all assigned points;
    public void showPoints() {
        JButton showPointsButton = new JButton("Show All Assigned Points in Question Bank");

        showPointsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                qbanksshower();
            }
        });
        buttonPanel.add(showPointsButton);

    }

    //MODIFIES: this
    //EFFECTS: insert a new frame in pop up window and shows all assigned points in app question bank
    public void qbanksshower() {
        JFrame showqframe = new JFrame("All Assigned Points in Bank");
        showqframe.setTitle("All Assigned Points in Bank");
        showqframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        showqframe.setSize(300, 200);

        int numquestion = 1;

        List<String> qsinstring = new ArrayList<>();

        for (Question q : app.questionBank.getListOfQuestions()) {

            int qinstring = q.getAssignedpoints();
            qsinstring.add("Question" + String.valueOf(numquestion) + " : " + qinstring + " points");
            numquestion++;
        }
        JLabel qlabel = new JLabel(qsinstring.toString());
        showqframe.add(qlabel);
        showqframe.setVisible(true);

    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and saves all states
    public void save() {
        JButton saveButton = new JButton("Save Question Bank to File");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.saveQuestionBank();
            }
        });
        buttonPanel.add(saveButton);
    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and loads the previously saved questions and states
    public void load() {
        JButton loadButton = new JButton("Load Question Bank from File");

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.loadQuestionBank();
            }
        });
        buttonPanel.add(loadButton);
    }

    //MODIFIES: this
    //EFFECTS: insert a new button to the ui, and quits the application
    //Prints all loged events in console
    public void quit() {
        JButton quitButton = new JButton("Quit");

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventLog eventLog = EventLog.getInstance();
                Iterator<model.Event> iterator = eventLog.iterator();
                while (iterator.hasNext()) {
                    Event event = iterator.next();
                    System.out.println(event.getDescription() + event.getDate());
                }
                System.exit(0);
            }
        });
        buttonPanel.add(quitButton);
    }


    //MODIFIES: this
    //EFFECTS: insert a new button to the ui in the form of pop up window, and allows the function of
    //         insert question to be shown
    public void insertQuestionInterface() {
        insertqframe = new JFrame("Insert Question");
        insertqframe.setDefaultCloseOperation(HIDE_ON_CLOSE);

        statusLabeliq = new JLabel(STATUSiq);
        insertqframe.add(statusLabeliq, BorderLayout.NORTH);
        insertqframe.add(buttonPaneliq, BorderLayout.CENTER);
        inputQ();
        inputA();
        inputS();
        submitButton();

        insertqframe.add(buttonPaneliq);

        insertqframe.pack();
        insertqframe.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: insert a textfield and label for insert question to the insertquestion interface
    public void inputQ() {
        JLabel labelforinputq = new JLabel("Please enter your question:");
        questioninput = new JTextField(50);
        buttonPaneliq.add(labelforinputq);
        buttonPaneliq.add(questioninput);
    }

    //MODIFIES: this
    //EFFECTS: insert a textfield and label for insert answer to the insertquestion interface
    public void inputA() {
        JLabel labelforinputa = new JLabel("Please enter the corresponding answer for the question "
                + "you have inserted");
        answerinput = new JTextField(50);
        buttonPaneliq.add(labelforinputa);
        buttonPaneliq.add(answerinput);
    }

    //MODIFIES: this
    //EFFECTS: insert a textfield and label for insert points to the insertquestion interface
    public void inputS() {
        JLabel labelforinputS = new JLabel("Please assign points from scale 1-10 for answering this "
                + "question correctly " + "(integer only)");
        scoreinput = new JTextField(50);
        buttonPaneliq.add(labelforinputS);
        buttonPaneliq.add(scoreinput);

    }

    //MODIFIES: this
    //EFFECTS: insert a button to the insertquestion interface and checks if the text fields input meets condition to be
    //         added
    public void submitButton() {
        submitB = new JButton("Submit");
        submitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertQreqiurement();
            }
        });
        buttonPaneliq.add(new JLabel());
        buttonPaneliq.add(submitB);
    }


    //EFFECTS: Checks if the text fields for the question parameters fits reqiurement to be added
    public void insertQreqiurement() {
        String questionstring = questioninput.getText();
        String anwserstring = answerinput.getText();
        int scoreint;
        try {
            scoreint = Integer.parseInt(scoreinput.getText());
            if (!(app.questionBank.isquestioninbank(questionstring))) {
                if (!(scoreint < 1 || scoreint > 10)) {
                    Question newq = new Question(questionstring, anwserstring, scoreint);
                    app.questionBank.addQuestion(newq);
                    new Questionhasbeenaddedimage();
                } else {
                    statusLabeliq.setText("Integer input is not within the bound 1-10");
                }
            } else {
                statusLabeliq.setText("Sorry the question you are trying to implement is already in the "
                        + "question bank and the question bank does not allow duplicates");
                questioninput.setText("");
            }
        } catch (NumberFormatException c) {
            statusLabeliq.setText("Please enter integer");
        }
    }


    //MODIFIES: this
    //EFFECTS: insert a new button to the ui in the form of pop up window, and allows the function of
    //         startQuiz to be shown
    public void startQuizInterface() {
        startqframe = new JFrame();
        startqframe.setTitle("Start Quiz");

        startqframe.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        startqframe.setSize(400, 200);

        panel.add(statusLabelsq, BorderLayout.NORTH);
        panel.add(pointsLabel, BorderLayout.NORTH);
        panel.add(questionlabel,  BorderLayout.CENTER);



        initializetextbox();
        submitButtonsq();
        showq(qnum);
        startqframe.add(panel);
        pack();
        startqframe.setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: insert textfields for answers for the questions into startquiz interface
    public void initializetextbox() {
        for (Question q : app.questionBank.getListOfQuestions()) {
            JTextField textField = new JTextField(10);
            useranswer.add(textField);
        }
    }

    //MODIFIES: this
    //EFFECTS: insert lables to start quiz interface and puts the question in the question bank in the label
    public void showq(int indexnum) {
        String question = app.getQuestionBank().getListOfQuestions().get(indexnum).getQuestion();
        questionlabel.setText("Question: " + question);
        useranswer.get(0).setText("");
    }

    //MODIFIES: this
    //EFFECTS: insert a button to the start quiz interface and checks if the text fields input for answer is correct
    public void submitButtonsq() {
        panel.add(useranswer.get(0));
        submitBsq = new JButton("Submit");
        panel.add(submitBsq);
        submitBsq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                answerchecker();
            }
        });
    }


    //EFFECTS: checks if the text field input is correct for the corresponding question.
    public void answerchecker() {

        String inputanswer = useranswer.get(0).getText();
        String correctAnswer = app.getQuestionBank().getListOfQuestions().get(qnum).getCorrectanswer();
        Question givenq = app.getQuestionBank().getListOfQuestions().get(qnum);
        if (givenq.checkcorrect(inputanswer, correctAnswer)) {
            new SuccessfulPage();
            points += app.getQuestionBank().getListOfQuestions().get(qnum).getAssignedpoints();
            pointsLabel.setText("Total Points: " + points);
        } else {
            new Unsuccessfulpage();
            pointsLabel.setText("Total Points: " + points);
        }
        qnum += 1;

        if (qnum < app.getQuestionBank().getListOfQuestions().size()) {
            showq(qnum);
        } else {
            statusLabelsq.setText("You have finished the quiz, your total score is " + this.points);

        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new MainMenuGUI();
    }

}