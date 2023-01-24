package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Model.Cep;
import Model.Fatura;
import Model.Hesap;
import Model.Kart;
import Model.M��teri;
import popupGUI.CepGUI;
import popupGUI.FaturaGUI;
import popupGUI.HesapPara�ekGUI;
import popupGUI.HesapParaYat�rGUI;
import popupGUI.KartBor�GUI;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class M��teriGUI extends JFrame {

	static M��teri m��teri = new M��teri();
	static Kart kart=new Kart();
	static Fatura fatura=new Fatura();
	static Hesap hesap=new Hesap();
	static Cep cep=new Cep();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					M��teriGUI frame = new M��teriGUI(m��teri);
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
	public M��teriGUI(M��teri m��teri) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 303, 208);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(10, 0, 764, 30);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u0130\u015Flemler");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("\u00D6demeler");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mn�tmKartBorcude = new JMenuItem("Kart borcu \u00F6de");
		mn�tmKartBorcude.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				KartBor�GUI kGUI = null;
				kGUI = new KartBor�GUI(kart);
				kGUI.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mn�tmKartBorcude);
		
		JMenuItem mn�tmFaturade = new JMenuItem("Fatura \u00F6de");
		mn�tmFaturade.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FaturaGUI fGUI = null;
				fGUI = new FaturaGUI(fatura);
				fGUI.setVisible(true);
				
			}
		});
		mnNewMenu_1.add(mn�tmFaturade);
		
		JMenu mnNewMenu_3 = new JMenu("Para yat\u0131rma");
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mn�tmHesabma = new JMenuItem("Hesab\u0131ma");
		mn�tmHesabma.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HesapParaYat�rGUI hGUI=new HesapParaYat�rGUI(hesap);
				hGUI.setVisible(true);
				
			}
		});
		mnNewMenu_3.add(mn�tmHesabma);
		
		JMenu mnNewMenu_2 = new JMenu("Para \u00E7ekme\r\n");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mn�tmHesaplarmdan = new JMenuItem("Hesab\u0131mdan");
		mn�tmHesaplarmdan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HesapPara�ekGUI h2GUI=new HesapPara�ekGUI(hesap);
				h2GUI.setVisible(true);
			}
		});
		mnNewMenu_2.add(mn�tmHesaplarmdan);
		
		JMenuItem mn�tmCebeTlYkle = new JMenuItem("Cebe tl y\u00FCkle");
		mn�tmCebeTlYkle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CepGUI cGUI=new CepGUI(cep);
				cGUI.setVisible(true);
			}
		});
		mnNewMenu.add(mn�tmCebeTlYkle);
		
		JButton btnk = new JButton("\u00C7\u0131k\u0131\u015F");
		btnk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnk.setBounds(170, 128, 107, 30);
		contentPane.add(btnk);
	}
}
