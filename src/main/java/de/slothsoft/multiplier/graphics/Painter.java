package de.slothsoft.multiplier.graphics;

import java.awt.Dimension;
import java.awt.Graphics2D;

public interface Painter {

	void paint(Graphics2D graphics);

	void setTargetSize(Dimension size);

	Dimension getTargetSize();

	Dimension getPreferredSize();

}
