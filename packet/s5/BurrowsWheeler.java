package s5;
// Mooshak #659
import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BurrowsWheeler {

	/******************************************************************************************************
	 * apply Burrows-Wheeler transform, reading from standard input and writing to standard output
	 *****************************************************************************************************/
	public static void transform(BinaryIn in, BinaryOut out) {
		String input = in.readString();
		CircularSuffixArray transformer = new CircularSuffixArray(input);

        // prints the index where input is in the circularSuffixArray
		for (int i = 0; i < transformer.length(); i++){
			if(transformer.index(i) == 0){
				out.write(i);
				break;
			}
		}
		// the input and the last character in each row
        // which is the first one in the previous rotation
        // (that's why we use -1)
		for(int i = 0; i < transformer.length(); i++){
			int index = transformer.index(i);
			if(index == 0){
				out.write(input.charAt(input.length() - 1));
			}else{
				out.write(input.charAt(index - 1));
			}
		}

	}
	/******************************************************************************************************
	 * apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	 *****************************************************************************************************/
	public static void inverseTransform(BinaryIn in, BinaryOut out) {

		// index where the original suffix appears in the shifted sorted suffixes
		int first = in.readInt();
		// reading the characters in the last column of each row
		String input = in.readString();
		// the characters in the last column of each row in array
		char[] t = input.toCharArray();

		//-----------------------------------------------------------//
		// corner case: if input string is full of same character
		boolean different = false;
		for (int i = 1; i < t.length; i++) {
			if (t[i - 1] != t[i]) {
				different = true;
				break;
			}
		}
		if (!different) {
			out.write(input);
			return;
		}
		//-----------------------------------------------------------//

		// new array of indexes construct
		int[] next = new int[t.length];
		// first row is sorted t[]
		char[] sorted = new char[t.length];

		// copying and initializing the values
		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = t[i];
			next[i] = -1;
		}
		// sort the values
		Arrays.sort(sorted);

		// calculating the next array
		for (int i = 0; i < sorted.length; i++) {
			for (int j = 0; j < t.length; j++) {
				// If sorted row i and j both start with the same character
				// and i < j, then next[i] < next[j]
				if (sorted[i] == t[j]) {
					if (i > 0 && sorted[i - 1] == sorted[i] && next[i - 1] >= j) {
						continue;
					}
					next[i] = j;
					break;
				}
			}
		}
		// printing the calculated values
		out.write(sorted[first]);
		int current = next[first];
		while (current != first) {
			out.write(sorted[current]);
			current = next[current];
		}
	}

	/****************************************************************************
	 * Test client
	 ****************************************************************************/
	public static void main(String[] args) {
		if (args.length < 3) {
			StdOut.println("Usage: java BurrowsWheeler (-|+) <infile> <outfile>");
			return;
				}
		BinaryIn in = new BinaryIn(args[1]);
		BinaryOut out = new BinaryOut(args[2]);
		char ch = args[0].charAt(0);
		if (ch == '-')
			transform(in, out);
		else if (ch == '+')
			inverseTransform(in,out);
		else if (ch == 'b') { // Do both encode then decode
			transform(in, out);
			out.close();
			BinaryIn in2 = new BinaryIn(args[2]);
			out = new BinaryOut(args[2]+".out");
			inverseTransform(in2,out);
		}
		out.close();
	}

}
