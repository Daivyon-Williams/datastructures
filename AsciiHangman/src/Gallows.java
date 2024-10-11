import java.util.Arrays;

public class Gallows {

	/* Gallows look like this:
	 *    ____
	 *   |    |
	 *   |    O
	 *   |   \|/
	 *   |   / \
	 * __|__
	 */

	protected Man man;
	protected char[] frame;

	public Gallows() {
		/* TODO: Initialize instance variables and
		 * otherwise construct the Gallows object.
		 */
		this.man = new Man();
		this.frame = new char[60];
		Arrays.fill(frame, ' ');
		for(int i = 0; i < frame.length; i+=10 ) {
			frame[i] = '\n';
		}
		makeCenterPost();
		makeBeam();
		makeBase();
		makeRope();
		
		
	}

	public void makeCenterPost() {
		/* TODO: Modify the frame to include
		 * the central post.
		 */
		frame[13] = '|';
		frame[23] = '|';
		frame[33] = '|';
		frame[43] = '|';
		frame[53] = '|';
	}

	public void makeBeam() {
		/* TODO: Modify the frame to include
		 * the top beam.
		 */
		frame[4] = '_';
		frame[5] = '_';
		frame[6] = '_';
		frame[7] = '_';
	}

	public void makeBase() {
		/* TODO: Modify the frame to include the
		 * base of the gallows.
		 */
		frame[51] = '_';
		frame[52] = '_';
		frame[54] = '_';
		frame[55] = '_';
	}

	public void makeRope() {
		/* TODO: Modify the frame to include the rope. */
		frame[18] = '|';
	}

	public void hang() {
		man.hang();
	}

	public boolean isAlive() {
		/* TODO: Check if the hangman is alive */
		return man.isAlive();
	}

	public String toString() {
		/* TODO: Render the hangman as a string
		 */
		char[] m = man.toCharArray();
		frame[28] = m[0]; 
        frame[37] = m[1];
        frame[38] = m[2];
        frame[39] = m[3];  
        frame[47] = m[4]; 
        frame[49] = m[5];  
		return new String(frame);
	}

	/* This code is included to allow you to test the
	 * Gallows independently from the Hangman code.
	 */
	public static void main(String[] args) {
		Gallows g = new Gallows();
		System.out.println(g);
		while(g.isAlive()) {
			g.hang();
			System.out.println(g);
		}
	}
}
