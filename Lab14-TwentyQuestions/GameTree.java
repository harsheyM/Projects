import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

/**
 * A model for the game of 20 questions
 *
 * @author Rick Mercer
 */

public class GameTree
{
	QuesNode root = null;
	QuesNode cur = null;
	String file;
	
	private boolean questionMark(String phrase)
	{
		if (phrase.endsWith("?"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 * Constructor needed to create the game.
	 *
	 * @param fileName
	 *          this is the name of the file we need to import the game questions
	 *          and answers from.
	 */
	public GameTree(String fileName)
	{
		try {
                  Scanner console = new Scanner(new File(fileName));
                  root = new QuesNode(console.nextLine());
                  
                  cur = root;
		  GameTreeHelper(console, root);

		  
		}
		catch (IOException e) { System.out.print("Can't find file!"); }
                
		file = fileName;
		
	}
    private void GameTreeHelper(Scanner console, QuesNode node)
	{
		
		if (node.isQuestion)
		{
			String temp = console.nextLine();
			if (temp == null)
			{
			    return;
                        }
		        node.left = new QuesNode(temp);
			GameTreeHelper(console, node.left);
			temp = console.nextLine();
			if (temp == null) { return; }
			node.right = new QuesNode(temp);
			GameTreeHelper(console, node.right);
			
		}
		

	}
	/*
	 * Add a new question and answer to the currentNode. If the current node has
	 * the answer chicken, theGame.add("Does it swim?", "goose"); should change
	 * that node like this:
	 */
	// -----------Feathers?-----------------Feathers?------
	// -------------/----\------------------/-------\------
	// ------- chicken  horse-----Does it swim?-----horse--
	// -----------------------------/------\---------------
	// --------------------------goose--chicken-----------
	/**
	 * @param newQ
	 *          The question to add where the old answer was.
	 * @param newA
	 *          The new Yes answer for the new question.
	 */

	public void add(String newQ, String newA)
	{
		cur.left = new QuesNode(newA);
		cur.right = new QuesNode(cur.word);
		cur.word = newQ;
	}


	/**
	 * True if getCurrent() returns an answer rather than a question.
	 *
	 * @return False if the current node is an internal node rather than an answer
	 *         at a leaf.
	 */
	public boolean foundAnswer()
	{
		return !cur.isQuestion;
	}

	/**
	 * Return the data for the current node, which could be a question or an
	 * answer.  Current will change based on the users progress through the game.
	 *
	 * @return The current question or answer.
	 */
	public String getCurrent()
	{
		return cur.word;
	}

	/**
	 * Ask the game to update the current node by going left for Choice.yes or
	 * right for Choice.no Example code: theGame.playerSelected(Choice.Yes);
	 *
	 * @param yesOrNo
	 */
	public void playerSelected(Choice yesOrNo)
	{
		if (yesOrNo.equals(Choice.Yes))
		{
           cur = cur.left;
		}
		else if (yesOrNo.equals(Choice.No))
		{
			cur = cur.right;
		}
	}

	/**
	 * Begin a game at the root of the tree. getCurrent should return the question
	 * at the root of this GameTree.
	 */
	public void reStart()
	{
		cur = root;
	}

	
	StringBuilder str = new StringBuilder();

	@Override
	public String toString()
	{
		return toStringHelper(root, 0);

	}
	private String toStringHelper(QuesNode node, int level)
	{
		if (node == null)
		{
			return "";
		}
		String str = "";
		for (int i = 0; i < level; i++)
		{
		    str += "_ ";
                }
                return (node.right != null ? toStringHelper(node.right, level + 1) + "\n" : "") + str + node.word + 
                (node.left != null ? "\n" + toStringHelper(node.left, level + 1) : "");
		
	}

	/**
	 * Overwrite the old file for this gameTree with the current state that may
	 * have new questions added since the game started.
	 *
	 */
	public void saveGame()
	{
		String str = root.word + saveGameHelper(root.left) + saveGameHelper(root.right);
		try {
		    FileWriter fileWriter = new FileWriter(file);
		    fileWriter.write(str);
		    fileWriter.close();
               }
               catch (IOException e) {
                   e.printStackTrace();
	       }
	}
	public String saveGameHelper(QuesNode node)
	{
	    if (node == null)
	    {
	        return "";
            }
            return "\n" + node.word + saveGameHelper(node.left) + saveGameHelper(node.right);
 }
	private class QuesNode
	{
		String word;
		QuesNode left, right;
                boolean isQuestion;
		public QuesNode(String word)
		{
			this.word = word;
			left = right = null;
			isQuestion = questionMark(word);
		}
	}
}