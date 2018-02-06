package bd2Test;

import GUI.LoginFrame;
import GUI.MainFrame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import Data.SystemData;
import Database.DatabaseConnection;

public class Application {
	static DatabaseConnection connection;
	SystemData systemData;
	MainFrame gui;
	LoginFrame login;
	public Application() {
		connection = null;
		login = new LoginFrame();
		while(!login.setConnection()) {
			
		}
		systemData = new SystemData(null);
		gui = new MainFrame(systemData);
	}
	public static void main(String []args) {
		Application app = new Application();
		
	}
	
	public static DatabaseConnection getConnection() {
		return connection;
	}
	public static void setConnection(DatabaseConnection newCon) {
		connection = newCon;
	}
	public static DatabaseConnection getConnection1() {
		return connection;
	}
	
}
