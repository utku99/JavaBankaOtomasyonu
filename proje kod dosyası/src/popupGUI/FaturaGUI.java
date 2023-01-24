package popupGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Helper;
import Model.Fatura;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class FaturaGUI extends JFrame {

	static Fatura fatura = new Fatura();
	DBConnection conn = new DBConnection();

	private JPanel contentPane;
	private JTextField faturaid;
	private JTextField elektrik;
	private JTextField su;
	private JTextField gaz;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FaturaGUI frame = new FaturaGUI(fatura);
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
	public FaturaGUI(Fatura fatura) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 247);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFaturaId = new JLabel("Fatura id\r\n");
		lblFaturaId.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblFaturaId.setBounds(10, 27, 121, 23);
		contentPane.add(lblFaturaId);

		faturaid = new JTextField();
		faturaid.setBounds(10, 61, 121, 23);
		contentPane.add(faturaid);
		faturaid.setColumns(10);

		JLabel lblElektrik = new JLabel("Elektrik");
		lblElektrik.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblElektrik.setBounds(10, 95, 121, 23);
		contentPane.add(lblElektrik);

		JLabel lblSu = new JLabel("Su");
		lblSu.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblSu.setBounds(141, 95, 121, 23);
		contentPane.add(lblSu);

		JLabel lblDoalgaz = new JLabel("Do\u011Falgaz");
		lblDoalgaz.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblDoalgaz.setBounds(272, 95, 121, 23);
		contentPane.add(lblDoalgaz);

		elektrik = new JTextField();
		elektrik.setColumns(10);
		elektrik.setBounds(10, 129, 121, 23);
		contentPane.add(elektrik);

		su = new JTextField();
		su.setColumns(10);
		su.setBounds(141, 129, 121, 23);
		contentPane.add(su);

		gaz = new JTextField();
		gaz.setColumns(10);
		gaz.setBounds(272, 129, 121, 23);
		contentPane.add(gaz);

		JButton button = new JButton("\u00C7\u0131k\u0131\u015F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(335, 11, 89, 23);
		contentPane.add(button);

		JButton fatura�de = new JButton("\u00D6de");
		fatura�de.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (elektrik.getText().length() == 0 || su.getText().length() == 0 || gaz.getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Bo� b�rakmak yerine 0 yaz�n");
				} else {
				
				
				try {
					Connection con = conn.connDb();
					String id = faturaid.getText();

					PreparedStatement ps = con.prepareStatement("SELECT * FROM fatura WHERE fatura_id=?");
					ps.setString(1, id);
					ps.executeQuery();
					ResultSet rs = ps.executeQuery();

					while (rs.next()) {
						PreparedStatement preparedStatement = con
								.prepareStatement("UPDATE fatura SET elektrik=?, su=?, do�algaz=? WHERE fatura_id=?");

						int elektrikBor� = Integer.parseInt(elektrik.getText());
						int suBor� = Integer.parseInt(su.getText());
						int gazBor� = Integer.parseInt(gaz.getText());

						int �uankiElektrik;
						if(rs.getString("elektrik")==null)
							�uankiElektrik=0;
						else
							�uankiElektrik=Integer.parseInt(rs.getString("elektrik"));
						
						int �uankiSu;
						if(rs.getString("su")==null)
							�uankiSu=0;
						else
							�uankiSu=Integer.parseInt(rs.getString("su"));
						
						int �uankiGaz;
						if(rs.getString("su")==null)
							�uankiGaz=0;
						else
							�uankiGaz=Integer.parseInt(rs.getString("do�algaz"));

						String totalElektrik = String.valueOf(�uankiElektrik + elektrikBor�);
						String totalSu = String.valueOf(�uankiSu + suBor�);
						String totalGaz = String.valueOf(�uankiGaz + gazBor�);

						if (Integer.parseInt(totalElektrik) >= 0 && Integer.parseInt(totalSu) >= 0
								&& Integer.parseInt(totalGaz) >= 0) {
							preparedStatement.setString(1, totalElektrik);
							preparedStatement.setString(2, totalSu);
							preparedStatement.setString(3, totalGaz);
							preparedStatement.setString(4, id);
							preparedStatement.executeUpdate();
							elektrik.setText(null);
							su.setText(null);
							gaz.setText(null);
							faturaid.setText(null);
							Helper.showMsg("succes");
						} else {
							JOptionPane.showMessageDialog(null, "Bir fatura i�in �dedi�iniz miktar mevcut borcundan fazlad�r");
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
		fatura�de.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fatura�de.setBounds(123, 163, 158, 34);
		contentPane.add(fatura�de);
	}

}
