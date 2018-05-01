import java.util.ArrayList;
import java.util.Random;

public class PathSelector {

	public PathNode NextNode (PathNode currentNode) {
		PathNode next = null;
		ArrayList<PathNode> links = currentNode.getLinks();
		
		int numLinks = links.size();
		
		if (numLinks > 0) {
			int pick = new Random().nextInt(numLinks);
			next = links.get(pick);
		}
		
		return next;
	}
	
	
}
