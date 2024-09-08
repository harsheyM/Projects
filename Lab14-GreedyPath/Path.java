//Harshitha Mahesh
//3A

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Path
{
	private Point[] points;
	private double  minX, minY; //min X and Y values, for setting canvas scale
	private double  maxX, maxY; //maxes

	/** construct a path from a given file */
	public Path(String fileName) throws IOException
	{
		File file = new File(fileName);
            Scanner scan = new Scanner(file);
            int length = 0;
            scan.nextLine();
            while(scan.hasNextDouble())
            {
                scan.nextDouble();
                scan.nextDouble();
                length++;
            }
            points = new Point[length];
            scan.close();
            
            Scanner s = new Scanner(file);
            s.nextLine();
            for(int a = 0; a < length; a++)
            {
              points[a] = new Point(s.nextDouble(), s.nextDouble());
            }
            
            double minX = Double.MAX_VALUE;
            double minY = Double.MAX_VALUE;
            double maxX = Double.MIN_VALUE;
            double maxY = Double.MIN_VALUE;
            
            for(int a = 0; a < points.length; a++)
            {
                if(points[a].getX() < minX)
                {
                    minX = points[a].getX();
                }
                if(points[a].getY() < minY)
                {
                    minY = points[a].getY();
                }
                if(points[a].getX() > maxX)
                {
                    maxX = points[a].getX();
                }
                if(points[a].getY() > maxY)
                {
                    maxY = points[a].getY();
                }
            }
            
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
	}
        public double getMinX()
        {
            return minX;
        }
        
        public double getMinY()
        {
            return minY;
        }
        
        public double getMaxX()
        {
            return maxX;
        }
        
        public double getMaxY()
        {
            return maxY;
        }
        
        public Point getPoint(int index)
        {
            return points[index];
        }
        
        public int getNumPoints()
        {
            return points.length;
        }
        
	/** returns the distance traveled going point to point, in order given in file */
	public double getDistance()
	{
            double distance = 0;
	    for (int i = 0; i < points.length - 1; i++)
	    {
		distance += points[i].getDistance(points[i + 1]);
	    }
	    return distance;
        }

	@Override
	public String toString()
	{
		String text = "The points in this path are : \n";
		for(int i = 0; i < points.length; i++)
		{
		    text += points[i].getX() + ", " + points[i].getY() + ") \n";
                }
                return text + "Total distance ---> " + getDistance();
	}
}