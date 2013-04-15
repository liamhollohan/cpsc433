package cpsc433;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * This class creates the tree structure of the search using a PriorityQueue
 * (Heap) that is sorted by utility. This class takes care of all the branching
 * and updating the best solution.
 * @author Liam Hollohan with help from Andrew Kuipers
 */
public class OTree {
    //Heap of leaves = Or Tree
	private PriorityQueue<OTreeNode> leaves;
    //List of leaf nodes (solutions to the problem)
	private ArrayList<OTreeNode> completed;
    
    private OTreeNode bestSol = null; //Node containing the best solution
    
    private Environment env; //instance of the environment class
    
    public OTree(Environment env) {
        this.env = env;
        
        leaves = new PriorityQueue<OTreeNode>();
        completed = new ArrayList<OTreeNode>();
        //add the root node onto the tree 
        leaves.add(new OTreeNode(env));
    }
    
    public boolean Transition() {
        if (leaves.isEmpty()) {
        	System.out.println(" -> Search Space Exhausted.");
        	return true;
        } else {
	    	OTreeNode selectedLeaf = SelectLeaf();
	        //System.out.println(" => SelectedLeaf = " + selectedLeaf.toString()+"\n");
	        OTreeNode children[] = selectedLeaf.Branch();
	        for (OTreeNode child : children) {
	            if (Solution.checkBigMoney(child.assignment)) {
		        	if (child.isComplete()) {
		                completed.add(child);
		
		                if (bestSol == null || child.getUtility() > bestSol.getUtility()) {
		                    bestSol = child;
		                }
		            } else {
		                leaves.add(child);
		            }
	        	}
	        }
	        return false;
        }
    }
    
    public OTreeNode SelectLeaf() {
    	return leaves.poll();
    }
    
    public OTreeNode getBestSol() {
        return bestSol;
    }
}
