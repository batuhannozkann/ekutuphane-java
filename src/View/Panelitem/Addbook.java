package View.Panelitem;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.User;
import View.Panel.adminpanel;
import View.Panel.yetkilipanel;
import ekutuphane.dbmanager;
import ekutuphane.progmanager;

import javax.swing.JLabel;
import java.awt.Font;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import java.awt.Button;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Addbook extends JFrame {

	private JPanel contentPane;
	private JTextField txtbookname;
	private JTextField txtauthor;
	private JTextField txtarama;
	private JTable table;
	private JTextField txtpage;
	private JTextField txtid;
	private JTextField txtaddcat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Addbook frame = new Addbook();
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
	public Addbook() throws SQLException {
		progmanager progmanager= new progmanager();
		dbmanager manager = new dbmanager();
		String[] category = new String[manager.GetCategory().toArray().length];
		Map<Integer, String> changetoname = new HashMap<Integer, String>();
		Map<String,Integer> changetoint = new HashMap<String,Integer>();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoname.put(manager.GetCategory().get(a).getidkategori(),manager.GetCategory().get(a).getkategori());
		}
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoint.put(manager.GetCategory().get(a).getkategori(),manager.GetCategory().get(a).getidkategori());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1068, 577);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kitap Ad:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(684, 85, 88, 30);
		contentPane.add(lblNewLabel);
		
		txtbookname = new JTextField();
		txtbookname.setBounds(784, 91, 237, 23);
		contentPane.add(txtbookname);
		txtbookname.setColumns(10);
		
		txtauthor = new JTextField();
		txtauthor.setColumns(10);
		txtauthor.setBounds(784, 142, 237, 23);
		contentPane.add(txtauthor);
		
		JLabel lblYazar = new JLabel("Yazar:");
		lblYazar.setHorizontalAlignment(SwingConstants.LEFT);
		lblYazar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYazar.setBounds(684, 136, 88, 30);
		contentPane.add(lblYazar);
		
		JLabel lblAklama = new JLabel("Açıklama:");
		lblAklama.setHorizontalAlignment(SwingConstants.LEFT);
		lblAklama.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAklama.setBounds(686, 301, 88, 30);
		contentPane.add(lblAklama);
		
		JTextArea descarea = new JTextArea();
		descarea.setBounds(784, 272, 237, 95);
		contentPane.add(descarea);
		
		JLabel lblKategori = new JLabel("Kategori:");
		lblKategori.setHorizontalAlignment(SwingConstants.LEFT);
		lblKategori.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblKategori.setBounds(686, 203, 88, 30);
		contentPane.add(lblKategori);
		
		JComboBox editcategory = new JComboBox(progmanager.getCategories());
		editcategory.setBounds(784, 208, 237, 24);
		contentPane.add(editcategory);
		
		JLabel lblNewLabel_1 = new JLabel("Kitap Yönetimi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(161, 11, 199, 30);
		contentPane.add(lblNewLabel_1);
		
		JButton btnadd = new JButton("Ekle");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(User.loginuser.getloginuser()==1)
					{manager.RequestBook(txtbookname.getText(),txtauthor.getText(),descarea.getText(),txtpage.getText(),changetoint.get(editcategory.getSelectedItem().toString()));
					JOptionPane.showMessageDialog(Addbook.this,"Kitap Ekleme İsteği Gönderildi");}
					else if(User.loginuser.getloginuser()==2)
					{manager.InsertBook(txtbookname.getText(),txtauthor.getText(),descarea.getText(),txtpage.getText(),changetoint.get(editcategory.getSelectedItem().toString()));
					table.setModel(progmanager.getadminsearchtable(txtarama.getText()));
					JOptionPane.showMessageDialog(Addbook.this,"Kitap Eklendi");}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//editcategory.opti
				
			}
		});
		btnadd.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnadd.setBounds(845, 399, 116, 30);
		contentPane.add(btnadd);
		
		JButton btnupdate = new JButton("Güncelle");
		btnupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(User.loginuser.getloginuser()==1)
					{JOptionPane.showMessageDialog(Addbook.this,"Yetkiniz Yok");}
					else{manager.UpdateBook(txtbookname.getText(),txtauthor.getText(),descarea.getText(),txtpage.getText(),changetoint.get(editcategory.getSelectedItem().toString()),Integer.parseInt(txtid.getText()));
					table.setModel(progmanager.getadminsearchtable(txtarama.getText()));
					JOptionPane.showMessageDialog(Addbook.this,"Kitap Güncellendi");}
				} catch (NumberFormatException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnupdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnupdate.setBounds(845, 439, 116, 30);
		contentPane.add(btnupdate);
		
		JButton btndelete = new JButton("Sil");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtid.getText().isBlank())
				{
				try {
					if(User.loginuser.getloginuser()==1)
					{JOptionPane.showMessageDialog(Addbook.this,"Yetkiniz Yok");}
					else
					{manager.RemoveBook(Integer.parseInt(txtid.getText()));
					JOptionPane.showMessageDialog(Addbook.this,"Kitap Silindi");}
				} catch (NumberFormatException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					table.setModel(progmanager.getadminsearchtable(txtarama.getText()));
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}}
		});
		btndelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btndelete.setBounds(845, 480, 116, 30);
		contentPane.add(btndelete);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 65, 665, 462);
		contentPane.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Kitap Adı:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(477, 78, 93, 20);
		panel.add(lblNewLabel_2);
		
		JLabel lblKategori_1 = new JLabel("Kategori:");
		lblKategori_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKategori_1.setBounds(477, 121, 93, 20);
		panel.add(lblKategori_1);
		
		JComboBox categorybox = new JComboBox(progmanager.getCategories());
		categorybox.setMaximumRowCount(50);
		categorybox.setBounds(554, 121, 100, 21);
		panel.add(categorybox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 461, 440);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2_1 = new JLabel("Kitap Arama");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2_1.setBounds(527, 24, 85, 17);
		panel.add(lblNewLabel_2_1);
		JLabel lblpage = new JLabel("Sayfa:");
		lblpage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblpage.setBounds(684, 402, 44, 23);
		contentPane.add(lblpage);
		
		txtpage = new JTextField();
		txtpage.setBounds(735, 399, 59, 33);
		contentPane.add(txtpage);
		txtpage.setColumns(10);
		
		txtarama = new JTextField();
		txtarama.setColumns(10);
		txtarama.setBounds(554, 78, 100, 20);
		panel.add(txtarama);
		
		Button button = new Button("ARA");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(categorybox.getSelectedIndex()<=-1)
				{
					try {
						table.setModel(progmanager.getadminsearchtable(txtarama.getText()));
						//table.setModel(progmanager.getsearchtablemodel(txtarama.getText(),table));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else if(!categorybox.getSelectedItem().toString().isBlank())
				{
					try {
						//table.setModel(progmanager.getsearchtablemodel(txtarama.getText(),categorybox.getSelectedItem().toString(), table));
						table.setModel(progmanager.getadminsearchtable(txtarama.getText(),categorybox.getSelectedItem().toString()));
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		button.setBounds(558, 184, 70, 22);
		panel.add(button);
		table.setModel(progmanager.getadminsearchtable(txtarama.getText()));
		
		txtid = new JTextField();
		txtid.setEditable(false);
		txtid.setBounds(850, 39, 44, 30);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("İd:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(825, 45, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnmenu = new JButton("Menü");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(User.loginuser.getloginuser()==2)
				{Addbook.this.dispose();
				adminpanel panel = new adminpanel();
				panel.setVisible(true);}
				else if(User.loginuser.getloginuser()==1)
				{
					Addbook.this.dispose();
					yetkilipanel ytpanel = new yetkilipanel();
					ytpanel.setVisible(true);
				}
				
			}
		});
		btnmenu.setBounds(10, 17, 89, 23);
		contentPane.add(btnmenu);
		
		JLabel lblNewLabel_4 = new JLabel("KATEGORİ EKLE:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(405, 18, 108, 23);
		contentPane.add(lblNewLabel_4);
		
		txtaddcat = new JTextField();
		txtaddcat.setBounds(511, 18, 160, 20);
		contentPane.add(txtaddcat);
		txtaddcat.setColumns(10);
		
		JButton btnNewButton = new JButton(">");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(User.loginuser.getloginuser()==2)
				{
				try {
					if(!txtaddcat.getText().isBlank())
					{manager.InsertCategory(txtaddcat.getText());
					JOptionPane.showMessageDialog(Addbook.this,"Kategori Eklendi");}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
				else {JOptionPane.showMessageDialog(Addbook.this,"Yetkiniz Yok");}
		}});
		btnNewButton.setBounds(683, 17, 41, 23);
		contentPane.add(btnNewButton);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				{
					if(table.getSelectedRow()>-1) {
						txtbookname.setText(table.getValueAt(table.getSelectedRow(),0).toString());
						txtauthor.setText(table.getValueAt(table.getSelectedRow(),1).toString());
						txtpage.setText(table.getValueAt(table.getSelectedRow(),2).toString());
						descarea.setText(table.getValueAt(table.getSelectedRow(),3).toString());
						editcategory.setSelectedItem(table.getValueAt(table.getSelectedRow(),4).toString());
						txtid.setText(table.getValueAt(table.getSelectedRow(),5).toString());
					}
					else
					{
						txtbookname.setText(null);
						txtauthor.setText(null);
						txtpage.setText(null);
						descarea.setText(null);
					}
				}
			}
		});
	
		
		
	}
}

