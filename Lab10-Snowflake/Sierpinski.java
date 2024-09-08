import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SierpinskiPanel extends JPanel
{
    public SierpinskiPanel()
    {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        int width  = getWidth();
        int height = getHeight();

        super.paintComponent(g);
        g.setColor(Color.BLUE);

        /*
         * DRAWING CODE BELOW
         */
        drawTriangle(g, new Point(0, height / 2), new Point(width / 2, height / 2), new Point(width / 2, 0), height, width);
        


    }
    public void drawTriangle(Graphics g, Point down, Point hyp,Point side, int height, int width)
    {
        g.drawLine(down.getX(), down.getY(), hyp.getX(), hyp.getY());
        g.drawLine(down.getX(), down.getY(), side.getX(), side.getY());
        g.drawLine(hyp.getX(), hyp.getY(), side.getX(), side.getY());
        
        if (width < 10 || height < 10) {
            return;
        }
        else
        {
           drawTriangle(g, new Point(down.getX(), down.getY() - height / 4), 
           new Point(hyp.getX() - width / 4, hyp.getY() - height / 4), 
           new Point(side.getX() - width / 4, side.getY()), height / 2, 
           width / 2);
        
           drawTriangle(g, new Point(down.getX(), down.getY() + height / 4), 
           new Point(hyp.getX() - width / 4, hyp.getY() + height / 4), 
           new Point(side.getX() - width / 4, side.getY() + height / 2), height / 2, 
           width / 2);
           
           drawTriangle(g, new Point(down.getX() + width / 2, down.getY() - height / 4), 
           new Point(hyp.getX() + width / 4, hyp.getY() - height / 4), 
           new Point(side.getX() + width / 4, side.getY()), height / 2, 
           width / 2);
        }
    }

}
class Point{
    private int x;
    private int y;
    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY()
    {
        return y;
    }
}
public class Sierpinski
{
    public static void main ( String[] args )
    {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Sierpinski");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }
}