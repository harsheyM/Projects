import java.util.*;
public class MazeSolverWithStack extends MazeSolver {
   
   public static MyStack workList = new MyStack();
   
   
   public MazeSolverWithStack(Maze maze) {
      super(maze);
   }
   
   @Override
   public void add(Square s) {
      workList.push(s);
   }
   
   @Override
   public void makeEmpty() {
      workList.clear();
   }
   
   @Override
   public boolean isEmpty() {
      return workList.isEmpty();
   }
   
   @Override
   public Square next() {
      return workList.pop();
   }
}