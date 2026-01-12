//Harshitha Mahesh
//dal267662

#include <iostream>
#include <string>
#include <fstream>
#include "Creature.h"
#include "Ant.h"
#include "Beetle.h"
using namespace std;

//Method headers
static void beetleStarveHelper(Creature *grid[][10], int i, int j, bool ans);
static void breedHelper(Creature *grid[][10], int i, int j, string direction);
static bool emptySpace(Creature* grid[][10], int i, int j, string direction);
static int numAdjacentAnts(Creature *grid[][10], int i, int j);
static int beetleFindDist(Creature *grid[][10], int i, int j, string direction);
static int antFindDist(Creature *grid[][10], int i, int j, string direction);
void beetleMoveHelper(Creature *grid[][10], int j, int i, string direction);
void antMoveHelper(Creature *grid[][10], int j, int i, string direction);

int main()
{
    //declaring a string variable fileName and storing the file name input
    string fileName;
    cin >> fileName;
    ifstream input;
    input.open(fileName);
    //Check if file is valid
    if (!input)
	{
		return 1;
	}
   
    //taking the character representation for ant and beetle
    //the number of turns from the user
    //initilizes the corresponding variables
    string antKey;
    string beetleKey;
    cin >> antKey;
    cin >> beetleKey;
    int turns;
    cin >> turns;
 
    //initializing constant int variables for the fixed grid - rows and columns
    const int rows = 10;
    const int columns = 10;
   
    //creating an array with objects of Creature abstract class
    Creature* grid[rows][columns] = {};
   
    //Statement to begin creating the grid
    int count = 0;
    string line;
   
    //while loop that runs 10 times and gets the line from the input file
    //creating ant or beetle instances in the Creature array (grid)
    while (count < 10)
    {
        //storing each line of the input file to the string variable line
        getline(input, line);
       
        //Creates a beetle or ant according to the character in the input file
        for (int i = 0; i < 10; i++)
        {
            //if the character is 'a', then an ant object is added to the grid at the corresponding position
            if (line[i] == 'a')
            {
                grid[count][i] = new Ant();
            }
            //if the character is 'B', then a beetle object is added to the grid at the corresponding position
            else if (line[i] == 'B')
            {
                grid[count][i] = new Beetle();
            }
            //if the character is blank, then the grid box is set to nullptr
            else
            {
                grid[count][i] = nullptr;
            }
        }
        //increments count for the next iteration
        count++;
    }
   
    //closes the input file after taking data from it
    input.close();
   
    //printing an empty line to create space
    cout << endl;
   
    //the loop is run the number of times equal to the number of turns input by the user
    for (int k = 1; k < turns + 1; k++)
    {
        //resetMovedandBred is called to make sure their hasMoved and hasBred instance variables are set to false
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (grid[i][j])
                {
                    grid[i][j]->resetMovedAndBred();
                }
               
            }
        }
       
        //printing the title "TURN"
        cout << "TURN " << k << endl;
       
        //checks if the object present in the grid at that position is a beetle.
        //If it is a beetle, it moves the beetle if it hasnt already been moved in this turn.
        //This is done by calling the move method in the beetle class which returns which direction to move, which is then
        //passed as an argument to beetleMoveHelper method in main
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                //checking if there is a creature present at this position
                if (grid[j][i])
                {
                    //if the creature at this position is a beetle and the beetle hasnt moved once in this turn
                    if ((dynamic_cast<Beetle*>(grid[j][i])) && (grid[j][i]->hasMovedOnce() == false))
                    {
                        //calling didNotEat method on the beetle at this position to increase the number of turns
                        //lastEaten is reset in the helper method if this beetle ends up eating an ant in this turn.
                        Beetle* b = dynamic_cast<Beetle*>(grid[j][i]);
                        b->didNotEat();
                       
                        //finds the distance to the ant in the north, east, south and west directions
                        int p = beetleFindDist(grid, j, i, "N");
                        int q = beetleFindDist(grid, j, i, "E");
                        int r = beetleFindDist(grid, j, i, "S");
                        int s = beetleFindDist(grid, j, i, "W");
                       
                        //move method ont the beetle to find the distance + direction the ant has to move
                        string direction = grid[j][i]->move(p, q, r, s);
                       
                        //[if there are no orthogonal ants, the variable direction will be an empty string
                        //if the direction is an empty string, then the farthest edge from the beetle is found
                        if (direction == "")
                        {
                            int smallest = p;
                            direction = "N";
                            if (smallest > q)
                            {
                                smallest = q;
                                direction = "E";
                            }
                            if (smallest > r)
                            {
                                smallest = r;
                                direction = "S";
                            }
                            if (smallest > s)
                            {
                                smallest = s;
                                direction = "W";
                            }
                            //if there are multiple farthest edges, using prioirty NESW.
                            //updated with the next most prioirty direction if there are multiple farthest edges
                            if (smallest == s)
                            {
                                direction = "W";
                            }
                            if (smallest == r)
                            {
                                direction = "S";
                            }
                            if (smallest == q)
                            {
                                direction = "E";
                            }
                            if (smallest == p)
                            {
                                direction = "N";
                            }
                        }
                        //grid, the row and column and the direction as the arguments
                        //the method will make movements in the grid after checking some conditions
                        beetleMoveHelper(grid, j, i, direction);
                       
                    }
                }
            }
        }
       
        //checks if the object present in the grid at that position is an ant
        //If it is an ant, it moves the ant if it hasn't already been moved in this turn.
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                //checking if there is a creature present at this position
                if (grid[j][i])
                {
                    //if the creature at this position is an ant and the ant hasn't moved once in this turn
                    if ((dynamic_cast<Ant*>(grid[j][i])) && (grid[j][i]->hasMovedOnce() == false))
                    {
                        //calling antFindDist method to find the distance to the beetle in NESW
                        int m = (antFindDist(grid, j, i, "N"));
                        int n = (antFindDist(grid, j, i, "E"));
                        int o = (antFindDist(grid, j, i, "S"));
                        int p = (antFindDist(grid, j, i, "W"));
                       
                        //make movements in the grid after checking some conditions
                        antMoveHelper(grid, j, i, grid[j][i]->move(m,n,o,p));
                    }
                }
            }
        }
       
        //the for inside the for loop checks if the object present in the grid at that position is a beetle.
        //If it is a beetle, it checks how many turns have passed since the beetle ate an ant using getNumTurnsSinceAte method.
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                //checks if there is a creature at this position
                if (grid[j][i])
                {
                    //checks if the ceature at this positon is a beetle
                    if (dynamic_cast<Beetle*>(grid[j][i]))
                    {
                        //finding the number of turns since the beetle ate an ant
                        Beetle* b = dynamic_cast<Beetle*>(grid[j][i]);


                        int numTurnsSinceLastAte = b->getNumTurnsSinceAte();
                       
                        //calling the starve method with the number of turns since the beetle ate an ant
                        bool shouldStarve = b->starve(numTurnsSinceLastAte);
                       
                        //The method will make the beetle starve if necessary
                        beetleStarveHelper(grid, j, i, shouldStarve);
                    }
                }
            }
        }
       
        //if the number of turns is perfectly divisible by 3 (every 3rd turn)
        if (k % 3 == 0)
        {
            //the for inside the for loop checks if the object present in the grid at that position is an ant
            //If it is an ant, the ant considers breeding if this ant wasnt newly born in the this turn.
            //NESW are checked using emptySpace method to see if its empty
            //The breed method returns the direction of the box in which the new ant has to be born
            //breedHelper actually does the breeding
            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    //if a creature is present at this position
                    if (grid[j][i])
                    {
                        //if the creature at this position is an ant and this ant wasnt newly born in this turn
                        if ((dynamic_cast<Ant*>(grid[j][i])) && (grid[j][i]->hasBredOnce() == false))
                        {
                            //calls emptySpace methods to see NESW are empty
                            //creates a new ant in the grid in the appropriate box
                            bool e = emptySpace(grid, j, i, "N");
                            bool f = emptySpace(grid, j, i, "E");
                            bool g = emptySpace(grid, j, i, "S");
                            bool h = emptySpace(grid, j, i, "W");
                            breedHelper(grid, j, i, grid[j][i]->breed(e, f, g, h));
                           
                        }
                    }
                }
            }
        }
       
        //if the number of turns is perfectly divisible by 8 (every 8th turn)
        if (k % 8 == 0)
        {
            //the for inside the for loop checks if the object present in the grid at that position is a beetle
            //If it is a beetle, the beetle considers breeding if this beetle wasnt newly born in the this turn.
            //NESW are checked using emptySpace method to check if its empty
            //The breed method returns the direction of the box in which the new beetle has to be born
            for (int i = 0; i < 10; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    //if a creature is present at this position
                    if (grid[j][i])
                    {
                        //if the creature at this position is a beetle and this beetle wasnt newly born in this turn
                        if ((dynamic_cast<Beetle*>(grid[j][i])) && (grid[j][i]->hasBredOnce() == false))
                        {
                            //calls emptySpace methods to see if the box NESW are empty
                            //creates a new beetle in the grid in the appropriate box
                            bool e = emptySpace(grid, j, i, "N");
                            bool f = emptySpace(grid, j, i, "E");
                            bool g = emptySpace(grid, j, i, "S");
                            bool h = emptySpace(grid, j, i, "W");
                            breedHelper(grid, j, i, grid[j][i]->breed(e,f,g,h));
                           
                        }
                    }
                }
            }
        }
       
        //antKey if it is an ant and beetleKey if it is a beetle and prints an empty space if there are no creatures.
        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                //if a creature is present at this position
                if (grid[i][j])
                {
                    //if the creature is an ant
                    if (dynamic_cast<Ant*>(grid[i][j]))
                    {
                        //prints the value of antRep if the creature is an ant
                        cout << antKey;
                    }
                    //if the creature is a beetle
                    else if (dynamic_cast<Beetle*>(grid[i][j]))
                    {
                        //prints the value of beetleRep if the creature is a beetle
                        cout << beetleKey;
                    }
                }
                //if there is no creature at this position
                else
                {
                    //prints an empty space if there is no creature at this position
                    cout << " ";
                }
            }
            //prints an empty line after every row in the grid
            cout << endl;
        }
       
        //prints an empty line
        cout << endl;
    }
   
    //return for main() method
    return 0;
}




