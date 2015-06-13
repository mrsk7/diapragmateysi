/* The idea is simple. Start from the given state and
	 * create children-states until you find the final_state.
	 * Each state has only 4 possible children-state: one for the
	 * rotation of each quadrant. Each state is represented as a String
	 * and when rotating, we actually swap specific indices according
	 * to the following diagram:
	 * 		0		1
	 * 	2		3		4
	 * 		5		6
	 * 	7		8		9
	 * 		10		11
	 * 
	 * So when rotating first quadrant, we set 0 to 3, 3 to 5, 5 to 2 and 2 to 0
	 * This mapping is given by state_indices array.
	 */

import java.util.HashSet;
import java.util.LinkedList;

public class Diapragmateysi {
	
	private class Node {
		
		public StringBuilder content = null;;
		public StringBuilder path = null;
		
		public Node(StringBuilder sb,StringBuilder parent) {
			content = sb;
			this.path = new StringBuilder(parent);
		}
		
		public void updatePath(int i) {
			path.append(i+1);
		}
	}
	
	public String final_state = "bgbGgGGrGyry";
	public int[][] state_indices = { {0,3,5,2} , {1,4,6,3}, {5,8,10,7}, {6,9,11,8} };
	public String input = null;
	LinkedList<Node> q = null;
	HashSet<Integer> closed_set = null;
	
	public Diapragmateysi(String input) {
		this.input = input;
		q = new LinkedList<Node>();
		closed_set = new HashSet<Integer>();
	}

	public String run() {
		StringBuilder initial = new StringBuilder(input);
		Node start = new Node(initial,new StringBuilder());
		q.add(start);
		closed_set.add(coding(start.content));
		Node head;
		while(!q.isEmpty()) {
			head = q.remove();
			if (head.content.toString().equals(final_state)) 
				return head.path.toString();
			createNodes(head);
		}
		return null;
	}

	/* Takes a node and add to the queue the nodes
	 * that can be created from this node (unless they are contained
	 * in the closed set so we have already visited them). A node contains two
	 * fields; current state (as a StringBuilder) and path, the way 
	 * that this state was reached.
	 * There are 4 possible moves: rotate right the first set, the 
	 * second, the third and the fourth set */
	
	public void createNodes(Node head) {
		for (int i=0;i<4;i++) {
			StringBuilder sb = new StringBuilder(head.content);
			rotate(sb,i);
			if (closed_set.contains(coding(sb))) continue;
			Node node = new Node(sb,head.path);
			node.updatePath(i);
			q.add(node);
			closed_set.add(coding(node.content));
		}		
	}
	

	public Integer coding(StringBuilder content) {
		int digit = 0;
		int ret=0;
		//red=0,blue=1,green=2,yellow=3,Grey==4
		for (int i=0;i<content.length();i++) {
			if (content.charAt(i) == 'r') {
				digit=0;
			}
			else if (content.charAt(i) == 'b') {
				digit=1;
			}
			else if (content.charAt(i) == 'g') {
				digit=2;
			}
			else if (content.charAt(i) == 'y') {
				digit=3;
			}
			else if (content.charAt(i) == 'G') {
				digit=4;
			}
			ret+= digit * (int) Math.pow(5, (double) (11-i)) ;
		}
		return (new Integer(ret));
	}
	
	
	/* Takes a StringBuilder and changes specific indices
	 * according to the given scheme */
	public StringBuilder rotate(StringBuilder sb,int index) {
		int[] indices = state_indices[index];
		char tmp;
		int i = indices.length-1;
		tmp = sb.charAt(indices[i]);
		sb.setCharAt(indices[i--], sb.charAt(indices[i]));
		sb.setCharAt(indices[i--], sb.charAt(indices[i]));
		sb.setCharAt(indices[i--], sb.charAt(indices[i]));
		sb.setCharAt(indices[i--], tmp);
		return sb;		
	}

	
	public static void main(String[] args) {
		String input = args[0];
		Diapragmateysi inst = new Diapragmateysi(input);
		String answer = inst.run();
		System.out.println(answer);
	}


}
