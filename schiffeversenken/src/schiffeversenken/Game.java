/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schiffeversenken;

import java.awt.Point;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Ruman
 */
public class Game
{

	static Random RANDOM = new Random();
	static final boolean GAME_OUTPUT_SLEEP = false;
	
	//Some directions
	static final int DIR_EAST = 0;
	static final int DIR_SOUTH = 1;
	static final int DIR_WEST = 2;
	static final int DIR_NORTH = 3;

	// Field arrays
	int[][] field_player1; // Ships of player 1, value is ship id
	int[][] field_player2; // Ships of player 2, value is ship id

	int[][] shots_player1; // Moves of player 1, 0=not shot, 1=not hit, 2=hit
	int[][] shots_player2; // Moves of player 2, 0=not shot, 1=not hit, 2=hit

	// Player ship variables
	int[] shipindices;

	// Ship placement list
	int[] placedships;

	public Game()
	{
		field_player1 = new int[10][10];
		field_player2 = new int[10][10];
		shots_player1 = new int[10][10];
		shots_player2 = new int[10][10];

		shipindices = new int[] { 1, 1 };

		placedships = new int[] // initialize ship list for players; may add
								// more
		{ 5, 4, 3, 2 };
	}

	/**
	 * Starts a 2P (Human-Human) game
	 * 
	 * @param player1ai
	 * @param player2ai
	 */
	public void startGame(boolean player1ai, boolean player2ai)
	{
		Scanner scanner = new Scanner(System.in);

		// ///////////////////////////////////////////////////////////// Phase
		// 1: Adding ships
		playerAddShips(scanner, player1ai, "Spieler 1", 0, field_player1);
		playerAddShips(scanner, player2ai, "Spieler 2", 1, field_player2);

		// ///////////////////////////////////////////////////////////// Phase
		// 2: Shooting ships
		boolean isplayer1 = Game.RANDOM.nextBoolean(); // randomize

		if (isplayer1)
		{
			System.out.println("***** Spieler 1 beginnt *****");
		}
		else
		{
			System.out.println("***** Spieler 2 beginnt *****");
		}

		boolean player1won = false;
		boolean player2won = false;

		while (!player1won && !player2won) // as long as nobody won
		{
			boolean validvalue = true;

			do
			{

				if (isplayer1)
				{
					validvalue = playerturn(scanner, "Spieler 1", player1ai,
							field_player2, shots_player1);
				}
				else
				{
					validvalue = playerturn(scanner, "Spieler 2", player2ai,
							field_player1, shots_player2);
				}
			}
			while (!validvalue);

			isplayer1 = !isplayer1; // swap players

			// refresh won status
			player1won = playerwon(field_player2);
			player2won = playerwon(field_player1);
		}

		// ///////////////////////////////////////////////////////////// Phase
		// 3: A player won!
		if (player1won)
		{
			System.out.println("### Spieler 1 hat gewonnen! ###");
		}
		else
		{
			System.out.println("### Spieler 2 hat gewonnen! ###");
		}
	}

	/**
	 * AI methods for player (selecting a non-random pos to shoot)
	 * 
	 * @param shots
	 *            field of player's shots
	 */
	String player_aiShootPos(int[][] shots)
	{
		// No while(true) to prevent infinite loop
		for (int i = 0; i < 100000; i++)
		{
			String pos_string = Game.randomPos(); // will always return a valid
													// postion
			Point pos = Game.getFieldStringIndices(pos_string); // get
																// coordinates
																// for fields

			if (Game.fieldIsLikelyForShot(shots, pos.x, pos.y))
			{
				return pos_string;
			}

		}

		System.out.println("AI didn't find a suitable position!");
		return Game.randomPos();
	}

