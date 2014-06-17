import java.util.ArrayList;


public class BuecherRegal
{
	private String signature;
	private ArrayList<Buch> books;
	
	public BuecherRegal(String signature)
	{
		this.signature = signature;
		this.books = new ArrayList<>();
	}
	
	public String getSignature()
	{
		return signature;
	}
	
	/**
	 * Buch in Bücherregal ablegen und sortieren
	 * @param book
	 */
	public void store(Buch book)
	{
		if(books.isEmpty())
		{
			books.add(book);
		}
		else
		{
			for(int i = 0; i < books.size();i++)
			{
				if(books.get(i).compareTo(book) < 0)
				{
					books.add(i, book);
					System.out.println("Buch zu " + signature + " hinzugefügt: " + book.toString());
					return;
				}
			}
			
			books.add(book);
		}			
		
		System.out.println("Buch zu " + signature + " hinzugefügt: " + book.toString());
	}
	
	/**
	 * Holt ein Buch aus dem Bücherregal
	 * @param title
	 * @param author
	 * @param year
	 * @return
	 */
	public Buch fetch(String title, String author, int year)
	{
		Buch book = null;
		
		for(Buch b : books)
		{
			if(b.getTitle().equals(title) && b.getAuthor().equals(author) && b.getYear() == year)
			{
				book = b;
				break;
			}
		}
		
		if(book != null)
		{
			books.remove(book);
			System.out.println("Buch von " + signature + " herausgenommen: " + book.toString());
		}
		else
		{
			System.out.println("Buch konnte in " + signature + " nicht gefunden werden!");
		}
		
		
		return book;
	}
	
	/**
	 * Holt alle Bücher mit dem Erschienungsjahr aus dem Regal
	 * @param year
	 * @return
	 */
	public ArrayList<Buch> fetchAll(int year)
	{
		ArrayList<Buch> result = new ArrayList<>();
		
		System.out.println("Folgende Bücher aus " + signature + " herausgenommen:");
		
		for(Buch b: books)
		{
			if(b.getYear() == year)
			{
				result.add(b);
				System.out.println("- " + b.toString());
			}
		}
		
		books.removeAll(result);
		
		return result;
	}
	
	public void printContents()
	{
		System.out.println();
		System.out.println("----- " + getSignature() + " -----");
		
		for(Buch b : books)
		{
			System.out.println("| " + b.toString());
		}
		
		System.out.println("------" + getSignature().replaceAll(".", "-") + "------");
		System.out.println();
	}
}
