package BookCopier;

public class Config 
{
	public Config()
	{
		loadConfig();
	}
	
	public void loadConfig()
	{
		GugaFile file = new GugaFile(filePath, GugaFile.READ_MODE);
		String line;
		String[] splittedLine;
		while ((line = file.ReadLine()) != null)
		{
			if(line.startsWith("//"))
			{
				continue;
			}
			splittedLine = line.split(":");
			if (splittedLine[0].matches("Only OPs"))
			{
				if(splittedLine[1].matches("true"))
				{
					this.OPsOnly = true;
				}
						
				else if(splittedLine[1].matches("false"))
				{
					this.OPsOnly = false;
				}
			}
			else if(splittedLine[0].matches("Allowed Players"))
			{
					this.allowedPlayers = splittedLine[1].split(",");
			}
		}
	}
	public boolean getOPsOnly()
	{
		return this.OPsOnly;
	}
	
	public String[] getAllowedPlayers()
	{
		return this.allowedPlayers;
	}
	
	private boolean OPsOnly;
	private String[] allowedPlayers;
	private String filePath = "plugins/BookCopier/configuration.ini";
}
