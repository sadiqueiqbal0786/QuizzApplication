import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
* ---------------------------------------------------------------------
* Owner - Sadique Iqbal                                               |
* B.Tech - Lovely Professional University                             |
* This code can use by anyone, hence, not restricted.                 |
* Creating this project for my class assignment.                      |
* Project is working fine.                                            |
* Concept used full oops , enums and all java basics concept.         |
* To know more please refer read.md file.                             |
* Thankyou                                                            |
* ---------------------------------------------------------------------
* */

//creating an abstract class for Question representation.
abstract class Question {
    private String text;
    // it will store the text of the questions.

    Question(String text) {
        this.text = text;
    }
    //Assigning text to the instance variable through constructor.

    abstract boolean checkAnswer(String answer);
    //abstract method to check the ans is correct or not.

    String getText() {
        //getter to get questions
        return text;
        //returning the questions
    }

}

class MultipleChoiceQuestion extends Question {
    //Constructor method that accepts three parameters: the question text (text),
    // the answer options (options), and the index of the correct option (correctOption)
    private String[] options;
    private int correctOption;

    MultipleChoiceQuestion(String text, String[] options, int correctOption) {
        super(text);
        // Calls the constructor method of the parent class (Question) to
        // set the text of this question to the given text.
        this.options = options;
        // Assigns the given options to the instance variable options.
        this.correctOption = correctOption;
        // Assigns the given correctOption to the instance variable correctOption.
    }

    String[] getOptions() {
        // Method that returns the answer options of this question.
        return options;
    }

    boolean checkAnswer(String answer) {
        // Method that checks if the given answer is correct.
        return Integer.parseInt(answer) == correctOption;
        // Parses the given answer (as a string) to an integer,
        // and compares it to the index of the correct option.
        // Returns true if they are equal, false otherwise.
    }
}

class TrueFalseQuestion extends Question {
    // constructor for the TrueFalseQuestion class
// inherited the Question class
    private boolean isTrue;

    TrueFalseQuestion(String text, boolean isTrue) {
        super(text);
        // calls the super class (Question) constructor and passes the text as an argument
        this.isTrue = isTrue;
        // sets the value of the instance variable isTrue
    }

    boolean checkAnswer(String answer) {
        // method to check if the answer provided is correct
        return Boolean.parseBoolean(answer) == isTrue;
        // parses the answer string to a boolean value and compares it to the value of isTrue
        // returns true if the values are equal and false otherwise
    }
}
enum Grade {
    // enum to represent the different grades a student can receive
    A("A", 90, 100),
    // constant A with a lower bound of 90 and upper bound of 100
    B("B", 80, 89),
    // constant B with a lower bound of 80 and upper bound of 89
    C("C", 70, 79),
    // constant C with a lower bound of 70 and upper bound of 79
    D("D", 40, 69),
    // constant D with a lower bound of 60 and upper bound of 69
    F("F", 0, 39);
    // constant F with a lower bound of 0 and upper bound of 59

    private final String grade;
    // variable to store the grade as a string
    private final int lowerBound;
    // variable to store the lower bound for the score range of this grade
    private final int upperBound;
    // variable to store the upper bound for the score range of this grade
    Grade(String grade, int lowerBound, int upperBound) {
        this.grade = grade;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        // constructor to initialize the grade,
        // lower bound and upper bound for a constant
    }

    public String getGrade() {
        return grade;
        // method to get the grade as a string
    }

    public static Grade getGrade(int score, int maxScore) {
        // method to get the grade based on the score and the maximum score
        int percentage = (score * 100) / maxScore;
        // calculate the percentage score
        for (Grade grade : Grade.values()) {
            // loop through each grade constant
            if (percentage >= grade.lowerBound && percentage <= grade.upperBound) {
                return grade;
                // if the percentage score is within the lower and upper bounds of the constant,
                // return the constant
            }
        }
        return null;
        // if no matching grade is found, return null
    }
}


class QuizGame {
    // Class created for the QuizGame to show the questions
    // and calculate the scores
    private ArrayList<Question> questions;
    // Private field to store a list of Question objects
    private int score;
    // Private field to store the quiz score
    QuizGame(ArrayList<Question> questions) {
        // Constructor to initialize the questions and the score
        this.questions = questions;
        // Assign the list of questions to the field
        this.score = 0;
        // Initialize the score to 0

    }

