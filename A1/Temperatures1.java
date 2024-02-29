// Temperatures1.java

// proccessing measurement data


/**********************************************************************

A problem: processing measurement data

Temperature readings are taken in one and the same place for a couple
of weeks. The readings are taken regularly � the same number of
readings each week. At the end of the measurement period the collected
data is to be processed: for each week the least, the greatest and the
average temperature is to be determined. The least, greatest and
average temperature for the whole period is also to be computed.

A solution to the problem

This program reads the temperatures and displays them. Then the least,
greatest and average temperature for each week is computed and stored.
These results are printed on the standard output device. Finally, the
least, greatest and average temperature over the whole measurement
period is decided. These results are also printed on the standard
output device.

Author: Fadil Galjic

**********************************************************************/


import java.util.*; // Scanner, Locale
import static java.lang.System.out;
class Temperatures1
{
	public static void main (String[] args)
	
	{
		out.println("TEMPERATURES\n");

		// input tool
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

        // enter the number of weeks and measurements
		out.print("number of weeks: ");
		int nofWeeks = in.nextInt();
		out.print("number of measurements per week: ");
		int nofMeasurementsPerWeek = in.nextInt();

        // storage space for temperature data
        double[][] t = new double[nofWeeks + 1][nofMeasurementsPerWeek + 1];

        // read the temperatures 
		for (int week = 1; week <= nofWeeks; week++)
		{
			out.println("temperatures - week " + week + ":");
			for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
				t[week][reading] = in.nextDouble ();
		}
		out.println("");

		// show the temperatures
		out.println("the temperatures");
        for (int week = 1; week <= nofWeeks; week++)
        {
			for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
			    out.print(t[week][reading] + " ");
		    out.println("");
		}
		out.println("");

		// the least, greatest and average temperatures - weekly
		double[] minT = new double[nofWeeks + 1];
		double[] maxT = new double[nofWeeks + 1];
		double[] sumT = new double[nofWeeks + 1];
		double[] avgT = new double[nofWeeks + 1];
        // add code here

		// the least & greatest temperature - weekly
		for (int week = 1; week <= nofWeeks; week++) 
		{	
            minT[week] = t[week][1];
			maxT[week] = t[week][1];
            for (int reading = 2; reading <= nofMeasurementsPerWeek; reading++) 
			{
				if (minT[week] > t[week][reading]) 
					minT[week] = t[week][reading];

				if (maxT[week] < t[week][reading])
					maxT[week] = t[week][reading];
			}
            
        }
        
		
		// average temperature - weekly
		for (int week = 1; week <= nofWeeks; week++) 
		{
			sumT[week] = 0;
			for (int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
			{
				sumT[week] += t[week][reading];
			}
            
			avgT[week] = (double) sumT[week] / (nofMeasurementsPerWeek);
        }

		// show the least, greatest and average temperatures
		out.println("the least, greatest and average temperatures" + " - weekly");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(minT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(maxT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(avgT[week] + " ");
		out.println("");
		out.println();

		// the least, greatest and average temperatures - whole period
		double minTemp = minT[1];
		double maxTemp = maxT[1];
		double sumTemp = sumT[1];
		double avgTemp = 0;
        // add code here

		// the least & greatest temperature - whole period

		for (int i = 2; i < minT.length; i++)  
		{	
			if (minTemp > minT[i]) 
					minTemp = minT[i];

			if (maxTemp < maxT[i])
					maxTemp = maxT[i];
		}
	
		// average temperature - whole period
		for (int i = 2; i < sumT.length; i++) 
		{
			sumTemp += sumT[i];
        }

		avgTemp = (double) sumTemp / (nofWeeks * nofMeasurementsPerWeek);
		
        // show the least, greatest and average temperature for
        // the whole period
		out.println("the least, greatest and average temperature" + " - whole period");
        out.println(minTemp + "\n" + maxTemp + "\n" + avgTemp);

		in.close();
    }
}
