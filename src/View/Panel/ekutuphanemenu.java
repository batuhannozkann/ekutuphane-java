package View.Panel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import Model.User;
import View.ekutuphanebooks;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ekutuphanemenu {

	public static JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ekutuphanemenu window = new ekutuphanemenu();
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
	public ekutuphanemenu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 388);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnkitaplist = new JButton("Kitap Listesi");
		btnkitaplist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				try {
					ekutuphanebooks booklist =new ekutuphanebooks();
					booklist.frame.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnkitaplist.setBounds(289, 135, 152, 42);
		frame.getContentPane().add(btnkitaplist);
		
		JLabel kullaniciname = new JLabel("New label");
		kullaniciname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		kullaniciname.setBounds(50, 93, 213, 42);
		frame.getContentPane().add(kullaniciname);
		kullaniciname.setText("Hoşgeldiniz "+User.loginuser.getusername());
	}
}
