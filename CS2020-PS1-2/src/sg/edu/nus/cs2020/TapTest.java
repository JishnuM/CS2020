package sg.edu.nus.cs2020;

/**
 * Class TapTest
 * @author Jishnu
 * To Test Suitability of Different Tap and Size Combinations
 */
public class TapTest {
	public int size, tap = -1;
	
	public TapTest(int input_size, int input_tap)
	{
		size = input_size;
		tap = input_tap;
	}
	
	/**
	 * @return An integer value which is the number
	 * of shifts before an integer repeats.
	 */
	public int do_test()
	{
		int[] start_seed = new int[size];
		int[] test_output = new int[(int)Math.pow(2,size)];
		int j = 0;
		boolean has_repeat = false;
		
		//Generate a random binary array for seeding
		for (int i=0;i<size;i++)
		{
			start_seed[i] = (int)Math.round(Math.random());
		}
		
		//Create a new shift register and seed it
		ShiftRegister shifter = new ShiftRegister(size,tap);
		
		shifter.setSeed(start_seed);
		
		//System.out.println(shifter.get_state());
		
		//Order(n^2) algorithm that runs while there are no
		//repeats or the maximum number of unique binary
		//sequences are reached
		
		while (!(has_repeat)&&(j!=(int)Math.pow(2,size))){
			//Shifts the register and stores its state in the array
			shifter.shift();
			test_output[j] = shifter.get_state();
			//System.out.println(test_output[j]);
			
			//Uses (very slow) linear search to find repeats
			has_repeat = naive_search(test_output,test_output[j],j++);
		}
		return j;
	}
	
	//A naive search algorithm that checks elements
	//in an array individually.
	public boolean naive_search(int[] array, int key,int until)
	{
		for (int i = 0;i<until;i++)
		{
			if (array[i]==key){
				return true;
			}
		}
		return false;
	}
}
