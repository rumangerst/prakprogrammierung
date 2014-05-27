import java.util.LinkedList;


public class BuecherRegal
{
	private String signature;
	private LinkedList<Buch> books;
	
	public BuecherRegal(String signature)
	{
		this.signature = signature;
		this.books = new LinkedList<>();
	}
	
	public String getSignature()
	{
		return signature;
	}
	
	public void store(Buch book)
	{
		
	}
	
	public Buch fetch(String title, String author, int year)
	{
		
	}
	
	public Buch[] fetchAll(int year)
	{
		
	}
	
	public void printContents()
	{
		System.out.println("----- " + getSignature() + " -----");
	}
}
