// Move-to-front
// 11 October 2016, Magnus M. Halldorsson
// Mooshak #224
package s5;

import edu.princeton.cs.algs4.*;

public class MoveToFront {

	// Initialize an ordered list of 256 characters
	private static final int NUMB_OF_CHARS = 256;

	/*************************************************************************
	 * apply move-to-front encoding
	 *************************************************************************/
	public static void encode(BinaryIn in, BinaryOut out) {
		// create a list from 0-256
		char[] sequence = new char[NUMB_OF_CHARS];
		for (char i = 0; i < NUMB_OF_CHARS; i++){
			// add value to the index
			sequence[i] = i;
		}

		// Now, read in each 8-bit character c from standard
		// input one at a time, output the 8-bit index in the sequence where c appears,
		// and move c to the front */
		while (!in.isEmpty()) {
			char c = in.readChar();
			char index = 0;
			// look through the sequence until we find the i-th char
			for (char i = 0; i < sequence.length; i++, index++) {
				if (c == sequence[i]){
					index = i;
					break;
				}
			}
			// The index of the character
			char character = sequence[index];
			// remove the character from the list
			for (int i = index; i > 0; i-- ){
				sequence[i] = sequence[i - 1];
			}
			// put the character to the front
			sequence[0] = character;
			// Write it out
			out.write(index);
		}
	}

	/*************************************************************************
	 * apply move-to-front decoding
	 *************************************************************************/
	public static void decode(BinaryIn in, BinaryOut out) {
		// create a list from 0-256
		char[] sequence = new char[NUMB_OF_CHARS];
		for (char i = 0; i < NUMB_OF_CHARS; i++){
			// add value to the index
			sequence[i] = i;
		}
		while (!in.isEmpty()) {
			char c = in.readChar();
			char character = sequence[c];
            // Write the character
            out.write(character);
			// remove the character from the list
			for (int i = c; i > 0; i-- ){
				sequence[i] = sequence[i - 1];
			}
			// put the character to the front
			sequence[0] = character;
		}
	}

	/*************************************************************************
	 * 								Test client
	 * if args[0] is '-', apply move-to-front encoding
	 * if args[0] is '+', apply move-to-front decoding
	 * if args[0] is 'b', perform both
	 *************************************************************************/
	public static void main(String[] args) {
		if (args.length < 1) {
			StdOut.println("Usage: java MoveToFront (-|+|b) <infile> <outfile>");
			return;
		}
		BinaryIn in = new BinaryIn(args[1]);
		BinaryOut out = new BinaryOut(args[2]);
		char ch = args[0].charAt(0);
		if (ch == '-')
			encode(in, out);
		else if (ch == '+')
			decode(in,out);
		else if (ch == 'b') { // Do both encode then decode
			encode(in,out);
			out.close();
			BinaryIn in2 = new BinaryIn(args[2]);
			out = new BinaryOut(args[2]+".out");
			decode(in2,out);
		}
		out.close(); 
	}

}
