
public class Jishnutem implements Comparable<Jishnutem>{

	private int key;
	
	private int value;
	
	public Jishnutem(int input_key, int input_value){
		key = input_key;
		value = input_value;
	}
	
	
	@Override
	public int compareTo(Jishnutem other) {
		if (this.key < other.key){return -1;}
		else if (this.key > other.key){return 1;}
		else if (this.key == other.key){return 0;}
		else{return 0;}
	}
	
	public int compareVal(Jishnutem other){
		if (this.value < other.value){return -1;}
		else if (this.value > other.value){return 1;}
		else if (this.value == other.value){return 0;}
		else{return 0;}
	}
	
}
