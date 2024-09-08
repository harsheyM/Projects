import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.Timer;



public class LifeModel implements ActionListener {

    private int r, c;
    /*
     *  This is the Model component.
     */

    private static int SIZE = 60;
    private LifeCell[][] grid;

    LifeView myView;
    Timer timer;

    /**
     * Construct a new model using a particular file
     */
    public void makeNew()
    {
        for (r = 0; r < SIZE; r++) {
            for (c = 0; c < SIZE; c++) {
                if (Math.random() > 0.85) //15% chance of a cell starting alive
                    grid[r][c].setAliveNow(true);
            }
        }

    }
    public LifeModel(LifeView view, String fileName) throws IOException {

        grid = new LifeCell[SIZE][SIZE];
        for (r = 0; r < SIZE; r++)
            for (c = 0; c < SIZE; c++)
                grid[r][c] = new LifeCell();

        if (fileName == null) //use random population
        {
            makeNew();
        } else {
            Scanner input = new Scanner(new File(fileName));
            int numInitialCells = input.nextInt();
            for (int count = 0; count < numInitialCells; count++) {
                r = input.nextInt();
                c = input.nextInt();
                grid[r][c].setAliveNow(true);
            }
            input.close();
        }

        myView = view;
        myView.updateView(grid);

    }

    /**
     * Constructor a randomized model
     */
    public LifeModel(LifeView view) throws IOException {
        this(view, null);
    }

    /**
     * pause the simulation (the pause button in the GUI
     */
    public void pause() {
        timer.stop();
    }

    /**
     * resume the simulation (the pause button in the GUI
     */
    public void resume() {
        timer.restart();
    }

    /**
     * run the simulation (the pause button in the GUI
     */
    public void run() {
        timer = new Timer(50, this);
        timer.setCoalesce(true);
        timer.start();
    }

    public void randomizeColor() {

        if (LifeView.get())
        {
            LifeView.colorSet(false);
        }
        else {
            LifeView.colorSet(true);
        }


    }

    public void reset() {
        makeNew();
        run();
    }

    /**
     * called each time timer fires
     */
    public void actionPerformed(ActionEvent e) {
        oneGeneration();
        myView.updateView(grid);
    }

    private boolean isInGrid(int r, int c)
    {
        return (r >= 0 && r < SIZE && c >= 0 && c < SIZE);
    }
    //Count # of neighbors
    public int getNeighborCells(int r, int c) {
        int neigCells = 0;

        for (int i = r - 1; i <= r + 1; i++) {
            for (int k = c - 1; k <= c + 1; k++) {
                if (isInGrid(i,k) && grid[i][k].isAliveNow() && (r != i || c != k)) {
                    neigCells += 1;
                }
            }
        }
        return neigCells;
    }

    /**
     * main logic method for updating the state of the grid / simulation
     */
    private void oneGeneration() {
        for (int i = 0; i < SIZE; i++) {
            for (int k = 0; k < SIZE; k++) {
                int neighbors = getNeighborCells(i, k);

                if (grid[i][k].isAliveNow()) {
                    if ((neighbors < 2) || (neighbors > 3)) {

                        grid[i][k].setAliveNext(false);
                    }
                    if ((neighbors == 2) || (neighbors == 3)) {

                        grid[i][k].setAliveNext(true);
                    }
                }
                if (!grid[i][k].isAliveNow() && neighbors == 3) {

                    grid[i][k].setAliveNext(true);
                }
            }
        }
        for (int i = 0; i < SIZE ; i++) {
            for (int k = 0; k < SIZE; k++) {
                if (grid[i][k].isAliveNext()) {

                    grid[i][k].setAliveNow(true);

                }
                else if (!grid[i][k].isAliveNext())
                {
                    grid[i][k].setAliveNow(false);
                }

            }

        }
    }
}
