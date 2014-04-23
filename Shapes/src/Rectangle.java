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
	 * @return Returns Rectangle if interselects, null if disjoint
	 */
	public Rectangle intersectWith(Rectangle other)
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

	public RectangleRelation getRelationTo(Rectangle other)
	{
		Rectangle intersected = this.intersectWith(other);

		// No intersection => disjoint
		if (intersected == null)
			return RectangleRelation.disjoint;

		// Check if same (by using equals)
		if (this.equals(intersected))
			return RectangleRelation.same;

		// Check if contained
		if (getY() > other.getY() && getRight() < other.getRight()
				&& getBottom() < other.getBottom())
			return RectangleRelation.contained;

		// Check if touching (width and height = 0)
		if (intersected.getWidth() == 0 && intersected.getHeight() == 0)
			return RectangleRelation.touching;

		// Check if aligned (width OR height = 0)
		if (intersected.getWidth() == 0 || intersected.getHeight() == 0)
			return RectangleRelation.aligned;

		return RectangleRelation.interselecting;
	}

}
