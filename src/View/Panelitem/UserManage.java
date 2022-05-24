package View.Panelitem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Model.User;
import View.Panel.adminpanel;
import ekutuphane.dbmanager;
import ekutuphane.progmanager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserManage extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JTextField txtpassword;
	private JTextField txtarama;
	private JTextField txtid;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManage frame = new UserManage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UserManage() throws SQLException {
		Map<Integer,String> changetoname = new HashMap<Integer,String>();
		Map<String,Integer> changetoint=new HashMap<String,Integer>();
		changetoname.put(0,"Normal Uye");
		changetoname.put(1, "Orta Seviye Uye");
		changetoname.put(2, "Yonetici");
		changetoint.put("Normal Uye",0);
		changetoint.put("Orta Seviye Uye",1);
		changetoint.put("Yonetici",2);
		dbmanager manager = new dbmanager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1104, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKullancAd = new JLabel("Kullanıcı Adı:");
		lblKullancAd.setHorizontalAlignment(SwingConstants.LEFT);
		lblKullancAd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKullancAd.setBounds(704, 100, 88, 30);
		contentPane.add(lblKullancAd);
		
		txtusername = new JTextField();
		txtusername.setColumns(10);
		txtusername.setBounds(784, 106, 237, 23);
		contentPane.add(txtusername);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(784, 157, 237, 23);
		contentPane.add(txtpassword);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(704, 151, 88, 30);
		contentPane.add(lblPassword);
		
		JLabel lblUsercontrol = new JLabel("Yetki:");
		lblUsercontrol.setHorizontalAlignment(SwingConstants.LEFT);
		lblUsercontrol.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsercontrol.setBounds(704, 218, 88, 30);
		contentPane.add(lblUsercontrol);
		
		JComboBox editauth = new JComboBox(progmanager.fiiluserbox());
		editauth.setBounds(784, 223, 237, 24);
		contentPane.add(editauth);
		
		JLabel lblNewLabel_1 = new JLabel("Kullanıcı Yönetimi");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(389, 26, 199, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btnupdate = new JButton("Güncelle");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!(editauth.getSelectedItem().toString().isBlank())) {
					manager.UpdateUser(txtusername.getText(),txtpassword.getText(),changetoint.get(editauth.getSelectedItem().toString()),Integer.parseInt(txtid.getText()));
					JOptionPane.showMessageDialog(UserManage.this,"Kullanıcı Güncellendi");
					table.setModel(progmanager.getUserstable());
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnupdate.setBounds(845, 302, 116, 30);
		contentPane.add(btnupdate);
		
		JButton btndelete = new JButton("Sil");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtid.getText().isBlank())
				{
					try {
						manager.RemoveUser(Integer.parseInt(txtid.getText()));
						JOptionPane.showMessageDialog(UserManage.this,"Kullanıcı Silindi");
						table.setModel(progmanager.getUserstable());
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btndelete.setBounds(845, 343, 116, 30);
		contentPane.add(btndelete);
		
		JLabel lblNewLabel_2 = new JLabel("Kullanıcı adı:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(477, 158, 93, 20);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblKategori_1 = new JLabel("Yetki:");
		lblKategori_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKategori_1.setBounds(495, 199, 93, 20);
		contentPane.add(lblKategori_1);
		
		JComboBox yetkibox = new JComboBox(progmanager.fiiluserbox());
		yetkibox.setMaximumRowCount(50);
		yetkibox.setBounds(569, 201, 100, 21);
		contentPane.add(yetkibox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 91, 461, 440);
		contentPane.add(scrollPane);
		
		table = new JTable(progmanager.getUserstable());
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_1 = new JLabel("Kitap Arama");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(527, 104, 85, 17);
		contentPane.add(lblNewLabel_2_1);
		
		txtarama = new JTextField();
		txtarama.setColumns(10);
		txtarama.setBounds(569, 158, 100, 20);
		contentPane.add(txtarama);
		
		Button button = new Button("ARA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!(yetkibox.getSelectedIndex()>-1))
				{
					try {
						table.setModel(progmanager.getSearchUser(txtarama.getText()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(yetkibox.getSelectedIndex()>-1)
				{
					try {
						table.setModel(progmanager.getSearchUser(txtarama.getText(),yetkibox.getSelectedItem().toString()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(558, 264, 70, 22);
		contentPane.add(button);
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setColumns(10);
		txtid.setBounds(850, 54, 44, 30);
		contentPane.add(txtid);
		
		JLabel lblNewLabel_3 = new JLabel("İd:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(825, 60, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnmenu = new JButton("Menü");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserManage.this.dispose();
				adminpanel panel = new adminpanel();
				panel.setVisible(true);
			}
		});
		btnmenu.setBounds(25, 26, 89, 23);
		contentPane.add(btnmenu);
		
		JButton btnrequest = new JButton("Kitap Ekleme İsteği");
		btnrequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UserManage.this.dispose();
					requestbook panel = new requestbook();
					panel.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnrequest.setBounds(132, 26, 123, 23);
		contentPane.add(btnrequest);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				{
					if(table.getSelectedRow()>-1) {
						txtid.setText(table.getValueAt(table.getSelectedRow(),0).toString());
						txtusername.setText(table.getValueAt(table.getSelectedRow(),1).toString());
						txtpassword.setText(table.getValueAt(table.getSelectedRow(),2).toString());
						editauth.setSelectedItem(table.getValueAt(table.getSelectedRow(),3).toString());
					}
					else
					{
						txtid.setText(null);
						txtusername.setText(null);
						txtpassword.setText(null);
					}
				}
			}
		});
	}
}
