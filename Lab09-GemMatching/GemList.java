
public class GemList
{
	private Node head;
	private int size;
	private class Node {
		private Gem gem;
		private Node next;
		
		public Node(Gem gem, Node next)
		{
		    this.gem = gem;
		    this.next = next;
                }
        }
	int size()
	{
		return size;
	}
	void draw(double y)
	{
            Node current = head;
            int sum = 1;
            while (current != null)
            {
               current.gem.draw(sum/16.0 - 1.0/32, y);
               sum++;
               current = current.next;
            }
        }
        public String toString()
        {
            if (head == null)
            {
                return "";
            }
            String retV = "";
            Node current = head;
            while (current.next != null)
            {
                retV = retV + current.gem.getType() + " -> ";
                current = current.next;
            }
            return retV + current.gem.getType();
        }
        void insertBefore(Gem gem, int index)
        {
            Node current = head;
            if (index <= 0 || size == 0)
            {
                head = new Node(gem, head);
                size++;
                return;
            }
            else if (index >= size)
            {
                for (int i = 0; i < size - 1; i++)
                {
                    current = current.next;
                }
                Node temp = current.next;
                current.next = new Node(gem, temp);
                size++;
                return;
            }
            
            int count = index - 1;
            int i = 0;
            while (i < count)
            {
                current = current.next;
                i++;
            }
            Node temp = current.next;
            current.next = new Node(gem, temp);
            size++;
        }
        public int score()
        {
            int score = 0;
            int sub = 0;
            int count = 0;
            GemType type = null;
            Node current = head;
            
            while (current != null)
            {
                if (current.gem.getType() == type)
                {
                    count++;
                    sub += current.gem.getPoints();
                }
                else
                {
                    score += (sub * count);
                    count = 1;
                    sub = current.gem.getPoints();
                    type = current.gem.getType();
                }
                current = current.next;
            }
            return score + sub * count;
        }
	public static void main(String [] args)
	{
		GemList list = new GemList();
	        System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.9);		
		
		list.insertBefore(new Gem(GemType.BLUE, 10), 0);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.8);
		
		list.insertBefore(new Gem(GemType.BLUE, 20), 99);  //not a mistake, should still work
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.7);
		
		list.insertBefore(new Gem(GemType.ORANGE, 30), 1);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.6);
		
		list.insertBefore(new Gem(GemType.ORANGE, 10), 2);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.5);
		
		list.insertBefore(new Gem(GemType.ORANGE, 50), 3);
		System.out.println("\n" + list);
		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.4);
		
		list.insertBefore(new Gem(GemType.GREEN, 50), 2);
		System.out.println("\n" + list);		System.out.println("size = " + list.size() + ", score = " + list.score());
		list.draw(0.3);		
      }
}