package Model;

public class Book {
	int id,idkategori,page;
	String name,Category,Author,description;
	
	public Book(int idkategori,int page,String name,String Author,String description)
	{
		this.idkategori=idkategori;
		this.page=page;
		this.name=name;
		this.Author=Author;
		this.description=description;
	}
	public Book(int id,int idkategori,int page,String name,String Author,String description)
	{
		this.id=id;
		this.idkategori=idkategori;
		this.page=page;
		this.name=name;
		this.Author=Author;
		this.description=description;
	}
	public Book()
	{
		
	}
	public String getbookname()
	{
		return this.name;
	}
	public String getdescription()
	{
		return this.description;
	}
	public String getauthor()
	{
		return this.Author;
	}
	public int getbookid()
	{
		return this.id;
	}
	public int getbookpage()
	{
		return this.page;
	}
	public int getcategoryid()
	{
		return this.idkategori;
	}
	
}
