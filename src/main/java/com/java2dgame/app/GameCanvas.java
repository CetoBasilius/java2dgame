package com.java2dgame.app;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.java2dgame.engines.GraphicsEngine;

public class GameCanvas extends JPanel{

	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g){
		GraphicsEngine.update(g);
	}
	
	@Override
	public void update(Graphics g){
		paint(g);
	}
}
