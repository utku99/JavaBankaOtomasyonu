package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Helper;
import Model.Müþteri;
import Model.Personel;
import Model.Yönetici;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField;
	private JTextField textField_1;
	
	private DBConnection conn=new DBConnection();
	private JTextField müþteri_tc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
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
	public LoginGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(10, 120, 463, 230);
		contentPane.add(tabbedPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		tabbedPane.addTab("Yönetici Giriþi", null, panel_2, null);
		panel_2.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setColumns(10);
		textField.setBounds(96, 37, 343, 30);
		panel_2.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBounds(96, 78, 343, 30);
		panel_2.add(textField_1);
		
		JLabel lblIsim = new JLabel("\u0130sim  :");
		lblIsim.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblIsim.setBounds(10, 36, 76, 30);
		panel_2.add(lblIsim);
		
		JLabel label_3 = new JLabel("\u015Eifre :");
		label_3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_3.setBounds(10, 77, 76, 30);
		panel_2.add(label_3);
		
		JButton button = new JButton("Giri\u015F Yap");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()==0 || textField_1.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						java.sql.Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM kullanýcý WHERE type = 'yönetici'");
						while(rs.next()) {
							if(textField.getText().equals(rs.getString("isim")) && textField_1.getText().equals(rs.getString("þifre"))) {
								Yönetici yönetici=new Yönetici();
								yönetici.setUser_id(rs.getInt("user_id"));
								yönetici.setIsim(rs.getString("isim"));
								yönetici.setÞifre(rs.getString("þifre"));
								yönetici.setType(rs.getString("type"));
								YöneticiGUI yGUI=new YöneticiGUI(yönetici);
								yGUI.setVisible(true);
								
							}
						}
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		button.setBounds(10, 137, 429, 30);
		panel_2.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Personel Giriþi", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblIsim_1 = new JLabel("\u0130sim  :");
		lblIsim_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblIsim_1.setBounds(10, 35, 76, 30);
		panel_1.add(lblIsim_1);
		
		JLabel label_1 = new JLabel("\u015Eifre :");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		label_1.setBounds(10, 76, 76, 30);
		panel_1.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setColumns(10);
		textField_2.setBounds(96, 36, 343, 30);
		panel_1.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_3.setColumns(10);
		textField_3.setBounds(96, 77, 343, 30);
		panel_1.add(textField_3);
		
		JButton button_1 = new JButton("Giri\u015F Yap");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField_2.getText().length()==0 || textField_3.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						java.sql.Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT * FROM kullanýcý WHERE type = 'personel'");
						while(rs.next()) {
							if(textField_2.getText().equals(rs.getString("isim")) && textField_3.getText().equals(rs.getString("þifre"))) {
								Müþteri müþteri=new Müþteri();
								PersonelGUI pGUI=new PersonelGUI(müþteri);
								pGUI.setVisible(true);
								
							}
						}
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		button_1.setBounds(10, 136, 429, 30);
		panel_1.add(button_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Diðer Müþteri Ýþlemleri", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblMteriTc = new JLabel("M\u00FC\u015Fteri tc            :");
		lblMteriTc.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblMteriTc.setBounds(10, 56, 151, 30);
		panel.add(lblMteriTc);
		
		müþteri_tc = new JTextField();
		müþteri_tc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		müþteri_tc.setColumns(10);
		müþteri_tc.setBounds(171, 57, 268, 30);
		panel.add(müþteri_tc);
		
		JButton button_2 = new JButton("Giri\u015F Yap");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(müþteri_tc.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						java.sql.Statement st=con.createStatement();
						ResultSet rs=st.executeQuery("SELECT tcno FROM müþteri");
						while(rs.next()) {
							if(müþteri_tc.getText().equals(rs.getString("tcno"))) {
								Müþteri müþteri=new Müþteri();
								müþteri.setTcno(rs.getString("tcno"));
								MüþteriGUI mGUI=new MüþteriGUI(müþteri);
								mGUI.setVisible(true);
								
							}
						}
					} catch (SQLException e1) {
				
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		});
		button_2.setBounds(10, 136, 429, 30);
		panel.add(button_2);
		
		JLabel lblNewLabel = new JLabel("BANKA OTOMASYONUNA HO\u015EGELD\u0130N\u0130Z");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(27, 25, 427, 84);
		contentPane.add(lblNewLabel);
	}
}
