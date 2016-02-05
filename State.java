import java.util.*;

public class State
{
   int id = -1;
   LinkedList board = new LinkedList();
   public State(int[] values)
   {
      id = values.hashCode();
      for(int i: values){board.add(i);}
      normalize();
   }
   
   private void normalize()
   {
      while((Integer) board.peek() != 1)
      {
         int temp = (Integer) board.pop();
         board.addLast(temp);
      }
   }

}