	/**
	 * Player turns
	 * 
	 * @param scanner
	 * @param ai
	 * @return
	 */
	boolean playerturn(Scanner scanner, String name, boolean ai,
			int[][] enemyfield, int[][] shots)
	{
		System.out.printf("####********* %s *********####", name);
		System.out.println();

		if (!ai)
		{
			System.out.println("Aus welches Feld schießen?");
			System.out.println("");
		}

		Game.drawFieldShot(shots);

		try
		{
			String pos;

			if (ai)
			{
				pos = player_aiShootPos(shots);
			}
			else
			{
				pos = scanner.next();
			}

			if (shootShip(pos, enemyfield, shots))
			{
				if (!ai)
				{
					System.out
							.println("#### Du hast ein Schiff getroffen! ####");

				}
				else
				{
					System.out.println("#### Schiff getroffen ####");
				}
			}
			else
			{
				if (!ai)
				{
					System.out.println("#### Leider nichts getroffen! ####");
				}
				else
				{
					System.out.println("#### Kein Schiff getroffen ####");
				}

			}

			// Sleep a little bit to show the player the results
			if (GAME_OUTPUT_SLEEP)
			{
				try
				{
					Thread.sleep(1000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			Game.drawFieldShot(shots); // draw second time

			// Sleep a little bit to show the player the results
			if (GAME_OUTPUT_SLEEP)
			{
				try
				{
					Thread.sleep(2000);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (InvalidParameterException ex)
		{
			if (!ai)
			{
				System.out.println(ex.getMessage());
			}
			return false;
		}

		return true;
	}

	/**
	 * Ask player for adding ships
	 * 
	 * @param ai
	 *            Add random ships
	 */
	void playerAddShips(Scanner scanner, boolean ai, String name, int playerid,
			int[][] field)
	{
		System.out.printf("#### %s ####", name);
		System.out.println();

		for (int i = 0; i < placedships.length; i++)
		{
			if (!ai)
			{
				Game.drawFieldBoolean(field); // Draw field 1x

				System.out.println("Platziere ein Schiff der Länge "
						+ placedships[i]);
				System.out.println("Dieses Schiff positionieren auf Feld = ?");
			}

			// Do it until the player entered a valid field
			boolean validvalue = true;
			do
			{
				String pos;

				// Randomize position or ask player
				if (ai)
				{
					pos = Game.randomPos();
				}
				else
				{
					pos = scanner.next();
				}

				validvalue = true;

				try
				{
					placeShip(pos, placedships[i], field, playerid);
				}
				catch (InvalidParameterException ex)
				{
					if (!ai)
					{
						System.out.println(ex.getMessage());
					}
					validvalue = false;
				}
			}
			while (!validvalue);
		}
	}

	/**
	 * Returns if player won
	 * 
	 * @param field
	 *            Field of OTHER player to check if empty
	 * @return
	 */
	boolean playerwon(int[][] enemyfield)
	{
		for (int x = 0; x < 10; x++)
		{
			for (int y = 0; y < 10; y++)
			{
				if (enemyfield[x][y] != 0)
				{
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Tries to shoot a ship on position
	 * 
	 * @param position
	 * @param field
	 *            Field of OTHER player
	 * @param shots
	 *            Shots of OWN player
	 * @return If successful
	 * @throws InvalidParameterException
	 */
	boolean shootShip(String position, int[][] enemyfield, int[][] shots)
			throws InvalidParameterException
	{
		Point indices = Game.getFieldStringIndices(position);

		int shiptype = enemyfield[indices.x][indices.y];

		if (shiptype != 0)
		{
			// Append to arrays (delete ships, refresh player's "shot" view)
			for (int x = 0; x < 10; x++)
			{
				for (int y = 0; y < 10; y++)
				{
					if (enemyfield[x][y] == shiptype)
					{
						enemyfield[x][y] = 0; // delete ship
						shots[x][y] = 2; // set to "shot"
					}
				}
			}

			return true;
		}
		else
		{
			shots[indices.x][indices.y] = 1; // Only set to "shot", not
												// successful

			return false;
		}
	}

	/**
	 * Places a ship for player 2
	 * 
	 * @param position
	 */
	void placeShip(String position, int length, int[][] field, int playerid)
			throws InvalidParameterException
	{
		Point indices = Game.getFieldStringIndices(position);
		
		ArrayList<Integer> validdirections = new ArrayList<>();

		// Find a orientation, suitable for the field
		// Step 1: basic check
		if((indices.x + length) <= 10)
		{
			boolean valid = true;
			
			for (int x = indices.x; x < indices.x + length; x++)
			{
				if (!Game.fieldIsSuitableForShip(field, x, indices.y))
				{
					valid = false;
					break;
				}
			}
			
			if(valid)
				validdirections.add(DIR_EAST);
		}
		if ((indices.y + length) <= 10)
		{
			boolean valid = true;
			
			for (int y = indices.y; y < indices.y + length; y++)
			{
				if (!Game.fieldIsSuitableForShip(field, indices.x, y))
				{
					valid = false;
					break;
				}
			}
			
			if(valid)
			validdirections.add(DIR_SOUTH);
		}
		if ((indices.x - length + 1) >= 0)
		{
			boolean valid = true;
			
			for (int x = indices.x; x >= indices.x - length + 1; x--)
			{
				if (!Game.fieldIsSuitableForShip(field, x, indices.y))
				{
					valid = false;
					break;
				}
			}
			
			if(valid)
			validdirections.add(DIR_WEST);
		}
		if ((indices.y - length + 1) >= 0)
		{
			boolean valid = true;
			
			for (int y = indices.y; y >= indices.y - length + 1; y--)
			{
				if (!Game.fieldIsSuitableForShip(field, indices.x, y))
				{
					valid = false;
					break;
				}
			}
			
			if(valid)
			validdirections.add(DIR_NORTH);
		}	

		// Check if both are invalid
		if (validdirections.isEmpty())
		{
			throw new InvalidParameterException("Ung�ltige Position!"); // throw
		}

		// If both are valid, randomize them
		int randomdir = validdirections.get(RANDOM.nextInt(validdirections.size()));

		// Here, only one value is valid
		switch (randomdir)
		{
		case DIR_EAST:
			for (int x = indices.x; x < indices.x + length; x++)
			{
				field[x][indices.y] = shipindices[playerid]; // Set to ship
																// index
			}
			break;
		case DIR_SOUTH:
			for (int y = indices.y; y < indices.y + length; y++)
			{
				field[indices.x][y] = shipindices[playerid];
			}
			break;
		case DIR_WEST:
			for (int x = indices.x; x >= indices.x - length + 1; x--)
			{
				field[x][indices.y] = shipindices[playerid];
			}
			break;
		case DIR_NORTH:
			for (int y = indices.y; y >= indices.y - length + 1; y--)
			{
				field[indices.x][y] = shipindices[playerid];
			}
			break;
		}
		
		// Add ship index
		shipindices[playerid] = shipindices[playerid] + 1;

	}

	// //////////////////////////////////////Static functions
	/**
	 * generates a random pos like A5
	 * 
	 * @return
	 */
	static String randomPos()
	{
		char x = (char) ('A' + RANDOM.nextInt(10));
		int y = RANDOM.nextInt(10);

		return x + "" + y;
	}

	/**
	 * Checks field if it can be used by AI to shoot @ it
	 * 
	 * @param field
	 */
	static boolean fieldIsLikelyForShot(int[][] field, int x, int y)
	{
		// Check if already shot or is a sunken ship
		if (field[x][y] != 0)
			return false; // Don't shoot again!

		// It's zero, now; check if there's a shot ship next to it => the cannot
		// be any other ships
		for (int cx = x - 1; cx <= x + 1; cx++)
		{
			for (int cy = y - 1; cy <= y + 1; cy++)
			{
				if (cx < 0 || cy < 0 || cx > 9 || cy > 9)
					continue;

				if (field[cx][cy] == 2) // if sunken ship next to it => no!
					return false;
			}
		}

		return true;
	}

	/**
	 * Checks field at position and all positions around to check if it's
	 * possible to place a ship
	 * 
	 * @param field
	 */
	static boolean fieldIsSuitableForShip(int[][] field, int x, int y)
	{
		//check if x,y are still in field
		if(x < 0 || y < 0 || x > 9 || y > 9)
			return false;
		
		for (int cx = x - 1; cx <= x + 1; cx++)
		{
			for (int cy = y - 1; cy <= y + 1; cy++)
			{
				if (cx < 0 || cy < 0 || cx > 9 || cy > 9)
					continue; //doesn't matter for sourrounding fields

				if (field[cx][cy] != 0)
					return false;
			}
		}

		return true;
	}

	/**
	 * Draws the field to screen Draws + if destroyed, 0 if shot
	 * 
	 * @param field
	 */
	static void drawFieldShot(int[][] field)
	{
		System.out.println("* 0 1 2 3 4 5 6 7 8 9");

		char row = 'A';

		for (int y = 0; y < 10; y++)
		{
			System.out.print(row + " ");

			for (int x = 0; x < 10; x++)
			{
				switch (field[x][y])
				{
				case 1:
					System.out.print("0 ");
					break;
				case 2:
					System.out.print("+ ");
					break;
				default:
					System.out.print("  ");
					break;

				}

			}

			row++;

			System.out.println();
		}
	}

	/**
	 * Draws the field to screen Draws # if not 0
	 * 
	 * @param field
	 */
	static void drawFieldBoolean(int[][] field)
	{
		System.out.println("* 0 1 2 3 4 5 6 7 8 9");

		char row = 'A';

		for (int y = 0; y < 10; y++)
		{
			System.out.print(row + " ");

			for (int x = 0; x < 10; x++)
			{
				System.out.print((field[x][y] != 0 ? "#" : " ") + " ");
			}

			System.out.println();

			row++;
		}
	}

	/**
	 * Converts a field position (like A5) to indices array
	 * 
	 * @param position
	 * @return [x,y]
	 */
	static Point getFieldStringIndices(String position)
			throws InvalidParameterException
	{
		position = position.toUpperCase(); // make upper

		if (position.length() != 2)
		{
			throw new InvalidParameterException("Falsche Eingabe!");
		}
		char letter = position.charAt(0);
		char number = position.charAt(1);

		if (!Character.isLetter(letter) || !Character.isDigit(number)) // check
																		// for
																		// letter/digit
		{
			throw new InvalidParameterException("Falsche Eingabe!");
		}

		int x = letter - 'A';
		int y = Integer.parseInt(number + "");

		if (x < 0 || x > 9 || y < 0 || y > 9) // last check
		{
			throw new InvalidParameterException("Falsche Eingabe!");
		}

		return new Point(y, x); // convert [row,column] to [x,y]
	}
}
