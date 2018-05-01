import java.util.ArrayList;


public class PathNode {

	private String name;
	private ArrayList<PathNode> links = new ArrayList<PathNode>();
	
	
	public PathNode(String name) {
		this.name = name;
	}
	
	public void LinkTo(PathNode output) {
		links.add(output);
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<PathNode> getLinks() {
		return this.links;
	}

	
}
