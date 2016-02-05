import java.io.*;
import java.util.*;

public class Main
{
   public static void main(String [] args)
   {
      boolean randomFlag = false;
   
      State startState = new State(startFileParse(Integer.parseInt(args[0]), args[1]));
      AStar player = new AStar();
      player.run(startState);
   }
   
   private static int[] startFileParse(int size, String fileName)
   {
      int[] res = new int[size];
      File file = new File(fileName);
      if(fileName.equals("random"))
      {
         Random rgen = new Random();
         for(int i = 0; i < res.length; i++)
         {
            res[i] = i + 1;
         }
         for(int i = 0; i < res.length; i++)
         {
            int randomPos = rgen.nextInt(res.length);
            int temp = res[i];
            res[i] = res[randomPos];
            res[randomPos] = temp;
         }
      }
      else
      {
         try {
            Scanner scan = new Scanner(file);
            for(int count = 0; scan.hasNextInt(); count++)
            {
               res[count] = scan.nextInt();
            }
            scan.close();
         } 
         catch(FileNotFoundException e)
         {
            e.printStackTrace();
         }
      }
      //for(int i: res){ System.out.print(i + " ");}
      return res;
   }
}