//Deletes an ant from the grid if the beetle hasn't eaten an ant in 8 turns.
//if the boolean value (ans) is true, it deletes the beetle at that position
static void beetleStarveHelper(Creature *grid[][10], int i, int j, bool ans)
{
    if (ans)
    {
        //if the boolean value (ans) is true, the beetle at that position is deleted and the pointer is pointed to nullptr
        delete grid[i][j];
        grid[i][j] = nullptr;
    }
}


//Creates a new creature in the grid if possible
//Creates a new creature in the grid if possible
static void breedHelper(Creature *grid[][10], int i, int j, string direction)
{
    //if the creature is an ant
    if (dynamic_cast<Ant*>(grid[i][j]))
    {
        //if the direction is north
        if (direction == "N")
        {
            //checking the gird boundary
            if (i-1 > -1)
            {
                //creates a new Ant at the position and calls the bred method to know that this ant is newly born in this turn
                grid[i-1][j] = new Ant();
                grid[i-1][j]->bred();
            }
        }
        //if the direction is east
        else if (direction == "E")
        {
            //checking the grid boundary
            if (j+1 < 10)
            {
                //creates a new Ant at the position and calls the bred method to know that this ant is newly born in this turn
                grid[i][j+1] = new Ant();
                grid[i][j+1]->bred();
            }
        }
        //if the direction is south
        else if (direction == "S")
        {
            //checking the grid boundary
            if (i+1 < 10)
            {
                //creates a new Ant at the position and calls the bred method to know that this ant is newly born in this turn
                grid[i+1][j] = new Ant();
                grid[i+1][j]->bred();
            }
        }
        //if the direction is west
        else if (direction == "W")
        {
            //checking the grid boundary
            if (j-1 > -1)
            {
                //creates a new Ant at the position and calls the bred method to know that this ant is newly born in this turn
                grid[i][j-1] = new Ant();
                grid[i][j-1]->bred();
            }
        }
    }
    //if the creatire is a beetle
    else if (dynamic_cast<Beetle*>(grid[i][j]))
    {
        //if the direction is north
        if (direction == "N")
        {
            //checking the grid boundary
            if (i-1 > -1)
            {
                //creates a new Beetle at the position and calls the bred method to know that this Beetle is newly born in this turn
                grid[i-1][j] = new Beetle();
                grid[i-1][j]->bred();
            }
        }
        //if the direction is east
        else if (direction == "E")
        {
            //checking the grid boundary
            if (j+1 < 10)
            {
                //creates a new Beetle at the position and calls the bred method to know that this Beetle is newly born in this turn
                grid[i][j+1] = new Beetle();
                grid[i][j+1]->bred();
            }
        }
        //if the direction is south
        else if (direction == "S")
        {
            //checking the grid boundary
            if (i+1 < 10)
            {
                //creates a new Beetle at the position and calls the bred method to know that this Beetle is newly born in this turn
                grid[i+1][j] = new Beetle();
                grid[i+1][j]->bred();
            }
        }
        //if the direction is west
        else if (direction == "W")
        {
            //checking the grid boundary
            if (j-1 > -1)
            {
                //creates a new Beetle at the position and calls the bred method to know that this Beetle is newly born in this turn
                grid[i][j-1] = new Beetle();
                grid[i][j-1]->bred();
            }
        }
    }
}


