package BookCopier;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class BookCopier extends JavaPlugin
{
	public void onEnable()
	{
		Commands.setPlugin(this);
		config.loadConfig();
	}
	
	public void onDisable()
	{
		
	}
	
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	{
		if(!(sender instanceof Player))
		{
			return false;
		}
		if(command.getName().equalsIgnoreCase("bc"))
		{
			Commands.commandCopy((Player)sender, args);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	Config config = new Config();
}