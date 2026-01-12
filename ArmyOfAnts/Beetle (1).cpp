//Harshitha Mahesh
//dal267662

#include <string>
#include "Beetle.h"

//empty destructor
Beetle::~Beetle(){}


std::string Beetle::move(int northDist, int eastDist, int southDist, int westDist)
{
    //declaring a string variable dir to store the direction
    std::string dir;
    
    //the negative arguments indicate there is no ant in that direction
    //if there is no ant in a direction return an empty string 
    //the main will determine the distance the beetles moves
    if ((northDist < 0) && (eastDist < 0) && (southDist < 0) && (westDist < 0))
    {
        return "";
    }
    else
    {
        //comparing the arguments and finding the closest ant
        //If there are multiple closest ants: tiebreaker conditions and priorities 
        int shortestPath = 10;
        int numAdjAnts;
        dir = "N";
        if (northDist > 0)
        {
            if (shortestPath > (northDist/10))
            {
                shortestPath = northDist/10;
                dir = "N";
            }
        }
        if (eastDist > 0)
        {
            if (shortestPath > (eastDist/10))
            {
                shortestPath = eastDist/10;
                dir = "E";
            }
        }
        if (southDist > 0)
        {
            if (shortestPath > (southDist/10))
            {
                shortestPath = southDist/10;
                dir = "S";
            }
        }
        if (westDist > 0)
        {
            if (shortestPath > (westDist/10))
            {
                shortestPath = westDist/10;
                dir = "W";
            }
        }
        
        numAdjAnts = 0;
        
        if (shortestPath == westDist/10)
        {
            if (numAdjAnts < westDist%10)
            {
                numAdjAnts = westDist%10;
            }
        }
        if (shortestPath == southDist/10)
        {
            if (numAdjAnts < southDist%10)
            {
                numAdjAnts = southDist%10;
            }
        }
        if (shortestPath == eastDist/10)
        {
            if (numAdjAnts < eastDist%10)
            {
                numAdjAnts = eastDist%10;
            }
        }
        if (shortestPath == northDist/10)
        {
            if (numAdjAnts < northDist%10)
            {
                numAdjAnts = northDist%10;
            }
        }
        
        
        if ((shortestPath == westDist/10) && (numAdjAnts == westDist%10))
        {
            dir = "W";
        }
        if ((shortestPath == southDist/10) && (numAdjAnts == southDist%10))
        {
            dir = "S";
        }
        if ((shortestPath == eastDist/10) && (numAdjAnts == eastDist%10))
        {
            dir = "E";
        }
        if ((shortestPath == northDist/10) && (numAdjAnts == northDist%10))
        {
            dir = "N";
        }
        return dir;
    }
}

//breed method takes in four boolean values and determines which direction the newly born beetle has to be placed
std::string Beetle::breed(bool N, bool E, bool S, bool W)
{
    if (N)
    {
        return "N";
    }
    else if (E)
    {
        return "E";
    }
    else if (S)
    {
        return "S";
    }
    else if (W)
    {
        return "W";
    }
    else
    {
        return "";
    }
}

bool Beetle::starve(int num){
        if (num == 5){return true;}
        return false;
}