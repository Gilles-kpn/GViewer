package fr.gilles.gviewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
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
		
		this.add(sous_menu,BorderLayout.NORTH);
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
				System.out.println(zoom.getValue()-50);
				pane.zoom(zoom.getValue());
				
			}
			
		});
		
	}
	private void initControlButtonIcon() {
		ImageIcon f = new ImageIcon(getClass().getResource("/images/next.png"));
		this.Next = new JButton(new ImageIcon(f.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		this.Next.setMaximumSize(new Dimension(30,30));
		this.Next.setBackground(Color.white);
		this.Next.setName("next");
		f = new ImageIcon(getClass().getResource("/images/previous.png"));
		this.Previous = new JButton(new ImageIcon(f.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		this.Previous.setMaximumSize(new Dimension(30,30));
		this.Previous.setBackground(Color.white);
		this.Previous.setName("prev");
		f = new ImageIcon(getClass().getResource("/images/play.png"));
		this.Play = new JButton(new ImageIcon(f.getImage().getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		this.Play.setMaximumSize(new Dimension(30,30));
		this.Play.setBackground(Color.white);
		this.Play.setName("play");
		
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
		this.File.addMouseListener(new MenuEvent());
		this.View.addMouseListener(new MenuEvent());
		this.Info.addMouseListener(new MenuEvent());
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
	}
	public boolean Diapoisrun() {
		return pane.isIsrun();
	}
	public void StopDiapo() {
		pane.stopDiaporame();
	}
	public void goNext() {
		this.zoom.setValue(50);
		pane.applyNext();
		this.zoom.repaint();
	}
	public void goBack() {
		this.zoom.setValue(50);
		pane.applyBack();
		this.zoom.repaint();
	}
}
