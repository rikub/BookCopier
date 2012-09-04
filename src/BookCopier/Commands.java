package BookCopier;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Commands 
{
	public static void setPlugin(BookCopier bc)
	{
		plugin = bc;
	}
	public static void commandCopy(Player commandSender, String args[])
	{
		if(plugin.config.getOPsOnly())
		{
			if(!commandSender.isOp())
				return;
		}
		if(args.length == 0)
		{
			commandSender.sendMessage(ChatColor.RED + "Type: \"/bc help\" for more informations.");
		}
		else if(args.length == 1)
		{
			if(args[0].toLowerCase().matches("help"))
			{
				commandSender.sendMessage(ChatColor.AQUA + "BOOK COPIER COMMANDS AND INFO!");
				commandSender.sendMessage("<> - required arguments");
				commandSender.sendMessage("[] - optional arguments");
				commandSender.sendMessage(ChatColor.YELLOW + "* " + ChatColor.WHITE + "/bc copy [value] - Copies book in your hand.(No value means 1*)");
				commandSender.sendMessage(ChatColor.YELLOW + "* " + ChatColor.WHITE + "/bc give <player> [value] - Adds book in your hand to target player's inventory.");
			}
			else if(args[0].toLowerCase().matches("copy"))
			{
				if(commandSender.getItemInHand().getTypeId() == 387)
				{
					MyBook book = new MyBook(commandSender.getItemInHand());
					commandSender.getInventory().addItem(book.createItem());
					commandSender.sendMessage("Book has been copied!");
				}
				else
				{
					commandSender.sendMessage("This isn't book! You need ID: 387.");
				}
			}
		}
		else if(args.length == 2)
		{
			if(args[0].toLowerCase().matches("copy"))
			{
				if(commandSender.getItemInHand().getTypeId() == 387)
				{
					try
					{
						int numberOfBooks = Integer.parseInt(args[1]);
						ItemStack item = new MyBook(commandSender.getItemInHand()).createItem();
						item.setAmount(numberOfBooks);
						commandSender.getInventory().addItem(item);
						commandSender.sendMessage("Books has been copied!");
					}
					catch(NumberFormatException e)
					{
						commandSender.sendMessage(ChatColor.RED + "Second argument must be number (Integer)");
						e.printStackTrace();
					}
				}
				else
				{
					commandSender.sendMessage("This isn't book! You need ID: 387.");
				}
			}
			else if(args[0].toLowerCase().matches("give"))
			{
				Player p;
				if((p = plugin.getServer().getPlayer(args[1])) != null)
				{
					try
					{
						ItemStack item = new MyBook(commandSender.getItemInHand()).createItem();
						p.getInventory().addItem(item);
						p.sendMessage("You recieve some book...");
						commandSender.sendMessage("Book has been added to target player's inventory!");
					}
					catch(NumberFormatException e)
					{
						commandSender.sendMessage(ChatColor.RED + "Second argument must be number (Integer)");
						e.printStackTrace();
					}
				}
				else
				{
					commandSender.sendMessage(ChatColor.RED + "Target player is offline");
				}
			}
			else
			{
				commandSender.sendMessage("Invalid argument! Type: \"/bc help\" for more informations.");
			}
		}
		else if(args.length == 3)
		{
			if(args[0].toLowerCase().matches("give"))
			{
				if(commandSender.getItemInHand().getTypeId() == 387)
				{
					Player p;
					if((p = plugin.getServer().getPlayer(args[1])) != null)
					{
						try
						{
							int numberOfBooks = Integer.parseInt(args[2]);
							ItemStack item = new MyBook(commandSender.getItemInHand()).createItem();
							item.setAmount(numberOfBooks);
							p.getInventory().addItem(item);
							p.sendMessage("You recieved some books...");
							commandSender.sendMessage("Books has been added to target player's inventory!");
						}
						catch(NumberFormatException e)
						{
							commandSender.sendMessage(ChatColor.RED + "Second argument must be number (Integer)");
							e.printStackTrace();
						}
					}
					else
					{
						commandSender.sendMessage(ChatColor.RED + "Target player is offline");
					}
				}
				else
				{
					commandSender.sendMessage("This isn't book! You need ID: 387.");
				}
			}
			else
			{
				commandSender.sendMessage("Invalid argument! Type: \"/bc help\" for more informations.");
			}
		}
		else
		{
			commandSender.sendMessage("Too many arguments!");
		}
	}
	
	private static BookCopier plugin;
}