//emptySpace method checks and returns if the box in a particular direction is empty
static bool emptySpace(Creature* grid[][10], int i, int j, std::string direction)
{
    //Directional offsets to look for the indexes
    int di = 0, dj = 0;

    if (direction == "N") di = -1;
    else if (direction == "E") dj = 1;
    else if (direction == "S") di = 1;
    else if (direction == "W") dj = -1;
    else return false; //Its an invalid direction

    int ni = i + di;
    int nj = j + dj;

    //Check bounds
    if (ni < 0 || ni >= 10 || nj < 0 || nj >= 10)
        return false;

    //Return true if the space is empty
    return (grid[ni][nj] == nullptr);
}


//numAdjacentAnts method finds and returns the number of adjacent ants in both the orthogonal and diagonal directions
//the method checks the 8 boxes surrounding the box and if an ant is found there
static int numAdjacentAnts(Creature* grid[][10], int i, int j)
{
    int num = 0;

    //All 8 directions around (i, j) to check
    int dx[] = {-1, -1, -1,  0, 0, 1, 1, 1};
    int dy[] = {-1,  0,  1, -1, 1, -1, 0, 1};

    for (int k = 0; k < 8; ++k)
    {
        //Traverses through each index
        int ni = i + dx[k];
        int nj = j + dy[k];

        //Check boundaries
        if (ni >= 0 && ni < 10 && nj >= 0 && nj < 10)
        {
            //Check if the Creature is an ant
            if (grid[ni][nj] && dynamic_cast<Ant*>(grid[ni][nj]))
            {
                num++;
            }
        }
    }

    return num;
}



