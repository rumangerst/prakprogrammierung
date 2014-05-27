
public class Buch
{
	private String title;
	private String author;
	private int year;
	
	public Buch(String title, String author, int year)
	{
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public String getTitle()
	{
		return title;
	}

	public String getAuthor()
	{
		return author;
	}
	
	public int getYear()
	{
		return year;
	}
	
	public void printData()
	{
		System.out.printf("%s: %s (%d)\n", getAuthor(), getTitle(), getYear());
	}
	
}
