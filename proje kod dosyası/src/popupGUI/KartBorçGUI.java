package popupGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.MüþteriGUI;
import Helper.DBConnection;
import Helper.Helper;
import Model.Kart;
import Model.Müþteri;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

public class KartBorçGUI extends JFrame {

	static Kart kart=new Kart();
	DBConnection conn = new DBConnection();
	
	private JPanel contentPane;
	private JTextField öde;
	private JTextField kartnumara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KartBorçGUI frame = new KartBorçGUI(kart);
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
	public KartBorçGUI(Kart kart) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnk = new JButton("\u00C7\u0131k\u0131\u015F");
		btnk.setBounds(335, 11, 89, 23);
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnk);
		
		JLabel lbldemekIstediinizMiktar = new JLabel("\u00D6denecek Miktar\u0131 Girin");
		lbldemekIstediinizMiktar.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lbldemekIstediinizMiktar.setBounds(97, 121, 221, 23);
		contentPane.add(lbldemekIstediinizMiktar);
		
		öde = new JTextField();
		öde.setBounds(97, 155, 221, 28);
		contentPane.add(öde);
		öde.setColumns(10);
		
		JButton btnde = new JButton("\u00D6de");
		btnde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(öde.getText().length()==0 || kartnumara.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						String numara=kartnumara.getText();
						
						PreparedStatement ps = con.prepareStatement("SELECT * FROM kart WHERE kart_numarasý=?");
						ps.setString(1, numara);
						ps.executeQuery();
						ResultSet rs=ps.executeQuery();
						
						while(rs.next()) {
							PreparedStatement preparedStatement = con.prepareStatement("UPDATE kart SET borç=? WHERE kart_numarasý=?");
							int ödenecekMiktar=Integer.parseInt(öde.getText());
							int þuankiBorç;
							if(rs.getString("borç")==null)
								þuankiBorç=0;
							else
								þuankiBorç=Integer.parseInt(rs.getString("borç"));
							String totalBorç=String.valueOf(þuankiBorç+ödenecekMiktar);
							if(Integer.parseInt(totalBorç)>=0) {
								preparedStatement.setString(1, totalBorç);
								preparedStatement.setString(2, numara);
								preparedStatement.executeUpdate();
								öde.setText(null);
								kartnumara.setText(null);
								Helper.showMsg("succes");
							}else {
								JOptionPane.showMessageDialog(null, "Ödediðiniz miktar mevcut borçtan fazladýr");
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
		
		btnde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnde.setBounds(126, 194, 158, 34);
		contentPane.add(btnde);
		
		JLabel lblKartIdGirin = new JLabel("Kart numaras\u0131 girin");
		lblKartIdGirin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblKartIdGirin.setBounds(97, 48, 221, 23);
		contentPane.add(lblKartIdGirin);
		
		kartnumara = new JTextField();
		kartnumara.setColumns(10);
		kartnumara.setBounds(97, 82, 221, 28);
		contentPane.add(kartnumara);
	}
}
