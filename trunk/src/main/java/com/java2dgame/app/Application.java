package com.java2dgame.app;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.java2dgame.configuration.Configurator;


public class Application extends JFrame{
	
	private static final long serialVersionUID = 1L;

	private Application() {
		init();
	}

	private void init() {
		initLogger();
		initWindow();
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
}
