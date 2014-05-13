public class Card
{
	private CardColor color;
	private CardValue value;
	
	public Card()
	{
		color = CardColor.KREUZ;
		value = CardValue.ASS;
	}
	
	public Card(CardColor color, CardValue value)
	{
		this.color = color;
		this.value = value;
	}
	
	public CardColor getColor()
	{
		return this.color;
	}
	
	public CardValue getValue()
	{
		return this.value;
	}
	
	@Override
	public String toString()
	{
		return String.format("%s %s", this.color.toString(), this.value.toString());
	}
	
	@Override
	public boolean equals(Object other)
	{
		if(!(other instanceof Card))
			return false;
		
		Card otherCard = (Card)other;
		
		return otherCard.color == this.color && otherCard.value == this.value;
	}
}
