package ekutuphane;
import View.ekutuphane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Model.Book;
import Model.Category;
import Model.User;

public class dbmanager {
	ekutuphane _ekutuphane = new ekutuphane();
	Connection connect = null;
	Dbcon conn= new Dbcon();
	PreparedStatement prstatement = null;
	public void Insert(String username,String password) throws SQLException
	{
		ekutuphane _ekutuphane = new ekutuphane();
		Connection connect = null;
		Dbcon conn = new Dbcon();
		PreparedStatement prstatement = null;
		try {
			connect = conn.getConnection();
			String sql ="INSERT INTO user (username,password) VALUES (?,?)";
			prstatement=connect.prepareStatement(sql);
			prstatement.setString(1,username);
			prstatement.setString(2, password);
			prstatement.executeUpdate();
			JOptionPane.showMessageDialog(_ekutuphane.frame,"Kayıt Olundu");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			prstatement.close();
			connect.close();
		}
}
	public ArrayList<String> Allusername() throws SQLException {
		connect=conn.getConnection();
		String Sql="Select username,password from user";
		prstatement=connect.prepareStatement(Sql);
		ResultSet results=prstatement.executeQuery();
		ArrayList<String> resultlist= new ArrayList<String>();
		while(results.next())
		{
			resultlist.add(results.getString(1));
		}
		connect.close();
		return resultlist;
		
	}
	public User usercontrol(String username,String password) throws SQLException
	{
		try {
			connect=conn.getConnection();
			prstatement=connect.prepareStatement("Select * from user where username='"+username+"';");
			ResultSet results=prstatement.executeQuery();
			while(results.next())
				{
				if(password.equals(results.getString("password")))
					{
						User loginuser = new User(results.getInt(3),username,password);
						return loginuser;
					}
						
				}
				
			}
			
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
		connect.close();
		prstatement.close();
		return null;
			
	}
	public boolean userauth(String username,String password) throws SQLException
	{
		try {
			connect=conn.getConnection();
			prstatement=connect.prepareStatement("Select * from user where username='"+username+"';");
			ResultSet results=prstatement.executeQuery();
			while(results.next())
				{
				if(password.equals(results.getString("password")))
					{
							
							return true;
					}
						
				}
				
			}
			
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		connect.close();
		prstatement.close();
		return false;
			
	}
	public ArrayList<Category> GetCategory() throws SQLException
	{
		connect = conn.getConnection();
		prstatement=connect.prepareStatement("Select * from category");
		ResultSet results = prstatement.executeQuery();
		ArrayList<Category> categorylist=new ArrayList<Category>();
		categorylist.add(new Category());
		while(results.next())
		{
			
			categorylist.add(new Category(results.getString("name"),Integer.parseInt(results.getString("idcategory"))));
		}
		connect.close();
		return categorylist;
		
	}
	public int getbooksum() throws SQLException 

	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("SELECT COUNT('name') FROM book");
		ResultSet result =prstatement.executeQuery();
		
		
		while(result.next())
		{
			return Integer.parseInt(result.getString(1));
		}
		return 0;
	}
	public ArrayList<Book> GetBooks() throws SQLException

	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("Select * from book");
		ResultSet results=prstatement.executeQuery();
		ArrayList<Book> books = new ArrayList<Book>();
		while(results.next())
		{
			books.add(new Book(Integer.parseInt(results.getString(6)),Integer.parseInt(results.getString(5)),results.getString(2),results.getString(3),results.getString(4)));
		}
		connect.close();
		return books;
		
	}

	public ArrayList<Book> searchbook(String bookname) throws SQLException

	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("Select * from book WHERE name LIKE '%"+bookname+"%'");
		ResultSet results=prstatement.executeQuery();
		ArrayList<Book> books = new ArrayList<Book>();
		while(results.next())
		{
			books.add(new Book(results.getInt("idbook"),Integer.parseInt(results.getString("idkategori")),Integer.parseInt(results.getString("page")),results.getString("name"),results.getString("author"),results.getString("description")));
		}
		connect.close();
		return books;
		
	}
	public void InsertBook(String Bookname,String Author,String desc,String page,int kategori) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("INSERT INTO book (name,author,description,page,idkategori) VALUES(?,?,?,?,?)");
		prstatement.setString(1,Bookname);
		prstatement.setString(2,Author);
		prstatement.setString(3,desc);
		prstatement.setString(4,page);
		prstatement.setInt(5, kategori);
		prstatement.executeUpdate();
		connect.close();
		
	}
	public void UpdateBook(String Bookname,String Author,String desc,String page,int kategori,int id) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("UPDATE book SET name=?,author=?,description=?,page=?,idkategori=? WHERE idbook=?");
		prstatement.setString(1,Bookname);
		prstatement.setString(2,Author);
		prstatement.setString(3,desc);
		prstatement.setString(4,page);
		prstatement.setInt(5, kategori);
		prstatement.setInt(6, id);
		prstatement.executeUpdate();
		connect.close();
	}
	public void RemoveBook(int id) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("DELETE FROM book WHERE idbook=?");
		prstatement.setInt(1, id);
		prstatement.executeUpdate();
		connect.close();
		
	
	}
	public ArrayList<User> getUsers() throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("Select * from user");
		ResultSet results = prstatement.executeQuery();
		ArrayList<User> users = new ArrayList<User>();
		while(results.next())
		{
			users.add(new User(results.getInt("iduser"),results.getInt("usercontrol"),results.getString("username"),results.getString("password")));
		}
		connect.close();
		return users;
	}
	public ArrayList<User> getSearchUser(String arama) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("Select * from user WHERE username LIKE '%"+arama+"%'");
		ResultSet results = prstatement.executeQuery();
		ArrayList<User> users = new ArrayList<User>();
		while(results.next())
		{
			users.add(new User(results.getInt("iduser"),results.getInt("usercontrol"),results.getString("username"),results.getString("password")));
		}
		connect.close();
		return users;
	}
	public void UpdateUser(String username,String password,int usercontrol,int userid) throws SQLException
	{
		connect = conn.getConnection();
		prstatement=connect.prepareStatement("UPDATE user SET username=?,password=?,usercontrol=? WHERE iduser=?");
		prstatement.setString(1, username);
		prstatement.setString(2,password);
		prstatement.setInt(3, usercontrol);
		prstatement.setInt(4, userid);
		prstatement.executeUpdate();
		connect.close();
	}
	public void RemoveUser(int userid) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("Delete from user Where iduser=?");
		prstatement.setInt(1, userid);
		prstatement.executeUpdate();
		connect.close();
	}
	public ArrayList<Book> getrequestbook() throws SQLException
	{
		connect = conn.getConnection();
		prstatement=connect.prepareStatement("Select * from requestbook");
		ArrayList<Book> books = new ArrayList<Book>();
		ResultSet results = prstatement.executeQuery();
		while(results.next())
		{
			books.add(new Book(results.getInt("idkategori"),results.getInt("page"),results.getString("name"),results.getString("author"),results.getString("description")));
		}
		connect.close();
		return books ;
	}
	public void removerequest(String name) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("Delete from requestbook WHERE name=?");
		prstatement.setString(1, name);
		prstatement.executeUpdate();
		connect.close();
	}
	public void RequestBook(String Bookname,String Author,String desc,String page,int kategori) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("INSERT INTO requestbook (name,author,description,page,idkategori) VALUES(?,?,?,?,?)");
		prstatement.setString(1,Bookname);
		prstatement.setString(2,Author);
		prstatement.setString(3,desc);
		prstatement.setString(4,page);
		prstatement.setInt(5, kategori);
		prstatement.executeUpdate();
		connect.close();
	}
	public void InsertCategory(String name) throws SQLException
	{
		connect=conn.getConnection();
		prstatement=connect.prepareStatement("INSERT INTO category (name) VALUES (?)");
		prstatement.setString(1,name);
		prstatement.executeUpdate();
		connect.close();
	}
}

