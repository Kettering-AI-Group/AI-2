/*
Class Name:AStar
Author's Name:
Date: 2/6/2016
Description of the class: Runs the AStar on the sliding puzzle using a given Heuristic 
*/
import java.util.*;

public class AStar{
   Heuristic curHeur = null;  
   ArrayList<Node> fringe = new ArrayList<Node>();
   ArrayList<String> expanded = new ArrayList<String>();
   private int maxFringeSize = 0;
   private Map<String, Node> nodeCostMap = new HashMap<>();
   private int size = 0;
   private int counter = 0;
   private int debugLvl = 0;
   boolean finished = false;
   
   /**************************************************
   Method Name: AStar
   Input to the method: int, which heurisic to use
   Output(Return value): AStar, the new AStar with given paramers
   Brief description of the task: This will handel the selection of which heuristic function to use
   Author:
   **************************************************/
   public AStar(int heuristicSelect, int dbLvl){
      curHeur = new Heuristic(heuristicSelect);
      debugLvl = dbLvl;
   }
   
   public int getMaxFringe(){
      return maxFringeSize;
   }

   /**************************************************
   Method Name: run
   Input to the method: State, the root state to find the end from
   Output(Return value): Void, outputs to the console
   Brief description of the task: This runs the selected heurstic on the given state
   Author:
   **************************************************/
   public void run(State start){
      counter = 0;
      finished = false;
      Node rootNode = new Node(null, start, 0, curHeur.calc(start), "None");
      fringe.add(rootNode);
      
      while(!finished){
         genChildren(fringe.remove(0));
      } 
   }

   /**************************************************
   Method Name: genChildren
   Input to the method: Node, the node to generate all legal children from
   Output(Return value): Void, finds what to add to the fringe array list
   Brief description of the task: This generates children from the node, all possible legal
         rotations of the given node stae
   Author:
   **************************************************/
   private void genChildren(Node curNode){
      counter++;
      //check if final
      State curState = curNode.getState();
      if(debugLvl > 0){
         System.out.println("Expanded Node " + counter + " Heuristic Value " + curNode.getHeuCost());
      }
      if(curNode.isFinal()){
         System.out.println("Number of expanded Nodes = " + counter);
         System.out.println("Maximum Fringe Size = " + maxFringeSize);
         System.out.println("Final Path Cost = " + curNode.getTotalCost());
         curNode.printPath();
      
         finished = true;
         return;
      }
      
      //add cur to expanded
      expanded.add(curNode.getState().getId());
      
      //gen children              
      for(int i = 1; i <= curState.size(); i++){
         State newState = curState.clone();
         newState.rotate(i);
         
         //check if children are in expanded
         if(!expanded.contains(newState.getId())){
            Node newNode = new Node(curNode, newState, curHeur.calc(newState), Integer.toString(i));
            updateFringe(newNode);
            if(debugLvl == 2){
               for(int j = 0; j < fringe.size(); j++){
                  System.out.println("Fringe Node " + j + " State: " + fringe.get(j).getState().id + " H-Value " + fringe.get(j).getHeuCost());
               }
            }
            if(fringe.size() > maxFringeSize)
            {
               maxFringeSize = fringe.size();
            }
         }
      }
   }

   /**************************************************
   Method Name: updateFringe
   Input to the method: Node, the node to add to the fringe
   Output(Return value): Void, adds to the fringe array list
   Brief description of the task: This finds if a node is legal to add to the fringe list
   Author:
   **************************************************/
   private void updateFringe(Node node){
      int totalCost = node.getTotalCost();
      String curId = node.getState().id;
      int size = fringe.size();
      int i = 0;
      Node curNode;
      
      //Why hashmap? speed over memory
      if((curNode = nodeCostMap.get(curId)) != null){
         if(curNode.getTotalCost() < totalCost){
            fringe.remove(node);
         }else {
            return;
         }
      }
      
      nodeCostMap.put(curId, node);
      
      while((size > i) && (fringe.get(i).getTotalCost() < totalCost)){
         i++;
      }
      
      fringe.add(i, node);
   }

   class Heuristic{
      int methodSelect = -1;

      /**************************************************
      Method Name: Heuristic
      Input to the method: int, selector that detirmins the heuristic
      Output(Return value): Heuristic, the selected heuristic
      Brief description of the task: Selects which heurstic should be used from many combinations
      Author:
      **************************************************/
      public Heuristic(int selector){
         methodSelect = selector;
      }

      /**************************************************
      Method Name: calc
      Input to the method: State, the state to generate a heurstic value
      Output(Return value): int, the value generated by the heurstic
      Brief description of the task: Used to detirmin the best possible next move from a predetirmined value
      Author:
      **************************************************/
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

      /**************************************************
      Method Name: simpleH
      Input to the method: State, the state to generate a heurstic value
      Output(Return value): int, the value generated by the heurstic
      Brief description of the task: detirims heurstic by how many misplaced pecies exist
      Author:
      **************************************************/
      private int simpleH(State state){
         int res = 0;
         
         for(int i = 0; i < state.size(); i++){
            if(i+1 != (Integer) state.board.get(i)){
               res++;
            }
         }
         
         return res;
      }

      /**************************************************
      Method Name: simpleH
      Input to the method: State, the state to generate a heurstic value
      Output(Return value): int, the value generated by the heurstic
      Brief description of the task: detirims heurstic by the diffrence from a unit and the unit to it's right
      Author:
      **************************************************/
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

      /**************************************************
      Method Name: customHTwo
      Input to the method: State, the state to generate a heurstic value
      Output(Return value): int, the value generated by the heurstic
      Brief description of the task: detirims heurstic by the diffrence from a unit and where the unit should be
      Author:
      **************************************************/
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
