import java.io.IOException;


public class Main
{

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException
	{
		InitializationFile ini = new InitializationFile("boot.ini");
		ini.print();
		
		System.out.println("-------------------------");
		System.out.println("Default boot loader:");
		System.out.println(ini.get("boot loader", "default", "None"));
		System.out.println("NSA disabled:");
		System.out.println(ini.get("boot loader", "no-nsa", "false"));

	}

}
