public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;
    private boolean safety = true;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    static int[][] points = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    
    /*
        recursiveFire method here
     */
    public void recursiveFire(int x, int y)
    {
        if (!outBounds(x, y) || myGrid[y][x].getStatus() != FireCell.GREEN)
        {
            return;
        }
        if (y == 0)
        {
           safety = false;
        }
           
        myGrid[y][x].setStatus(FireCell.BURNING);
        myView.updateView(myGrid);
        
        
        try {
            Thread.sleep(5);
         } catch(Exception e) {
            e.printStackTrace();
         }
        
        
        for (int[] poi : points)
        {
            recursiveFire(x + poi[0], y + poi[1]);
        }
        
    }

    public boolean outBounds(int x, int y)
    {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
    
    public void solve()
    {
        // student code here
        for (int i = 0; i < SIZE; i++)
        {
            recursiveFire(i, SIZE - 1);
        }
        if (safety)
        {
            System.out.println("Onett is safe");
        }
        else
        {
            System.out.println("Onett is in trouble!");
        }
        myView.updateView(myGrid);
    }

}
