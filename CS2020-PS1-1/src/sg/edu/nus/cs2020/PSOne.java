package sg.edu.nus.cs2020;

public class PSOne {
	
	//Problem 1

	/**
	 * 
	 * MysteryFunction is used to calculate exponentiation
	 * 
	 * @param argA is the base
	 * @param argB is the exponent
	 * @return is argA raised to the power argB
	 */
	static int MysteryFunction(int argA, int argB)
	{
		int c = 1;
		int d = argA;
		int e = argB;
		while (e>0)
		{
			if (2*(e/2)!=e)
			{
				c = c*d;
			}
			d = d*d;
			e = e/2;
		}
		return c;
	}
	
	/**
	 * @param main function calls MysteryFunction on (5,5)
	 */
	public static void main(String args[])
	{
			int output = MysteryFunction(5,5);
			System.out.printf("The answer is: " + output + ".");
	}
			
}

//Mystery Function calculates the value of the first argument
//raised to the power of second argument.
