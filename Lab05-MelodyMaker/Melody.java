import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Melody
{
    private Queue<Note> notes;
    
    Melody(Queue<Note> song)
    {
        this.notes = song;
    }
    double getTotalDuration()
    {
        Queue<Note> copy = new LinkedList<Note>(notes);
        boolean alRep = false;
        double duration = 0;
        while (!copy.isEmpty())
        {
            Note tempo = copy.poll();
            if(tempo.isRepeat() || alRep)
            {
                duration += 2 * tempo.getDuration();
            }
            else {
            duration += tempo.getDuration();
            }
            if (tempo.isRepeat())
            {
                alRep = !alRep;
            }
        }
        return duration;
    }
    
    public String toString()
    {
        String output = "";
        Queue<Note> copy = new LinkedList<Note>(notes);

        while (!copy.isEmpty())
        {
            Note tempo = copy.poll();
            
        }
        return output;
    }
    void changeTempo(double tempo)
    {
        for (int i = 0; i < notes.size(); i++)
        {
            Note temp = notes.poll();
            temp.setDuration(temp.getDuration() / tempo); 
            notes.offer(temp);
        }
    }
    void reverse()
    {
        Stack<Note> reverse = new Stack<Note>();
        
        while (notes.peek() != null)
        {
           reverse.push(notes.poll()); 
        }
        
        while (!reverse.isEmpty())
        {
            notes.offer(reverse.pop());
        }
    }
    void append(Melody other)
    {
        Queue<Note> copy = new LinkedList<Note>(other.getNotes());
        double duration = 0;
        while (!copy.isEmpty())
        {
            Note tempo = copy.poll();
            notes.offer(tempo);
        }
    }
    void play()
    {
       Queue<Note> repeat = new LinkedList<Note>();
       Queue<Note> copy = new LinkedList<Note>(notes);
       boolean repeated = false;
       
       while (!copy.isEmpty())
       {
           Note current = copy.poll();
           
           if (current.isRepeat())
           {
               repeated = !repeated;
           }
           current.play();
           if (repeated || current.isRepeat())
           {
               repeat.offer(current);
           }
           
           if (repeated == false && current.isRepeat())
           {
               while (repeat.peek() != null)
               {
                   current = repeat.poll();
                   current.play();
               }
           }
       }
       
    }
    Queue<Note> getNotes()
    {
        return this.notes;
    }
}