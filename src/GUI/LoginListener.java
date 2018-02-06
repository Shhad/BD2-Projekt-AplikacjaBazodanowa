package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import Database.DatabaseConnection;
import bd2Test.Application;
//if you want to get real data from your database you must uncomment line 20
public class LoginListener implements ActionListener {
	JTextField pas;
	JTextField use;
	public LoginListener() {

	}
	public void actionPerformed(ActionEvent event) {
		pas = LoginFrame.returnTextPF();
		use  = LoginFrame.returnTextUF();
		//Application.setConnection(new DatabaseConnection(pas.getText(),use.getText())); 
		if(pas.getText().equals("pas") && use.getText().equals("pas")) {
			LoginFrame.setLoopBool(false);
		}
	}
}