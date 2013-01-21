package sg.edu.nus.cs2020;

/**
 * Class TapTest
 * @author Jishnu
 * To Test Suitability of Different Taps
 */
public class TapTest {
	public int size, tap = -1;
	
	public TapTest(int input_size, int input_tap)
	{
		size = input_size;
		tap = input_tap;
	}
	
	public int do_test()
	{
		int[] start_seed = new int[size];
		int[] test_output = new int[(int)Math.pow(2,size)];
		int j = 0;
		boolean has_repeat = false;
		
		for (int i=0;i<size;i++)
		{
			start_seed[i] = (int)Math.round(Math.random());
		}
		
		ILFShiftRegister shifter = new ShiftRegister(size,tap);
		
		shifter.setSeed(start_seed);
		
		while (!(has_repeat)&&(j!=(int)Math.pow(2,size))){
			
		}
	}
}
