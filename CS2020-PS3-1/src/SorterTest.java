//Collaboration - Worked with David Ziegler

//Notes - for size 10, all results were true and false respectively (Why?)
//for size 100 and 1000, all results for sort were true but A and C were unstable
//Using alternative compare tools, D is also shown to be unstable
//Using alternative compare, this result (A,C,D unstable in smaller size) 
//can also be replicated in smaller sizes
//B and F have extremely slow running times for large inputs
//F is extremely fast for sorted arrays - does it check for sortedness?
//B is also fast for sorted arrays
//A,C have consistently low running times
//E is fast but not as fast as A/C
//D is slow but not as slow as B/F
//Stable algorithms - Bubble,Insertion
//B and F are Bubble and Insertion
//Unstable - Quicksort, Selection
//D is Selection Sort
//Unknown - Merge, Evil
//A and C are Quicksort and Merge
//E is Evil (?)

public class SorterTest {
	public static void main(String[] args){

		int test_size = 1000;
		
		//Set up sorters
		ISort sortA = new SorterA();
		ISort sortB = new SorterB();
		ISort sortC = new SorterC();
		ISort sortD = new SorterD();
		ISort sortE = new SorterE();
		ISort sortF = new SorterF();
		ISort mySort = new Jishquick();
		
		//test for sorted
		SorterTest myTest = new SorterTest();
		
		myTest.do_tests(sortA,test_size);
		myTest.do_tests(sortB,test_size);
		myTest.do_tests(sortC,test_size);
		myTest.do_tests(sortD,test_size);
		myTest.do_tests(sortE,test_size);
		myTest.do_tests(sortF,test_size);
		myTest.do_tests(mySort,test_size);
		/*
		boolean sort_resultA = myTest.checkSorted(sortA, test_size);
		boolean sort_resultB = myTest.checkSorted(sortB, test_size);
		boolean sort_resultC = myTest.checkSorted(sortC, test_size);
		boolean sort_resultD = myTest.checkSorted(sortD, test_size);
		boolean sort_resultE = myTest.checkSorted(sortE, test_size);
		boolean sort_resultF = myTest.checkSorted(sortF, test_size);
		
		boolean stable_resultA = myTest.isStable(sortA, test_size);
		boolean stable_resultB = myTest.isStable(sortB, test_size);
		boolean stable_resultC = myTest.isStable(sortC, test_size);
		boolean stable_resultD = myTest.isStable(sortD, test_size);
		boolean stable_resultE = myTest.isStable(sortE, test_size);
		boolean stable_resultF = myTest.isStable(sortF, test_size);
		
		float time_resultA = myTest.checkTime(sortA, test_size);
		float time_resultB = myTest.checkTime(sortB, test_size);
		float time_resultC = myTest.checkTime(sortC, test_size);
		float time_resultD = myTest.checkTime(sortD, test_size);
		float time_resultE = myTest.checkTime(sortE, test_size);
		float time_resultF = myTest.checkTime(sortF, test_size);
		
		
		
		System.out.println("Testing Sorter A");
		System.out.println("Result for checking sorted is " + sort_resultA);
		System.out.println("Result for checking stability is " + stable_resultA);
		System.out.println("Time taken for sorting array of size " + test_size + " is " + time_resultA + " seconds");
		
		System.out.println("Testing Sorter B");
		System.out.println("Result for checking sorted is " + sort_resultB);
		System.out.println("Result for checking stability is " + stable_resultB);
		System.out.println("Time taken for sorting array of size " + test_size + " is " + time_resultB + " seconds");
		
		System.out.println("Testing Sorter C");
		System.out.println("Result for checking sorted is " + sort_resultC);
		System.out.println("Result for checking stability is " + stable_resultC);
		System.out.println("Time taken for sorting array of size " + test_size + " is " + time_resultC + " seconds");
		
		System.out.println("Testing Sorter D");
		System.out.println("Result for checking sorted is " + sort_resultD);
		System.out.println("Result for checking stability is " + stable_resultD);
		System.out.println("Time taken for sorting array of size " + test_size + " is " + time_resultD + " seconds");
		
		System.out.println("Testing Sorter E");
		System.out.println("Result for checking sorted is " + sort_resultE);
		System.out.println("Result for checking stability is " + stable_resultE);
		System.out.println("Time taken for sorting array of size " + test_size + " is " + time_resultE + " seconds");
		
		System.out.println("Testing Sorter F");
		System.out.println("Result for checking sorted is " + sort_resultF);
		System.out.println("Result for checking stability is " + stable_resultF);
		System.out.println("Time taken for sorting array of size " + test_size + " is " + time_resultF + " seconds");
		*/
	}
	
