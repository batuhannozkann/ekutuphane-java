package Model;

public class User {
	int id,usercontrol;
	String username,password;
	
	public User(int id,int usercontrol,String username,String password)
	{
		this.id=id;
		this.usercontrol=usercontrol;
		this.username=username;
		this.password=password;
	}
	public User(int usercontrol,String username,String password)
	{
		this.id=id;
		this.usercontrol=usercontrol;
		this.username=username;
		this.password=password;
	}
	public static User loginuser = new User();
	public int getloginuser()
	{
		return this.usercontrol;
	}
	public User()
	{}
	public int getId()
	{
		return this.id;
	}
	public String getusername()
	{
		return this.username;
	}
	public String getpassword()
	{
		return this.password;
	}
	public void setusercontrol(int uc)
	{
		this.usercontrol=uc;
	}
	public void setusername(String usern)
	{
		this.username=usern;
	}
	public void setpassword(String password)
	{
		this.password=password;
	}
}
