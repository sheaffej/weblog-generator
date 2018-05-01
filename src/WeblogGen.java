import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class WeblogGen {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		int numIPs = 500;
		int numLoops = 10000;
		

		
		ArrayList<PathNode> inputs = new ArrayList<PathNode>();
		ArrayList<PathNode> outputs = new ArrayList<PathNode>();
		ArrayList<PathNode> middle1 = new ArrayList<PathNode>();
		ArrayList<PathNode> middle2 = new ArrayList<PathNode>();

		IPState[] ips = new IPState[numIPs];
		
		Random rand = new Random();
		for (int i = 0; i < ips.length; i++) {
			int o1 = rand.nextInt(254)+1;
			int o2 = rand.nextInt(254)+1;
			int o3 = rand.nextInt(254)+1;
			int o4 = rand.nextInt(254)+1;
			ips[i] = new IPState (o1 + "." + o2 + "." + o3 + "." + o4);
//			System.out.println(ips[i].GetIP());
		}
		
		
		PathNode node = null;
		
		// Output Nodes
//		node = new PathNode("W");
//		outputs.add(node);
		node = new PathNode("X");
		outputs.add(node);
		node = new PathNode("Y");
		outputs.add(node);
		node = new PathNode("Z");
		outputs.add(node);
		
		// Input Nodes
		node = new PathNode("A");
		inputs.add(node);
		node = new PathNode("B");
		inputs.add(node);
		node = new PathNode("C");
		inputs.add(node);
		node = new PathNode("D");
		inputs.add(node);
		
		// Middle1 Nodes
		node = new PathNode("1");
		middle1.add(node);
		node = new PathNode("2");
		middle1.add(node);
		node = new PathNode("3");
		middle1.add(node);
		
		// Middle2 Nodes
		node = new PathNode("10");
		middle2.add(node);
		node = new PathNode("20");
		middle2.add(node);
		node = new PathNode("30");
		middle2.add(node);
		node = new PathNode("40");
		middle2.add(node);

		// Link inputs to middle1
		for (PathNode n : inputs) {
			for (PathNode m : middle1) {
				n.LinkTo(m);
			}
		}
		
		// Link middle1 to middle2
		for (PathNode n : middle1) {
			for (PathNode m : middle2) {
				n.LinkTo(m);
			}
		}

		// Link middle2 to outputs
		for (PathNode n : middle2) {
			for (PathNode m : outputs) {
				n.LinkTo(m);
			}
		}		

		
		// Let's do some work
		PathSelector selector = new PathSelector();
		
		Writer output = new BufferedWriter(new FileWriter("/Users/jsheaffe/working/t"));
	    try {	      
			for (int i = 0; i < (ips.length*numLoops); i++) {
				IPState ip = ips[rand.nextInt(ips.length)];
				
				String line = getDateTime() + "|" + ip.GetIP() + "|";
			
				if (ip.GetState() == null) {
					// Set input node as state
					int idx = rand.nextInt(inputs.size());
					PathNode cur = inputs.get(idx);
					ip.SetState(cur);
					line += cur.getName();
				}
				else {
					PathNode cur = (PathNode)ip.GetState();
					PathNode next = selector.NextNode(cur);
					if (next == null) {
						ip.SetState(null);
//						System.out.println("End");
					}
					else {
						ip.SetState(next);
//						System.out.println(cur.getName() + " --> " + next.getName());
						line += next.getName();
					}
					
				}
				
//				for (PathNode n : inputs) {
//					PathNode cur = n;
//					while (cur != null) {
//						System.out.print(" --> " + cur.getName());
//						cur = selector.NextNode(cur);
//					}
//					System.out.println();
	//
//				}
				
				if (ip.GetState() != null)
//					System.out.println(line);
					output.write(line + "\n");
				
			}
	      
	    }
	    finally {
	      output.close();
	    }		
		System.out.println ("Done...");
		
	}
	
    static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date date = new Date();
        return dateFormat.format(date);
    }

}



