package sg.edu.nus.cs2020;

/**
 * class TestCase
 * @author Jishnu
 * Description - Runs Test Cases to test and debug ShiftRegister
 * 
 */
public class TestCase {
	/**
	 * Main function that runs all the test cases and prints output
	 */
	public static void main(String[] args){
		
		//Test Case 1 - Given - To Test Shift
		int[] array1 = new int[9];
		array1[0] = 0;
		array1[1] = 1;
		array1[2] = 0;
		array1[3] = 1;
		array1[4] = 1;
		array1[5] = 1;
		array1[6] = 1;
		array1[7] = 0;
		array1[8] = 1;
		ShiftRegister shifter1 = new ShiftRegister(9,7);
		shifter1.setSeed(array1);
		for (int i=0; i<10; i++){
			int j = shifter1.shift();
			System.out.print(j);
		}
		
		System.out.println('\n');
		
		//Test Case 2 - Given - To Test Generate
		int[] array2 = new int[9];
		array2[0] = 0;
		array2[1] = 1;
		array2[2] = 0;
		array2[3] = 1;
		array2[4] = 1;
		array2[5] = 1;
		array2[6] = 1;
		array2[7] = 0;
		array2[8] = 1;
		ShiftRegister shifter2 = new ShiftRegister(9,7);
		shifter2.setSeed(array2);
		for (int i=0; i<10; i++){
			int j = shifter2.generate(3);
			System.out.println(j);
		}
		
		System.out.println('\n');
		
		//Test Case 3 - User-Created
		int[] array3 = {1,0,0,1,1,0,0,0,0,1,1};
		ShiftRegister shifter3 = new ShiftRegister(11,8);
		shifter3.setSeed(array3);
		for (int i=0; i<10; i++){
			int j = shifter3.generate(4);
			System.out.println(j);
		}
		
		System.out.println('\n');
		
		//Test Case 4 - UserCreated
		int[] array4 = {0};
		ShiftRegister shifter4 = new ShiftRegister(1,0);
		shifter4.setSeed(array4);
		for (int i=0; i<10; i++){
			int j = shifter4.generate(5);
			System.out.println(j);
		}
		
		System.out.println('\n');
		
		//Test Case 5 - User Created
		int[] array5 = {1,0,1,0,0,1,0,0,1,1};
		ShiftRegister shifter5 = new ShiftRegister(10,5);
		shifter5.setSeed(array5);
		for (int i=0; i<10; i++){
			int j = shifter5.shift();
			System.out.print(j);
		}
		
		System.out.println('\n');
		
		//Test Case 6 - User Created
		int[] array6 = {1};
		ShiftRegister shifter6 = new ShiftRegister(1,0);
		shifter6.setSeed(array6);
		for (int i=0; i<10; i++){
			int j = shifter6.shift();
			System.out.print(j);
		}
		
		//This code uses class TapTest to test
		//various taps and size combinations
		//to find how often the code repeats
		
		TapTest test = new TapTest(9,6);
		System.out.print(test.do_test());

		System.out.println("\n");

		//Tests using a string to seed the shift register
		
		String test_string = "CatWalkOnTheMoonDance";
		
		ShiftRegister shifter7 = new ShiftRegister(0,0);
		
		shifter7.stringShiftRegister(test_string, 5);
		
		for (int i=0; i<10; i++){
			int j = shifter7.shift();
			System.out.print(j);
		}
		
		System.out.println("\n");
		for (int i=0; i<10; i++){
			int j = shifter7.generate(5);
			System.out.println(j);
		}
	}
}
