import java.util.ArrayList;
import java.util.Random;


public class Deck
{
	/**
	 * Static RANDOM for random stuff.
	 */
	public static final Random RANDOM = new Random();
	
	private ArrayList<Card> deck;
	
	public Deck()
	{
		//Initialize variables
		deck = new ArrayList<>();
		
		//Create card set
		for(CardValue value : CardValue.values())
		{
			for(CardColor color : CardColor.values())
			{
				deck.add(new Card(color, value));
			}
		}
	}
	
	/**
	 * Merges card deck using 1000 generations of swapping random positions
	 */
	public void merge()
	{
		if(deck.size() == 0)
			return;
		
		for(int i = 0; i < 1000; i++)
		{
			//Select two indices to swap cards
			int first = RANDOM.nextInt(deck.size());
			int second = RANDOM.nextInt(deck.size());
			
			//If not same index, swap them
			if(first != second)
			{
				Card firstCard = deck.get(first);
				Card secondCard = deck.get(second);
				
				deck.set(second, firstCard);
				deck.set(first, secondCard);
			}
		}
		
		System.out.println("Karten gemischt!");
	}
	
	/**
	 * Prints cards in this deck
	 */
	public void print()
	{
		System.out.println("Kartendeck:");
		
		for(Card card : this.deck)
		{
			System.out.println(card.toString());
		}
	}
	
	public int size()
	{
		return this.deck.size();
	}
	
	/**
	 * Returns first card in deck and removes it from list
	 * @return
	 * @throws Exception
	 */
	public Card pop() throws Exception
	{
		if(deck.size() == 0)
			throw new Exception("No more cards left in deck!");
		
		Card first = deck.get(0);
		deck.remove(0);
		
		return first;
	}
	
	/**
	 * Checks if given card is still in deck
	 * @param card
	 * @return
	 */
	public boolean isInDeck(Card card)
	{
		for(Card c : deck)
		{
			if(c.equals(card))
				return true;
		}
		
		return false;
	}

}
