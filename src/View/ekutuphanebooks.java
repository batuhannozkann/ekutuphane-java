package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Model.Book;
import Model.User;
import View.Panel.adminpanel;
import View.Panel.ekutuphanemenu;
import View.Panel.yetkilipanel;
import ekutuphane.dbmanager;
import ekutuphane.progmanager;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.SystemColor;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.UIManager;
import java.awt.Button;
import javax.swing.JButton;

public class ekutuphanebooks {

	public JFrame frame;
	private JTextField txtarama;
	private JTable table;
	private JTextField txtkitapad;
	private JTextField txtyazar;
	private JTextField txtpage;
	private JTextField txtkategori;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ekutuphanebooks window = new ekutuphanebooks();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public ekutuphanebooks() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		progmanager tablecs=new progmanager();
		frame = new JFrame();
		dbmanager manager = new dbmanager();
		frame.setBounds(100, 100, 794, 561);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 130, 747, 381);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Kitap Listesi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kitap Adı:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(555, 72, 93, 20);
		panel.add(lblNewLabel);
		
		JLabel lblKategori = new JLabel("Kategori:");
		lblKategori.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKategori.setBounds(555, 115, 93, 20);
		panel.add(lblKategori);
		JComboBox categorycmbox = new JComboBox(progmanager.getCategories());
		categorycmbox.setMaximumRowCount(50);
		categorycmbox.setBounds(632, 115, 100, 21);
		panel.add(categorycmbox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 535, 331);
		panel.add(scrollPane);
		JTable table = new JTable(progmanager.gettablemodel());
		table.setFillsViewportHeight(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBorder(null);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Kitap Arama");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setBounds(605, 18, 85, 17);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Kitap Adı");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(39, 38, 56, 17);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Yazar");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(170, 38, 56, 17);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Sayfa Sayısı");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(281, 38, 71, 17);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Kategori");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(390, 38, 50, 17);
		frame.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Açıklama");
		lblNewLabel_1_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(582, 11, 77, 14);
		frame.getContentPane().add(lblNewLabel_1_4);
		
		txtkitapad = new JTextField();
		txtkitapad.setEditable(false);
		txtkitapad.setBounds(10, 66, 122, 24);
		frame.getContentPane().add(txtkitapad);
		txtkitapad.setColumns(50);
		
		txtyazar = new JTextField();
		txtyazar.setEditable(false);
		txtyazar.setBounds(142, 66, 122, 24);
		frame.getContentPane().add(txtyazar);
		txtyazar.setColumns(50);
		
		txtpage = new JTextField();
		txtpage.setEditable(false);
		txtpage.setBounds(291, 66, 56, 24);
		frame.getContentPane().add(txtpage);
		txtpage.setColumns(50);
		
		txtkategori = new JTextField();
		txtkategori.setEditable(false);
		txtkategori.setBounds(368, 66, 92, 24);
		frame.getContentPane().add(txtkategori);
		txtkategori.setColumns(50);
		
		txtarama = new JTextField();
		txtarama.setBounds(632, 72, 100, 20);
		panel.add(txtarama);
		txtarama.setColumns(10);
		
		Button button = new Button("ARA");
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(categorycmbox.getSelectedIndex()==-1)
					{
						table.setModel(progmanager.getsearchtablemodel(txtarama.getText()));
					}
					else if(!(categorycmbox.getSelectedItem().toString().isBlank()))
						
					{
						table.setModel(progmanager.getsearchtablemodel(txtarama.getText(),categorycmbox.getSelectedItem().toString()));
					}
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setBounds(636, 178, 70, 22);
		panel.add(button);
		
		JButton btnNewButton = new JButton("İndir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fs = new JFileChooser(new File("C:/"));
				fs.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int response=fs.showOpenDialog(frame);
				
				if( response == fs.APPROVE_OPTION)
				{
					File fi=fs.getSelectedFile();
					System.out.println(fi.getPath());
					try {
						progmanager.Downloader("https://cdn.discordapp.com/attachments/961045156126134315/976236584678608946/sql.sql",fi.getPath());
						Desktop.getDesktop().browse(new URL("https://cdn.discordapp.com/attachments/961045156126134315/976236584678608946/sql.sql").toURI());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (URISyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			

	
			
			}
		});
		btnNewButton.setBounds(589, 250, 89, 23);
		panel.add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(470, 41, 287, 99);
		frame.getContentPane().add(textArea);
		
		JButton btnmenu = new JButton("Menü");
		btnmenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(User.loginuser.getloginuser()==0)
				{
					frame.dispose();
					ekutuphanemenu menu = new ekutuphanemenu();
					menu.frame.setVisible(true);
				}
				else if(User.loginuser.getloginuser()==1)
				{
					frame.dispose();
					yetkilipanel panel = new yetkilipanel();
					panel.setVisible(true);
				}
				else if(User.loginuser.getloginuser()==2)
				{
					frame.dispose();
					adminpanel panel = new adminpanel();
					panel.setVisible(true);
				}
			}
		});
		btnmenu.setBounds(10, 4, 89, 23);
		frame.getContentPane().add(btnmenu);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{

			@Override
			public void valueChanged(ListSelectionEvent e) {
				System.out.println(table.getSelectedRow());
			
				{
					if(table.getSelectedRow()>-1) {
				txtkitapad.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				txtyazar.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
				txtpage.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				txtkategori.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				textArea.setText(table.getValueAt(table.getSelectedRow(), 3).toString());}
					else
					{
						txtkitapad.setText(null);
						txtyazar.setText(null);
						txtpage.setText(null);
						txtkategori.setText(null);
						textArea.setText(null);
					}
					
				
				
					
				
				}
			}
			
		}
				
				);
		
		
	}
}

