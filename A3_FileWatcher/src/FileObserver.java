import java.io.File;
import java.io.IOException;


public class FileObserver
{
	private File file;
	
	public FileObserver(String path)
	{
		this.file = new File(path);
		this.lastModified = 0;
	}	
	
	public void observe() throws IOException
	{
		if(!file.exists())
			throw new IOException("File is not existing!");
		
		//Set initial modify date
		long lastModified = file.lastModified();
		
		//Start comparer loop
		//Will lock main thread
		while(lastModified == file.lastModified())
		{
			lastModified = file.lastModified();
		}
		
		
	}
	
}
