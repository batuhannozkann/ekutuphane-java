package View;

import java.awt.EventQueue;
import ekutuphane.dbmanager;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ekutuphane {

	public static JFrame frame;
	public JTextField txtusern;
	public JPasswordField txtpasw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ekutuphane window = new ekutuphane();
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
	public ekutuphane() {
		initialize();
	}
	
	@SuppressWarnings("deprecation")

	
	
	/**
	 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 395);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KAYIT OLMA FORMU");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(lblNewLabel.getFont().deriveFont(14f));
		lblNewLabel.setBounds(114, 47, 181, 31);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblusern = new JLabel("Kullanıcı adı:");
		lblusern.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblusern.setBounds(45, 132, 92, 25);
		frame.getContentPane().add(lblusern);
		
		JLabel lblpasw = new JLabel("Password:");
		lblpasw.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblpasw.setBounds(45, 197, 92, 25);
		frame.getContentPane().add(lblpasw);
		
		txtusern = new JTextField();
		txtusern.setBounds(147, 136, 137, 20);
		frame.getContentPane().add(txtusern);
		txtusern.setColumns(10);
		
		JButton btnkayitol = new JButton("Kayıt Ol");
		btnkayitol.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dbmanager manager = new dbmanager();
				try {
					 
					if(manager.Allusername().contains(txtusern.getText())&&!txtusern.getText().isEmpty())
					{
						JOptionPane.showMessageDialog(frame, "Kullanıcı adı sistemde kayıtlı");
					}
					else if(txtusern.getText().isBlank()|| txtpasw.getText().isBlank())
					{
						JOptionPane.showMessageDialog(frame, "Boş Alan Bırakmayınız");
					}
					else
					{
						manager.Insert(txtusern.getText(),txtpasw.getText());
						
						
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnkayitol.setBounds(165, 271, 89, 23);
		frame.getContentPane().add(btnkayitol);
		
		txtpasw = new JPasswordField();
		txtpasw.setBounds(147, 201, 137, 20);
		frame.getContentPane().add(txtpasw);
		
		JButton btnNewButton = new JButton("Giriş Ekranı");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				ekutuphanelogin form=new ekutuphanelogin();
				form.frame.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(318, 25, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
