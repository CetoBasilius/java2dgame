package com.java2dgame.app;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.java2dgame.adapters.GameKeyAdapter;
import com.java2dgame.configuration.Configurator;
import com.java2dgame.engines.GraphicsEngine;

public class Application extends JFrame implements MouseMotionListener, MouseListener, MouseWheelListener{
	
	private static final long serialVersionUID = 1L;

	private Application() {
		init();
	}

	private void init() {
		initLogger();
		initConfiguration();
		initWindow();
		Game.startGame();
	}

	private void initConfiguration() {
		Configurator.loadConfigurationFile();
	}

	private void initWindow() {
		GameCanvas gameCanvas = new GameCanvas();
		gameCanvas.setPreferredSize(Configurator.getConfigurationResolution());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.getClass().getSimpleName()+" "+serialVersionUID);
		this.getContentPane().add(gameCanvas);
		this.pack();
		this.setLayout(null);
		this.getContentPane().setBackground(Color.darkGray);
		this.setVisible(true);
		this.setResizable(false);
		
		gameCanvas.createBufferGraphics();
		gameCanvas.addKeyListener(new GameKeyAdapter());
		gameCanvas.addMouseMotionListener(this);	
		gameCanvas.addMouseListener(this); 
		gameCanvas.addMouseWheelListener(this);	
	
		GraphicsEngine.getInstance().setWindowReference(this, gameCanvas, gameCanvas.getPreferredSize());
		GraphicsEngine.getInstance().resetWindow();

	}

	private void initLogger() {
		PropertyConfigurator.configure(getClass().getResource("log4j.properties"));
		Logger.getLogger(this.getClass()).info("Logger started");
	}

	public static void main(String [] args) {
		new Application();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
}
