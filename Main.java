import java.io.*;
import java.util.*;

public class Main{
   public static void main(String [] args){
      int size = 6;
      String fileLoc = "random";

      if(args.length == 1){
         size = Integer.parseInt(args[0]);
      }else if(args.length == 2){
         size = Integer.parseInt(args[0]);
         fileLoc = args[1];
      }else{
         System.out.println("No arguments or incorrect args supplied, using size " + size + " with " + fileLoc + " ordering");
      }
      
      State startState = new State(new int[]{5,6,2,1,4,3});   //startFileParse(size, fileLoc)
      AStar player = new AStar(1);
      player.run(startState);
   }
   
   private static int[] startFileParse(int size, String fileName){
      int[] res = new int[size];
      File file = new File(fileName);
      
      if(fileName.equals("random")){
         Random rgen = new Random();
         
         for(int i = 0; i < res.length; i++){
            res[i] = i + 1;
         }
         
         for(int i = 0; i < res.length; i++){
            int randomPos = rgen.nextInt(res.length);
            int temp = res[i];
            res[i] = res[randomPos];
            res[randomPos] = temp;
         }
      }else{
         try {
            Scanner scan = new Scanner(file);
            
            for(int count = 0; scan.hasNextInt(); count++){
               res[count] = scan.nextInt();
            }
            
            scan.close();
         }catch(FileNotFoundException e){
            e.printStackTrace();
         }
      }
      
      return res;
   }
}
