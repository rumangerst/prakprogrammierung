
public abstract class Figure
{
	protected float x;
	protected float y;
	
	public Figure()
	{
		x = 0;
		y = 0;
	}
	
	public Figure(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	public void printCoordinates()
	{
		System.out.printf("(%f, %f)\n", x, y);
	}
}
