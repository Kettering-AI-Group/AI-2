import java.util.*;

public class State
{
   int id = -1;
   LinkedList<Integer> board = new LinkedList<Integer>();
   public State(int[] values)
   {
      id = values.hashCode();
      for(int i: values){board.add(i);}
      normalize();
   }
   
   private void normalize()
   {
      while(board.peek() != 1)
      {
         int temp = board.pop();
         board.addLast(temp);
      }
   }

}