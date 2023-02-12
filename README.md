# QuizzApplication
This code is a Java program for creating a quiz application. The code consists of several classes, each responsible for a specific aspect of the quiz.

The Question class is an abstract class that represents a question in the quiz. It has an instance variable text to store the text of the question and an abstract method checkAnswer to check if the answer provided is correct or not.

The MultipleChoiceQuestion class extends the Question class and represents a multiple-choice question in the quiz. It has two instance variables: options, which stores the answer options for the question, and correctOption, which stores the index of the correct option. It overrides the checkAnswer method to parse the given answer as an integer and compare it to the index of the correct option.

The TrueFalseQuestion class also extends the Question class and represents a true/false question in the quiz. It has an instance variable isTrue that stores the correct answer for the question. It overrides the checkAnswer method to parse the given answer as a boolean value and compare it to the value of isTrue.

The Grade enum defines the different grades a student can receive based on their score. Each constant in the enum has a lower bound and upper bound for the score range it represents, as well as a string representation of the grade. The enum also has a static method getGrade to calculate the grade based on the score and the maximum score.

The Quiz class represents a quiz and contains the questions for the quiz, as well as methods to run the quiz and calculate the score. It has an instance variable questions of type ArrayList to store the questions and a method loadQuestions to load the questions from a text file.

The Main class contains the main method to run the quiz. It creates an instance of the Quiz class, loads the questions, and runs the quiz. It also calculates the final score and displays the result.
