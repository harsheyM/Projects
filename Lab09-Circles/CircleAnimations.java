import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CircleAnimations
{
	private ArrayList<Circle> circles; //the circles to animate
	private int               size;    //canvas width and height (will be square)
	private Random            rng;     //use to make random numbers

	/** create a drawing pane of a particular size */
	public CircleAnimations(int s) {
		circles = new ArrayList<>();
		size    = s;
		rng     = new Random();

		//don't mess with this
		StdDraw.setCanvasSize(size, size); //set up drawing canvas
		StdDraw.setXscale(0, size);        //<0, 0> is bottom left.  <size-1, size-1> is top right
		StdDraw.setYscale(0, size);
	}
	void drawCircles()
	{
	   for (int i = 0; i < circles.size(); i++)
	   {
	       circles.get(i).draw();
           }
        }
        void addCircles()
        {
            for (int i = 0; i < 3; i++)
            {
                int radius = rng.nextInt(120);
                circles.add(new Circle(rng.nextInt(size - radius * 2) +
                radius, rng.nextInt(size - radius * 2) + radius, radius, 
                new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256))));
            }
        }
        void addCircles(int number)
        {
            for (int i = 0; i < number; i++)
            {
                int radius = rng.nextInt(120);
                circles.add(new Circle(rng.nextInt(size - radius * 2) +
                radius, rng.nextInt(size - radius * 2) + radius, radius, 
                new Color(rng.nextInt(256), rng.nextInt(256), rng.nextInt(256))));
            }
        }
        boolean checkOverlapping(Circle c)
        {
            for (int i = 0; i < circles.size(); i++)
            {
                if (circles.get(i).overlaps(c))
                {
                    return false;
                }
            }
            return true;
        }
        void noOverlapping(int number)
        {
            for (int i = 0; i < number; i++)
            {
                Circle newCircle;
                    int newX = rng.nextInt(size + 1);
                    int newY = rng.nextInt(size + 1); 
                    int newRadius = rng.nextInt(75) + 1;
                    Color newColor = new Color(rng.nextInt(256), rng.nextInt(256),
                    rng.nextInt(256));
                    newCircle = new Circle(newX, newY, newRadius, newColor);
                if (checkOverlapping(newCircle))
                {
                  circles.add(newCircle);
                }
                else
                {
                    i--;
                }
            }
        }
        void movingCircles(int num)
        {
            addCircles(num);
            for (int i = 0; i < circles.size(); i++)
            {
                int rand = rng.nextInt(5) + 1;
                circles.get(i).setDx(rand);
                circles.get(i).setDy(rand);
            }
            while (true)
            {
                drawCircles();
                for (int i = 0; i < circles.size(); i++)
                {
                    circles.get(i).update();
                }
                StdDraw.enableDoubleBuffering();
                StdDraw.show();
                StdDraw.pause(10);
                StdDraw.clear();
            }
        }
        void removeClicked(int num)
        {
            addCircles(num);
            while(true)
            {
                drawCircles();
                for (int i = 0; i < circles.size(); i++)
                {
                    if (StdDraw.mousePressed() && StdDraw.mouseX() <
                    circles.get(i).getX() + circles.get(i).getRadius() &&
                    StdDraw.mouseX() > circles.get(i).getX() - 
                    circles.get(i).getRadius() && StdDraw.mouseY() < circles.get(i).getY()
                    + circles.get(i).getRadius() && StdDraw.mouseY() > circles.get(i).getY()
                    - circles.get(i).getRadius())
                    {
                        circles.remove(i);
                        i--;
                    }
                }
                StdDraw.show(10);
                StdDraw.clear();
            }
        }
}
