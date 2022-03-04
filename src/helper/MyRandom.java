package helper;

import java.util.Random;

public class MyRandom {
	static Random rand;
	
	static {
		rand = new Random();
	}
	
	public static Coordinate getRandomCoordinate(int maxNumberX, int maxNumberY)
	{		
		int x = rand.nextInt(maxNumberX);
		int y = rand.nextInt(maxNumberY);
		
		return new Coordinate(x, y);
	}

	public static int getRandomNumber(int maxNumber)
	{
		return rand.nextInt(maxNumber);
	}
}
