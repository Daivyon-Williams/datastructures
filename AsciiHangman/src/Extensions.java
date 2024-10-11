import java.io.Console;
import java.util.Scanner;


public class Extensions {

	private static boolean wordGuessed(char[] guessedLetters) {
		for (char letter : guessedLetters) {
			if (letter == '_') {
				return false;
			}
		}
		return true;
	}

	private static boolean hasLetter(char[] letters, char guess) {
		for (char letter : letters) {
			if (letter == guess) {
				return true;
			}
		}
		return false;
	}

	private static void updateGuessedLetters(char[] letters, char[] guessedLetters, char guess) {
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == guess) {
				guessedLetters[i] = guess;
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Welcome to the ASCII Version of Hangman!");

		Console c = System.console();
		Scanner s = new Scanner(System.in);
		int player1Score = 0;
		int player2Score = 0;
		char[] letters;
		char[] wrongletters;

		boolean playAgain = true;
		while (playAgain) {
			String prompt = "Please enter a secret word: ";
			if (c != null && false) {
				letters = c.readPassword(prompt);
				for (int i = 0; i < letters.length; i++) {
					letters[i] = Character.toUpperCase(letters[i]);
				}
			} else {
				System.out.println("For best results, please run this from the command line.");
				System.out.print(prompt);
				letters = s.nextLine().trim().toUpperCase().toCharArray();
				for (int i = 0; i < 10000; i++) System.out.println();
			}


			Gallows g = new Gallows();
			char[] guessedLetters = new char[letters.length];
			for (int i = 0; i < guessedLetters.length; i++) {
				guessedLetters[i] = '_';  
			}

			int incorrectGuesses = 0;
			final int MAX_INCORRECT = 6;

			System.out.println(g);  // Display gallows
			while (g.isAlive() && !wordGuessed(guessedLetters)) {

				System.out.print("Puzzle to solve: ");
				for (char guessedLetter : guessedLetters) {
					System.out.print(guessedLetter + " ");
				}
				System.out.println();


				System.out.print("Please guess a letter: ");
				String guessAnswer = s.nextLine().toUpperCase();
				char guess = guessAnswer.charAt(0);
				System.out.println(guess);



				boolean alreadyGuessed = false; //Checks if Letter has been used.
				for (char letter : guessedLetters) {
					if (letter == guess) {
						alreadyGuessed = true;
						break;
					}
				}

				if (alreadyGuessed) {
					//System.out.println("You've already guessed that letter. Try a different one!");
					continue;
				}


				if (hasLetter(letters, guess)) {
					updateGuessedLetters(letters, guessedLetters, guess);
				} else {
					incorrectGuesses++;
					g.hang();
					System.out.println(g);  // Display gallows
					//System.out.println("Incorrect guess! You have " + (MAX_INCORRECT - incorrectGuesses) + " attempts left.");
				}


				if (wordGuessed(guessedLetters)) {
					System.out.println("Success!  Player 2 wins!"); // Gives user 2 a victory royale!
					player2Score++;
					break;
				}


				if (incorrectGuesses >= MAX_INCORRECT) {
					System.out.println("Game over! Player 1 wins!"); //Gives user 2 a losing message
					player1Score++;
					break;
				}
			}

			// Display current score
			System.out.println("Current Score: ");
			System.out.println("Player 1: " + player1Score);
			System.out.println("Player 2: " + player2Score);

			// Ask if the user wants to play again
			System.out.println("Do you want to play again? (Y/N): ");
			String response = s.nextLine().toUpperCase();

			if (!response.equals("Y")) {
				playAgain = false;
				System.out.println("Thank you for playing!");
			}

		}


	}
}

