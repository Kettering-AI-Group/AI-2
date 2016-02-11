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
   ArrayList<String> expanded = new ArrayList<String>();
   private int size = 0;
   private int counter = 0;
   boolean finished = false;
   
   public AStar(int heuristicSelect){
      curHeur = new Heuristic(heuristicSelect);
   }

   public void run(State start){
      counter = 0;
      finished = false;
      Node rootNode = new Node(null, start, 0, curHeur.calc(start));
      fringe.add(rootNode);
      
      while(!finished){
         genChildren(fringe.remove(0));
      } 
   }
   
   private void genChildren(Node curNode){
      counter++;
      //check if final
      State curState = curNode.getState();
      
      if(curNode.isFinal()){
         curNode.printNode();
         System.out.println("Number of expanded Nodes = " + counter);
         finished = true;
         return;
      }
      
      //add cur to expanded
      expanded.add(curNode.getState().getId());
      
      //gen children              
      for(int i = 1; i <= curState.size(); i++){
         State newState = curState.clone();
         newState.rotate(i);
         
         //check if children are added to fringe
         if(!expanded.contains(newState.getId())){
            Node newNode = new Node(curNode, newState, curHeur.calc(newState));
            addedUnExpandedNode(newNode);
         }
      }
   }
   
   private void addedUnExpandedNode(Node node){
      int totalCost = node.getTotalCost();
      int size = fringe.size();
      int i = 0;
      
      while((size > i) && (fringe.get(i).getTotalCost() < totalCost)){
         i++;
      }
      
      fringe.add(i, node);
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
            case 3:
               result = customHTwo(state);
               break;
            case 4:
               result = simpleH(state) + customH(state);
               break;
            case 5:
               result = customHTwo(state) + customH(state);
               break;
            case 6:
               result = simpleH(state) + customHTwo(state);
               break;
            case 7:
               result = simpleH(state) + customH(state) + customHTwo(state);
               break;
         }
         
         return result;
      }
   
      private int simpleH(State state){
         int res = 0;
         
         for(int i = 0; i < state.size(); i++){
            if(i+1 != (Integer) state.board.get(i)){
               res++;
            }
         }
         
         return res;
      }
   
      private int customH(State state){
         int current = 0;
         int size = state.size();
         int res = 0;
         int past = state.board.get(0);
         
         for(int i = 1; i < size; i++){
            current = state.board.get(i);
            res += current - past;
            past = current;
         }
         
         return Math.abs(res - size + 1);
      }
   
      private int customHTwo(State state){
         int size = state.size();
         int res = 0;
         
         for(int i = 0; i < size; i++){
            res += Math.abs(i + 1 - state.board.get(i));
         }
         
         return res;
      }
   }
}
