package sg.edu.nus.cs2020;

/**
 * Class ShiftRegister
 * @author Jishnu
 * Description Implements Interface ILFShiftRegister
 * A Linear Feedback Shift Register
 * 
 * Methods:
 * Shift, Generate, SetSeed
 * stringShiftRegister, StringToBin, get_state
 * 
 */
public class ShiftRegister implements ILFShiftRegister {

	/**
	 * register is where the shift register is stored as an integer array
	 */
	public int[] register;
	
	
	/**
	 * register_size stores the size of the register.
	 * It is initialised to -1 by default.
	 */
	public int register_size = -1;
	
	
	/**
	 * tap stores the tap value
	 * It is initialised to -1.
	 */
	public int tap = -1;
	
	
	/**
	 * 
	 * Constructor function for a shift register
	 * 
	 * @param input_size is an integer specifying length of register
	 * @param input_tap is an integer specifying tap location
	 */
	public ShiftRegister(int input_size, int input_tap){
		register = new int[input_size];
		tap = input_tap;
	}
	
	/* (non-Javadoc)
	 * @see sg.edu.nus.cs2020.ILFShiftRegister#setSeed(int[])
	 */
	public void setSeed(int[] seed) 
	{
		//Sets the register to the specified seed
		register = seed;
		
		//Stores length in register_size
		register_size = register.length;
	}
	
	/**
	 * @param string_seed - The string input that is the seed
	 * @param tap - The location of the tap
	 */
	public void stringShiftRegister(String string_seed, int tap)
	{
		int[] bin_array = StringToBin(string_seed);
		
		//Sets size, tap, and seed
		this.register_size = bin_array.length;
		this.tap = tap;
		this.setSeed(bin_array);
	}
	
	/**
	 * @param input - String
	 * @return A binary integer array
	 */
	public int[] StringToBin (String input)
	{
		int size,temp,output_count = 0;
		
		//Uses inbuilt functionality to convert
		//string into character array
		char[] char_array = input.toCharArray();
		
		size = char_array.length;
		
		//Since each character is 8 bits
		//the binary array would be 8 times
		//the length
		int[] output = new int[8*size];
		
		//Loops over each character
		for (int i=0;i<size;i++)
		{
			//Uses inbuilt functionality to conver
			//character to int
			temp = Character.getNumericValue(char_array[i]);
			
			//This loop converts the integer to binary
			//using repeated divisions by powers of 2
			for (int j=7;j>=0;j--){
				output[output_count] = (int) (temp/Math.pow(2, j));
				temp %= Math.pow(2, j);
				output_count++;
			}
		}
		return output;
	}
	
	/**
	 * Returns the state of a binary array
	 * as represented by the integer it represents
	 * @return integer
	 */
	public int get_state()
	{
		int output = 0;
		
		//Calculate integer represented by binary
		for (int i=this.register_size-1;i>=0;i--){
			output += (this.register[i]*Math.pow(2,i));
		}
		return output;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.cs2020.ILFShiftRegister#shift()
	 */
	public int shift() {
		
		//Bit to be added is bitwise XOR of last bit and tap
		int next_bit = register[register_size-1]^register[tap];
		
		//Initialises a temp integer array whose first bit is
		//the aforementioned bit to be added
		int[] temp = new int[register_size];
		temp[0] = next_bit;
		
		//Use arraycopy command as referenced on Oracle website to
		//set copy the first (size-1) bits from the register and
		//make them the last (size-1) bits in the temp.
		//If not, a loop can be used as well
		System.arraycopy(register, 0, temp, 1, register_size - 1);
		
		//Set the register to temp
		register = temp;
		
		return next_bit;
	}

	/* (non-Javadoc)
	 * @see sg.edu.nus.cs2020.ILFShiftRegister#generate(int)
	 */
	public int generate(int k) {
		int i,sum=0;
		
		//This for loop utilises the fact that in binary numbers
		//value of a place changes by factor of 2.
		//Therefore, the calling of shift as well as the 
		//value calculation is simultaneous.
		for (i=k;i>0;i--){
			sum += (this.shift())*Math.pow(2,i-1);
		}
		return sum;
	}

}