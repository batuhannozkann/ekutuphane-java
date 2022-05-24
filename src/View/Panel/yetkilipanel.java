package View.Panel;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.User;
import View.ekutuphanebooks;
import View.ekutuphanelogin;
import View.Panelitem.Addbook;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class yetkilipanel extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					yetkilipanel frame = new yetkilipanel();
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
	public yetkilipanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnkitaplist = new JButton("Kitap Listesi");
		btnkitaplist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ekutuphanebooks booklist = new ekutuphanebooks();
					booklist.frame.setVisible(true);
					yetkilipanel.this.dispose();
					} 
				catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnkitaplist.setBounds(319, 78, 152, 42);
		contentPane.add(btnkitaplist);
		
		JButton btnkitapekle = new JButton("Kitap Ekle");
		btnkitapekle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yetkilipanel.this.dispose();
				Addbook bookpanel;
				try {
					bookpanel = new Addbook();
					bookpanel.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnkitapekle.setBounds(319, 175, 152, 42);
		contentPane.add(btnkitapekle);
		
		JLabel kullaniciname = new JLabel("New label");
		kullaniciname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kullaniciname.setBounds(50, 93, 204, 27);
		contentPane.add(kullaniciname);
		ekutuphanelogin log = new ekutuphanelogin();
		kullaniciname.setText("Hoşgeldiniz "+User.loginuser.getusername());
	}

}
