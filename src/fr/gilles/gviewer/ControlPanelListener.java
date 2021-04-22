package fr.gilles.gviewer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class ControlPanelListener implements MouseListener {
private String[] ext = {"jpeg","png","gif","jpg"};
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass().getName().equalsIgnoreCase("javax.swing.JButton")){
			switch (((JButton) e.getSource()).getName()) {
				case "play" -> {
					open();
				}
				case "prev" -> {
					if (Main.f.isViewing())
						Main.f.goBack();
				}
				case "next" -> {
					if (Main.f.isViewing())
						Main.f.goNext();
				}
			}
		}else if(e.getSource().getClass().getName().equalsIgnoreCase("javax.swing.JMenu")){
			switch (((JMenuItem) e.getSource()).getName()) {
				case "info" -> {
					Main.f.showInfo();
				}
			}
		}
	}
	private void open(){
		if(Main.f.isViewing()) {
			if(Main.f.Diapoisrun()) {
				Main.f.StopDiapo();
			}else {
				Main.f.enablediapo();
			}
		}else {
			JFileChooser f = new JFileChooser();
			f.setDialogTitle("Choose image");
			int returnal = f.showOpenDialog(Main.f);
			if(returnal == JFileChooser.APPROVE_OPTION) {
				if(!f.getSelectedFile().getName().isEmpty()) {
					boolean find = false;
					for(String str:ext) {
						if(str.equalsIgnoreCase(f.getSelectedFile().getName().substring(f.getSelectedFile().getName().lastIndexOf(".")+1))) {
							Main.f.SetImage(f.getSelectedFile().getAbsolutePath());
							find=true;
						}else {
							find = false;
						}
					}

				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
