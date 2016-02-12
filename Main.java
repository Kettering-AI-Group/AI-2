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
      int debugLvl = 0;
      if(args.length > 2){
         fileLoc = args[args.length - 1];
      }
      if(args.length > 1){
         size = Integer.parseInt(args[args.length - 2]);
      }
      if(args.length > 0){
         if(args[0].equals("-d1")){
            debugLvl = 1;
         } 
         else if(args[0].equals("-d2")){
            debugLvl = 2;
         }
      }
      else{
         System.out.println("No arguments or incorrect args supplied, using size " + size + " with " + fileLoc + " ordering");
      }

      State fileState = new State(startFileParse(size, fileLoc));
      AStar player = new AStar(1, debugLvl);
      System.out.println("------1-------");
      player.run(fileState);
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
      }
      else{
         try {
            Scanner scan = new Scanner(file);
            
            for(int count = 0; scan.hasNextInt(); count++){
               res[count] = scan.nextInt();
            }
            
            scan.close();
         }
         catch(FileNotFoundException e){
            e.printStackTrace();
         }
      }
      
      return res;
   }
}