//beetleFindDist method finds the distance from the beetle to the ant in a particular direction
static int beetleFindDist(Creature* grid[][10], int i, int j, std::string direction)
{
    //Directions for N, E, S, W
    int di = 0, dj = 0;

    //Used to calculate indexes
    if (direction == "N") di = -1;
    else if (direction == "E") dj = 1;
    else if (direction == "S") di = 1;
    else if (direction == "W") dj = -1;
    else return 0; //IF there's an invalid direction

    int c = 1;
    bool foundAnt = false;

    int indexI = i + di;
    int indexJ = j + dj;

    //Traverse through grid in the direction needed
    while (indexI >= 0 && indexI < 10 && indexJ >= 0 && indexJ < 10)
    {
        if (grid[indexI][indexJ])
        {
            //If an ant is found, the distance to the ant is found
            if (dynamic_cast<Ant*>(grid[indexI][indexJ]))
            {
                foundAnt = true;
                c = (c * 10) + numAdjacentAnts(grid, indexI, indexJ);
                break;
            }
        }
        c++;
        indexI += di;
        indexJ += dj;
    }

    //Either returns the distance or -1
    return foundAnt ? c : -c;
}



//antFindDist method finds the distance from the ant to the beetle in a particular direction
//the method checks every box in a direction from the ant until it finds a beetle and the distance is being kept track of.
//if there is no beetle in a particular direction, the distance to the edge will be stored  and returned as a negative number,
static int antFindDist(Creature *grid[][10], int i, int j, string direction)
{
    // Direction vectors: N, E, S, W
    int di = 0, dj = 0;
    //Looks through all the directions and uses these values for arthimentic
    if (direction == "N")      { di = -1; dj = 0; }
    else if (direction == "E") { di = 0;  dj = 1; }
    else if (direction == "S") { di = 1;  dj = 0; }
    else if (direction == "W") { di = 0;  dj = -1; }
    else return 0; // Invalid direction

    //Calculates the index
    int c = 1;
    bool foundBeetle = false;
    int x = i + di, y = j + dj;

    //Traverses the grid in the direction given to find the beetle
    while (x >= 0 && x < 10 && y >= 0 && y < 10)
    {
        if (grid[x][y])
        {
            if (dynamic_cast<Beetle*>(grid[x][y]))
            {
                foundBeetle = true;
                break;
            }
        }
        c++;
        x += di;
        y += dj;
    }

    //It either returns c or -1 (-c = -1)
    return foundBeetle ? c : -c;
}


