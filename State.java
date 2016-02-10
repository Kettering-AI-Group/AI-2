import java.util.*;
import java.lang.*;

public class State{
   String id = "";
   private int size;
   LinkedList<Integer> board = new LinkedList<Integer>();
   
   public State(int[] values){
      for(int i: values){ 
         board.add(i); 
      } 
      normalize();
      size = values.length;
      id = genHashId();
   }
   
   private void normalize(){
      while(board.peek() != 1){
         int temp = board.pop();
         board.addLast(temp);
      }
   }
   
   private String genHashId(){
      int size = board.size();
      String hashId = "";
      
      for(int i = 0; i < size; i++){
         hashId += board.get(i) + "";
      }
      
      return hashId;
   }
   
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
   }
   
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
   
   public State clone(){
      int size = board.size();
      int[] array = new int[size];
      
      for (int i = 0; i < size; i++) {
          array[i] = board.get(i);
      }      
      
      State clone = new State(array);
      return clone; 
   }
   
   public String getId(){
      return genHashId();
   }
   
   public int size(){
      return size;
   }
}