    void start() {
        // Method to start the quiz game
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Name");
        String name=sc.nextLine();
        // Ask the user for their name and store it in a variable
        System.out.println("Welcome to our Sasta Quiz, "+ name);
        // Welcome the user to the quiz game
        for (Question question : questions) {
            // Loop through each question in the list
            System.out.println(question.getText());
            // Print the text of the question
            if (question instanceof MultipleChoiceQuestion) {
                // Check if the question is a MultipleChoiceQuestion
                MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
                // Cast the question to a MultipleChoiceQuestion object
                String[] options = mcQuestion.getOptions();
                // Get the options for the MultipleChoiceQuestion
                for (int i = 0; i < options.length; i++) {
                    System.out.println(i + ": " + options[i]);
                    // Loop through the options and print each one
                }
                System.out.print("Enter your answer: ");
                // Ask the user to enter their answer
                String answer = sc.nextLine();
                // Read the user's answer
                try {
                    int selectedOption = Integer.parseInt(answer);
                    // Parse the answer as an integer
                    if (selectedOption < 0 || selectedOption >= options.length) {
                        // Check if the selected option is within the range of options
                        throw new Exception("Invalid option selected");
                        // If not, throw an exception
                    }
                } catch (Exception e) {
                    System.out.println("Invalid option selected, the answer will not be counted.");
                    // If an exception is thrown, print an error message
                    continue;
                    // Continue to the next iteration of the loop
                }
                if (question.checkAnswer(answer)) {
                    // Check if the answer is correct
                    score++;
                }
            } else if (question instanceof TrueFalseQuestion) {
                // If the question is a TrueFalseQuestion, ask the user for their answer
                System.out.print("Enter your answer (true/false): ");
                String answer = sc.nextLine();
                // Read the user's answer
                try {
                    Boolean.parseBoolean(answer);
                    // Parse the answer as a boolean
                } catch (Exception e) {
                    System.out.println("Invalid answer, the answer will not be counted.");
                    // If an exception is thrown, print an error message
                    continue;
                    // Continue to the next iteration of the loop
                }
                if (question.checkAnswer(answer)) {
                    // Check the answer, if correct then point will be increase by 1.
                    score++;
                }
            }
        }
        System.out.println(name+", Quiz completed, your score is: " + score + " out of " + questions.size());
        // Print the username ,score and number of questions.
        int maxScore = questions.size();
        // Creating a variable that store the number of questions called maxScore.
        Grade grade = Grade.getGrade(score, maxScore);
        // Grade type variable , that are storing score and maxScore to enum constants method
        // to calculate percentage and comparing the upperBounds and LowerBounds for grading.
        System.out.println("Your grade is: " + grade.getGrade());
        // Print the grade by calling getters method.
    }
}

public class Quizz {
    // Quiz class that contains main method.
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();
        // Create an ArrayList to store Question objects
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\sadiq\\IdeaProjects\\Quiz\\src\\questions.txt"));
            // Open a scanner to read the questions from a file
            while (sc.hasNextLine()) {
                // Loop through the lines of the file
                String line = sc.nextLine();
                // Read the next line from the file
                String[] parts = line.split(":");
                // Split the line into parts
                String type = parts[0];
                // The first part is the type of question
                String text = parts[1];
                // The second part is the text of the question
                if (type.equals("MC")) {
                    // If the question is a multiple choice question
                    String[] options = parts[2].split(",");
                    // The third part is the options
                    int correctOption = Integer.parseInt(parts[3]);
                    // The fourth part is the correct option
                    questions.add(new MultipleChoiceQuestion(text, options, correctOption));
                    // Add a new MultipleChoiceQuestion to the ArrayList
                } else if (type.equals("TF")) {
                    // If the question is a true/false question
                    boolean isTrue = Boolean.parseBoolean(parts[2]);
                    // The third part is whether the statement is true or false
                    questions.add(new TrueFalseQuestion(text, isTrue));
                    // Add a new TrueFalseQuestion to the ArrayList
                }
            }
            sc.close();
            // Close the scanner
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(1);
            // If the file is not found, print an error message and exit
        }

        QuizGame quiz = new QuizGame(questions);
        // Creating the object of QuizGame and passing questions as arguments.
        quiz.start();
        // Start the quiz game
    }
}
