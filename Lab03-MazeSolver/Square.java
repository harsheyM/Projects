public class Square {
   public static final int EMPTY = 0;
   public static final int WALL = 1;
   public static final int START = 2;
   public static final int EXIT = 3;
   
   public static final char WORKING = 'o';
   public static final char EXPLORED = '.';
   public static final char ON_EXIT_PATH = 'x';
   public static final char UNKNOWN = '_';
   
   private int type;
   private char status;
   private int row, col;
   
   public Square(int row, int col, int type) {
      this.type = type;
      this.row = row;
      this.col = col;
   }
   
   @Override
   public String toString() {
      switch (this.type) {
      case 0: return this.status == '_' ? "_" : this.status + "";
      case 1: return "#";
      case 2: return "S";
      case 3: return "E";
      }
      return "";
   } 
   
   @Override
   public boolean equals(Object obj) {
      if (obj instanceof Square) {
         Square sqr = (Square) obj;
         if (sqr.getRow() == this.row && sqr.getCol() == this.col) {
            return true;
         }
      }
      return false;
   }
   
   public int getRow() {
      return this.row;
   }
   
   public int getCol() {
      return this.col;
   }
   
   public int getType() {
      return this.type;
   }
   
   public char getStatus() {
      return this.status;
   }
   
   public void setStatus(char c) {
      this.status = c;
   }
   

}