	public void do_tests(ISort sorter, int size){
		System.out.println("Testing " + sorter);
		boolean sort_result = checkSorted(sorter, size);
		System.out.println("Result for checking sorted is " + sort_result);
		boolean stable_result = isStable(sorter,size);
		System.out.println("Result for checking stability is " + stable_result);
		boolean is_consistent = isConsistent(sorter,size);
		System.out.println("Result for consistency is " + is_consistent);
		float time_result = checkTime(sorter, size);
		System.out.println("Time taken for sorting array of size " + size + " is " + time_result + " seconds");
		float time_sorted = checkTime_sorted(sorter, size);
		System.out.println("Time taken for sorting sorted array of size " + size + " is " + time_sorted + " seconds");
		
		return;
		
	}
	
	public boolean checkSorted(ISort sorter, int size){
		//Random array Generation
		boolean ans2;
		Integer[] test_array = new Integer[size];
		Integer[] control_array = new Integer[size];
		//Integer[] output_array = new Integer[size];
		for (int i = 0; i < size; i ++){
			test_array[i] = (int)Math.round(Math.random()*size);
			control_array[i] = test_array[i];
			//System.out.print(test_array[i] + " ");
		}
		sorter.sort(test_array);
		/*
		//Simple Sorting Algorithm
		for (int i = 0; i < size; i++){
		
		//System.out.print("\n");
		//Sorted check
		for (int j = 0; j < size; j++){
			//System.out.print(test_array[j]+ " ");
			if (test_array[j]!=output_array[j]){
				ans1 = false;
			}
		}
		//System.out.print(test_array[size-1]);
		//System.out.print("\n");
		ans1 = true;
		*/
		Jishnutem[] test_array2 = new Jishnutem[size];
		for (int i = 0; i < size; i ++){
	
			int key = (int)Math.round(Math.random()*size);;
			int value = i;
			test_array2[i] = new Jishnutem(key,value);
		}
		
		sorter.sort(test_array2);
		
		//Sorted Check
		for (int j = 0; j < size - 1; j++){
			if (test_array2[j].compareTo(test_array2[j+1])==1){
				ans2 = false;
			}
		}
		ans2 = true;
		
		return (ans2);
	}
	
	public boolean isStable(ISort sorter, int size){
		Jishnutem[] test_array = new Jishnutem[size];
		for (int i = 0; i < size; i ++){
			
			//Ensures that there are values with the
			//same key and different value
			int key = i%2;
			int value = i;
			test_array[i] = new Jishnutem(key,value);
		}
		
		sorter.sort(test_array);
		
		//Stability check
		/*
		for (int j = 0; j < size/2; j++){
			if (test_array[2*j].compareVal(test_array[(2*j)+1])==1){
				return false;
			}
		}
		return true;
		*/
		
		//Alternatively and slowly
		
		for (int j =0; j < size; j++){
			for (int k=0; k < j; k++){
				if ((test_array[j].compareTo(test_array[k])==0)&&
						(test_array[j].compareVal(test_array[k])==-1)){
					return false;
				}
			}
		}
		return true;
	}
	
	public float checkTime(ISort sorter, int size){
		Integer[] test_array = new Integer[size];
		for (int i = 0; i < size; i ++){
			test_array[i] = (int)Math.round(Math.random()*size);
		}
		
		StopWatch clock = new StopWatch();
		
		clock.start();
		sorter.sort(test_array);
		clock.stop();
		
		return clock.getTime();
	}
	
	public float checkTime_sorted(ISort sorter, int size){
		Integer[] test_array = new Integer[size];
		for (int i = 0; i < size; i ++){
			test_array[i] = 2*i;
		}
		StopWatch clock = new StopWatch();
		
		clock.start();
		sorter.sort(test_array);
		clock.stop();
		
		return clock.getTime();
		
	}
	
	public boolean isConsistent(ISort sorter, int size){
		Jishnutem[] test_array = new Jishnutem[size];
		Jishnutem[] temp_array = new Jishnutem[size];
		for (int i = 0; i < size; i ++){
			
			//Ensures that there are values with the
			//same key and different value
			int key = i%2;
			int value = i;
			test_array[i] = new Jishnutem(key,value);
			temp_array[i] = new Jishnutem(key,value);
		}
		sorter.sort(test_array);
		sorter.sort(temp_array);
		for (int i = 0; i < size; i ++){
			if (test_array[i].compareVal(temp_array[i])!=0){
				return false;
			}
		}
		return true;
	}
	/*
	public ISort trapforEvil(int size){
		Integer[] test_array = new Integer[size];
		for (int i = 0; i < size; i ++){
			test_array[i] = (int)Math.round(Math.random()*size);
		}
		ISort sortA = new SorterA();
		ISort sortB = new SorterB();
		ISort sortC = new SorterC();
		ISort sortD = new SorterD();
		ISort sortE = new SorterE();
		ISort sortF = new SorterF();
		
	}*/
}


