package NumberGuessingGame;
import java.util.Scanner;
import java.util.Random;


public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        int round = 1;

        System.out.println("🎯 Welcome to the Number Guessing Game!");

        boolean playAgain = true;
        while (playAgain) {
            int number = rand.nextInt(100) + 1; // 1–100
            int attempts = 0;
            int maxAttempts = 7;
            boolean guessed = false;

            System.out.println("\nRound " + round + " — Guess the number (1–100):");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == number) {
                    System.out.println("✅ Correct! You guessed it in " + attempts + " attempts.");
                    guessed = true;
                    break;
                } else if (guess < number) {
                    System.out.println("Too Low!");
                } else {
                    System.out.println("Too High!");
                }
            }

            if (!guessed) {
                System.out.println("❌ You Lost! The number was " + number);
            }

            System.out.print("Play Again? (yes/no): ");
            String choice = sc.next().toLowerCase();
            playAgain = choice.equals("yes");
            round++;
        }

        System.out.println("\nThanks for playing!");
        sc.close();
    }
}
