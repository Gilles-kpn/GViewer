package fr.gilles.gviewer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Panel extends JPanel{
	/**
	 * 
	 */
	private Image currentImg;
	private  String name =null;
	private int prev = 0;
	private static final long serialVersionUID = 1L;
	private boolean isView = false,isrun = false;
	private int origineX,origineY,longeur,hauteur,normalX,normalY;
	private String[] ext = {"jpeg","png","gif","jpg"};
	private   ArrayList<String> cache = new ArrayList<String>();
	public Panel() {
		currentImg = null;
		isView =false;
		origineX = 0;
		origineY =0;
		longeur = 0;
		hauteur = 0;
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.white);
		
		setNewDimension(this.getWidth(),this.getHeight());
		normalX =this.getWidth();
		normalY = this.getHeight();
		g2.fillRect(0, 0, normalX, normalY);
		
		g2.drawImage(currentImg, origineX,origineY,getLongeur(),getHauteur(), this);
		
	}
	public void loadImg(String s) {
		this.name = s;
		this.currentImg = new ImageIcon(s).getImage();
		isView =true;
		
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
		
		
		
		
	}
	public void stopDiaporame() {
		
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
			
			this.origineX = this.origineX - (x-prev)*2;
			this.origineY = this.origineY - (x-prev)*2;
			this.setNewDimension(this.getLongeur()+(x-prev)*2, this.getHauteur()+(x-prev)*2);
			
		
		
		this.repaint();
		prev= x;
		
	}
}
