package sg.edu.nus.cs2020;

//  Import file handling classes
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * class HerbertLog
 * The HerbertLog class records the jobs worked by Herbert, and
 * the wages paid to Herbert, over the last period of employment.
 * The constructor opens the specified log-file, and the get(.) method
 * returns records from the file.
 * 
 */
public class HerbertLog {
		
	/**
	 *  Public static final constants
	 */
	
	// Separator character used in the database file
	public static final String SEP = ":";
	// Length of each record in the database
	public static final int rLength = 18;
	// Padding character for database file
	public static final char PADDING = '.';

	/**
	 * Private state for the HerbertLog
	 */
	// Filename where the database can be found
	private String m_name = null;
	// Variable that points to the database, once opened
	private File m_file = null;
	// Variable for reading from the database file
	private RandomAccessFile m_inRAM = null;
	// Size of the database: number of available records
	private long m_numMinutes = 0;
	
	/**
	 * Debugging information
	 */
	// Number of "get" operations performed on the database
	// Note this is primarily for debugging.
	protected long m_numGets = 0;
	
	/**
	 * Constructor 
	 * @param filename File where the database can be found.
	 * The specified file must exist, and must contain records
	 * in the proper format. 
	 **/
	HerbertLog(String fileName){
		// Save the filename
		m_name = fileName;
		// Next, we open the file
		try {
			// Open the file
			m_file = new File(m_name);
			m_inRAM = new RandomAccessFile(m_file, "r");
			
			// Calculate the number of records in the database by
			// dividing the number of characters by the length of each record
			long numChars = m_inRAM.length();
			m_numMinutes = numChars/rLength;
		} catch (IOException e) {
			System.out.println("Error opening file: " + e);
		}
	}
	
	/**
	 * size
	 * @return the number of records in the database
	 */
	public long numMinutes(){
		return m_numMinutes;
	}

        /**
	 * numGets : primarily for debugging
         * @return number of times get has been called
         */
         public long numGets(){
	         return m_numGets;
         }

	/**
	 * get
	 * @param i specified the record number to retrieve, starting from 0
	 * @return the specified record, if it exists, or null otherwise
	 */
	public Record get(long i){
		
		// Increment the number of "get" operations
		m_numGets++;
		
		// Check for errors: if i is too large or too small, fail
		if (i > numMinutes()) return null;
		if (i < 0) return null;
		
		// Retrieve the proper record
		try {
			// First, calculate the offset into the file, and seek to that location
			long numChars = i*rLength;			
			m_inRAM.seek(numChars);
			
			// Next, read in rLength bytes
			// Recall that rLength is the length of one record
			byte[] entry = new byte[rLength];
			m_inRAM.read(entry);
			
			// Now, convert the string to a record.
			// Convert it to a string...
			String line = new String(entry);
			// .. parse the string using the record separator
			String[] tokens = line.split(SEP);
			// Every record should have 2 or 3 components
			assert(tokens.length==2 || tokens.length==3);
			// The first token is the name
			String name = tokens[0];
			// The second token is the height
			int height = Integer.parseInt(tokens[1]);
			return new Record(name, height);
			
		} catch (IOException e) {
			System.out.println("Error getting data from file: " + e);
		}
		// If the record wasn't found, for any reason, return null
		return null;
	}
	
	//Slow Linear Search
	/*
	public int calculateSalary(){
		long total_mins = this.numMinutes();
		int sum = 0;
		Record current = null, last = null;
		for (long i = 0; i < total_mins; i++){
			last = current;
			current = this.get(i);
			if (i == 0){
				continue;
			}
			else if (!(current.getName()).equals(last.getName())){
				//System.out.println(last.getWages());
				sum += last.getWages();
			}
		}
		sum += current.getWages();
		return sum;
	}*/
	
	/**
	 * Calculate using Divide and Conquer Algorithm
	 * @return Salary earned by Herbert
	 */
	public int calculateSalary(){
		long end = this.numMinutes()-1;
		int sum = 0;
		
		//Loop until the whole log is searched
		while (end != 0){
			//end is to be updated in find_next
			Record last_rec = this.get(end);
			sum += last_rec.getWages();
			end = find_next(end,last_rec.getName());
		}
		return sum;
	}
	
	
	/**
	 * Helper function for calculateSalary
	 * @param position latest position (till where salary has been calculated)
	 * @param last_name latest name of employer
	 * @return
	 */
	private long find_next(long position,String last_name){
		long to_check, start = 0,end = position;
		Record current;
		while (end - start > 1){
			to_check = (start + end)/2;
			current = this.get(to_check);
			//Use divide and conquer algorithm
			if (current.getName().equals(last_name)){
				end = to_check;
			} else {
				start = to_check;
			}
		}
		return start;
	}
	
	/**
	 * Similar method as before but now ensures same name is not called
	 * more than i times
	 * @param position
	 * @param last_name
	 * @param max_num
	 * @return
	 */
	private long restricted_find_next(long position, String last_name, int max_num){
		long to_check, start = position - max_num,end = position;
		Record current;
		while (end - start > 1){
			to_check = (start + end)/2;
			current = this.get(to_check);
			//Use divide and conquer algorithm
			if (current.getName().equals(last_name)){
				end = to_check;
			} else {
				start = to_check;
			}
		}
		return start;
	}
	
	/**
	 * Sorry! Untested Code due to lack of time!
	 * @param goalIncome
	 * @return
	 */
	int calculateMinimumWork (int goalIncome){
		
		long end = this.numMinutes()-1;
		int sum = 0;
		
		for(int i=0;i<(int)end;i++)
		{
		//Loop until the whole log is searched
			while (end != 0){
				//end is to be updated in find_next
				Record last_rec = this.get(end);
				sum += last_rec.getWages();
				end = restricted_find_next(end,last_rec.getName(),i);
			}
			if (sum >= goalIncome)
			{
				return i;
			}
		}
		return (int)end;
	}
	
	public static void main(String[] args){
		HerbertLog longH = new HerbertLog("longNamesHerbert.txt");
		//Salary 2796254; 
		//Divide and Conquer calls to get - 500; 
		//Linear Search calls to get - 559528
		
		HerbertLog manyH = new HerbertLog("manyNamesHerbert.txt");
		//Salary - 47803;
		//Divide and Conquer calls to get - 10559;
		//Linear Search calls to get  - 8802
		
		HerbertLog shortH = new HerbertLog("shortNamesHerbert.txt");
		//Salary - 401;
		//Divide and Conquer calls to get - 108;
		//Linear Search calls to get  - 62
		
		HerbertLog veryshortH = new HerbertLog("veryshortNamesHerbert.txt");
		//Salary - 52;
		//Divide and Conquer calls to get - 15; 
		//Linear Search calls to get  - 7
		
		
		int ans1 = longH.calculateSalary();
		long call1 = longH.numGets();
		int ans2 = manyH.calculateSalary();
		long call2 = manyH.numGets();
		int ans3 = shortH.calculateSalary();
		long call3 = shortH.numGets();
		int ans4 = veryshortH.calculateSalary();
		long call4 = veryshortH.numGets();

		System.out.println(ans1);
		System.out.println(call1);
		System.out.println(ans2);
		System.out.println(call2);
		System.out.println(ans3);
		System.out.println(call3);
		System.out.println(ans4);
		System.out.println(call4);
	}
}
