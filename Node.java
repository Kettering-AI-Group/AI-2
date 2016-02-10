public class Node
{
   private Node parent = null;
   private State state = null;
   private int pathCost = -1;
   private int heuristicCost = -1;
   private int totalCost = -1;

   //allows direct specification of path cose
   public Node(Node pNode, State myState, int gCost, int hCost){
      this.setup(pNode, myState, gCost, hCost);
   }

   //bases path cost of 1 plus parent cost
   public Node(Node pNode, State myState, int hCost){
      this.setup(pNode, myState, (pNode.getPathCost() + 1), hCost);
   }
   
   private void setup(Node pNode, State myState, int gCost, int hCost){
      parent = pNode;
      state = myState;
      pathCost = gCost;
      heuristicCost = hCost;
      totalCost = gCost + hCost;   
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
   
   public boolean isFinal(){
      int past = state.board.get(0);
      int current;
      int i;
      
      for(i = 1; i < state.board.size(); i++){
         current = state.board.get(i);
         
         if(past > current){
            return false;
         }
         
         past = current;
      }
      
      return true;
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
