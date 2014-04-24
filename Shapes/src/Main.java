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
		Rectangle aligned = new Rectangle(400, 200, 50, 50);
		Rectangle touching = new Rectangle(400, 150, 50, 50);
		Rectangle disjoint = new Rectangle(1000, 1000, 10, 10);

		// test 'same'
		System.out.println("---- Test 'same'");
		checkRelation(rect1, rect1);

		// test 'contained'/'interselect'
		System.out.println("---- Test 'contained'");
		checkRelation(rect1, contained);

		// test 'aligned'
		System.out.println("---- Test 'aligned'");
		checkRelation(rect1, aligned);

		// test 'touching'
		System.out.println("---- Test 'touching'");
		checkRelation(rect1, touching);

		// test 'disjoint'
		System.out.println("---- Test 'disjoint'");
		checkRelation(rect1, disjoint);

	}

}
