package Helper;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class Helper {
	public static void showMsg(String str) {
		String msg;
		
		switch(str) {
		case "fill":
			msg="L�tfen t�m alanlar� doldurunuz";
			break;
		case "succes":
			msg="i�lem ba�ar�l�";
			break;
			
			default:
				msg=str;
		}
		
		JOptionPane.showMessageDialog(null, msg, "Mesaj",JOptionPane.INFORMATION_MESSAGE);
		
	}
	
	
}
