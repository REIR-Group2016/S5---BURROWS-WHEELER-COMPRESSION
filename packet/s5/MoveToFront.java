// Move-to-front
// 11 October 2016, Magnus M. Halldorsson
// Mooshak #44
package s5;

import edu.princeton.cs.algs4.*;
import java.util.ArrayList;
import java.util.List;

public class MoveToFront {

	// Initialize an ordered list of 256 characters
	private static final int NUMB_OF_CHARS = 256;

	/*************************************************************************
	 * apply move-to-front encoding
	 *************************************************************************/
	public static void encode(BinaryIn in, BinaryOut out) {
		// create a list from 0-256
		List<Character> sequence = new ArrayList<Character>();
		for (char i = 0; i < NUMB_OF_CHARS; i++){
			// add value to the index
			sequence.add(i, i);
		}

		// Now, read in each 8-bit character c from standard
		// input one at a time, output the 8-bit index in the sequence where c appears,
		// and move c to the front */
		while (!in.isEmpty()) {
			char c = in.readChar();
			char index = 0;
			// look through the sequence until we find the i-th char
			for (char i = 0; i < sequence.size(); i++, index++) {
				if (c == sequence.get(i)){
					index = i;
					break;
				}
			}
			// The index of the character
			Character character = sequence.get(index);
			// remove the character from the list
			sequence.remove(index);
			// put the character to the front
			sequence.add(0, character);
			// Write it out
			out.write(index);
		}
	}

	/*************************************************************************
	 * apply move-to-front decoding
	 *************************************************************************/
	public static void decode(BinaryIn in, BinaryOut out) {
		// create a list from 0-256
		List<Character> sequence = new ArrayList<Character>();
		for (char i = 0; i < NUMB_OF_CHARS; i++) {
			// add value to the index
			sequence.add(i, i);
		}
		while (!in.isEmpty()) {
			char c = in.readChar();
            Character character = sequence.get(c);
            // Write the character
            out.write(character);
            // remove it from the list
            sequence.remove(c);
            // add it to the front in index 0
            sequence.add(0, character);
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
