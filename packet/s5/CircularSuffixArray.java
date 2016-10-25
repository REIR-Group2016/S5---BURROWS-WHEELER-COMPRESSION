// Circular Suffix Array
// 11 October 2016
// Magnus M. Halldorsson
package s5;

import java.util.*;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class CircularSuffixArray {

    private List<StateContainer> mSortedSuffixes;

    public CircularSuffixArray(String input) {

        if(input == null){
            throw new NullPointerException();
        }

        char charAt0;
        StringBuilder builder = new StringBuilder(input);
        int length = builder.length();

        mSortedSuffixes = new ArrayList<StateContainer>();
        for (int i = 0; i < length; i++) {
            // add the original string and shift it
            mSortedSuffixes.add(new StateContainer(builder.toString(), i));

            // take the first character
            charAt0 = builder.charAt(0);
            // remove that character
            builder.deleteCharAt(0);
            // add the character to the end
            builder.append(charAt0);
        }
        Comparator<StateContainer> comparator = new Comparator<StateContainer>() {
            public int compare(StateContainer o1, StateContainer o2) {
                return o1.mShiftedSuffix.compareTo(o2.mShiftedSuffix);
            }
        };
        Collections.sort(mSortedSuffixes, comparator);
    }

    /*******************************************************
     * Returns the length of the input string.
     *******************************************************/
    public int length() {
        // lenght of the list
        return mSortedSuffixes.size();
    }

    /**
     * Returns the index into the original string of the <em>i</em>th smallest circular suffix.
     * That is, {@code text.substring(sa.index(i))} is the <em>i</em>th smallest circular suffix.
     *
     * @param i an integer between 0 and <em>n</em>-1
     * @return the index into the original string of the <em>i</em>th smallest suffix
     * @throws java.lang.IndexOutOfBoundsException unless {@code 0 <= i < n}
     */
    public int index(int i){
        if(i < 0 || i >= length()){
            throw new IndexOutOfBoundsException();
        }
        return mSortedSuffixes.get(i).mOriginalPosition;
    }

    private class StateContainer{
        int mOriginalPosition;
        String mShiftedSuffix;

        private StateContainer(String suffix, int position){
            mShiftedSuffix = suffix;
            mOriginalPosition = position;
        }
    }

    public static void main(String[] args) // unit testing
    {

        In in = new In(args[0]);
        String s = in.readAll();  // Read whole file
        String pair = s + s;
        CircularSuffixArray suffix = new CircularSuffixArray(s);

        StdOut.println("  i ind select");
        StdOut.println("-------------------");

        for (int i = 0; i < s.length(); i++) {
            int index = suffix.index(i);
            String ith = "\"" + pair.substring(index, index + Math.min(index + 50, s.length())) + "\"";
            StdOut.printf("%3d %3d %s\n", i, index, ith);
        }
    }

}
