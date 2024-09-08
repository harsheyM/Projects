import java.awt.*;
import javax.swing.*;
import java.util.Random;

/** The view (graphical) component */
public class LifeView extends JPanel
{
    private static boolean colorChange;


	private static final long serialVersionUID = 1L;
	private static int SIZE = 60;

	public static boolean get()
    {
        return colorChange;
    }

    public static void colorSet(boolean color)
    {
        colorChange = color;
    }
	/** store a reference to the current state of the grid */
    private LifeCell[][] grid;

    public LifeView()
    {
        grid = new LifeCell[SIZE][SIZE];
    }

    /** entry point from the model, requests grid be redisplayed */
    public void updateView( LifeCell[][] mg )
    {
        grid = mg;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        Random rand = new Random();
        super.paintComponent(g);
        int testWidth = getWidth() / (SIZE+1);
        int testHeight = getHeight() / (SIZE+1);
        // keep each life cell square
        int boxSize = Math.min(testHeight, testWidth);

        for ( int r = 0; r < SIZE; r++ )
        {
            for (int c = 0; c < SIZE; c++ )
            {
                if (grid[r][c] != null)
                {
                    if ( grid[r][c].isAliveNow() )
                        if (!colorChange) {
                            g.setColor(Color.BLUE);
                        }
                        else
                        {
                             int red = rand.nextInt(256);
                             int blue = rand.nextInt(256);
                             int green = rand.nextInt(256);
                             Color randomColor = new Color(red, green, blue);
                             g.setColor(randomColor);
                        }
                    else
                        g.setColor( new Color(235,235,255) );

                    g.fillRect( (c+1)*boxSize, (r+1)* boxSize, boxSize-2, boxSize-2);
                }
            }
        }
    }
}