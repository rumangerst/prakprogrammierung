import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class InitializationFile
{
	private String file;
	private ArrayList<IniSection> sections;

	public InitializationFile(String file) throws IOException
	{
		this.file = file;
		this.sections = new ArrayList<IniSection>();
		
		read();
	}
	
	private void read() throws IOException, InvalidInitializationFileException
	{
		sections.clear();
		
		BufferedReader rd = new BufferedReader(new FileReader(file));
		
		/**
		 * Gerade bearbeitete Sektion ist Standard-Sektion
		 */
		IniSection currentSection = new IniSection("");		

		String buffer = null;
		while ((buffer = rd.readLine()) != null)
		{
			/**
			 * Trimme Buffer
			 */
			buffer = buffer.trim();
			
			/**
			 * Kommentare und leere Zeilen ignorieren
			 */
			if (buffer.isEmpty() || buffer.startsWith(";"))
				continue;

			/**
			 * Ist Zeile eine Sektion? => Current section wechseln
			 * Ist Zeile eine Zuweisung => In Sektion schreiben
			 */
			if(buffer.startsWith("[") && buffer.endsWith("]"))
			{
				buffer = buffer.substring(1, buffer.length() - 1); //Schneide [] ab
				
				/**
				 * Sektion wird jetzt gewechselt
				 */
				if(!currentSection.isEmpty())
				{
					sections.add(currentSection);
				}
				
				currentSection = new IniSection(buffer);
			}
			else if(buffer.contains("=")) 
			{
				/**
				 * Splitte String, um Zuweisung zu lesen
				 */
				String[] assign = buffer.split("=");
				
				if(assign.length >= 3)
					throw new InvalidInitializationFileException("Multi-Assignment: " + buffer);
				
				/**
				 * Schreibe in Sektion
				 */
				String key = assign[0].trim();
				String value = assign[1].trim();
				
				currentSection.setData(key, value);
			}
			else
			{
				throw new InvalidInitializationFileException("Invalid line: " + buffer);
			}
		}
		
		/**
		 * Füge zuletzt bearbeitete Sektion ein
		 */
		if(!currentSection.isEmpty())
		{
			sections.add(currentSection);
		}
		
		/**
		 * Sortiere Sektionen
		 */
		Collections.sort(sections);

		rd.close();
	}
	
	/**
	 * Gets value from INI section
	 * @param section
	 * @param key
	 * @param def
	 * @return
	 */
	public String get(String section, String key, String def)
	{
		for(IniSection sec : sections)
		{
			if(sec.getName().equalsIgnoreCase(section))
			{
				return sec.getData(key, def);
			}
		}
		
		return def;
	}
	
	public void print()
	{
		System.out.println(this.toString());
	}
	
	@Override
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		
		for(IniSection sec : sections)
		{
			output.append(sec.toString() + "\n");
		}
		
		return output.toString();
	}
	
	

}
