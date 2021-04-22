package fr.gilles.gviewer;

import javax.swing.*;

public class Main {
	public static Frame f;
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[3].getClassName());
		f =new Frame();
		f.setVisible(true);
		if ( args.length >0 && !args[0].isEmpty() && !args[0].isBlank() ){
			f.SetImage(args[0]);
		}
	}

}
