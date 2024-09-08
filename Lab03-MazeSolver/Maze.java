import java.io.*;
import java.util.*;

public class Maze {
   public static final Integer[][] outlierCheck = { {-1,0}, {0,-1}, {0,1}, {1,0}}; 
   
   private Square[][] maze;
   
   private Square start, exit;
   
   public Maze() {
      
   }
   
   
   
   public boolean loadMaze(String fileName) {
      try {
         File file = new File(fileName);
         Scanner in = new Scanner(file);
         
         int rows = in.nextInt();
         int cols = in.nextInt();
         
         this.maze = new Square[rows][cols];
         
         for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
              this.maze[i][j] = new Square(i,j,in.nextInt());
              if (this.maze[i][j].getType() == 2) 
              {
                  this.start = this.maze[i][j];
              } else if (this.maze[i][j].getType() == 3) { 
                  this.exit = this.maze[i][j];
              }
            }
         }
         return true;
      } catch (FileNotFoundException e) {
         System.out.println("File not found, please fix error");
      }
      return false;
   }
   
   public ArrayList<Integer[]> getOutlierDirections(Square s) {
      ArrayList<Integer[]> outlier = new ArrayList<Integer[]>();
      
      if (s.getRow() > exit.getRow()) {
         outlier.add(outlierCheck[0]);
      }  else if (s.getRow() < exit.getRow()) {
         outlier.add(outlierCheck[3]);
      }
      
      if (s.getCol() > exit.getCol()) {
         outlier.add(outlierCheck[1]);
      }  else if (s.getCol() < exit.getCol()) {
         outlier.add(outlierCheck[2]);
      }
      
      for (int i = 0; i < outlierCheck.length; i++) {
         if (outlier.contains(outlierCheck[i]) == false) {
            outlier.add(outlierCheck[i]);
         }
      }
      return outlier;
   }
   
   public List<Square> getNeighbors(Square s) {
      List<Square> neigh = new ArrayList<Square>();
      ArrayList<Integer[]> optimal = getOutlierDirections(s);
      for (int i = 0; i < optimal.size(); i++) {
         Integer[] direction = optimal.get(optimal.size()-i-1);
         if (s.getRow() + direction[0] >= 0 && s.getRow() + direction[0] < maze.length && s.getCol() + direction[1] < maze[0].length && s.getCol() + direction[1] >= 0) {
            neigh.add(maze[s.getRow() + direction[0]][s.getCol() + direction[1]]);
         }
      }
      return neigh;
   }
   
   public Square getStart() {
      return this.start;
   }
   
   public Square getExit() {
      return this.exit;
   }
   
   public void reset() {
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze[i].length; j++) {
            this.maze[i][j].setStatus('_');
         }
      }
   }   
   
   public String toString() {
      String mazeStr = "";
      for (int i = 0; i < this.maze.length; i++) {
         for (int j = 0; j < this.maze[i].length; j++) {
            mazeStr = mazeStr + this.maze[i][j];
         }
         mazeStr = mazeStr + "\n";
      }
      return mazeStr;
   }
   
}