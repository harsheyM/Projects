//Harshitha Mahesh
//3A

import java.io.IOException;

public class GreedyPath extends Path
{
    private Point[] pointsGreedy;
    private double distanceGreedy;
    
    public GreedyPath(String fileName) throws IOException
    {
        super(fileName);
        pointsGreedy = new Point[super.getNumPoints()];
        findPath();
    }
    
        private void findPath()
    {
        //filling out pointsGreedy
        
        pointsGreedy[0] = super.getPoint(0);
        super.getPoint(0).setVisited(true);
        pointsGreedy[0].setVisited(true);
        
        for(int i = 1; i < pointsGreedy.length; i++)
        {
            double tempDistance = Double.MAX_VALUE;
            int tempIndex = -1;
            
            for(int b = 1; b < super.getNumPoints(); b++)
            {
                if(pointsGreedy[i - 1].getDistance(super.getPoint(b)) < tempDistance && !super.getPoint(b).getVisited())
                {
                    tempDistance = pointsGreedy[i - 1].getDistance(super.getPoint(b));
                    tempIndex = b;
                }
            }
            
            pointsGreedy[i] = super.getPoint(tempIndex);
            super.getPoint(tempIndex).setVisited(true);
        }
        
        //calculating distance
        
        distanceGreedy = 0;
        for(int i = 0; i < pointsGreedy.length - 1; i++)
        {
            distanceGreedy += Math.sqrt(Math.pow((pointsGreedy[i + 1].getX() - pointsGreedy[i].getX()), 2) + Math.pow((pointsGreedy[i + 1].getY() - pointsGreedy[i].getY()), 2));
        }
    }
    @Override public double getDistance()
    {
        return distanceGreedy;
    }
    
    @Override public Point getPoint(int index)
    {
        return pointsGreedy[index];
    }
    
    @Override public String toString()
    {
        String text = "The points in this path are: \n";
            for(int a = 0; a < pointsGreedy.length; a++)
            {
                text += "(" + pointsGreedy[a].getX() + ", " + pointsGreedy[a].getY() + ") \n";
            }
            return text + "Total distance ---> " + getDistance();
    }
}