package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Helper.Helper;
import Model.Yönetici;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class YöneticiGUI extends JFrame {
	
	
	
	private JPanel contentPane;
	private JTextField isim;
	private JTextField þifre;
	private JTextField textField_2;
	private JTable table;
	
	static Yönetici yönetici=new Yönetici();
	private DefaultTableModel personelModel = null;
	private Object[] personelData = null;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					YöneticiGUI frame = new YöneticiGUI(yönetici);
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
	 * @throws ClassNotFoundException 
	 */
	public YöneticiGUI(Yönetici yönetici) throws SQLException, ClassNotFoundException {
		
		personelModel = new DefaultTableModel();
		Object[] colPersonel = new Object[3];
		colPersonel[0] = "ID";
		colPersonel[1] = "ÞÝFRE";
		colPersonel[2] = "ÝSÝM";
		personelModel.setColumnIdentifiers(colPersonel);
		personelData = new Object[3];
		for (int i = 0; i < yönetici.getList().size(); i++) {
			personelData[0] = yönetici.getList().get(i).getUser_id();
			personelData[1] = yönetici.getList().get(i).getÞifre();
			personelData[2] = yönetici.getList().get(i).getIsim();
			personelModel.addRow(personelData);
		}
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Ho\u015Fgeldiniz, Say\u0131n "+ yönetici.getIsim());
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label.setBounds(10, 29, 283, 24);
		contentPane.add(label);
		
		JButton btnk = new JButton("\u00C7\u0131k\u0131\u015F");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnk.setBounds(545, 63, 89, 23);
		contentPane.add(btnk);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 133, 664, 317);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Personel Yönetimi", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblIsim = new JLabel("\u0130sim");
		lblIsim.setBounds(472, 14, 89, 25);
		lblIsim.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel.add(lblIsim);
		
		isim = new JTextField();
		isim.setBounds(472, 50, 177, 25);
		isim.setColumns(10);
		panel.add(isim);
		
		JButton button = new JButton("Ekle");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isim.getText().length() == 0 || þifre.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {
					try {
						boolean control = yönetici.add(þifre.getText(), isim.getText());
						if (control) {
							Helper.showMsg("succes");
							isim.setText(null);
							þifre.setText(null);
							updatePersonelModel();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		button.setBounds(472, 147, 177, 29);
		button.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel.add(button);
		
		JLabel label_2 = new JLabel("\u015Eifre");
		label_2.setBounds(472, 86, 89, 25);
		label_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel.add(label_2);
		
		þifre = new JTextField();
		þifre.setBounds(472, 111, 177, 25);
		þifre.setColumns(10);
		panel.add(þifre);
		
		JLabel lblUserId = new JLabel("User id");
		lblUserId.setBounds(472, 187, 89, 25);
		lblUserId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel.add(lblUserId);
		
		textField_2 = new JTextField();
		textField_2.setBounds(472, 213, 177, 25);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		JButton button_1 = new JButton("Sil");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectID = Integer.parseInt(textField_2.getText());
				try {
					boolean control = yönetici.delete(selectID);
					if (control) {
						Helper.showMsg("succes");
						textField_2.setText(null);
						updatePersonelModel();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		button_1.setBounds(472, 249, 177, 29);
		button_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 14));
		panel.add(button_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 14, 452, 264);
		panel.add(scrollPane);
		
		table = new JTable(personelModel);
		scrollPane.setViewportView(table);
		
		JLabel lblifreVeIsmi = new JLabel("!!!   Bilgileri g\u00FCncellemek i\u00E7in istedi\u011Finiz kutucu\u011Fa t\u0131klay\u0131n   !!!");
		lblifreVeIsmi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblifreVeIsmi.setBounds(160, 117, 377, 24);
		contentPane.add(lblifreVeIsmi);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				try {
					textField_2.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				} catch (Exception ex) {

				}
				
			}
		});
		
		
		table.getModel().addTableModelListener(new TableModelListener() {
			
			@Override
			public void tableChanged(TableModelEvent e) {
				if (e.getType() == TableModelEvent.UPDATE) {
					int selectID = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					String selectÞifre = table.getValueAt(table.getSelectedRow(), 1).toString();
					String selectÝsim = table.getValueAt(table.getSelectedRow(), 2).toString();
					try {
						boolean control = yönetici.update(selectID, selectÞifre, selectÝsim);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		
		
	}
	
	
	public void updatePersonelModel() throws SQLException, ClassNotFoundException {
		DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
		clearModel.setRowCount(0);
		for (int i = 0; i < yönetici.getList().size(); i++) {
			personelData[0] = yönetici.getList().get(i).getUser_id();
			personelData[1] = yönetici.getList().get(i).getÞifre();
			personelData[2] = yönetici.getList().get(i).getIsim();
			personelModel.addRow(personelData);
		}
	}
	
	
}
