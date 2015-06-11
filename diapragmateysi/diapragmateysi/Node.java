package diapragmateysi;

public class Node {
	
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
