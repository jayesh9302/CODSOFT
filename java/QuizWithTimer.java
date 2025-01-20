import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    private String questionText;
    private String[] options;
    private String correctAnswer;

    public Question(String questionText, String[] options, String correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getOptions() {
        return options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}

class QuizApp {
    private static final int TIME_LIMIT = 10; // Time limit for each question in seconds
    private int score = 0;
    private int currentQuestionIndex = 0;
    private Question[] questions;
    private Scanner scanner;

    public QuizApp() {
        // Sample questions
        questions = new Question[]{
            new Question("What is the capital of France?", new String[]{"1. Paris", "2. London", "3. Berlin", "4. Rome"}, "1"),
            new Question("Which planet is known as the Red Planet?", new String[]{"1. Venus", "2. Earth", "3. Mars", "4. Jupiter"}, "3"),
            new Question("Who wrote 'Hamlet'?", new String[]{"1. Shakespeare", "2. Dickens", "3. Austen", "4. Orwell"}, "1")
        };
        scanner = new Scanner(System.in);
    }

    public void startQuiz() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.length; currentQuestionIndex++) {
            askQuestion(questions[currentQuestionIndex]);
        }

        displayResult();
    }

    private void askQuestion(Question question) {
        System.out.println("\nQuestion: " + question.getQuestionText());

        String[] options = question.getOptions();
        for (String option : options) {
            System.out.println(option);
        }

        // Start the timer
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                checkAnswer(""); // Submit an empty answer if time runs out
            }
        }, TIME_LIMIT * 1000); // Timer set for the time limit in milliseconds

        System.out.print("Choose your answer (1-4): ");
        String userAnswer = scanner.nextLine();
        checkAnswer(userAnswer);

        timer.cancel(); // Stop the timer after the user submits the answer
    }

    private void checkAnswer(String userAnswer) {
        String correctAnswer = questions[currentQuestionIndex].getCorrectAnswer();
        if (userAnswer.equals(correctAnswer)) {
            System.out.println("Correct!");
            score++;
        } else if (!userAnswer.isEmpty()) {
            System.out.println("Incorrect! The correct answer was option " + correctAnswer + ".");
        }

        // Move to the next question
        if (currentQuestionIndex < questions.length - 1) {
            System.out.println("Next question...");
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz Over!");
        System.out.println("Your final score: " + score + " out of " + questions.length);
    }
}

public class QuizWithTimer {
    public static void main(String[] args) {
        QuizApp quizApp = new QuizApp();
        quizApp.startQuiz();
    }
}
