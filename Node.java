public class Node
{
   Node parent = null;
   State state = null;
   int pathCost = -1;
   int heuristicCost = -1;
   int totalCost = -1;

   public Node(Node pNode, State myState, int gCost, int hCost)
   {
      parent = pNode;
      state = myState;
      pathCost = gCost;
      heuristicCost = hCost;
      totalCost = gCost + hCost;   
   }
}