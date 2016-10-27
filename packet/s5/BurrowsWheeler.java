package s5;

import edu.princeton.cs.algs4.BinaryIn;
import edu.princeton.cs.algs4.BinaryOut;
import edu.princeton.cs.algs4.StdOut;

public class BurrowsWheeler {

	// apply Burrows-Wheeler transform, reading from standard input and writing to standard output
	public static void transform(BinaryIn in, BinaryOut out)
	 {
		
		//Convert bitstream to String to use for CircularSuffixArray
		
		String input = in.readString();
		
		CircularSuffixArray transformer = new CircularSuffixArray(input); // CircularSuffixArray to be used
		
		for(int i = 0; i < transformer.length(); i++) // Write out
		{
			if(transformer.index(i) == 0)
			{
				out.write(i);
			}
		}
		
		for(int i = 0; i < transformer.length(); i++) //Transformation
		{
			int index = transformer.index(i);
			if(index == 0)
			{
				out.write(input.charAt(input.length() - 1));
			}
			else
			{
				out.write(input.charAt((index+input.length() -1) % input.length()));
			}
		}
	}
	
	// apply Burrows-Wheeler inverse transform, reading from standard input and writing to standard output
	public static void inverseTransform(BinaryIn in, BinaryOut out) 
	{
		int first = in.readInt();
		String input = in.readString();
		
		CircularSuffixArray inverter = new CircularSuffixArray(input); // CircularSuffixArray to be used
		
		//I was told to count how often a letter comes up and such see below
		//Ascii codes used for position.
		//However I didn't end up using it and understanding its importance so I commented it out
		//Delete it if you want
		
		/*int [] count = new int [256];
		
		for(int i = 0; i < input.length(); i++)
		{
			count[input.charAt(i)]++;
		} */
		
		//Need to define a next, that checks what came after in the original sorted suffixes
		
		int [] next = new int [input.length()];
		
		for(int i = 0; i < inverter.length(); i++)
		{
			if(i+1 >= inverter.length())
			{
				next[i] = inverter.index(0);
			}
			else
			{
				next[i] = inverter.index(i+1);
			}
			
		}
		
		int [] original = new int [inverter.length()];
		original[0] = first; // Our First value, essential for further recreation of original string
		
		//This part isn't giving quite the right result
		
		for(int i = 1; i < inverter.length(); i++) //loop to use for recreating original string
		{ 
			original[i] = next[first];
			first = original[i];
		}
		
		// We should now have all the numbers to recreate the original string based on the input of lasts.
		
		for(int i = 0; i < inverter.length(); i++)
		{
			out.write(input.charAt(original[i]));
			System.out.println(input.charAt(original[i])); //Debug
		}
		
	}

	public static void main(String[] args) { // This main is for our file testing
		if (args.length < 0) {
			StdOut.println("Usage: java BurrowsWheeler (-|+) <infile> <outfile>");
			return;
				}
		BinaryIn in = new BinaryIn("D:/Verkefni/Reiknirit/Data/abra.txt");
		BinaryOut out = new BinaryOut("D:/Verkefni/Reiknirit/Data/output/abra_out.txt");
		char ch = 'b';
		if (ch == '-')
			transform(in, out);
		else if (ch == '+')
			inverseTransform(in,out);
		else if (ch == 'b') { // Do both encode then decode
			transform(in, out);
			out.close();
			BinaryIn in2 = new BinaryIn("D:/Verkefni/Reiknirit/Data/output/abra_out.txt");
			out = new BinaryOut("D:/Verkefni/Reiknirit/Data/output/abra_out_2.txt");
			inverseTransform(in2,out);
		}
		out.close();
	}
	
	/*public static void main(String[] args) { // mooshak test main
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
	} */

}
