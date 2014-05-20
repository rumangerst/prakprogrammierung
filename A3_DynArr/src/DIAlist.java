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
		if (data == null)
			data = new DIAlistNode(e, null);
		else
		{
			try
			{
				data.add(e);
			}
			catch (Exception e1)
			{
				System.out.println("Cannot add value to list! Must use add @ root node!");
			}
		}

	}

	@Override
	public void setElementAt(int i, int e)
	{
		if(i < 0)
			return;
		if (data == null)
			return;
		else
			data.set(i, e);
	}

	@Override
	public int getElementAt(int i)
	{
		if(i < 0)
			return 0;
		if (data == null)
			return 0;

		return data.get(i);
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

		System.out.print("]\n");
	}

}
