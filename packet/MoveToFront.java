// Move-to-front
// 11 October 2016, Magnus M. Halldorsson
package s5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;

public class MoveToFront {

	// apply move-to-front encoding
	public static void encode(BinaryIn in, BinaryOut out) {
	    // TO BE ADDED
		
		//Gr�mur: Basically we need to arrange the input file differently
		//Gr�mur: And Output it as out
		//Gr�mur: Sadly the specifics are not available right now but will tomorrow, teachers are
		//Gr�mur: Not prepared to help with this just yet.
		//Gr�mur: Decode is the same but in reverse, returning the arranged file out into the original in
	}
	
	// apply move-to-front decoding
	public static void decode(BinaryIn in, BinaryOut out) {
	    // TO BE ADDED
	}
	
	// if args[0] is '-', apply move-to-front encoding
	// if args[0] is '+', apply move-to-front decoding
	// if args[0] is 'b', perform both
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
