package fr.gilles.gviewer;

import javax.swing.*;

public class Main {
	public static Frame f;
	public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
		UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		f =new Frame();
		f.setVisible(true);
	}

}
