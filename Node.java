public class Node
{
   private Node parent = null;
   private State state = null;
   private int pathCost = -1;
   private int heuristicCost = -1;
   private int totalCost = -1;

   //allows direct specification of path cose
   public Node(Node pNode, State myState, int gCost, int hCost){
      parent = pNode;
      state = myState;
      pathCost = gCost;
      heuristicCost = hCost;
      totalCost = gCost + hCost;   
   }

   //bases path cost of 1 plus parent cost
   public Node(Node pNode, State myState, int hCost){
      parent = pNode;
      state = myState;
      pathCost = pNode.getPathCost() + 1;
      heuristicCost = hCost;
      totalCost = pathCost + hCost;   
   }
   
   public State getState(){
      return state;
   }
   
   public Node getParent(){
      return parent;
   }
   
   public int getTotalCost(){
      return totalCost;
   }
   
   public int getHeuCost(){
      return heuristicCost;
   }
   
   public int getPathCost(){
      return pathCost;
   }
   
   public void printNode(){
      if(parent != null){
         System.out.println("id: " + state.getId() + 
                              ", parent: " + parent.getState().getId() + 
                              ", heu: " + heuristicCost + 
                              ", path: " + pathCost + 
                              ", total: " + totalCost);
      }else{
         System.out.println("id: " + state.getId() + 
                              ", parent: none, is root" + 
                              ", heu: " + heuristicCost + 
                              ", path: " + pathCost + 
                              ", total: " + totalCost);
      }
   }
}