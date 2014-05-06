import java.security.InvalidParameterException;

public class Rectangle extends Figure implements MobileObject
{
	public float width;
	public float height;

	/**
	 * Constructs default rectangle @ 0,0 with width=0 and height=0
	 */
	public Rectangle()
	{
		super();

		width = 0;
		height = 0;
	}

	/**
	 * Constructs a new rectangle with given parameters
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public Rectangle(float x, float y, float width, float height)
	{
		super(x, y);
		
		if(width < 0 || height < 0)
		{
			throw new InvalidParameterException("No negative width or height allowed!");
		}

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

	/**
	 * Print all relevant data of this rectangle
	 */
	@Override
	public void printCoordinates()
	{
		System.out.printf("(%f,  %f, w:%f, h:%f)\n", this.getX(), this.getY(),
				this.getWidth(), this.getHeight());
	}

	/**
	 * Move rectangle to other position x,y
	 */
	@Override
	public void move(float x, float y)
	{
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Increases rectangle by adding value to width and height
	 */
	@Override
	public void increase(float value) throws InvalidParameterException
	{
		if (value < 0)
			throw new InvalidParameterException("No negative values allowed!");

		this.width += value;
		this.height += value;
	}

	/**
	 * Shrinks the rectangle by subtracting value from width and height; will set width/height to zero if value is too big
	 */
	@Override
	public void decrease(float value) throws InvalidParameterException
	{
		if (value < 0)
		{
			throw new InvalidParameterException("No negative values allowed!");
		}
		
		if(this.getWidth() - value < 0 || this.getHeight() - value < 0)
		{
			throw new InvalidParameterException("Cannot decrease that much! Rectangle would have negative size!");
		}

		this.width -= value;
		this.height -= value;
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
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean containsPoint(float x, float y)
	{
		return x >= getX() && x <= getRight() && y >= getY()
				&& y <= getBottom();
	}

	/**
	 * Intersects rectangles
	 * 
	 * @param other
	 * @return Returns Rectangle if intersects, null if disjoint
	 */
	public Rectangle getIntersectionBetween(Rectangle other)
	{
		float x = Math.max(this.getX(), other.getX());
		float y = Math.max(this.getY(), other.getY());
		float right = Math.min(this.getRight(), other.getRight());
		float bottom = Math.min(this.getBottom(), other.getBottom());

		if (x > right || y > bottom) // cannot be!
			return null;

		return new Rectangle(x, y, right - x, bottom - y);
	}

	/**
	 * Checks if the two rectangles are equal
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(Rectangle other)
	{
		return this.getX() == other.getX() && this.getY() == other.getY()
				&& this.getWidth() == other.getWidth()
				&& this.getHeight() == other.getHeight();
	}

	/**
	 * Get relation of this rectangle to the other rectangle
	 * @param other rectangle
	 * @return
	 */
	public RectangleRelation getRelationTo(Rectangle other)
	{
		//Intersect rectangles and check it
		Rectangle intersected = this.getIntersectionBetween(other);

		// Check if contained (Check here, intersected == other if contained! (Chooses bigger rectangle) )
		/*if (getY() > other.getY() && getRight() < other.getRight()
				&& getBottom() < other.getBottom())
			return RectangleRelation.contained;*/

		// No intersection => disjoint
		if (intersected == null)
			return RectangleRelation.disjoint;
		
		//Check if contained	
		//All points of this rectangle should be in intersection, BUT not all points of other rectangle should be in intersection
		if(this.equals(intersected) && !other.equals(intersected))
			return RectangleRelation.contained;

		// Check if same (by using equals)
		if (this.equals(intersected))
		{
			return RectangleRelation.same;
		}

		// Check if touching (width and height = 0)
		if (intersected.getWidth() == 0 && intersected.getHeight() == 0)
			return RectangleRelation.touching;

		// Check if aligned (width OR height = 0)
		if (intersected.getWidth() == 0 || intersected.getHeight() == 0)
			return RectangleRelation.aligned;

		return RectangleRelation.interselecting;
	}

}
