package View.Panel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.User;
import View.ekutuphanebooks;
import View.Panelitem.Addbook;
import View.Panelitem.UserManage;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class adminpanel extends JFrame {

	public static JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adminpanel frame = new adminpanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public adminpanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnyonetici = new JButton("Yönetici Paneli");
		btnyonetici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					adminpanel.this.dispose();
					UserManage useradd= new UserManage();
					useradd.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
						
			}
		});
		btnyonetici.setBounds(306, 55, 152, 42);
		contentPane.add(btnyonetici);
		
		JButton btnkitaplist = new JButton("Kitap Listesi");
		btnkitaplist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ekutuphanebooks booklist = new ekutuphanebooks();
					booklist.frame.setVisible(true);
					adminpanel.this.dispose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		btnkitaplist.setBounds(306, 144, 152, 42);
		contentPane.add(btnkitaplist);
		
		JButton btnkitapekle = new JButton("Kitap Ekle/Güncelle");
		btnkitapekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Addbook addbook;
				try {
					adminpanel.this.dispose();
					addbook = new Addbook();
					addbook.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnkitapekle.setBounds(306, 239, 152, 42);
		contentPane.add(btnkitapekle);
		
		JLabel kullaniciname = new JLabel("New label");
		kullaniciname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kullaniciname.setBounds(50, 93, 224, 42);
		contentPane.add(kullaniciname);
		kullaniciname.setText("Hoşgeldiniz "+User.loginuser.getusername());
	}

}
