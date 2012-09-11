package BookCopier;

import org.bukkit.inventory.ItemStack;

public class BookCreator 
{
	public static boolean checkFormat(String filePath)
	{
		GugaFile file = new GugaFile(filePath, GugaFile.READ_MODE);
		file.Open();
		String line;
		int i=0;
		while((line = file.ReadLine()) != null)
		{
			if(i==0)
			{
				if(!line.startsWith("title"))
				{
					return false;
				}
				if(!(line.split(": ").length == 2))
				{
					return false;
				}
			}
			else if(i==1)
			{
				if(!line.startsWith("author"))
				{
					return false;
				}
				if(!(line.split(": ").length == 2))
				{
					return false;
				}
			}
			else if(i==2)
			{
				if(!line.startsWith("pages"))
				{
					return false;
				}
				if(!(line.split(": ").length == 2))
				{
					return false;
				}
			}
			else
			{
				return false;
			}
			i++;
		}
		file.Close();
		return true;
	}
	
	public static void saveBook(String title, String author, String[] pages, String filePath)
	{
		GugaFile file = new GugaFile(filePath, GugaFile.WRITE_MODE);
		file.Open();
		String pagesInString = "";
		int i = 0;
		while(i<pages.length)
		{
			if(i==0)
			{
				pagesInString += pages[i];
			}
			else 
			{
				pagesInString += "," + pages[i];
			}
			i++;
		}
		file.WriteLine("title: " + title);
		file.WriteLine("author: " + author);
		file.WriteLine("pages: " + pagesInString);
		file.Close();
	}
	
	public static ItemStack loadBook(String filePath)
	{
		GugaFile file = new GugaFile(filePath, GugaFile.READ_MODE);
		file.Open();
		String title = "default";
		String author = "default";
		String line;
		String[] splittedLine;
		String[] pages = {"default"};
		while((line = file.ReadLine()) != null)
		{
			splittedLine = line.split(": ");
			if(splittedLine[0].matches("title"))
			{
				title = line.split(": ")[1];
			}
			else if(splittedLine[0].matches("author"))
			{
				author = line.split(": ")[1];
			}
			else if(splittedLine[0].matches("pages"))
			{
				pages = line.split(": ")[1].split(",");
			}
		}
		file.Close();
		return new MyBook(title, author, pages).createItem();
	}
	
	BookCopier bc;
}
