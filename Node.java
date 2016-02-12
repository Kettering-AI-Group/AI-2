public class Node
{
   private Node parent = null;
   private State state = null;
   private int pathCost = -1;
   private int heuristicCost = -1;
   private int totalCost = -1;
   private String rotateValue = "";

   //allows direct specification of path cose
   public Node(Node pNode, State myState, int gCost, int hCost, String rotate){
      this.setup(pNode, myState, gCost, hCost, rotate);
   }

   //bases path cost of 1 plus parent cost
   public Node(Node pNode, State myState, int hCost, String rotate){
      this.setup(pNode, myState, (pNode.getPathCost() + 1), hCost, rotate);
   }
   
   private void setup(Node pNode, State myState, int gCost, int hCost, String rotate){
      parent = pNode;
      state = myState;
      pathCost = gCost;
      heuristicCost = hCost;
      totalCost = gCost + hCost;   
      rotateValue = rotate;
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
   public void printPath(){
      String[] reversed = new String[pathCost+1];
      Node curNode = this;
      int count = 0;
      while(curNode != null){
         reversed[pathCost - count] = curNode.printNode();
         curNode = curNode.getParent();
         count++;
      }
      while(count > 0){
         count--;
         System.out.println(reversed[count]);
      }
      return;
   }
   public String printNode(){
         return "State: " + state.getId() + 
                              " heu: " + heuristicCost + 
                              ", path: " + pathCost + 
                              ", total: " + totalCost +
                              "\nMove Made: " + rotateValue;
   }
}
