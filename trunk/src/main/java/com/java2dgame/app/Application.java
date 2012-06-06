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
	private GameCanvas gameCanvas;

	public Application() {}

	public void init() {
		prepareBasics();	
		Game.startGame();
	}

	private void prepareBasics() {
		initLogger();
		initConfiguration();
		
		gameCanvas = new GameCanvas();	
		joinWindowAndCanvas();
		initWindow();
		initCanvas(gameCanvas,new GameKeyAdapter());
		initGraphics();
	}

	public void joinWindowAndCanvas() {
		this.getContentPane().add(gameCanvas);
	}

	private void initGraphics() {
		GraphicsEngine.getInstance().setWindowReference(this, gameCanvas, gameCanvas.getPreferredSize());
		GraphicsEngine.getInstance().resetWindow();
	}

	private void initConfiguration() {
		Configurator.loadConfigurationFile();
	}
	
	public void initCanvas(GameCanvas canvas,GameKeyAdapter gameKeyAdapter) {
		canvas.setPreferredSize(Configurator.getConfigurationResolution());
		canvas.createBufferGraphics();
		canvas.addKeyListener(gameKeyAdapter);
		canvas.addMouseMotionListener(this);	
		canvas.addMouseListener(this); 
		canvas.addMouseWheelListener(this);		
	}

	public void initWindow() {	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(this.getClass().getSimpleName()+" "+serialVersionUID);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.darkGray);
		this.setVisible(true);
		this.setResizable(false);
	}

	private void initLogger() {
		PropertyConfigurator.configure(getClass().getResource("log4j.properties"));
		Logger.getLogger(this.getClass()).info("Logger started");
	}

	public static void main(String [] args) {
		Application application = new Application();
		application.init();
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



	public GameCanvas getGameCanvas() {
		return gameCanvas;
	}

	public void setGameCanvas(GameCanvas gameCanvas) {
		this.gameCanvas = gameCanvas;
	}
}
