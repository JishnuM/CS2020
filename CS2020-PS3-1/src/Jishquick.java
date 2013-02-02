

/**
 * @author Jishnu
 * Implementation of Quicksort
 * using Dutch Flag Partitioning
 * as explained in Algorithms - Sedgewick, Wayne
 */
public class Jishquick implements ISort{

	@Override
	public <T extends Comparable<T>> void sort(T[] array) {
		internal_sort(array,0,array.length-1);
	}
	
	private <T extends Comparable<T>> void internal_sort(T[] array, int lo, int hi){
		//Base Case
		if (hi<=lo){return;}
		
		// lo and hi - static integers representing lo and hi points
		//lt and gt - dynamic integers representing current low and high vals
		T temp;
		int lt = lo, i = lo+1, gt = hi;
		T v = array[lo];
		while (i <= gt)
		{
			int cmp = array[i].compareTo(v);
			if ( cmp < 0){
				temp = array[lt];
				array[lt] = array[i];
				array[i] = temp;
				lt++;
				i++;
			} else if (cmp > 0){
				temp = array[gt];
				array[gt] = array[i];
				array[i] = temp;
				gt--;
			} else {i ++; }
		}
		internal_sort(array, lo, lt - 1);
		internal_sort(array, gt+1, hi);
	}

}
