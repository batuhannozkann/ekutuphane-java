package View;

import java.awt.EventQueue;

import ekutuphane.dbmanager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;

import Model.User;
import View.Panel.adminpanel;
import View.Panel.ekutuphanemenu;
import View.Panel.yetkilipanel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ekutuphanelogin {

	public  JFrame frame;
	private JTextField txtusern;
	private JPasswordField txtpasw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ekutuphanelogin window = new ekutuphanelogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ekutuphanelogin() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 368);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		dbmanager manager = new dbmanager();
		ekutuphane kayitform = new ekutuphane();
		
		JLabel lblKullancGir = new JLabel("KULLANICI GİRİŞ FORMU");
		lblKullancGir.setHorizontalAlignment(SwingConstants.CENTER);
		lblKullancGir.setFont(lblKullancGir.getFont().deriveFont(14f));
		lblKullancGir.setBounds(128, 40, 181, 31);
		frame.getContentPane().add(lblKullancGir);
		
		JLabel lblusern = new JLabel("Kullanıcı adı:");
		lblusern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblusern.setBounds(45, 132, 92, 25);
		frame.getContentPane().add(lblusern);
		
		JLabel lblpasw = new JLabel("Password:");
		lblpasw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblpasw.setBounds(45, 197, 92, 25);
		frame.getContentPane().add(lblpasw);
		
		txtusern = new JTextField();
		txtusern.setColumns(10);
		txtusern.setBounds(142, 132, 137, 20);
		frame.getContentPane().add(txtusern);
		
		JButton btnGirisYap = new JButton("Giriş yap");
		btnGirisYap.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					if(manager.userauth(txtusern.getText(), txtpasw.getText()))
					{
						
						
						User.loginuser.setpassword(manager.usercontrol(txtusern.getText(),txtpasw.getText()).getpassword());
						User.loginuser.setusername(manager.usercontrol(txtusern.getText(),txtpasw.getText()).getusername());
						User.loginuser.setusercontrol(manager.usercontrol(txtusern.getText(),txtpasw.getText()).getloginuser());
						
						if(User.loginuser.getloginuser()==0)
						{ekutuphanemenu form = new ekutuphanemenu();frame.dispose(); form.frame.setVisible(true);}
						else if(User.loginuser.getloginuser()==2)
						{adminpanel form = new adminpanel();frame.dispose(); form.setVisible(true); }
						else if(User.loginuser.getloginuser()==1)
						{yetkilipanel form = new yetkilipanel();frame.dispose();form.setVisible(true); }
					
					}
					else {
						JOptionPane.showMessageDialog(frame,"Kullanıcı adı veya şifre yanlış!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGirisYap.setBounds(149, 258, 89, 23);
		frame.getContentPane().add(btnGirisYap);
		
		txtpasw = new JPasswordField();
		txtpasw.setBounds(142, 198, 137, 20);
		frame.getContentPane().add(txtpasw);
		
		JButton btnKaytOl = new JButton("Kayıt Ol");
		btnKaytOl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kayitform.frame.setVisible(true);
				frame.dispose();
			}
		});
		btnKaytOl.setBounds(317, 284, 89, 23);
		frame.getContentPane().add(btnKaytOl);
	}

}
