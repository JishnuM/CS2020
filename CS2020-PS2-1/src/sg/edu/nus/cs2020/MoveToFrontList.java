package sg.edu.nus.cs2020;

/**
 * @author Jishnu
 * Implements List Management heuristic such that every search
 * moves the element searched for to the front.
 * Evaluation - Significantly faster than FixedLengthList as well as MyFastList
 * in general cases (except lists in decreasing order where FixedLengthList
 * performs well)
 * Conclusion - MoveToFront is an effective tool
 * in a situation like the one simulated by the simulator - 
 * where some values are likelier to be accessed than others
 */
public class MoveToFrontList extends FixedLengthList {

	public MoveToFrontList(int length) {
		super(length);
	}
	
	public boolean search(int key){
		for (int i = 0; i <= m_max; i++){
			if (key == m_list[i]){
				for (int j = i;j>0;j--){
					m_list[j] = m_list[j-1];
				}
				m_list[0] = key;
				return true;
			}
		}
		return false;
	}
	
	
	

}
