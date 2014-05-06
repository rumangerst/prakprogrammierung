
public abstract class Figure
{
	private float x;
	private float y;
	
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
	
	protected void setX(float value)
	{
		this.x = value;
	}
	
	protected void setY(float value)
	{
		this.y = value;
	}
	
	public void printCoordinates()
	{
		System.out.printf("(%f, %f)\n", x, y);
	}
}
