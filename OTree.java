package cpsc433;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class OTree {
    private PriorityQueue<OTreeNode> leaves;
    private ArrayList<OTreeNode> completed;
    
    private OTreeNode bestSol = null;
    
    private Environment env;
    
    public OTree(Environment env) {
        this.env = env;
        
        leaves = new PriorityQueue<OTreeNode>();
        completed = new ArrayList<OTreeNode>();
        
        //add the root node onto the tree 
        leaves.add(new OTreeNode(env));
    }
    
    public void Transition() {
        OTreeNode selectedLeaf = SelectLeaf();
        
        OTreeNode children[] = selectedLeaf.Branch();
        System.out.println(children.length);
        for (OTreeNode child : children) {
            if (child.isComplete()) {
                completed.add(child);

                if (bestSol == null || child.getUtility() < bestSol.getUtility()) {
                    bestSol = child;
                }
            } else {
                leaves.add(child);
            }
        }
    }
    
    public OTreeNode SelectLeaf() {
        return leaves.poll();
    }
    
    public OTreeNode getBestSol() {
        return bestSol;
    }
}
