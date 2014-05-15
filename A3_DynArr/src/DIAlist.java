public class DIAlist implements DynIntArray
{
	private DIAlistNode data;

	public DIAlist()
	{
		this.data = null;
	}

	@Override
	public void add(int e)
	{
		if(data == null)
			data = new DIAlistNode(e);
		else
			data.add(e);

	}

	@Override
	public void setElementAt(int i, int e)
	{
		if(data == null)
			throw new ArrayIndexOutOfBoundsException();
		else
			data.set(i, e);
	}

	@Override
	public int getElementAt(int i)
	{
		if(data == null)
			return 0;
		
		try
		{
			return data.get(i);
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			return 0;
		}
	}

	@Override
	public int getElementCount()
	{
		if (data == null)
			return 0;
		return 1 + data.countNextElements(); // 1 is root node itself!!
	}

	@Override
	public void print()
	{
		System.out.print("[");

		for (int i = 0; i < this.getElementCount(); i++)
		{
			if (i < this.getElementCount() - 1)
			{
				System.out.printf("%d, ", this.getElementAt(i));
			}
			else
			{
				System.out.printf("%d", this.getElementAt(i));
			}
		}

		System.out.print("]");
	}

}
