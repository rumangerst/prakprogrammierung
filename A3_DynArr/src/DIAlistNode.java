public class DIAlistNode
{
	private int value;
	private DIAlistNode next;
	private DIAlistNode previous;

	private int nextElementCount;

	public DIAlistNode()
	{
		value = 0;
		nextElementCount = 0;
		next = null;
	}

	/**
	 * Initialize new node with value and no next element.
	 * 
	 * @param value
	 */
	public DIAlistNode(int value, DIAlistNode previous)
	{
		this.value = value;
		this.next = null;
		this.previous = previous;
	}

	/**
	 * Returns if this node has a next element
	 * 
	 * @return
	 */
	public boolean hasNext()
	{
		return next != null;
	}

	/**
	 * Add value to list, created by this node
	 * 
	 * @param value
	 * @throws Exception
	 */
	public void add(int value) throws Exception
	{
		// Check if it's a root element
		if (previous != null)
			throw new Exception("Can only add elements to root node!");

		add_internal(value);
	}

	/**
	 * Internal add function to encapsulate list functions
	 * 
	 * @param value
	 */
	private void add_internal(int value)
	{
		// Increase count of next elements by 1
		this.nextElementCount++;

		if (!hasNext())
		{
			next = new DIAlistNode(value, this);
		}
		else
		{
			next.add_internal(value);
		}
	}

	public DIAlistNode getNext()
	{
		return this.next;
	}

	public int getValue()
	{
		return this.value;
	}

	/**
	 * Returns count of all elements after this node
	 * 
	 * @return
	 */
	public int countNextElements()
	{
		return this.nextElementCount;
	}

	/**
	 * Gets element i, starting from this node
	 * 
	 * @param i
	 * @return
	 */
	public int get(int index)
	{
		if(index < 0)
			return 0;
		
		if (index == 0)
		{
			return this.value;
		}
		else
		{
			if (!hasNext())
				return 0; // Return 0

			return next.get(index - 1); // Call for next with index - 1
		}

	}

	public void set(int index, int value)
	{
		if(index < 0)
			return;
		
		if (index == 0)
		{
			this.value = value;
		}
		else
		{
			if (!hasNext())
				return;

			next.set(index - 1, value); // Call for next with index - 1
		}
	}
}
