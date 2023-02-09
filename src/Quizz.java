import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Question {
    private String text;

    Question(String text) {
        this.text = text;
    }

    abstract boolean checkAnswer(String answer);

    String getText() {
        return text;
    }
}

class MultipleChoiceQuestion extends Question {
    private String[] options;
    private int correctOption;

    MultipleChoiceQuestion(String text, String[] options, int correctOption) {
        super(text);
        this.options = options;
        this.correctOption = correctOption;
    }

    String[] getOptions() {
        return options;
    }

    boolean checkAnswer(String answer) {
        return Integer.parseInt(answer) == correctOption;
    }
}

class TrueFalseQuestion extends Question {
    private boolean isTrue;

    TrueFalseQuestion(String text, boolean isTrue) {
        super(text);
        this.isTrue = isTrue;
    }

    boolean checkAnswer(String answer) {
        return Boolean.parseBoolean(answer) == isTrue;
    }
}

class QuizGame {
    private ArrayList<Question> questions;
    private int score;

    QuizGame(ArrayList<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }

    void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your Name");
        String name=sc.nextLine();
        System.out.println("Welcome to our Sasta Quiz, "+ name);
        for (Question question : questions) {
            System.out.println(question.getText());
            if (question instanceof MultipleChoiceQuestion) {
                MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
                String[] options = mcQuestion.getOptions();
                for (int i = 0; i < options.length; i++) {
                    System.out.println(i + ": " + options[i]);
                }
                System.out.print("Enter your answer: ");
                String answer = sc.nextLine();
                try {
                    int selectedOption = Integer.parseInt(answer);
                    if (selectedOption < 0 || selectedOption >= options.length) {
                        throw new Exception("Invalid option selected");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid option selected, the answer will not be counted.");
                    continue;
                }
                if (question.checkAnswer(answer)) {
                    score++;
                }
            } else if (question instanceof TrueFalseQuestion) {
                System.out.print("Enter your answer (true/false): ");
                String answer = sc.nextLine();
                try {
                    Boolean.parseBoolean(answer);
                } catch (Exception e) {
                    System.out.println("Invalid answer, the answer will not be counted.");
                    continue;
                }
                if (question.checkAnswer(answer)) {
                    score++;
                }
            }
        }
        System.out.println(name+", Quiz completed, your score is: " + score + " out of " + questions.size());
    }
}
public class Quizz {
    public static void main(String[] args) {
        ArrayList<Question> questions = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File("C:\\Users\\sadiq\\IdeaProjects\\Quiz\\src\\questions.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(":");
                String type = parts[0];
                String text = parts[1];
                if (type.equals("MC")) {
                    String[] options = parts[2].split(",");
                    int correctOption = Integer.parseInt(parts[3]);
                    questions.add(new MultipleChoiceQuestion(text, options, correctOption));
                } else if (type.equals("TF")) {
                    boolean isTrue = Boolean.parseBoolean(parts[2]);
                    questions.add(new TrueFalseQuestion(text, isTrue));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
            System.exit(1);
        }

        QuizGame quiz = new QuizGame(questions);
        quiz.start();
    }
}
