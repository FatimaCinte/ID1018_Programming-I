// NumberSequenceTest.java

/****************************************************************

NumberSequenceTest is a test program for the classes
ArrayNumberSequence and LinkedNumberSequence.

Author
Fadil Galjic

****************************************************************/

import static java.lang.System.out;

import javax.swing.text.Position;

class NumberSequenceTest
{
    public static void main (String[] args)
    {   
		double[] realNumbers =
		    {1.0, 2.0, 3.0, 5.0, 8.0, 13.0, 21.0};
        NumberSequence sequence = null;
        //sequence = new ArrayNumberSequence(realNumbers);
        sequence = new LinkedNumberSequence(realNumbers);
        out.println("the sequence:");
        out.println(sequence);
        out.println();

        out.println("length: " + sequence.length());
        out.println ("one upper bound: " + sequence.upperBound());
        out.println ("one lower bound: " + sequence.lowerBound());
        out.println();

        out.println ("number at position 4: " + sequence.numberAt(4));
        out.println ("position of 8.0: " + sequence.positionOf(8.0));
        out.println();

        out.println ("is increasing: " + sequence.isIncreasing());
        out.println ("is decreasing: " + sequence.isDecreasing());
        out.println("contains 21.0: " + sequence.contains(21.0));
        out.println();

        out.println ("add 34.0: ");
        sequence.add(34.0);
        out.println(sequence);
        out.println ("insert 0.0 at position 7:");
        sequence.insert(7, 0.0);
        out.println (sequence);
        out.println ("remove at position 7:");
        sequence.removeAt(7);
        out.println(sequence);

        out.println();
        out.println("corresponding array:");
        sequence.asArray();
        out.println(sequence);


















       

    }
}