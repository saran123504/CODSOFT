import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_RANGE = 100;

    private int secretNumber;
    private int attempts;
    private boolean gameOver;

    private int totalRounds;
    private int totalWins;

    private Scanner scanner;
    private Random random;

    public NumberGuessingGame() {
        scanner = new Scanner(System.in);
        random = new Random();
        totalRounds = 0;
        totalWins = 0;
    }

    public void play() {
        System.out.println("Welcome to the Number Guessing Game!");
        do {
            totalRounds++;
            initializeGame();
            playRound();
            gameOver = !playAgain();
        } while (!gameOver);

        displayGameSummary();
        scanner.close();
    }

    private void initializeGame() {
        secretNumber = random.nextInt(MAX_RANGE) + 1;
        attempts = 0;
    }

    private void playRound() {
        System.out.println("\nRound " + totalRounds + ": I'm thinking of a number between 1 and " + MAX_RANGE + ".");
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess it.");

        while (attempts < MAX_ATTEMPTS) {
            attempts++;
            int guess = getUserGuess();
            if (guess == secretNumber) {
                handleCorrectGuess();
                break;
            } else {
                handleIncorrectGuess(guess);
            }
        }

        if (attempts == MAX_ATTEMPTS && secretNumber != -1) {
            handleNoMoreAttempts();
        }
    }

    private int getUserGuess() {
        int guess = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Enter your guess (1-" + MAX_RANGE + "): ");
                String input = scanner.nextLine().trim();
                guess = Integer.parseInt(input);
                if (guess < 1 || guess > MAX_RANGE) {
                    System.out.println("Please enter a number between 1 and " + MAX_RANGE + ".");
                } else {
                    validInput = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return guess;
    }

    private void handleCorrectGuess() {
        System.out.println("Congratulations! You guessed the number " + secretNumber + " correctly in " + attempts + " attempts.");
        totalWins++;
    }

    private void handleIncorrectGuess(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try again.");
        } else {
            System.out.println("Too high! Try again.");
        }
    }

    private void handleNoMoreAttempts() {
        System.out.println("Sorry, you've run out of attempts.");
        System.out.println("The number I was thinking of was " + secretNumber + ".");
    }

    private boolean playAgain() {
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgainInput = scanner.nextLine().trim().toLowerCase();
        return playAgainInput.equals("yes") || playAgainInput.equals("y");
    }

    private void displayGameSummary() {
        System.out.println("\nGame over!");
        System.out.println("You played " + totalRounds + " round(s) and won " + totalWins + " round(s).");
    }

    public static void main(String[] args) {
        NumberGuessingGame game = new NumberGuessingGame();
        game.play();
    }
}