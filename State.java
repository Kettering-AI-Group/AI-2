/*
Class Name:Node
Author's Name:
Date: 2/11/2016
Description of the class: This holds the order and gives ability to manipulate
   The game board by rotation 
*/
import java.util.*;
import java.lang.*;

public class State{
   String id = "";
   private int size;
   LinkedList<Integer> board = new LinkedList<Integer>();

   /**************************************************
   Method Name: State
   Input to the method: int[], the order and number of game pecices
   Output(Return value): State, the configured State given inputs
   Brief description of the task: Generates the "board" and init state of the game
   Author:
   **************************************************/
   public State(int[] values){
      for(int i: values){ 
         board.add(i); 
      } 
      
      normalize();
      size = values.length;
      id = genHashId();
   }

   /**************************************************
   Method Name: normalize
   Input to the method: Void
   Output(Return value): Void, reorders the virtual game board
   Brief description of the task: Shifts all game parts until 1 is the leading unit
   Author:
   **************************************************/
   private void normalize(){
      while(board.peek() != 1){
         int temp = board.pop();
         board.addLast(temp);
      }
   }

   /**************************************************
   Method Name: genHashId
   Input to the method: Void
   Output(Return value): String, the id based off of the order of the peices
   Brief description of the task: This gives a unique id based on the order of the peices
   Author:
   **************************************************/
   private String genHashId(){
      int size = board.size();
      String hashId = "";
      
      for(int i = 0; i < size; i++){
         hashId += board.get(i) + ",";
      }
      
      return hashId;
   }

   /**************************************************
   Method Name: rotate
   Input to the method: int, the first peices to start the rotation with
   Output(Return value): Void, re-orders the peices
   Brief description of the task: Gives the rotation function of the board game
   Author:
   **************************************************/
   public void rotate(int start){
      if(start < 1 || start > board.size()){
        throw new java.lang.IndexOutOfBoundsException();
      }
      
      //shift starting item to front for easy shift      
      while(board.peek() != start){
         int temp = board.pop();
         board.addLast(temp);
      }
      
      int first = board.get(2);
      int second = board.get(3);
      
      board.set(2, board.get(1));
      board.set(3, board.get(0));
      board.set(1, first);
      board.set(0, second);
      normalize();
      id = genHashId();
   }

   /**************************************************
   Method Name: printState
   Input to the method: Void
   Output(Return value): Void, outputs to the console
   Brief description of the task: This gives outputs information based on state
   Author:
   **************************************************/
   public void printState(){
      int last = board.peekLast();
      int temp;
         
      while(board.peek() != last){
         temp = board.pop();
         board.addLast(temp);
         System.out.print(temp + ", ");
      }
      
      board.addLast(board.pop());
      System.out.println(last);
   }

   /**************************************************
   Method Name: clone
   Input to the method: Void
   Output(Return value): State, a true clone of the array
   Brief description of the task: This makes a deep clone of the state
   Author:
   **************************************************/
   public State clone(){
      int size = board.size();
      int[] array = new int[size];
      
      for (int i = 0; i < size; i++) {
          array[i] = board.get(i);
      }      
      
      State clone = new State(array);
      return clone; 
   }

   /**************************************************
   Method Name: getId
   Input to the method: Void
   Output(Return value): String, gets a id base on state order
   Brief description of the task: Gets the most current ID of the state based on state order
   Author:
   **************************************************/
   public String getId(){
      return genHashId();
   }

   /**************************************************
   Method Name: size
   Input to the method: Void
   Output(Return value): int, returns number of elements in state
   Brief description of the task: this gives the size of the state
   Author:
   **************************************************/
   public int size(){
      return size;
   }
}