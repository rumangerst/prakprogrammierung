import java.security.InvalidParameterException;

public class Rectangle extends Figure implements MobileObject
{
	public float width;
	public float height;

	public Rectangle()
	{
		super();

		width = 0;
		height = 0;
	}

	public Rectangle(float x, float y, float width, float height)
	{
		super(x, y);

		this.width = width;
		this.height = height;
	}

	public float getWidth()
	{
		return width;
	}

	public float getHeight()
	{
		return height;
	}

	@Override
	public void printCoordinates()
	{
		System.out.printf("(%f,  %f, w:%f, h:%f)\n", this.getX(), this.getY(),
				this.getWidth(), this.getHeight());
	}

	@Override
	public void move(float x, float y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void increase(float value) throws InvalidParameterException
	{
		if (value < 0)
			throw new InvalidParameterException("No negative values allowed!");

		this.width += value;
		this.height += value;
	}

	/**
	 * Shrinks the rectangle; will set width/height to zero if value is too big
	 */
	@Override
	public void decrease(float value) throws InvalidParameterException
	{
		if (value < 0)
			throw new InvalidParameterException("No negative values allowed!");

		this.width -= value;
		this.height -= value;

		if (this.width < 0)
			this.width = 0;
		if (this.height < 0)
			this.height = 0;
	}

	/**
	 * Returns right (x + width)
	 * 
	 * @return
	 */
	public float getRight()
	{
		return getX() + getWidth();
	}

	/**
	 * Returns bottom (y + height)
	 * 
	 * @return
	 */
	public float getBottom()
	{
		return getY() + getHeight();
	}
	
	/**
	 * Returns if the given point (x,y) is in this rectangle
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean containsPoint(float x, float y)
	{
		return x >= getX() && x <= getRight() && y >= getY() && y <= getBottom();
	}

	public RectangleRelation getRelationTo(Rectangle other)
	{
		//Check for 'same', 'touch' and 'aligned' by couting equal points
		{
			int equalPoints = 0;
			
			equalPoints += getX() == other.getX() ? 1 : 0;
			equalPoints += getY() == other.getY() ? 1 : 0;
			equalPoints += getRight() == other.getRight() ? 1 : 0;
			equalPoints += getBottom() == other.getBottom() ? 1 : 0;
			
			switch(equalPoints)
			{
			case 4:
				return RectangleRelation.same;
			case 2:
				return RectangleRelation.aligned;
			case 1:
				return RectangleRelation.touching;
			case 3: //nononononono!
				return RectangleRelation.ERROR;
			}
		}
		
		
		
		// Check if contained
		if (getX() > other.getX()  && getY() > other.getY() && getRight() < other.getRight() && getBottom() < other.getBottom())
			return RectangleRelation.contained;
		
		//Check if interselects
		if(other.containsPoint(getX(), getY()) || other.containsPoint(getRight(), getY()) || other.containsPoint(getX(), getBottom()) || other.containsPoint(getRight(), getBottom()))
			return RectangleRelation.interselecting;
		if(this.containsPoint(other.getX(), other.getY()) || this.containsPoint(other.getRight(), other.getY()) || this.containsPoint(other.getX(), other.getBottom()) || this.containsPoint(other.getRight(), other.getBottom()))
			return RectangleRelation.interselecting;

		return RectangleRelation.disjoint; //disjoint is negation of interselection
	}

}
