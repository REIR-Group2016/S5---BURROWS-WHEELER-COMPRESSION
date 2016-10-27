package s5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {

	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
	public static void transform(BinaryIn in, BinaryOut out) {
		String input = in.readString();
		CircularSuffixArray circularSuffixArray = new CircularSuffixArray(input);

		for (int i = 0; i < circularSuffixArray.length(); i++){
			if(circularSuffixArray.index(i) == 0){
				out.write(i);
				break;
			}
		}

		for(int i = 0; i < circularSuffixArray.length(); i++){
			int index = circularSuffixArray.index(i);
			if(index == 0){
				out.write(input.charAt(input.length() - 1));
			}else{
				out.write(input.charAt(index));
			}
		}

	}
	
	// apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	public static void inverseTransform(BinaryIn in, BinaryOut out) {

	}

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
