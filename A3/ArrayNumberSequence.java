import java.util.Arrays;

import javax.lang.model.util.ElementScanner14;
import javax.sound.midi.Sequence;

// ArrayNumberSequence.java

/****************************************************************

ArrayNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses an array to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class ArrayNumberSequence implements NumberSequence
{
	// numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

		this.numbers = new double[numbers.length];
		for (int i = 0; i < numbers.length; i++)
		    this.numbers[i] = numbers[i];
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		for (int i = 0; i < numbers.length - 1; i++)
		    s = s + numbers[i] + ", ";
		s = s + numbers[numbers.length - 1];

		return s;
	}

    // length returns the number of numbers in this sequence.
	public int length ()
	{   
        return numbers.length;
	}

    // upperBound returns an upper bound for this sequence.
	public double upperBound ()
	{

        double upBound = numbers[0];
        for (int i = 1; i < numbers.length; i++)
            if (upBound < numbers[i])
				upBound = numbers[i];
        
        return upBound;
	}

    // lowerBound returns a lower bound for this sequence.
    public double lowerBound ()
	{
        double upBound = numbers[0];
        for (int i = 1; i < numbers.length; i++)
            if (upBound > numbers[i])
                upBound = numbers[i];

        return upBound;
	}

    // numberAt returns the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
	public double numberAt (int position) throws IndexOutOfBoundsException
	{
        return numbers[position];

	}

    // positionOf returns the position of the first occurance of
    // the specified number in this sequence.
    // If the number is not present in the sequence, -1 is returned.
    public int positionOf (double number)
	{
        int numIndex = -1;
        for (int i = 0; i < numbers.length; i++)
            if(numbers[i] == number)
                numIndex = i;
                
        return numIndex;
	}

    // isIncreasing returns true if this sequence is increasing,
    // else false is returned.
    public boolean isIncreasing ()
	{
        boolean result = true;
        for (int i = 0; i < numbers.length - 1; i++)
            if (numbers[i] >= numbers[i + 1])
                result = false;
        
        return result;
	}

    // isDecreasing returns true if this sequence is decreasing,
    // else false is returned.
    public boolean isDecreasing ()
	{
        boolean result = true;
        for (int i = 0; i < numbers.length - 1; i++)
            if (numbers[i] <= numbers[i + 1])
                result = false;

        return result;
	}

    // contains returns true if this sequence contains the
    // specified number, else false is returned.
    public boolean contains (double number)
	{
        boolean exist = false;
        int i = 0;
        while (!exist && i < numbers.length)
        {
            if (numbers[i] == number)
                exist = true;
            else
                i++;
        }
        return exist;
	}

    // add adds the specified number to the end of this sequence.
    public void add (double number)
	{
        double[] newArr = new double[numbers.length + 1];
        for (int i = 0; i < numbers.length; i++)
            newArr [i] = numbers[i];
        newArr[newArr.length - 1] = number;
        numbers = newArr;
    }

    // insert inserts the given number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
    public void insert (int position, double number)
	{
        if (position < 0 || position >= length())
			throw new IndexOutOfBoundsException("wrong position " + position);
            
        double[] newArr = new double[numbers.length + 1];
        for (int i = 0; i < position; i++)
            newArr[i] = numbers[i];
        newArr[position] = number;
        for (int i = position + 1; i < newArr.length; i++)
            newArr[i] = numbers[i - 1];
        numbers = newArr;
	}

    // removeAt removes the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
    // The method throws IllegalStateException if there are
    // just two numbers in the sequence.
    public void removeAt (int position) throws IndexOutOfBoundsException
	{
        if (numbers.length <= 2)
		   throw new IllegalStateException("only two numbers in the sequence.");
        
        double[] newArr = new double[numbers.length - 1];
        for (int i = 0; i < position; i++)
            newArr[i] = numbers[i];
        for (int i = position + 1; i < numbers.length; i++)
            newArr[i - 1] = numbers[i];
        numbers = newArr;
	}

    // asArray returns an array containing all of the numbers in
    // this sequence, in the same order as in the sequence.
    public double[] asArray ()
	{
        double[] copy = new double[numbers.length]; 
        for (int i = 0; i < numbers.length; i++)
            copy[i] = numbers[i];

        return copy; 
	}
}