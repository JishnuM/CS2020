package sg.edu.nus.cs2020;

/**
 * Class ShiftRegister
 * @author Jishnu
 * Description Implements Interface ILFShiftRegister
 * A Linear Feedback Shift Register
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
	public void setSeed(int[] seed) {
		//Sets the register to the specified seed
		register = seed;
		
		//Stores length in register_size
		register_size = register.length;
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