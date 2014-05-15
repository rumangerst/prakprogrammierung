


public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		FileObserver observer = new FileObserver("./work/TestFile.txt");
		
		try
		{
			observer.observe();
		} 
		catch (Exception e)
		{
			System.out.println("Fehler: " + e.getMessage());
		} 
	}

}
