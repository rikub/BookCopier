package BookCopier;

import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;
import org.bukkit.Material;
import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
 
public class MyBook
{
    public MyBook(ItemStack bookItem)
    {
        NBTTagCompound bData = ((CraftItemStack) bookItem).getHandle().tag;
        this.author = bData.getString("author");
        this.title = bData.getString("title");      
        NBTTagList nPages = bData.getList("pages");
        String[] sPages = new String[nPages.size()];
        for(int i = 0;i<nPages.size();i++)
        {
            sPages[i] = nPages.get(i).toString();
        }     
        this.pages = sPages;
    }
 
    public MyBook(String title, String author, String[] pages) 
    {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
   
    public String getAuthor()
    {
        return author;
    }
 
    public void setAuthor(String sAuthor)
    {
        author = sAuthor;
    }
   
    public String getTitle()
    {
        return title;
    }
   
    public String[] getPages()
    {
        return pages;
    }
 
    public ItemStack createItem()
    {
        CraftItemStack writableBook = new CraftItemStack(Material.WRITTEN_BOOK);
        NBTTagCompound bData = new NBTTagCompound();
        bData.setString("author",author);
        bData.setString("title",title);
        NBTTagList nPages = new NBTTagList();
        for(int i = 0;i<pages.length;i++)
        { 
            nPages.add(new NBTTagString(pages[i],pages[i]));
        }
        bData.set("pages", nPages);
        writableBook.getHandle().tag = bData;
        return (ItemStack) writableBook;
    }
    private String author;
    private String title;
    private String[] pages;
}