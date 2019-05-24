package lab12;

import java.util.LinkedList;

public class Node {
	
	boolean visited;
	char content;
	Node previous;
	int row;
	int col;
	LinkedList <Node> neighbors= new LinkedList<Node>();
			
	public Node(boolean visited, char content, Node previous, int row, int col) {
		super();
		this.visited = visited;
		this.content = content;
		this.previous = previous;
		this.row = row;
		this.col = col;
	}	
}