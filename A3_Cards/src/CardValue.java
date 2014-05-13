
public enum CardValue
{
	ASS,
	ZWEI,
	DREI,
	VIER,
	FÜNF,
	SECHS,
	SIEBEN,
	ACHT,
	NEUN,
	ZEHN,
	BUBE,
	DAME,
	KÖNIG;
	
	CardValue()
	{
		
	}
	
	@Override
	public String toString()
	{
		switch(this)
		{
		case ASS:
			return "Ass";
		case ZWEI:
			return "2";
		case DREI:
			return "3";
		case VIER:
			return "4";
		case FÜNF:
			return "5";
		case SECHS:
			return "6";
		case SIEBEN:
			return "7";
		case ACHT:
			return "8";
		case NEUN:
			return "9";
		case ZEHN:
			return "10";
		case BUBE:
			return "Bube";
		case DAME:
			return "Dame";
		case KÖNIG:
			return "König";
		default:
			return null;
		}
	}
}
