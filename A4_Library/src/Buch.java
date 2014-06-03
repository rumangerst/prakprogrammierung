
public class Buch implements Comparable
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
		System.out.println(this.toString());
	}

	@Override
	public int compareTo(Object obj)
	{
		Buch book = (Buch)obj;
		
		return book.getAuthor().compareTo(this.getAuthor());
	}
	
	@Override
	public boolean equals(Object obj)
	{
		Buch book = (Buch)obj;
		
		return book.getAuthor().equals(this.getAuthor()) && book.getTitle().equals(this.getTitle()) && book.getYear() == this.getYear();
	}
	
	@Override
	public String toString()
	{		
		return String.format("%s: %s (%d)", getAuthor(), getTitle(), getYear());
	}
	
}
