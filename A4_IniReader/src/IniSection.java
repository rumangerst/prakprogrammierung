import java.util.HashMap;
import java.util.Map.Entry;


/**
 * Verwaltet eine Sektion
 * @author ju39gox
 *
 */
public class IniSection implements Comparable
{
	private String name;
	private HashMap<String, String> configuration;
	
	public IniSection(String name)
	{
		this.name = name;
		this.configuration = new HashMap<>();
	}
	
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gibt Zuweisung des Schlüssels key in der Sektion zurück oder den Standardwert
	 * @param key
	 * @param def
	 * @return
	 */
	public String getData(String key, String def)
	{
		String data = configuration.get(key.toLowerCase());
		
		if(data == null)
		{
			return def;
		}
		else
		{
			return data;
		}
	}
	
	/**
	 * Setzt einen Parameter in der Sektion
	 * @param key
	 * @param value
	 */
	public void setData(String key, String value)
	{
		configuration.put(key.toLowerCase(), value);
	}
	
	/**
	 * Setzt einen Parameter auf den Standardwert
	 * @param key
	 */
	public void resetData(String key)
	{
		configuration.remove(key.toLowerCase());
	}
	
	/**
	 * Gibt zurück, ob Sektion leer ist
	 * @return
	 */
	public boolean isEmpty()
	{
		return configuration.isEmpty();
	}
	
	@Override
	public String toString()
	{
		StringBuilder output = new StringBuilder();
		output.append("[" + getName() + "]\n");
		
		for(Entry<String,String> assignment : configuration.entrySet())
		{
			output.append(assignment.getKey() + "=" + assignment.getValue() + "\n");
		}
		
		return output.toString();
	}
	
	@Override
	public int compareTo(Object o)
	{
		IniSection sec = (IniSection)o;
		
		return this.name.compareToIgnoreCase(sec.name);
	}

}
