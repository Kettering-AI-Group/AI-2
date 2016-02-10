/*Class Name:AStar
Author's Name:
Date: 2/6/2016
Description of the class: Runs the AStar on the sliding puzzle using a given Heuristic 
*/
import java.util.*;

public class AStar{
   Heuristic curHeur = null;
   Tree nodeTree;   
   ArrayList<Node> fringe = new ArrayList<Node>();
   ArrayList<Node> expanded = new ArrayList<Node>();
   private int size = 0;
   
   public AStar(int heuristicSelect){
      curHeur = new Heuristic(heuristicSelect);
   }

   public void run(State start){
      Node rootNode = new Node(null, start, 0, curHeur.calc(start));
      //nodeTree = new Tree(rootNode);
      fringe.add(rootNode);
      while(fringe.size() != 0)
      {
         genChildren(fringe.remove(0));
      }
      nodeTree.printTree();
   }
   
   private void genChildren(Node curNode){
      //check if final
      State curState = curNode.getState();
      if(curNode.isFinal())
      {
              curNode.printNode();
              System.exit(0);
      }
            //add cur to expanded
      expanded.add(curNode);
      //gen children              
      for(int i = 1; i <= curState.size(); i++){
         State newState = curState.clone();
         newState.rotate(i);
         //check if children are added to fringe
         if(!expanded.contains(newState.getId())){
            Node newNode = new Node(curNode, newState, curHeur.calc(newState));
            int marker;
            for(marker = 0; marker < size && fringe.get(marker).getTotalCost() > newNode.getTotalCost(); marker++)
            {
            }
            fringe.add(marker, newNode);
         }
      }
   }

   class Heuristic{
      int methodSelect = -1;
   
      public Heuristic(int selector){
         methodSelect = selector;
      }
      
      protected int calc(State state){
         int result = -1;
         
         switch(methodSelect){
            case 0:
               result = 0;
               break;
            case 1:
               result = simpleH(state);
               break;
            case 2:
               result = customH(state);
               break;
         }
         
         return result;
      }
   
      private int simpleH(State state){
         int res = 0;
         
         for(int i = 0; i < state.board.size(); i++){
            if(i+1 != (Integer) state.board.get(i)){
               res++;
            }
         }
         
         return res;
      }
   
      private int customH(State state){
         int res = -1;
      //MUCH later
         return res;
      }
   }
}
