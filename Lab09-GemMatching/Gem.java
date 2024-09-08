import java.awt.Font;

enum GemType {
    GREEN, BLUE, ORANGE; //define the different types of Gems, comma delimited
}

public class Gem 
{
	private GemType type;
	private int points;
	Gem()
	{
		int colorCho = 1 + (int)((Math.random()) * ((3-1) + 1));
		if (colorCho == 1)
		{
			type = GemType.GREEN;
		}
		else if (colorCho == 2)
		{
			type = GemType.BLUE;
		}
		else
		{
			type = GemType.ORANGE;
		}
		points = ((int)((Math.random() * 5))) * 10;
	}
	Gem(GemType type, int points)
	{
		this.type = type;
		this.points = points;
	}

	GemType getType()
	{
		return this.type;
	}
	int getPoints()
	{
		return this.points;
	}
	void draw(double x, double y)
	{
		String name = "";
		if (getType().equals(GemType.GREEN))
		{
			name = "gem_green.png";
		}
		else if (getType().equals(GemType.BLUE))
		{
			name = "gem_blue.png";
		}
		else
		{
			name = "gem_orange.png";
		}
                String points = "" + getPoints();
		StdDraw.picture(x,y,name);
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.setFont(new Font("SansSerif", Font.BOLD, 14));
		StdDraw.text(x,y,points);


	}

	@Override
	public String toString()
	{
		return ("" + this.type + " " + this.points);
	}
	/** Tester main method */
	public static void main(String [] args)
	{

		final int maxGems = 16;

		// Create a gem of each type
		Gem green  = new Gem(GemType.GREEN, 10);
		Gem blue   = new Gem(GemType.BLUE, 20);
		Gem orange = new Gem(GemType.ORANGE, 30);
		System.out.println(green  + ", " + green.getType()  + ", " + green.getPoints());
		System.out.println(blue   + ", " + blue.getType()   + ", " + blue.getPoints());
		System.out.println(orange + ", " + orange.getType() + ", " + orange.getPoints());
		green.draw(0.3, 0.7);
		blue.draw(0.5, 0.7);
		orange.draw(0.7, 0.7);

		// A row of random gems
		for (int i = 0; i < maxGems; i++)
		{
			Gem g = new Gem();
			g.draw(1.0 / maxGems * (i + 0.5), 0.5);
		}
	}
}