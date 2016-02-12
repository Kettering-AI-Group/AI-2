/*
Class Name:Main
Author's Name:
Date: 2/11/2016
Description of the class: This is the main program that combines states with the AStar function 
*/
import java.io.*;
import java.util.*;

public class Main{

   /**************************************************
   Method Name: main
   Input to the method: String[], arguments from command line, size and file
   Output(Return value): void, outputs to console
   Brief description of the task: gets user parameters on how the board should look
   Author:
   **************************************************/
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
       
      State s1 = new State(new int[]{4,2,1,3,9,5,8,7,10,6});   //startFileParse(size, fileLoc)   
      
      AStar playerOne = new AStar(1);
      AStar playerFive = new AStar(5);
      AStar playerSeven = new AStar(7);
      
      System.out.println("------1-------");
      playerOne.run(s1.clone());
      System.out.println("------5-------");
      playerFive.run(s1.clone());
      System.out.println("------7-------");
      playerSeven.run(s1.clone());

   }

   /**************************************************
   Method Name: startFileParse
   Input to the method: 
         int, the amount of pecices the board should have
         String, eithe "random" for a random board, or the location of a file deliminated file for board init order
   Output(Return value): int[], the board int array in the order specified or random
   Brief description of the task: gets user parameters on how the board should look
   Author:
   **************************************************/
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
