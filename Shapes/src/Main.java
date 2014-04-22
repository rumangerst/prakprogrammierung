
public class Main
{
	
	public static void checkRelation(Rectangle rect1, Rectangle rect2)
	{
		rect1.printCoordinates();
		rect2.printCoordinates();
		
		System.out.println("Result (1st - 2nd): " + rect1.getRelationTo(rect2));
		System.out.println("Result (2nd - 1st): " + rect2.getRelationTo(rect1));
	}

	public static void main(String[] args)
	{
		Rectangle rect1 = new Rectangle(100, 200, 300, 200);
		Rectangle contained = new Rectangle(110, 220, 50, 60);
		
		//test 'same'
		checkRelation(rect1, rect1);
		
		//test 'contained'
		checkRelation(rect1, contained);

	}

}
