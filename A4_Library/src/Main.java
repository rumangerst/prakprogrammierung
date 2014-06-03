
public class Main
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		BuecherRegal regal = new BuecherRegal("Bücher");
		
		regal.store(new Buch("Faust", "Goethe", 1808));
		regal.store(new Buch("Nathan der Weise", "Lessing", 1779));
		regal.store(new Buch("Faust II", "Goethe", 1832));
		regal.store(new Buch("Der Sandmann", "Hoffmann", 1816));
		
		regal.printContents();
		
		regal.fetchAll(1832);
		
		regal.printContents();
		
		regal.fetch("Faust", "Goethe", 1808);
		
		regal.printContents();
		
		regal.fetch("Der Sandmann", "Hoffmann", 1816);
		
		regal.printContents();

	}

}
