package popupGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Helper.DBConnection;
import Helper.Helper;
import Model.Cep;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class CepGUI extends JFrame {
	
	static Cep cep=new Cep();
	DBConnection conn = new DBConnection();

	private JPanel contentPane;
	private JTextField cepid;
	private JTextField cepyat�r;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CepGUI frame = new CepGUI(cep);
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
	public CepGUI(Cep cep) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCepIdGirin = new JLabel("Cep id girin");
		lblCepIdGirin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		lblCepIdGirin.setBounds(95, 50, 221, 23);
		contentPane.add(lblCepIdGirin);
		
		cepid = new JTextField();
		cepid.setColumns(10);
		cepid.setBounds(95, 84, 221, 28);
		contentPane.add(cepid);
		
		JLabel label_1 = new JLabel("Yat\u0131r\u0131lacak Miktar\u0131 Girin");
		label_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 16));
		label_1.setBounds(95, 123, 221, 23);
		contentPane.add(label_1);
		
		cepyat�r = new JTextField();
		cepyat�r.setColumns(10);
		cepyat�r.setBounds(95, 157, 221, 28);
		contentPane.add(cepyat�r);
		
		JButton button = new JButton("Yat\u0131r");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cepid.getText().length()==0 || cepyat�r.getText().length()==0) {
					Helper.showMsg("fill");
				}else {
					try {
						Connection con=conn.connDb();
						String id=cepid.getText();
						
						PreparedStatement ps = con.prepareStatement("SELECT * FROM cep WHERE cep_id=?");
						ps.setString(1, id);
						ps.executeQuery();
						ResultSet rs=ps.executeQuery();
				
						while(rs.next()) {
							PreparedStatement preparedStatement = con.prepareStatement("UPDATE cep SET bakiye=? WHERE cep_id=?");
							int yat�r�lacakMiktar=Integer.parseInt(cepyat�r.getText());
							int �uankiBakiye;
							if(rs.getString("bakiye")==null)
								�uankiBakiye=0;
							else
								�uankiBakiye=Integer.parseInt(rs.getString("bakiye"));
							String totalBakiye=String.valueOf(�uankiBakiye+yat�r�lacakMiktar);
									preparedStatement.setString(1, totalBakiye);
									preparedStatement.setString(2, id);
									preparedStatement.executeUpdate();
									cepid.setText(null);
									cepyat�r.setText(null);
									Helper.showMsg("succes");
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
		button.setFont(new Font("Tahoma", Font.PLAIN, 14));
		button.setBounds(124, 196, 158, 34);
		contentPane.add(button);
		
		JButton button_1 = new JButton("\u00C7\u0131k");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button_1.setBounds(335, 11, 89, 23);
		contentPane.add(button_1);
	}
}
