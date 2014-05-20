public class DIAlistNode
{
	private int value;
	private DIAlistNode next;

	public DIAlistNode()
	{
		value = 0;
		next = null;
	}

	/**
	 * Initialize new node with value and no next element.
	 * 
	 * @param value
	 */
	public DIAlistNode(int value)
	{
		this.value = value;
		this.next = null;
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
	 */
	public void add(int value)
	{	
		if (!hasNext())
		{
			next = new DIAlistNode(value);
		}
		else
		{
			next.add(value);
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
	 * 
	 * @return
	 */
	public int countNextElements()
	{
		if(next == null)
			return 0;
		else
		{
			return 1 + next.countNextElements();
		}		
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
