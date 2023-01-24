package popupGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import GUI.M��teriGUI;
import Helper.DBConnection;
import Helper.Helper;
import Model.Kart;
import Model.M��teri;

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

public class KartBor�GUI extends JFrame {

	static Kart kart=new Kart();
	DBConnection conn = new DBConnection();
	
	private JPanel contentPane;
	private JTextField �de;
	private JTextField kartnumara;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KartBor�GUI frame = new KartBor�GUI(kart);
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
	public KartBor�GUI(Kart kart) {
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
		
		�de = new JTextField();
		�de.setBounds(97, 155, 221, 28);
		contentPane.add(�de);
		�de.setColumns(10);
		
		JButton btnde = new JButton("\u00D6de");
		btnde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(�de.getText().length()==0 || kartnumara.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						String numara=kartnumara.getText();
						
						PreparedStatement ps = con.prepareStatement("SELECT * FROM kart WHERE kart_numaras�=?");
						ps.setString(1, numara);
						ps.executeQuery();
						ResultSet rs=ps.executeQuery();
						
						while(rs.next()) {
							PreparedStatement preparedStatement = con.prepareStatement("UPDATE kart SET bor�=? WHERE kart_numaras�=?");
							int �denecekMiktar=Integer.parseInt(�de.getText());
							int �uankiBor�;
							if(rs.getString("bor�")==null)
								�uankiBor�=0;
							else
								�uankiBor�=Integer.parseInt(rs.getString("bor�"));
							String totalBor�=String.valueOf(�uankiBor�+�denecekMiktar);
							if(Integer.parseInt(totalBor�)>=0) {
								preparedStatement.setString(1, totalBor�);
								preparedStatement.setString(2, numara);
								preparedStatement.executeUpdate();
								�de.setText(null);
								kartnumara.setText(null);
								Helper.showMsg("succes");
							}else {
								JOptionPane.showMessageDialog(null, "�dedi�iniz miktar mevcut bor�tan fazlad�r");
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
