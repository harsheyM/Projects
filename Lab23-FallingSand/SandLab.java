//Harshitha Mahesh
//3A

import java.awt.*;
import java.util.*;

public class SandLab
{
	//add constants for particle types here
	public static final int EMPTY = 0;
	public static final int METAL = 1;
	public static final int SAND = 2;
	public static final int WATER = 3;

	//do not add any more fields!
	private int[][] grid;
	private SandDisplay display; //SandDisplay is the GUI class

	public SandLab(int numRows, int numCols)
	{
		String[] names = new String[4];

		names[EMPTY] = "Empty";
		names[METAL] = "Metal";
		names[SAND] = "Sand";
		names[WATER] = "Water";

		display = new SandDisplay("Falling Sand", numRows, numCols, names);

		grid = new int[numRows][numCols];
	}

	/** called when the user clicks on a location using the given tool */
	private void locationClicked(int row, int col, int tool)
	{
		grid[row][col] = tool;
	}

	/** copies each element of grid into the display */
	public void updateDisplay()
	{
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
			{
				if (grid[i][j] == 0)
					display.setColor(i, j, Color.black);
				if (grid[i][j] == 1)
					display.setColor(i, j, Color.gray);
				if (grid[i][j] == 2)
					display.setColor(i, j, new Color(194,178,128));
				if (grid[i][j] == 3)
					display.setColor(i, j, new Color(28,163,236));
			}
		}

	}

	/** called repeatedly, causes one random particle to maybe do something */
	public void step()
	{
		Random r = new Random();
		int row = r.nextInt(grid.length);
		int col = r.nextInt(grid[row].length);
		int cell = grid[row][col];
		if (cell == SAND)
		{
			if (row + 1 < grid.length && grid[row + 1][col] == EMPTY)
			{
				grid[row + 1][col] = grid[row][col];
				grid[row][col] = EMPTY;
			}
			else if (row + 1 < grid.length && grid[row + 1][col] == WATER)
			{
				int tmpCell = grid[row][col]; // save SAND
				grid[row][col] = grid[row + 1][col]; // WATER to SAND
				grid[row + 1][col] = tmpCell; // SAND to WATER
			}
		}
		if (cell == WATER)
		{
			//DOWN
			if (row + 1 < grid.length && grid[row + 1][col] == EMPTY)
			{
				grid[row + 1][col] = grid[row][col];
				grid[row][col] = EMPTY;
			}
			//LEFT
			if (col - 1 >= 0 && grid[row][col - 1] == EMPTY)
			{
				grid[row][col - 1] = grid[row][col];
				grid[row][col] = EMPTY;
			}
			//RIGHT
			if (col + 1 < grid[row].length && grid[row][col + 1] == EMPTY)
			{
				grid[row][col + 1] = grid[row][col];
				grid[row][col] = EMPTY;
			}
		}
	}
	//do not modify!
 	public void run()
 	{
   		while (true)
		{
			for (int i = 0; i < display.getSpeed(); i++)
				step();

			updateDisplay();

			display.repaint();

			display.pause(1);  //wait for redrawing and for mouse

			int[] mouseLoc = display.getMouseLocation();

			if (mouseLoc != null)  //test if mouse clicked
				locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
		}
	}
}