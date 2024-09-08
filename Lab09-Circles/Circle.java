import java.util.*;
import java.awt.Color;

public class Circle
{
     private int x;
     private int y;
     private int dx;
     private int dy;
     private int radius;
     private Color color;
     
     public Circle(int xCent, int yCent, int radVal, Color colCir)
     {
         this.x = xCent;
         this.y = yCent;
         this.radius = radVal;
         this.color = colCir;
     }
     public Circle(int xCent, int yCent, int radVal, int dXval, int dYval, Color colCir)
     {
         this.x = xCent;
         this.y = yCent;
         this.dx = dXval;
         this.dy = dYval;
         this.radius = radVal;
         this.color = colCir;
     }
     public int getRadius()
     {
         return radius;
     }
     public int getX()
     {
         return x;
     }
     public int getY()
     {
         return y;
     }
     void draw()
     {
         StdDraw.setPenColor(color);
         StdDraw.filledCircle(x, y, radius);
     }
     public static void main(String args[])
     {
         new Circle(0, 0, 1, new Color(255, 0, 0)).draw();
     }
     boolean overlaps(Circle other)
     {
         int tempX = Math.abs(x - other.x);
         int tempY = Math.abs(y - other.y);
         double distance = Math.sqrt(Math.pow(tempX, 2) + Math.pow(tempY, 2));
         
         if (distance < (radius + other.radius))
         {
             return true;
         }
         else
         {
             return false;
         }
     }
     void update()
     {
         x += dx;
         y += dy;
         bounce();
     }
     void bounce()
     {
         if (x >= 600 - radius || y >= 600 - radius)
         {
             if (x == y)
             {
                 dx -= dx * 2;
                 dy -= dy * 2;
             }
             else if (x > y)
             {
                 dx -= dx * 2;
             }
             else if (y > x)
             {
                 dy -= dy * 2;
             }
         }
         else if (x <= 0 + radius || y <= 0 + radius)
         {
             if (x == y)
             {
                 dx -= dx * 2;
                 dy -= dy * 2;
             }
             else if (x < y)
             {
                 dx -= dx * 2;
             }
             else if (y < x)
             {
                 dy -= dy * 2;
             }
         }
     }
     public void setDx(int dx)
     {
         this.dx = dx;
     }
     public void setDy(int dy)
     {
         this.dy = dy;
     }
}