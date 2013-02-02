package sg.edu.nus.cs2020;

//Implement Binary Search
/**
 * @author Jishnu
 * MyFastList is an Ordered List Implementation
 * Both add and search methods of the parent class are overriden.
 * Add implemented so that order is always maintained
 * Search implemented via binary search
 * Evaluation - Faster than FixedLengthList, slower than MoveToFront
 */
public class MyFastList extends FixedLengthList{

	public MyFastList(int length) {
		super(length);
	}
	
	public boolean add(int key){
		if (key < 0){
			System.out.println("Error: negative element added to list.");
			return false;
		}
		m_max++;
		if (m_max < m_length){
			for (int i = m_max - 1;i>=0;i--){
				//Sorting in Ascending Order
				if (m_list[i]<=key){
					m_list[i+1] = key;
					return true;
				} else {
					m_list[i+1] = m_list[i];
				}
			}
			m_list[0] = key;
			return true;
		}
		else{
			System.out.println("Error: list length exceeded.");
			return false;
		}
	}
	
	public boolean search(int key){
		int begin = 0;
		int end = m_max;
		int mid;
		while (begin < end){ 
			if (key < m_list[begin]||key > m_list[end]){
				System.out.println("Not in range");
				return false;
			}
			mid = (begin + end)/2;
			if (m_list[mid]==key){
				return true;
			}else if (end - begin == 1){
				if (m_list[begin]==key||m_list[end]==key){
					return true;
				} else {
					System.out.println("Huh");
					return false;
				}
			}else if (m_list[mid]>key){
				end = mid;
			}else if (m_list[mid]<key){
				begin = mid;
			}
		}
		System.out.println("Finished");
		return false;
	}
}
