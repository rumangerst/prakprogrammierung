package fastaV2;

@SuppressWarnings("serial")
public class IllegalHeaderException extends RuntimeException
{
	public IllegalHeaderException()
	{
		
	}
	
	public IllegalHeaderException(String msg)
	{
		super(msg);
	}
}
