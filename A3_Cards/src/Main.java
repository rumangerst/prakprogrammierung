
public class Main
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		Deck deck = new Deck();
		deck.print();
		
		System.out.println("---");
		
		deck.merge();
		
		System.out.println("---");
		
		deck.print();
		
		System.out.println("---");
		
		System.out.println(deck.pop().toString() + " gezogen!");
		System.out.println(deck.pop().toString() + " gezogen!");
		System.out.println(deck.pop().toString() + " gezogen!");
		
		System.out.println("---");
		
		deck.merge();
		
		System.out.println("---");
		
		deck.print();
		
		System.out.println("---");
		
		Card tocheck = new Card(CardColor.HERZ, CardValue.KÖNIG);		
		System.out.printf("Karte %s noch im Stapel? : %b", tocheck.toString(), deck.isInDeck(tocheck));

	}

}
