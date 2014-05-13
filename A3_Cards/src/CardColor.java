
public enum CardColor
{
	KREUZ,
	HERZ,
	PIK,
	KARO;
	
	private CardColor()
	{
		
	}
	
	@Override
	public String toString()
	{
		switch(this)
		{
		case HERZ:
			return "Herz";
		case KARO:
			return "Karo";
		case KREUZ:
			return "Kreuz";
		case PIK:
			return "Pik";
		default:
			return null;
		}
	}
}
