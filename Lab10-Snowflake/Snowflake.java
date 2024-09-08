import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel
{
    public SnowFlakePanel()
    {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    public void paintComponent(Graphics g)
    {
        int width  = getWidth();
        int height = getHeight();

        super.paintComponent(g);

        /*
         * DRAWING CODE BELOW
         */
        g.setColor(Color.BLUE);
        Random col = new Random();
        for (int i = 0; i < 20; i++)
        {
            g.setColor(new Color(col.nextFloat(), col.nextFloat(), col.nextFloat()));
            int size = Math.max(width / ((int)(Math.random() * 4) + 2), ((int)(Math.random() * 4) + 2));
            drawStar(g, (int)(Math.random() * (width - 20)), (int)(Math.random() * (width - 20)), size);
        }
        
        //g.drawLine(0, 0, width - 1, height - 1);
    }
    public void drawStar(Graphics g, int x, int y, int size)
    {
        if (size < 15)
        {
            return;
            }
            else
            {
           size = size / 4;
           for (int i = 0; i < 6; i++)
           {
              g.drawLine(x, y, x + (int)(Math.cos(i * (Math.PI / 3)) * size), y + (int)(Math.sin(i * (Math.PI / 3)) * size));
               }
               for (int i = 0; i < 6; i++)
               {
                drawStar(g, x + (int)(Math.cos(i * (Math.PI / 3)) * size), y + (int)(Math.sin(i * (Math.PI / 3)) * size), size);
               }
            }
        }
    
}

public class Snowflake
{
    public static void main ( String[] args )
    {
        /*
         * A frame is a container for a panel
         * The panel is where the drawing will take place
         */
        JFrame frame = new JFrame("Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SnowFlakePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