//beetleMoveHelper method does the movement of beetles in the 2D array (grid)
//the method checks the boundary and then moves the beetle to the box in the direction passed as the argument if that box is empty.
//the method checks the boundary and then moves the beetle to the box in the direction passed as the argument to
void beetleMoveHelper(Creature* grid[][10], int j, int i, std::string direction)
{
    int newJ = j;
    int newI = i;

    //Determine new position based on direction
    if (direction == "N") newJ--;
    else if (direction == "S") newJ++;
    else if (direction == "W") newI--;
    else if (direction == "E") newI++;

    //Check if the move is within bounds
    if (newJ >= 0 && newJ < 10 && newI >= 0 && newI < 10)
    {
        //If the target space is empty
        if (grid[newJ][newI] == nullptr)
        {
            grid[newJ][newI] = grid[j][i];
            grid[newJ][newI]->moved();
            grid[j][i] = nullptr;
        }
        //If the target space contains an Ant
        else if (dynamic_cast<Ant*>(grid[newJ][newI]))
        {
            //Deletes the ant
            delete grid[newJ][newI];  
            grid[newJ][newI] = grid[j][i];
            grid[newJ][newI]->moved();

            //Since it's a Beetle call ate()
            if (Beetle* b = dynamic_cast<Beetle*>(grid[newJ][newI])) {
                b->ate();
            }

            grid[j][i] = nullptr;
        }
    }
}



//antMoveHelper method does the movement of ants in the 2D array (grid)
//the method checks the boundary and then moves the ant to the box in the direction passed as the argument if that box is empty.
void antMoveHelper(Creature* grid[][10], int j, int i, std::string direction)
{
    if (grid[j][i] == nullptr)
    //There's nothing to move
        return; 

    int newJ = j;
    int newI = i;

    //Calculate target coordinates for the ant to move (depends on direction)
    if (direction == "N") newJ--;
    else if (direction == "S") newJ++;
    else if (direction == "W") newI--;
    else if (direction == "E") newI++;
    else
        return; //If there's an invalid direction

    //Check if its within grid bounds
    if (newJ >= 0 && newJ < 10 && newI >= 0 && newI < 10)
    {
        //Make sure space is empty and then save the ant into the space
        //Mark it as moved so there's no repeats
        if (grid[newJ][newI] == nullptr)
        {
            grid[newJ][newI] = grid[j][i];
            grid[newJ][newI]->moved();
            grid[j][i] = nullptr;
        }
    }
}
