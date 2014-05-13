import java.util.ArrayList;
import java.util.Random;


public class Deck
{
	public static final Random RANDOM = new Random();
	
	private ArrayList<Card> deck;
	
	public Deck()
	{
		//Initialize variables
		deck = new ArrayList<>();
		
		//Create card set
		for(CardColor color : CardColor.values())
		{
			for(CardValue value : CardValue.values())
			{
				deck.add(new Card(color, value));
			}
		}
	}
	
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
	
	public Card pop() throws Exception
	{
		if(deck.size() == 0)
			throw new Exception("No more cards left in deck!");
		
		Card first = deck.get(0);
		deck.remove(0);
		
		return first;
	}
	
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
