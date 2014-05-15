import java.io.File;
import java.io.IOException;


public class FileObserver
{
	
	private File file;
	
	public FileObserver(String path)
	{
		this.file = new File(path);
	}	
	
	public void observe() throws IOException, InterruptedException
	{
		System.out.println("Observer gestartet.");
		
		if(!file.exists())
			throw new IOException("File is not existing!");
		
		//Set initial modify date
		long lastModified = file.lastModified();
		
		//Set timeout variables
		long runTime = 0;
		
		//Start comparer loop
		//Will lock main thread
		while(runTime <= 30000)
		{
			System.out.println("Überprüfe ...");
			
			Thread.sleep(500);
			runTime += 500;
			
			if(file.lastModified() != lastModified)
			{
				System.out.printf("Datei %s wurde geändert!\n", this.file.toString());
				
				//Set last modified to current modified
				lastModified = file.lastModified();
				
				//Set timeout back to zero
				runTime = 0;
			}
		}		
		
		System.out.println("Die Datei wurde in den letzen 30s nicht geändert. Abbruch!");		
		
	}
	
}
