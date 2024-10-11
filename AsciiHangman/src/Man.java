import java.util.Arrays;
public class Man {
	
	/*  An ASCII Hangman looks like this:
	 *   O 
	 *  \|/
	 *  / \
	 */

	protected static final int MAX_INCORRECT = 6;
	protected int numIncorrect;
	protected char[] body;
	
	public Man() {
		/* TODO: Intiaialize the Man object */
		this.numIncorrect = 0;
		this.body = new char[6];
		Arrays.fill(body, ' ');
	}
	
	public boolean isAlive() {
		/* TODO: Check if the man is alive */
		return numIncorrect < MAX_INCORRECT;
	}
	
	public void hang() {
		/* TODO: modify the man data to reflect * a new incorrect guess. */
		if(numIncorrect<MAX_INCORRECT) {
			numIncorrect++;
			updateBody();
		}
	}
	
	private void updateBody() {
		if(numIncorrect == 1) {
			body[0] = 'O';
		}else if(numIncorrect == 3) {
			body[1] = '\\';
		}else if(numIncorrect == 2) {
			body[2] = '|';
		}else if(numIncorrect == 4) {
			body[3] = '/';
		}else if(numIncorrect ==5) {
			body[4] = '/';
		}else if(numIncorrect == 6) {
			body[5] = '\\';
		}
	}
	
	public String toString() {
		/* TODO: Render the man as a string. */
		return new String(body);
	}
	
	protected char[] toCharArray() {
		/* TODO: Return the relevant data */
		return body;
	}
	
	/* Again, this main method is provided to assist
	 * with testing. 
	 */
	public static void main(String[] args) {
		Man m = new Man();
		for(int i=0; i<Man.MAX_INCORRECT; i++) {
			m.hang();
			System.out.println(m);
		}
	}
	
}
