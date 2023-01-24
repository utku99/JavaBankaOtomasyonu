package popupGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Helper;
import Model.Hesap;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class HesapParaÇekGUI extends JFrame {

	static Hesap hesap=new Hesap();
	DBConnection conn = new DBConnection();
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HesapParaÇekGUI frame = new HesapParaÇekGUI(hesap);
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
	public HesapParaÇekGUI(Hesap hesap) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton button = new JButton("\u00C7\u0131k");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(335, 11, 89, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("\u0130ban girin");
		label.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label.setBounds(95, 50, 221, 23);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(95, 84, 221, 28);
		contentPane.add(textField);
		
		JLabel lblekilecekMiktarGirin = new JLabel("\u00C7ekilecek Miktar\u0131 Girin");
		lblekilecekMiktarGirin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblekilecekMiktarGirin.setBounds(95, 123, 221, 23);
		contentPane.add(lblekilecekMiktarGirin);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(95, 157, 221, 28);
		contentPane.add(textField_1);
		
		JButton btnek = new JButton("\u00C7ek");
		btnek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().length()==0 || textField_1.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						String iban=textField.getText();
						
						PreparedStatement ps = con.prepareStatement("SELECT * FROM hesap WHERE iban=?");
						ps.setString(1, iban);
						ps.executeQuery();
						ResultSet rs=ps.executeQuery();
				
						while(rs.next()) {
							PreparedStatement preparedStatement = con.prepareStatement("UPDATE hesap SET bakiye=? WHERE iban=?");
							int çekilecekMiktar=Integer.parseInt(textField_1.getText());
							int þuankiBakiye;
							if(rs.getString("bakiye")==null)
								þuankiBakiye=0;
							else
								þuankiBakiye=Integer.parseInt(rs.getString("bakiye"));
							String totalBakiye=String.valueOf(þuankiBakiye-çekilecekMiktar);
								if(Integer.parseInt(totalBakiye)>=0) {
									preparedStatement.setString(1, totalBakiye);
									preparedStatement.setString(2, iban);
									preparedStatement.executeUpdate();
									textField_1.setText(null);
									textField.setText(null);
									Helper.showMsg("succes");
								}else {
									JOptionPane.showMessageDialog(null, "Hesap bakiyesi yetersiz");
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
		btnek.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnek.setBounds(124, 196, 158, 34);
		contentPane.add(btnek);
	}

}
