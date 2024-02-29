// SynonymHandler

/****************************************************************

SynonymHandler handles information about synonyms for various
words.

The synonym data can be read from a file and handled in various
ways. These data consists of several lines, where each line begins
with a word, and this word is followed with a number of synonyms.

The synonym line for a given word can be obtained. It is possible
to add a synonym line, and to remove the synonym line for a given
word. Also a synonym for a particular word can be added, or
removed. The synonym data can be sorted. Lastly, the data can be
written to a given file.

Author: Fadil Galjic

****************************************************************/

import java.io.*;    // FileReader, BufferedReader, PrintWriter,
//import java.lang.reflect.Array; // IOException
import java.util.*;  // LinkedList

class SynonymHandler
{
	// readSynonymData reads the synonym data from a given file
	// and returns the data as an array
    public static String[] readSynonymData (String synonymFile)
                                         throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(synonymFile));
        LinkedList<String> synonymLines = new LinkedList<>();
        String synonymLine = reader.readLine();
        while (synonymLine != null)
        {
			synonymLines.add(synonymLine);
			synonymLine = reader.readLine();
		}
		reader.close();

		String[] synonymData = new String[synonymLines.size()];
		synonymLines.toArray(synonymData);

		return synonymData;
    }

    // writeSynonymData writes a given synonym data to a given
    // file
    public static void writeSynonymData (String[] synonymData,
        String synonymFile) throws IOException
    {
        PrintWriter writer = new PrintWriter(synonymFile);
        for (String synonymLine : synonymData)
            writer.println(synonymLine);
        writer.close();

    }

    // synonymLineIndex accepts synonym data, and returns the
    // index of the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    private static int synonymLineIndex (String[] synonymData,
        String word)
    {
		int delimiterIndex = 0;
		String w = "";
		int i = 0;
		boolean wordFound = false;
		while (!wordFound  &&  i < synonymData.length)
		{
		    delimiterIndex = synonymData[i].indexOf('|');
		    w = synonymData[i].substring(0, delimiterIndex).trim();
		    if (w.equalsIgnoreCase(word))
				wordFound = true;
			else
				i++;
	    }

	    if (!wordFound)
	        throw new IllegalArgumentException(
		        word + " not present");

	    return i;
	}

    // getSynonymLine accepts synonym data, and returns
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
    public static String getSynonymLine (String[] synonymData, String word)
    {
		int index = synonymLineIndex(synonymData, word);

	    return synonymData[index];
	}

    // addSynonymLine accepts synonym data, and adds a given
    // synonym line to the data
	public static String[] addSynonymLine (String[] synonymData, String synonymLine)
	{
		String[] synData = new String[synonymData.length + 1];
		for (int i = 0; i < synonymData.length; i++)
		    synData[i] = synonymData[i];
		synData[synData.length - 1] = synonymLine;

	    return synData;
	}
    
    // removeSynonymLine accepts synonym data, and removes
    // the synonym line corresponding to a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static String[] removeSynonymLine (String[] synonymData, String word)
	{
        int wordIndex = synonymLineIndex(synonymData, word);

        String[] synLine = new String[synonymData.length - 1];
        for (int i = 0; i < wordIndex; i++) 
            synLine[i] = synonymData[i];
        for (int i = wordIndex + 1; i < synonymData.length; i++)
            synLine[i - 1] = synonymData[i]; 
            
        return synLine;
	}

    // addSynonym accepts synonym data, and adds a given
    // synonym for a given word.
    // If the given word is not present, an exception of
    // the type IllegalArgumentException is thrown.
	public static void addSynonym (String[] synonymData, String word, String synonym)
	{
        //synonymData[synonymLineIndex(synonymData, word)] += ", " + synonym;
        
        String[] str1 = getSynonymLine(synonymData, word).split("[| ,]+");

        String[] copy = new String[str1.length + 1]; 
        for (int i = 0; i < str1.length; i++)
            copy[i] = str1[i];
        copy[copy.length - 1] = synonym; 
        
        copy[0] += " |";
        String lastString = Arrays.toString(copy).replace("[", "").
            replace("]","").replace("|,", "|");
        
        synonymData[synonymLineIndex(synonymData, word)] = lastString;
    }

    // removeSynonym accepts synonym data, and removes a given
    // synonym for a given word.
    // If the given word or the given synonym is not present, an
    // exception of the type IllegalArgumentException is thrown.
	public static void removeSynonym (String[] synonymData, String word, String synonym)
	{
        String[] str1 = getSynonymLine(synonymData, word).split("[| ,]+");
        
        String[] synData = new String[str1.length - 1];
        int counter = 0;
        for (int i = 0; i < str1.length; i++) 
        {
            if(!str1[i].equalsIgnoreCase(synonym)) 
                {
                    synData[counter] = str1[i];
                    counter++;
                }
        }
        
        synData[0] += " |";
        String lastString = Arrays.toString(synData).replace("[", "").
                replace("]","").replace("|,", "|");
        
        synonymData[synonymLineIndex(synonymData, word)] = lastString;   
    }

    // sortIgnoreCase sorts an array of strings, using
    // the selection sort algorithm
    private static void sortIgnoreCase (String[] strings)
    {
        for (int i = 0; i < strings.length - 1; i++)  
        {  
            int minIndex = i;  
            for (int j = i + 1; j < strings.length; j++)
            {  
                if (strings[j].compareToIgnoreCase(strings[minIndex]) < 0) 
                    minIndex = j;  
            }  
            
            if(i != minIndex)
            {
            //byter plats på element
            String p = strings[minIndex];   
            strings[minIndex] = strings[i];  
            strings[i] = p;
            } 
        }
	}

    // sortSynonymLine accepts a synonym line, and sorts
    // the synonyms in this line
    private static String sortSynonymLine (String synonymLine)
    {
        String[] Line = synonymLine.split("[|]");
        String[] synLineSplit = Line[1].trim().split("[ ,]+");
        sortIgnoreCase(synLineSplit);

        String sortedString = Line[0].trim() + " | " + Arrays.toString(synLineSplit).
            replace("[", "").replace("]","");

        return sortedString;
	}

    // sortSynonymData accepts synonym data, and sorts its
    // synonym lines and the synonyms in these lines
	public static void sortSynonymData (String[] synonymData)
	{
        //sorts synonyms
        for(int i = 0; i < synonymData.length; i++)   
            synonymData[i] = sortSynonymLine(synonymData[i]);
        
        //sorts synonym lines
        sortIgnoreCase(synonymData);
	} 
}