import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;

public class BoggleSolver
{
	// Initializes the data structure using the given array of strings as the dictionary.
	// (You can assume each word in the dictionary contains only the uppercase letters A - Z.)
	private Set<String> dictionary;
	private int count = 0;
	private Check check;
	public BoggleSolver(String dictionaryName)
	{
		dictionary = new HashSet<>();
		String next;
		check = new Check();
		try {
		    BufferedReader in = new BufferedReader(new FileReader(dictionaryName));
		    next = in.readLine();
		    while (next != null)
		    {
		        dictionary.add(next);
		        check.addBranch(next);
		        next = in.readLine();
		        
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
	}

	// Returns the set of all valid words in the given Boggle board, as an Iterable object
	public Iterable<String> getAllValidWords(BoggleBoard board)
	{
		return getAllValidWords(board, 100000, board.cols() * board.rows());
	}
	public Iterable<String> getAllValidWords(BoggleBoard board, int recurCount, int maxPos)
	{
	   List<int[]> places = new ArrayList<>();
	   for (int y = 0; y < board.rows(); y++)
	   {
	       for (int x = 0; x < board.cols(); x++)
	       {
	           places.add(new int[] {x,y});
               }
           }
           for (int i = 0; i < board.rows() * board.cols() - maxPos; i++)
           {
               places.remove((int) (Math.random() * places.size()));
           }
           Set<String> validWords = new HashSet<>();
           for (int[] location : places)
           {
               count = 0;
               boolean[][] check = new boolean[board.rows()][board.cols()];
               getAllValidWords(board, location[0], location[1], "", validWords, check, recurCount / maxPos);
           }
           return validWords;
        }
        
        public static int[][] directions = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
   
        public void getAllValidWords(BoggleBoard board, int x, int y, String word, Set<String> validWords, boolean visited[][], int maxRecurCount) 
        {
            if (count >= maxRecurCount)
               return;
               
            if (dictionary.contains(word) && word.length() > 2)
            validWords.add(word);
            
            if (!check.isValidPrefix(word))
               return;
               
            for (int[] direction : directions) 
            {
                
            if (!inBounds(visited,board,x + direction[0],y + direction[1]))
               continue;
               
             visited[y + direction[1]][x + direction[0]] = true;
             
             count++;
             
             getAllValidWords(board,x + direction[0], y + direction[1], word + board.getLetter(y + direction[1],x + direction[0]) + (board.getLetter(y + direction[1],x + direction[0]) == 'Q' ? "U":""), validWords,visited, maxRecurCount);
             
             visited[y + direction[1]][x + direction[0]] = false;
            }
   }
   
    public boolean inBounds(boolean[][] seen, BoggleBoard board, int x, int y) {
      return x >= 0 && x < board.cols() && y >= 0 && y < board.rows() && !seen[y][x];
   }
   


	// Returns the score of the given word if it is in the dictionary, zero otherwise.
	// (You can assume the word contains only the uppercase letters A - Z.)
	public int scoreOf(String word)
	{
		if (word.length() < 3 || !dictionary.contains(word))
                return 0;
                
                if (word.length() < 5)
                return 1;
                
                if (word.length() < 6)
                return 2;
                
                if (word.length() < 7)
                return 3;
                
                if (word.length() < 8)
                return 5;
                
                return 11;
	}

	public static void main(String[] args) {
		System.out.println("WORKING");

		final String PATH   = "data/";
		BoggleBoard  board  = new BoggleBoard(PATH + "board-q.txt");
		BoggleSolver solver = new BoggleSolver(PATH + "dictionary-algs4.txt");

		int totalPoints = 0;

		for (String s : solver.getAllValidWords(board)) {
			System.out.println(s + ", points = " + solver.scoreOf(s));
			totalPoints += solver.scoreOf(s);
		}

		System.out.println("Score = " + totalPoints); //should print 84

		//new BoggleGame(4, 4);
	}

}
