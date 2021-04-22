package fr.gilles.gviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Frame extends JFrame{
	/**
	 * 
	 */
	private JMenu File,View,Info;
	private JButton Next,Previous,Play;
	private JMenuBar sous_menu;
	private JMenuBar control;
	private JSlider zoom;
	private static final long serialVersionUID = 1L;
	private Panel pane = new Panel();
	private Diaporama diapo = new Diaporama(); 
	public Frame() {
		this.setTitle("GViewer");
		this.setMinimumSize(new Dimension(500,400));
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.initComponent();
		this.setVisible(true);
	}
	private void initComponent() {

		this.sous_menu = new JMenuBar();
		this.control =new JMenuBar();
		this.File = new JMenu("File");
		this.File.setName("file");
		this.View = new JMenu("View");
		this.View.setName("view");
		//enable drag and drop on View
		this.Info = new JMenu("Info");
		this.Info.setName("info");
		initControlButtonIcon( );
		intizoompanel();
		initcontrolPanel();
		addEventtoMenuItem();
		addControlPanelListener();
		this.sous_menu.add(this.File);
		this.sous_menu.add(this.View);
		this.sous_menu.add(this.Info);
		this.setJMenuBar(sous_menu);
		this.add(pane,BorderLayout.CENTER);
		this.add(control,BorderLayout.SOUTH);
		
	}
	private void intizoompanel() {
		this.zoom  = new JSlider();
		this.zoom.setName("zoom");
		this.zoom.setOrientation(javax.swing.JSlider.VERTICAL);
		this.add(zoom,BorderLayout.LINE_START);
		this.zoom.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub

				pane.zoom(zoom.getValue()-50);
				
			}
			
		});
		
	}
	private void initControlButtonIcon() {
		ImageIcon f = new ImageIcon(getClass().getResource("/images/next.png"));
		this.Next = new JButton(new ImageIcon(f.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		this.Next.setMaximumSize(new Dimension(30,30));
		this.Next.setBackground(Color.white);
		this.Next.setName("next");
		this.Next.setBorderPainted(false);
		f = new ImageIcon(getClass().getResource("/images/previous.png"));
		this.Previous = new JButton(new ImageIcon(f.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		this.Previous.setMaximumSize(new Dimension(30,30));
		this.Previous.setBackground(Color.white);
		this.Previous.setName("prev");
		this.Previous.setBorderPainted(false);
		f = new ImageIcon(getClass().getResource("/images/play.png"));
		this.Play = new JButton(new ImageIcon(f.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		this.Play.setMaximumSize(new Dimension(30,30));
		this.Play.setBackground(Color.white);
		this.Play.setName("play");
		this.Play.setBorderPainted(false);
	}
	private void initcontrolPanel() {
		this.control.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.gridx = 0;c.gridy =0;
		this.Previous.setSize(40, 40);
		this.control.add(Previous,c);
		c.gridx = 1; c.gridy =0;
		this.Previous.setSize(40, 40);
		this.control.add(Play,c);
		c.gridx = 2;c.gridy = 0;
		this.Previous.setSize(40, 40);
		this.control.add(Next,c);
		c.gridx = 4; c.gridy = 0;
		
	}
	private void addEventtoMenuItem() {
		//********************************************
		//file options open file
		JMenuItem file = new JMenuItem("Open Image");
		file.setName("play");
		file.addMouseListener(new ControlPanelListener());

		JMenuItem quit = new JMenuItem("Quitter");
		quit.setName("quit");
		quit.addActionListener((event)->System.exit(0));


		//adding to component PopuMenu
		this.File.getPopupMenu().add(file);
		this.File.getPopupMenu().add(quit);

		

		//******************************************
		JMenuItem eDisapo = new JMenuItem("Activer le diaporame");
		JMenuItem dDiaspo = new JMenuItem("Desactiver le diaporame");

		this.View.getPopupMenu().add(eDisapo);
		this.View.getPopupMenu().add(dDiaspo);

		//*****************************************
		this.Info.addMouseListener(new ControlPanelListener());

	}
	private void addControlPanelListener() {
		this.Next.addMouseListener(new ControlPanelListener());
		this.Previous.addMouseListener(new ControlPanelListener());
		this.Play.addMouseListener(new ControlPanelListener());
	}
	public void SetImage(String s) {
		this.pane.SetCurrentFile(s);
	}
	public boolean isViewing() {
		return pane.isViewe();
	}
	public void enablediapo(){
		pane.enableDiaporame();
		diapo.run();
	}
	public boolean Diapoisrun() {
		return pane.isIsrun();
	}//c'est public hein et depuis je vois pas boff je vais contourner ca ca
	public void StopDiapo() {
		pane.stopDiaporame();
	}
	public void goNext() {
		pane.applyNext();
	}
	public void goBack() {
		pane.applyBack();
	}
	public void showInfo(){
		if(pane.isViewe())
			JOptionPane.showMessageDialog(this,"Dossier: "+pane.getActivefileInfo()[0]+"\nNom: "+pane.getActivefileInfo()[1]+"\nExtension: "+pane.getActivefileInfo()[2]+"\nWidth: "+pane.getActivefileInfo()[3]+"\nHeight: "+pane.getActivefileInfo()[4]);
		else
			JOptionPane.showMessageDialog(this,"Aucune image n'est en cours de visulaisation");
	}


}

