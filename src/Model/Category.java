package Model;

public class Category {
	int idkategori;
	String name;
	public Category(String name,int idkategori)
	{
		this.name=name;
		this.idkategori=idkategori;
	}
	public Category()
	{
		
	}
	public int getidkategori()
	{
		return this.idkategori;
	}
	public String getkategori()
	{
		return this.name;
	}
}
