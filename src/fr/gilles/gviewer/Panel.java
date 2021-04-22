package fr.gilles.gviewer;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.*;

public class Panel extends JPanel{
	/**
	 * 
	 */
	private Image currentImg;
	private  String name ="null";
	private static final long serialVersionUID = 1L;
	private int ImageWidth =0,ImageHeight = 0;
	private boolean isView = false,isrun = false;
	private int origineX,origineY,longeur,hauteur;
	private String[] ext = {"jpeg","png","gif","jpg"};
	private   ArrayList<String> cache = new ArrayList<String>();

	public Panel() {
		currentImg = null;
		isView =false;
		origineX = 0;
		origineY = 0;
		longeur = 0;
		hauteur = 0;//attends il reste la coloration tous le
		this.setDropTarget(new DropTarget(){
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					java.util.List<File> droppedFiles = (java.util.List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles) {
						if(file.isDirectory()){
							loadImg(Objects.requireNonNull(file.listFiles())[1].getAbsolutePath());
							repaint();
						}else if(file.isFile()){
							loadImg(file.getAbsolutePath());
							repaint();

						}

					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());
		setNewDimension(this.getWidth(),this.getHeight());
		if(!this.name.equalsIgnoreCase("null") ) {
			g2.setColor(Color.BLACK);
			g2.drawImage(currentImg, (getWidth() / 2) - (ImageWidth / 2), (getHeight() / 2) - (ImageHeight / 2), ImageWidth, ImageHeight, this);
		}else {
			g2.setColor(Color.BLACK);
			/*Font font = new Font("Arial", Font.BOLD, 20);
			/*FontMetrics fm = g2.getFontMetrics();
			int x = ((getWidth() / 2 - fm.stringWidth(new String("Aucune image Selectionnée"))) / 2);
			int y = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
			g2.setFont(font);*/
			g2.drawString(new String("Aucune image Selectionnée"), getWidth()/3, this.getHeight()/2);
		}
		
	}
	public void loadImg(String s) {
		this.name = s;
		ImageWidth= new ImageIcon(s).getIconWidth();
		ImageHeight = new ImageIcon(s).getIconHeight();
		this.currentImg = new ImageIcon(s).getImage();
		isView =true;
		imginfolder();
		
	}
	private void setNewDimension(int x, int y) {
		this.longeur = x;
		this.hauteur = y;
	} 
	private int getLongeur() {
		return this.longeur;
	}
	private int getHauteur() {
		return this.hauteur;
	}
	public boolean isViewe() {
		return isView;
	}
	
	public  void SetCurrentFile(String s) {
		loadImg(s);
		this.repaint();
		imginfolder(); 
		this.name= s;
		
	}
	private void imginfolder() {
		String d = this.name.substring(0,this.name.lastIndexOf("/"));
		File f = new File(d);
		if(f.isDirectory()) {
			for(File fi : f.listFiles()) {
				for(String str : ext) {
					if(fi.getAbsolutePath().substring(fi.getAbsolutePath().lastIndexOf(".")+1).equals(str)) {
						cache.add(fi.getAbsolutePath());
					
					}
				}
				
			}
		}
	}
	public String getCurrentFileName() {
		
		return this.name;
		
	}
	public void enableDiaporame() {
		this.isrun = true;
	
		
	}
	public void stopDiaporame() {
		this.isrun = false;
	}
	public  void applyNext() {
		origineX =0;
		origineY = 0;
		if(cache.indexOf(name) == cache.size()-1) {
			SetCurrentFile(cache.get(0));
		}else {
			SetCurrentFile(cache.get(cache.indexOf(name)+1));
		}
		if(isIsrun()) {
			enableDiaporame();
		}
	}
public  void applyBack() {
		origineX =0;
		origineY = 0;
		if(cache.indexOf(name) == 0) {
			SetCurrentFile(cache.get(cache.size()-1));
		}else {
			SetCurrentFile(cache.get(cache.indexOf(name)-1));
		}
		if(isIsrun()) {
			enableDiaporame();
		}
	}
	public boolean isIsrun() {
		return isrun;
	}
	public void setIsrun(boolean isrun) {
		this.isrun = isrun;
	}
	public void zoom(int x) {

		
	}
	public String[] getActivefileInfo(){
		String fileextension = this.name.substring(this.name.lastIndexOf(".")+1);
		String fileDir = this.name.substring(0,this.name.lastIndexOf("/"));
		String filename = this.name.substring(this.name.lastIndexOf("/")+1,this.name.lastIndexOf("."));
		return new String[]{fileDir, filename, fileextension, String.valueOf(ImageWidth), String.valueOf(ImageHeight)};
	}
}
