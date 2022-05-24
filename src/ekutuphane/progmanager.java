package ekutuphane;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Model.Book;
import Model.User;

public class progmanager {
	
	public static DefaultTableModel gettablemodel() throws SQLException
	{
		dbmanager manager = new dbmanager();
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
		
		Object[] book = new Object[5];
		book[0]="Ad";
		book[1]="Yazar";
		book[2]="Sayfa";
		book[3]="Açıklama";
		book[4]="Kategori";
		Object[][] veri = new Object[manager.getbooksum()][5];
		for(int a=0;a<veri.length;a++)
		{
				veri[a][0]=manager.GetBooks().get(a).getbookname();
				veri[a][1]=manager.GetBooks().get(a).getauthor();
				veri[a][2]=manager.GetBooks().get(a).getbookpage();
				veri[a][3]=manager.GetBooks().get(a).getdescription();
				veri[a][4]=changetoname.get(manager.GetBooks().get(a).getcategoryid());
			
		}
		DefaultTableModel tablemodel = new DefaultTableModel(veri,book)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
	}
	public static DefaultTableModel getsearchtablemodel(String arama,String kategori) throws SQLException
	{
		Object[] book = new Object[5];
		book[0]="Ad";
		book[1]="Yazar";
		book[2]="Sayfa";
		book[3]="Açıklama";
		book[4]="Kategori";
		ArrayList<Book> books = new ArrayList<>();
		dbmanager manager =new dbmanager();
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
		books=manager.searchbook(arama);
		ArrayList<Book>catgbook=new ArrayList<Book>();
		for(Book list:books)
		{
			
			if(changetoname.get(list.getcategoryid()).equals(kategori))
			{
				catgbook.add(list);
			}
			
		}
		
		Object[][] catgbooks = new Object[catgbook.size()][5];
		for(int a=0;a<catgbooks.length;a++)
		{
				catgbooks[a][0]=catgbook.get(a).getbookname();
				catgbooks[a][1]=catgbook.get(a).getauthor();
				catgbooks[a][2]=catgbook.get(a).getbookpage();
				catgbooks[a][3]=catgbook.get(a).getdescription();
				catgbooks[a][4]=changetoname.get(catgbook.get(a).getcategoryid());
				
		}
		DefaultTableModel tablemodel = new DefaultTableModel(catgbooks,book)
		{

			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
		
	}
	public static DefaultTableModel getsearchtablemodel(String arama) throws SQLException
	{
		
		ArrayList<Book> books = new ArrayList<>();
		dbmanager manager =new dbmanager();
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
	
		Object[] book = new Object[5];
		book[0]="Ad";
		book[1]="Yazar";
		book[2]="Sayfa";
		book[3]="Açıklama";
		book[4]="Kategori";
	
	try {
		books=manager.searchbook(arama);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Object[][] searchbook = new Object[books.size()][5];
	for(int a=0;a<searchbook.length;a++)
	{
			searchbook[a][0]=books.get(a).getbookname();
			searchbook[a][1]=books.get(a).getauthor();
			searchbook[a][2]=books.get(a).getbookpage();
			searchbook[a][3]=books.get(a).getdescription();
			searchbook[a][4]=changetoname.get(books.get(a).getcategoryid());
	}
	
	DefaultTableModel tablemodel = new DefaultTableModel(searchbook,book)
	{
		public boolean isCellEditable(int row, int column)
		{
			return false;//This causes all cells to be not editable
		}
	};
	return tablemodel;
	}
	public static DefaultTableModel getadminsearchtable(String arama,String kategori) throws SQLException
	{
		Object[] book = new Object[6];
		book[0]="Ad";
		book[1]="Yazar";
		book[2]="Sayfa";
		book[3]="Açıklama";
		book[4]="Kategori";
		book[5]="İd";
		dbmanager manager =new dbmanager();
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
		
		ArrayList<Book>catgbook=new ArrayList<Book>();
		for(Book list:manager.searchbook(arama))
		{
			
			if(changetoname.get(list.getcategoryid()).equals(kategori))
			{
				catgbook.add(list);
			}
			
		}
		
		Object[][] catgbooks = new Object[catgbook.size()][6];
		for(int a=0;a<catgbooks.length;a++)
		{
				catgbooks[a][0]=catgbook.get(a).getbookname();
				catgbooks[a][1]=catgbook.get(a).getauthor();
				catgbooks[a][2]=catgbook.get(a).getbookpage();
				catgbooks[a][3]=catgbook.get(a).getdescription();
				catgbooks[a][4]=changetoname.get(catgbook.get(a).getcategoryid());
				catgbooks[a][5]=catgbook.get(a).getbookid();
				
		}
		DefaultTableModel tablemodel = new DefaultTableModel(catgbooks,book)
		{

			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
	}
	public static DefaultTableModel getadminsearchtable(String arama) throws SQLException {
		ArrayList<Book> books = new ArrayList<>();
		dbmanager manager =new dbmanager();
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
	
		Object[] book = new Object[6];
		book[0]="Ad";
		book[1]="Yazar";
		book[2]="Sayfa";
		book[3]="Açıklama";
		book[4]="Kategori";
		book[5]="İd";
	
	try {
		books=manager.searchbook(arama);
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	Object[][] searchbook = new Object[books.size()][6];
	for(int a=0;a<searchbook.length;a++)
	{
			searchbook[a][0]=books.get(a).getbookname();
			searchbook[a][1]=books.get(a).getauthor();
			searchbook[a][2]=books.get(a).getbookpage();
			searchbook[a][3]=books.get(a).getdescription();
			searchbook[a][4]=changetoname.get(books.get(a).getcategoryid());
			searchbook[a][5]=books.get(a).getbookid();
			
	}
	
	DefaultTableModel tablemodel = new DefaultTableModel(searchbook,book)
	{
		public boolean isCellEditable(int row, int column)
		{
			return false;//This causes all cells to be not editable
		}
	};
	return tablemodel;
	}

	public static String[] getCategories() throws SQLException
	{
		dbmanager manager = new dbmanager();
		String[] category = new String[manager.GetCategory().toArray().length];
		for(int i=0;i<manager.GetCategory().toArray().length;i++)
		{
			category[i]=manager.GetCategory().get(i).getkategori();
		}
		return category;
	}
	public static DefaultTableModel getUserstable() throws SQLException
	{
		Map<Integer,String> changetoname = new HashMap<Integer,String>();
		dbmanager manager= new dbmanager();
		changetoname.put(0,"Normal Uye");
		changetoname.put(1, "Orta Seviye Uye");
		changetoname.put(2, "Yonetici");
		Object[] user = new Object[4];
		user[0]="İd";
		user[1]="Kullanıcı adı";
		user[2]="password";
		user[3]="Yetki";
		
		Object[][] users = new Object[manager.getUsers().size()][4];
		for(int a=0;a<manager.getUsers().size();a++)
		{
			users[a][0]=manager.getUsers().get(a).getId();
			users[a][1]=manager.getUsers().get(a).getusername();
			users[a][2]=manager.getUsers().get(a).getpassword();
			users[a][3]=changetoname.get(manager.getUsers().get(a).getloginuser());
		}
		DefaultTableModel tablemodel = new DefaultTableModel(users,user){
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
	}
	public static DefaultTableModel getSearchUser(String arama) throws SQLException
	{
		Map<Integer,String> changetoname = new HashMap<Integer,String>();
		dbmanager manager= new dbmanager();
		changetoname.put(0,"Normal Uye");
		changetoname.put(1, "Orta Seviye Uye");
		changetoname.put(2, "Yonetici");
		Object[] user = new Object[4];
		user[0]="İd";
		user[1]="Kullanıcı adı";
		user[2]="password";
		user[3]="Yetki";
		
		Object[][] users = new Object[manager.getSearchUser(arama).size()][4];
		for(int a=0;a<manager.getSearchUser(arama).size();a++)
		{
			users[a][0]=manager.getSearchUser(arama).get(a).getId();
			users[a][1]=manager.getSearchUser(arama).get(a).getusername();
			users[a][2]=manager.getSearchUser(arama).get(a).getpassword();
			users[a][3]=changetoname.get(manager.getSearchUser(arama).get(a).getloginuser());
		}
		DefaultTableModel tablemodel = new DefaultTableModel(users,user){
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
	}
	public static DefaultTableModel getSearchUser(String arama,String yetki) throws SQLException {
		Map<Integer,String> changetoname = new HashMap<Integer,String>();
		dbmanager manager= new dbmanager();
		ArrayList<User> selecteduser= new ArrayList<User>();
		changetoname.put(0,"Normal Uye");
		changetoname.put(1, "Orta Seviye Uye");
		changetoname.put(2, "Yonetici");
		Object[] user = new Object[4];
		user[0]="İd";
		user[1]="Kullanıcı adı";
		user[2]="password";
		user[3]="Yetki";
		for(User users:manager.getSearchUser(arama))
		{
			if(changetoname.get(users.getloginuser()).equals(yetki))
			{
				selecteduser.add(users);
			}
		}
		Object[][] users = new Object[selecteduser.size()][4];
		for(int a=0;a<selecteduser.size();a++)
		{
			users[a][0]=selecteduser.get(a).getId();
			users[a][1]=selecteduser.get(a).getusername();
			users[a][2]=selecteduser.get(a).getpassword();
			users[a][3]=changetoname.get(selecteduser.get(a).getloginuser());
		}
		DefaultTableModel tablemodel = new DefaultTableModel(users,user){
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
		
		}
	public static String[] fiiluserbox()
	{
		Map<Integer,String> changetoname = new HashMap<Integer,String>();
		changetoname.put(0,"Normal Uye");
		changetoname.put(1, "Orta Seviye Uye");
		changetoname.put(2, "Yonetici");
		String[] fiilbox = new String[4];
		fiilbox[0]=null;
		fiilbox[1]=changetoname.get(0);
		fiilbox[2]=changetoname.get(1);
		fiilbox[3]=changetoname.get(2);
		return fiilbox;
	}
	public static void Downloader(String Url,String file) throws IOException
	{
	
		URL url = new URL(Url);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
		
	}
	public static DefaultTableModel getrequestbook() throws SQLException
	{
		dbmanager manager = new dbmanager();
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
		
		Object[] book = new Object[5];
		book[0]="Ad";
		book[1]="Yazar";
		book[2]="Sayfa";
		book[3]="Açıklama";
		book[4]="Kategori";
		Object[][] veri = new Object[manager.getrequestbook().size()][5];
		for(int a=0;a<veri.length;a++)
		{
				veri[a][0]=manager.getrequestbook().get(a).getbookname();
				veri[a][1]=manager.getrequestbook().get(a).getauthor();
				veri[a][2]=manager.getrequestbook().get(a).getbookpage();
				veri[a][3]=manager.getrequestbook().get(a).getdescription();
				veri[a][4]=changetoname.get(manager.getrequestbook().get(a).getcategoryid());
			
		}
		DefaultTableModel tablemodel = new DefaultTableModel(veri,book)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		return tablemodel;
	}
	}