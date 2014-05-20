
public class DIAarray implements DynIntArray
{
	/**
	 * Main data array, will always be with 2^n elements, increasing efficiency of "add" operation,
	 * but using more space than needed
	 */
	private int[] data;
	
	/**
	 * The for user visible size of this array:
	 * 
	 * size <= length(data) is always true
	 */
	private int size;
	
	public DIAarray()
	{
		//Initialize data array with 2^0 data container
		data = new int[1];
		
		//Set size to zero, because no item is in array
		size = 0;
	}
	
	/**
	 * Will increase data array size from 2^n to 2^(n+1) and preserves all data
	 */
	private void increaseArray()
	{
		int newArraySize = data.length * 2;
		int[] newDataArray = new int[newArraySize];
		
		//Copy all data from old array to new array
		for(int i = 0; i < size; i++)
		{
			newDataArray[i] = data[i];
		}
		
		//Set new array as current array
		this.data = newDataArray;
	}

	@Override
	public void add(int e)
	{
		//Check if data array can hold element
		if(data.length == this.size)
		{
			increaseArray();
		}
		
		//Add new element to array
		data[this.size] = e;
		size++;

	}

	@Override
	public void setElementAt(int i, int e)
	{		
		if(i >= this.size || i < 0)
		{
			return;
		}
		
		data[i] = e;
	}

	@Override
	public int getElementAt(int i)
	{
		if(i >= this.size || i < 0)
		{
			return 0;
		}
		
		return data[i];
	}

	@Override
	public int getElementCount()
	{		
		return this.size;
	}

	@Override
	public void print()
	{
		System.out.print("[");
		
		for(int i = 0; i < this.getElementCount();i++)
		{
			if(i < this.getElementCount() - 1)
			{
				System.out.printf("%d, ", this.getElementAt(i));
			}
			else
			{
				System.out.printf("%d", this.getElementAt(i));
			}
		}
		
		System.out.print("]\n");
	}

}
