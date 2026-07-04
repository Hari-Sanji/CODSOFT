package COdsoft;

import java.util.Random;
import java.util.Scanner;

public class randomNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int roundsWon = 0;
        boolean playAgain = true;

        while (playAgain) {
            int target = random.nextInt(100) + 1;
            int maxAttempts = 5;
            int attempts = 0;
            boolean guessed = false;

            System.out.println("Guess the number between 1 and 100");
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int guess = sc.nextInt();
                attempts++;

                if (guess == target) {
                    System.out.println("Correct! You guessed the number.");
                    totalScore += (maxAttempts - attempts + 1) * 10;
                    roundsWon++;
                    guessed = true;
                    break;
                } else if (guess < target) {
                    System.out.println("Too low.");
                } else {
                    System.out.println("Too high.");
                }

                System.out.println("Attempts left: " + (maxAttempts - attempts));
            }

            if (!guessed) {
                System.out.println("You lost. The number was " + target);
            }

            System.out.println("Rounds Won: " + roundsWon);
            System.out.println("Score: " + totalScore);

            System.out.print("Play again? (yes/no): ");
            String choice = sc.next();

            if (!choice.equalsIgnoreCase("yes")) {
                playAgain = false;
            }
        }

        System.out.println("Final Score: " + totalScore);
        System.out.println("Total Rounds Won: " + roundsWon);

        sc.close();
    }
}