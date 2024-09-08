import java.util.*;
public abstract class MazeSolver {
   
   Maze maze;
   boolean solved;
   
   public MazeSolver(Maze maze) {
      this.solved = false;
      this.maze = maze;
      makeEmpty();
      add(maze.getStart());
   }
   
   public abstract void makeEmpty();
   public abstract boolean isEmpty();
   public abstract void add(Square s);
   public abstract Square next();
   
   
   public boolean isSolved() {
      if (isEmpty() || solved) {
         return true;  
      }
      return false;
   }
   
   public void step() {
      if (!isEmpty()) {
         Square next = next();
         next.setStatus(Square.EXPLORED);
         if (next.equals(maze.getExit())) {
            solved = true;
         } else {
            List<Square> list = maze.getNeighbors(next);
            for (Square s : list) {
               if (s.getStatus() == '_' && s.getType() != Square.WALL) {
                  add(s);
                  s.setStatus(Square.WORKING);
               }
            }
         }
      }
   }
   
   public String getPath() {
      if (solved) {
         return "Maze is solved";
      } else if (isEmpty()) {
         return "Maze not solved";
      }
      return "Maze is unsolved";
   }
   
   public void solve() {
      while (!isSolved()) {
         step();
      }
   }
   
}