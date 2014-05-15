
public class DIAlistNode
{
	private int value;
	private DIAlistNode next;
	
	private int nextElementCount;
	
	public DIAlistNode()
	{
		value = 0;
		nextElementCount = 0;
		next = null;
	}
	
	/**
	 * Initialize new node with value and no next element.
	 * @param value
	 */
	public DIAlistNode(int value)
	{
		this.value = value;
		this.next = null;
	}
	
	/**
	 * Returns if this node has a next element
	 * @return
	 */
	public boolean hasNext()
	{
		return next != null;
	}
	
	/**
	 * Add value to list, created by this node
	 * @param value
	 */
	public void add(int value)
	{
		//Increase count of next elements by 1
		this.nextElementCount++;
		
		if(!hasNext())
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
	 * @return
	 */
	public int countNextElements()
	{
		return this.nextElementCount;
	}
	
	/**
	 * Gets element i, starting from this node
	 * @param i
	 * @return
	 */
	public int get(int index) throws ArrayIndexOutOfBoundsException
	{
		if(index == 0)
		{
			return this.value;
		}
		else
		{
			if(!hasNext())
				throw new ArrayIndexOutOfBoundsException();
			
			return next.get(index - 1); //Call for next with index - 1
		}
		
	}
	
	public void set(int index, int value) throws ArrayIndexOutOfBoundsException
	{
		if(index == 0)
		{
			this.value = value;
		}
		else
		{
			if(!hasNext())
				throw new ArrayIndexOutOfBoundsException();
			
			next.set(index - 1, value); //Call for next with index - 1
		}
	}
}
