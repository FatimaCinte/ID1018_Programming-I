import javax.lang.model.util.ElementScanner14;
import javax.swing.text.Position;

// LinkedNumberSequence.java

/****************************************************************

LinkedNumberSequence represents a sequence of real numbers.
Such a sequence is defined by the interface NumberSequence.
The class uses linked nodes to store the numbers in the sequence.

Author
Fadil Galjic

****************************************************************/

public class LinkedNumberSequence implements NumberSequence
{
	private class Node
	{
		public double number;
		public Node next;

		public Node (double number)
		{
			this.number = number;
			next = null;
		}
	}

	// the first node in the node-sequence
    private Node first;

    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
		if (numbers.length < 2)
		    throw new IllegalArgumentException("not a sequence");

        first = new Node(numbers[0]);
        Node n = first;
		for (int i = 1; i < numbers.length; i++)
		{
			n.next = new Node(numbers[i]);
			n = n.next;
		}
	}

    // toString returns the character string representing this
    // sequence
	public String toString ()
	{
		String s = "";
		Node n = first;
		while (n.next != null)
		{
		    s = s + n.number + ", ";
		    n = n.next;
		}
		s = s + n.number;

		return s;
	}
	
	// length returns the number of numbers in this sequence.
	public int length ()
	{   
		int number = 0;
		Node n = first;
		while (n != null)
		{
			number++;
			n = n.next;
		}
		return number;

	}

    // upperBound returns an upper bound for this sequence.
	public double upperBound ()
	{
		Node n = first;
		double max = n.number;
		
		while(n != null)
		{
			if (max < n.number)
				max = n.number;
			n = n.next;
		}
		return max;
	}

    // lowerBound returns a lower bound for this sequence.
    public double lowerBound ()
	{
		Node n = first;
		double min = n.number;
		
		while(n != null)
		{
			if (min > n.number)
				min = n.number;
			n = n.next;
		}
		return min;
	}

    // numberAt returns the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
	public double numberAt (int position) throws IndexOutOfBoundsException
	{
		if (position < 0 || position >= length())
			throw new IndexOutOfBoundsException("wrong position " + position);
		
		Node n = first;
		for (int i = 0; i < position; i++)
			n = n.next;
		
		return n.number;
	}

    // positionOf returns the position of the first occurance of
    // the specified number in this sequence.
    // If the number is not present in the sequence, -1 is returned.
    public int positionOf (double number)
	{
		int index = -1;
        Node n = first;
        int i = 0; 
        while (index == -1 && n != null) 
        {
            if (n.number == number)
                index = i;
            else
            {
                n = n.next;
                i++;
            }
        }
        return index;
	}

    // isIncreasing returns true if this sequence is increasing,
    // else false is returned.
    public boolean isIncreasing ()
	{
		boolean isIncrs = true;
		for (int i = 0; i < length() - 1; i ++)
		{
			if (numberAt(i) >= numberAt(i + 1))
				isIncrs = false;
		}

		return isIncrs;
	}

    // isDecreasing returns true if this sequence is decreasing,
    // else false is returned.
    public boolean isDecreasing ()
	{
		boolean isDcrs = true;
		for (int i = 0; i < length() - 1; i ++)
		{
			if (numberAt(i) <= numberAt(i + 1))
				isDcrs = false;
		}

		return isDcrs;
	}

    // contains returns true if this sequence contains the
    // specified number, else false is returned.
    public boolean contains (double number)
	{
		Node n = first;
		int i = 0;
		while(n != null)
		{
			double num = numberAt(i);
			if(num == number)
				return true;
			else
			{
				n = n.next;
				i++;
			}
		}
		return false;
	}

    // add adds the specified number to the end of this sequence.
    public void add (double number)
	{
		Node node = new Node(number);
		Node n = first;
		while (n.next != null)
			n = n.next;
		n.next = node;

    }

    // insert inserts the given number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
    public void insert (int position, double number) 
	{
		if (position < 0 || position >= length())
			throw new IndexOutOfBoundsException("wrong position " + position);

		Node node = new Node (number);
        if (position == 0)
        {
            node.next = first;
            first = node;
        }
        else
        {
            Node n = first;
            for (int i = 1; i < position; i++)
            	n = n.next;
            node.next = n.next;
            n.next = node;
        }
	}

    // removeAt removes the number at the specified position
    // in this sequence.
    // The method throws IndexOutOfBoundsException if the
    // position is wrong.
    // The method throws IllegalStateException if there are
    // just two numbers in the sequence.
    public void removeAt (int position) throws IndexOutOfBoundsException
	{
		if (length() <= 2)//first.next.next == null
			throw new IllegalStateException("only two numbers in the sequence.");

		Node node = first;
		if (position == 0)
			first = node.next;
		else 
		{
			for (int i = 1; i < position; i++)
				node = node.next;
			
			node.next  = node.next.next;
		}
	}

    // asArray returns an array containing all of the numbers in
    // this sequence, in the same order as in the sequence.
    public double[] asArray ()
	{
		double[] newArr = new double[length()];
		Node node = first;
		int i = 0;
		while (node != null) 
		{
			newArr[i] = node.number; //interface number
			node = node.next;
			i++;
		}
		return newArr;
	}
}

