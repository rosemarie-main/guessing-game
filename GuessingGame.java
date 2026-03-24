import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

    Scanner input = new Scanner(System.in);
    Random rand = new Random();

    String playerName;
    int secretNumber;
    int attempts;
    final int MIN = 1;
    final int MAX = 100;
    final int MAX_ATTEMPTS = 10;

    void displayWelcome() {
        System.out.println("========================================");
        System.out.println("    WELCOME TO THE GUESSING GAME!");
        System.out.println("========================================");
        System.out.println("Hello, " + playerName + "!\n");
        System.out.println("I'm thinking of a number between 1 and 100.");
        System.out.println("You have 10 attempts to guess it.");
        System.out.println("I'll give you a hint after each guess.\n");
        System.out.println("Let's begin!");
        System.out.println("========================================");
    }

    int generateSecretNumber() {
        return rand.nextInt(MAX - MIN + 1) + MIN;
    }

    int getUserGuess() {
        int guess;

        while (true) {
            System.out.print("Enter your guess (1-100): ");

            // FIX: handle invalid (non-integer) input
            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                input.next(); // clear invalid input
                continue;
            }

            guess = input.nextInt();

            if (guess >= MIN && guess <= MAX) {
                return guess;
            } else {
                System.out.println("Invalid! Please enter a number between 1 and 100.");
            }
        }
    }

    void giveHint(int guess) {
        if (guess < secretNumber) {
            System.out.println("Too low! Try a higher number.");
        } else if (guess > secretNumber) {
            System.out.println("Too high! Try a lower number.");
        }
    }

    void playGame() {
        attempts = 0;

        while (attempts < MAX_ATTEMPTS) {
            System.out.println("\n--- Attempt #" + (attempts + 1) + " ---");
            int guess = getUserGuess();
            attempts++;

            if (guess == secretNumber) {
                System.out.println("\nCongratulations " + playerName + "!");
                System.out.println("You guessed the number " + secretNumber + " in " + attempts + " attempts!");
                return;
            } else {
                giveHint(guess);
            }
        }

        System.out.println("\nGAME OVER!");
        System.out.println("You've used all " + MAX_ATTEMPTS + " attempts.");
        System.out.println("The secret number was " + secretNumber + ".");
        System.out.println("Better luck next time, " + playerName + "!");
    }

    void displayStats() {
        String rating;

        if (attempts == 1) {
            rating = "Perfect!";
        } else if (attempts <= 3) {
            rating = "Excellent!";
        } else if (attempts <= 6) {
            rating = "Good job!";
        } else {
            rating = "Nice try!";
        }

        System.out.println("\n========================================");
        System.out.println("            GAME STATISTICS");
        System.out.println("========================================");
        System.out.println("Player: " + playerName);
        System.out.println("Secret Number: " + secretNumber);
        System.out.println("Attempts Used: " + attempts);
        System.out.println("Rating: " + rating);
        System.out.println("========================================");
    }

    boolean askPlayAgain() {
        System.out.print("\nWould you like to play again, " + playerName + "? (Y/N): ");
        char choice = input.next().toLowerCase().charAt(0);
        return choice == 'y';
    }

    void startGame() {
        System.out.print("Enter your name: ");
        playerName = input.nextLine(); // FIX: removed extra nextLine()

        boolean playAgain;

        do {
            secretNumber = generateSecretNumber();
            displayWelcome();
            playGame();
            displayStats();
            playAgain = askPlayAgain();
        } while (playAgain);

        System.out.println("\n========================================");
        System.out.println("Thanks for playing, " + playerName + "!");
        System.out.println("See you next time!");
        System.out.println("========================================");
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame();
        game.startGame();
    }
}