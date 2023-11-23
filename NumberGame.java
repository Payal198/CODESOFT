package game;

	import java.util.Scanner;
	import java.util.Random;

	public class NumberGame{
	    public static void main(String[] args) {
	        playGame(); // to start game
	    }

	    static void playGame() {
	        Scanner scanner = new Scanner(System.in);
	        Random random = new Random();

	        System.out.println("Welcome to the Guess the Number game!");

	        // Set the range for the random number
	        int lowerLimit = 1;
	        int upperLimit = 100;
	        int targetNumber = random.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;

	        // Set the maximum number of attempts
	        int maxAttempts = 10;
	        int attemptsLeft = maxAttempts;

	        // Initialize score
	        int score = 0;

	        while (attemptsLeft > 0) {
	            // Prompt user for guess
	            System.out.print("Guess the number (" + lowerLimit + "-" + upperLimit + "): ");
	            int userGuess = scanner.nextInt();

	            // Compare the guess with the target number
	            if (userGuess == targetNumber) {
	                System.out.println("Congratulations! You guessed the correct number (" + targetNumber + ")!");
	                score++;
	                break;
	            } else if (userGuess < targetNumber) {
	                System.out.println("Too low! Try again.");
	            } else {
	                System.out.println("Too high! Try again.");
	            }

	            // Decrement attempts left
	            attemptsLeft--;
	            System.out.println("Attempts left: " + attemptsLeft);
	        }

	        // Display the score
	        System.out.println("Your score: " + score);

	        // Ask if the user wants to play again
	        System.out.print("Do you want to play again? (yes/no): ");
	        String playAgain = scanner.next().toLowerCase();

	        if (playAgain.equals("yes")) {
	            playGame();
	        } else {
	            System.out.println("Thanks for playing!");
	        }

	        // Close the scanner
	        scanner.close();
	    }
	}
