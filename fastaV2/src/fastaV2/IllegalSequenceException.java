package fastaV2;

@SuppressWarnings("serial")
public class IllegalSequenceException extends RuntimeException
{
	public IllegalSequenceException()
	{
		
	}
	
	public IllegalSequenceException(String msg)
	{
		super(msg);
	}
}
