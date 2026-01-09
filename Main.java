import java.util.Random;
import java.util.Scanner;

/**
 * Main class for the Number Guessing Game.
 * This class handles the game loop, difficulty selection, and user interaction.
 * * @author Saksham Kumar
 * @version 1.0
 */
public class Main {

    /**
     * The main entry point of the application.
     * Continuously prompts the user to select a difficulty level or exit the game.
     * * @param args Command line arguments (not used).
     */

    static int bestScore = Integer.MAX_VALUE; // Tracks Best Score of player.
    public static void main(String[] args) {
        // Main game loop to keep the application running until user chooses to exit
        
        // Used Scanner class for input
        Scanner scan = new Scanner(System.in); 

        Random rnd = new Random();
        // Generating a random number between 1 and 100
        int ans = rnd.nextInt(100) + 1;
        while(true) {
            String choice;
            
            // Displaying the Game Menu
            System.out.println();
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I'm thinking of a number between 1 and 100.");
            System.out.println("You have chances to guess the correct number according to difficulty.");
            
            System.out.println();
            System.out.println("Please select the difficulty level:");
            System.out.println("1. Easy (10 chances)");
            System.out.println("2. Medium (5 chances)");
            System.out.println("3. Hard (3 chances)");
            System.out.println();
            System.out.println("To Exit enter 'x'.");
            
            System.out.println();
            System.out.print("Enter your choice: ");
            choice = scan.nextLine(); // Reading input as String to handle 'x'
            
            // Check for exit condition
            if(choice.equals("x") || choice.equals("X")) {
                System.out.println("Exiting Game. Goodbye!");
                System.exit(0);
            }
            
            // Validate input and start the game round
            try {
                long startTime = System.currentTimeMillis(); // Start time, used for tracking time taken.

                int intChoice = Integer.parseInt(choice);
                int limit = 0; // Variable to store the max number of attempts
                // Setting the limit based on difficulty selection
                switch (intChoice) {
                    case 1 -> { 
                        System.out.println("Great! You have selected the Easy difficulty level.");
                        limit = 10;
                    }
                    case 2 -> { 
                        System.out.println("Great! You have selected the Medium difficulty level.");
                        limit = 5;
                    }
                    case 3 -> { 
                        System.out.println("Great! You have selected the Hard difficulty level.");
                        limit = 3;
                    }
                    default -> {
                        System.out.println("Invalid Choice! Enter a valid Choice");
                    }
                }

                // Converting String choice to Integer for the game logic
                boolean hasWon = startRound(limit, scan, ans);

                long endTime = System.currentTimeMillis(); // End time, used for tracking time taken.
                
                if(hasWon) {
                    double timeTaken = (endTime - startTime) / 1000.0; // Time Calculation 
                    System.out.println("Took you " + timeTaken + " seconds.");
                } else {
                    System.out.println("Better Luck next time.");
                }

            } catch (NumberFormatException e) {
                // Handling non-integer inputs gracefully
                System.out.println("Invalid Choice! Please enter a number (1-3) or 'x' to exit.");
            }
        }
    }
    
    /**
     * Handles the core logic of a single game round.
     * Sets chances based on difficulty and processes user guesses.
     * * @param choice The difficulty level selected by the user (1, 2, or 3).
     */
    static boolean startRound(int limit, Scanner scan, int ans) {        
        System.out.println();
        System.out.println("Let's start the game!");
        System.out.println();
        
        int count = 0; // Tracks the number of attempts used.
        
        // Loop for the guessing logic
        while (true) { 
            System.out.print("Enter your guess: ");
            
            int user_guess;
            try {
                user_guess = scan.nextInt();
                scan.nextLine(); 
            } catch (Exception e) {
                System.out.println("Invalid Choice! Enter a valid Choice");
                scan.nextLine();
                continue;
            }
            
            System.out.println();
            count++;
            
            // Check if guess matches the target number
            if(user_guess == ans) {
                System.out.println("Congratulations! You guessed the correct number in " + count + " attempts."); 
                if (count < bestScore) {
                    bestScore = count;
                    System.out.println("Naya Record! New High Score: " + bestScore + " attempts!");
                } else {
                    System.out.println("High Score abhi bhi " + bestScore + " attempts hai. Try again!");
                }
                System.out.println();
                return true; // Victory: Break the loop
            } else if(user_guess > ans){
                System.out.println("Incorrect! The number is less than " + user_guess);
                System.out.println();
            } else {
                System.out.println("Incorrect! The number is greater than " + user_guess);
                System.out.println();
            }
            
            // Decrement remaining chances
            limit--;
            
            // Check for Game Over condition
            if(limit <= 0) {
                System.out.println("Chances over! You lost this round.");
                System.out.println("The correct number was: " + ans);
                System.out.println("Try in next round.");
                return false;
            }
        }
    }
}