import java.io.Console;
import java.util.Scanner;


/*1.The String class toUpperCase method allows any input from the user for the guesses or secret word into uppercase. 
//  This is so that when comparing the letters there won't be any case-senstive issue. Therefore making the code case insenstive.

//2.In order to make gallows into a picture frame you have to change the four methods in Gallows. The first one would be
//  makeCenterPost to make the vertical walls of the picture frame. Then for both makeBeam and makeBase it will be the bottom and
//  top part of the picture frame. Lastly, you would have to remove makeRope because it is the not necessary for the picture frame 
//  and it has the most conflict with the racial aspect of the issue.

//3.No, they won't be the same. cat dorms.txt | sort | uniq | wc -l will first sort the stuff in dorms.txt and then uniq will remove
    all the duplicates in a consecutive manner and then count the lines in dorm.txt.On the other hand, cat dorms.txt | uniq | sort | wc -l
    will get rid of duplicates if they are consecutive and then sort them, but there will still be duplicates if dorms.txt isn't sorted. The
    only case they will output the same thing is if dorms.txt is sorted beforehand or there aren't any duplicate line that aren't consecutive.
    
//4.It is better for MAX_INCORRECT to be in the Man class because the number of wrong guesses correlates to the how many body parts of the man
    is printed. It ensures encapsulation, reduces redundancy, and discourages inconsistencies within the code by having it in one place making
    the style more readable. Also, changes to MAX_INCORRECT will only need to be updated in one place.


*/
public class Hangman {

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
		char[] letters;
		char[] wrongletters;


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
				break;
			}

			
			if (incorrectGuesses >= MAX_INCORRECT) {
				System.out.println("Game over! Player 1 wins!"); //Gives user 2 a losing message
				break;
			}
		}

	}
}
