/*import java.util.Scanner;

interface Quiz {
    void startQuiz();
}

abstract class Question {
    String question;
    String option1;
    String option2;
    String option3;
    String option4;
    int correctOption;

    Question(String question, String option1, String option2, String option3, String option4, int correctOption) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctOption = correctOption;
    }

    abstract void displayQuestion();
}

class MCQQuestion extends Question {
    MCQQuestion(String question, String option1, String option2, String option3, String option4, int correctOption) {
        super(question, option1, option2, option3, option4, correctOption);
    }

    void displayQuestion() {
        System.out.println(question);
        System.out.println("A. " + option1);
        System.out.println("B. " + option2);
        System.out.println("C. " + option3);
        System.out.println("D. " + option4);
    }
}

class QuizGame {
    private Question[] questions;
    private int score;

    QuizGame(Question[] questions) {
        this.questions = questions;
        score = 0;
    }

    public void startQuiz() {
        Scanner sc = new Scanner(System.in);

        for (Question q : questions) {
            try {
                q.displayQuestion();
                System.out.println("Enter your answer (A/B/C/D): ");
                String answer = sc.nextLine();
                if (!(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))) {
                    throw new IllegalArgumentException("Invalid answer option");
                }
                int userOption = answer.charAt(0) - 'A' + 1;
                if (userOption == q.correctOption) {
                    score++;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Quiz completed. Your score is: " + score);
    }
}

public class Main {
    public static void main(String[] args) {
        Question[] questions = new Question[2];
        questions[0] = new MCQQuestion("What is the capital of India?", "Mumbai", "Delhi", "Chennai", "Kolkata", 2);
        questions[1] = new MCQQuestion("What is the currency of Japan?", "Yen", "Won", "Rupiah", "Dollar", 1);

        QuizGame quiz = new QuizGame(questions);
        quiz.startQuiz();
    }}*/