package de.slothsoft.multiplier.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JPanel;

import de.slothsoft.multiplier.graphics.Painter;

public class PainterPanel extends JPanel {

	private static final long serialVersionUID = -8284740896277282141L;

	private final Painter painter;

	public PainterPanel(Painter painter) {
		this.painter = painter;
		this.painter.setTargetSize(getSize());

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				PainterPanel.this.painter.setTargetSize(getSize());
			}
		});
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.painter.paint((Graphics2D) g);
	}

	@Override
	public Dimension getPreferredSize() {
		return this.painter.getPreferredSize();
	}
}
