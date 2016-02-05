public class AStar
{
   Heuristic curHeur = null;
      
   public AStar(int heuristicSelect)
   {
      curHeur = new Heuristic(heuristicSelect);
   }

   public void run(State start)
   {
      Node rootNode = new Node(null, start, 0, curHeur.calc(start));
   }

   class Heuristic
   {
      int methodSelect = -1;
   
      public Heuristic(int selector)
      {
         methodSelect = selector;
      }
      
      protected int calc(State state)
      {
         int result = -1;
         switch(methodSelect)
         {
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
   
      private int simpleH(State state)
      {
         int res = 0;
         for(int i = 0; i < state.board.size(); i++)
         {
            if(i+1 != (Integer) state.board.get(i))
            {
               res++;
            }
         }
         return res;
      }
   
      private int customH(State state)
      {
         int res = -1;
      //MUCH later
         return res;
      }
   }
}