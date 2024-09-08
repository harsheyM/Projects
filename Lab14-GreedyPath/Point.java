//Harshitha Mahesh
//3A
public class Point 
{
	private double  x, y;
	private boolean visited;
	
	public Point(double x, double y)
	{
	    this.x = x;
	    this.y = y;
	    visited = false;
        }
        public void setVisited(boolean isVisited)
        {
            visited = isVisited;
        }
	public double getX()
	{
	    return x;
        }
        public double getY()
        { 
               return y;
        }
        public boolean getVisited()
        {
            return visited;
        }
        
	/** get the Euclidean distance between two points */
	public double getDistance(Point other)
	{
		double distance = 0.0;
		double dX = Math.pow((other.x - x), 2);
		double dY = Math.pow((other.y - y), 2);
		distance = Math.sqrt(dX + dY);
		return distance; 
	}
	
	@Override
	public String toString()
	{
	    String text = "(" + x + ", " + y + ")";
	    return text;
	}
}