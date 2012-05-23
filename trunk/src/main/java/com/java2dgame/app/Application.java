package com.java2dgame.app;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.java2dgame.configuration.Configurator;
import com.java2dgame.engines.InputEngine;

public class Application extends JFrame implements MouseMotionListener, KeyListener, MouseListener, MouseWheelListener{
	
	private static final long serialVersionUID = 1L;

	private Application() {
		init();
	}

	private void init() {
		initLogger();
		initConfiguration();
		initWindow();
		addInputListeners();
	}

	private void initConfiguration() {
		Configurator.loadConfigurationFile();
	}
	
	private void addInputListeners() {
		addMouseMotionListener(this);
		addKeyListener(this);
		addMouseListener(this); 
		addMouseWheelListener(this);
	}

	private void initWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.getClass().getSimpleName()+" "+serialVersionUID);
		this.setSize(Configurator.getConfigurationResolution());
		this.setVisible(true);
	}

	private void initLogger() {
		PropertyConfigurator.configure(getClass().getResource("log4j.properties"));
		Logger.getLogger(Application.class).info("Logger started");
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
	public void keyPressed(KeyEvent key) {	
		InputEngine.pressKey(key.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent key) {
		InputEngine.releaseKey(key.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
}
