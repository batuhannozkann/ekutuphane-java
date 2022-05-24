package View.Panelitem;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ekutuphane.dbmanager;
import ekutuphane.progmanager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class requestbook extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtname;
	private JTextField txtauthor;
	private JTextField txtpage;
	private JTextField txtdesc;
	private JTextField txtkategori;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					requestbook frame = new requestbook();
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
	public requestbook() throws SQLException {
		Map<String,Integer> changetoint = new HashMap<String,Integer>();
		dbmanager manager = new dbmanager();
		for(int a=0;a<manager.GetCategory().size();a++)
		{
			changetoint.put(manager.GetCategory().get(a).getkategori(),manager.GetCategory().get(a).getidkategori());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 717, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 510, 378);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(progmanager.getrequestbook());
		
		JButton btnNewButton = new JButton("Onayla");
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() 
		{
			@Override
			public void valueChanged(ListSelectionEvent e) {
				{
					if(table.getSelectedRow()>-1) {
						txtname.setText(table.getValueAt(table.getSelectedRow(),0).toString());
						txtauthor.setText(table.getValueAt(table.getSelectedRow(),1).toString());
						txtpage.setText(table.getValueAt(table.getSelectedRow(),2).toString());
						txtkategori.setText(table.getValueAt(table.getSelectedRow(),4).toString());
						txtdesc.setText(table.getValueAt(table.getSelectedRow(),3).toString());
						
						}
						else
						{
							txtname.setText(null);
							txtauthor.setText(null);
							txtpage.setText(null);
							txtkategori.setText(null);
							txtdesc.setText(null);
						}
					
				}
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(table.getSelectedRow()>-1) {
					//manager.InsertBook(table.getValueAt(table.getSelectedRow(),0).toString(),table.getValueAt(table.getSelectedRow(),1).toString(),table.getValueAt(table.getSelectedRow(),3).toString(),table.getValueAt(table.getSelectedRow(),2).toString(),changetoint.get(table.getValueAt(table.getSelectedRow(),4).toString()));
					manager.InsertBook(txtname.getText(),txtauthor.getText(),txtdesc.getText(),txtpage.getText(),changetoint.get(txtkategori.getText()));
					manager.removerequest(txtname.getText());
					JOptionPane.showMessageDialog(requestbook.this,"Kitap Eklendi");
					table.setModel(progmanager.getrequestbook());
					}
					else
					{
						JOptionPane.showMessageDialog(requestbook.this,"Kitap Seçiniz");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(553, 11, 80, 39);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Reddet");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					manager.removerequest(txtname.getText());
					table.setModel(progmanager.getrequestbook());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(553, 61, 80, 39);
		contentPane.add(btnNewButton_1);
		
		txtname = new JTextField();
		txtname.setBounds(547, 173, 86, 20);
		contentPane.add(txtname);
		txtname.setColumns(10);
		txtname.hide();
		
		txtauthor = new JTextField();
		txtauthor.setBounds(547, 204, 86, 20);
		contentPane.add(txtauthor);
		txtauthor.setColumns(10);
		txtauthor.hide();
		
		txtpage = new JTextField();
		txtpage.setBounds(547, 235, 86, 20);
		contentPane.add(txtpage);
		txtpage.setColumns(10);
		txtpage.hide();
		
		txtdesc = new JTextField();
		txtdesc.setBounds(547, 266, 86, 20);
		contentPane.add(txtdesc);
		txtdesc.setColumns(10);
		txtdesc.hide();
		
		txtkategori = new JTextField();
		txtkategori.setBounds(547, 297, 86, 20);
		contentPane.add(txtkategori);
		txtkategori.setColumns(10);
		
		btnNewButton_2 = new JButton("<=");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					requestbook.this.dispose();
					Addbook panel = new Addbook();
					panel.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(553, 339, 80, 39);
		contentPane.add(btnNewButton_2);
		txtkategori.hide();
	}
}
