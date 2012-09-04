package BookCopier;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Config 
{
	public Config()
	{
		loadConfig();
	}
	
	public void loadConfig()
	{
		try 
		{
			FileInputStream fRead = new FileInputStream(filePath);
			DataInputStream inStream = new DataInputStream(fRead);
			BufferedReader bReader = new BufferedReader(new InputStreamReader(inStream));		
			String line;
			String[] splittedLine;
			try {
				while ((line = bReader.readLine()) != null)
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
				bReader.close();
				inStream.close();
				fRead.close();
				
			} 
			catch(IOException e) 
			{
				e.printStackTrace();		
			}				
		} 
		catch(FileNotFoundException e) 
		{
			e.printStackTrace();
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
