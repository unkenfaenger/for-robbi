package de.slothsoft.multiplier.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Objects;

import de.slothsoft.multiplier.Input;

public class InputPainter implements Painter {

	private final Input input;

	private Color[] colors = new Color[] { Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN };
	private int padding = 10;
	private int horizontalGap = 5;
	private int verticalGap = 5;
	private int size = 30;
	private Dimension targetSize;

	public InputPainter(Input input) {
		this.input = input;
	}

	@Override
	public void paint(Graphics2D graphics) {
		int index = 0;

		scaleToSize(graphics);

		for (int factor1 = 0; factor1 < this.input.getFactor1(); factor1++) {
			for (int factor2 = 0; factor2 < this.input.getFactor2(); factor2++) {
				int x = calculatePosition(factor1, this.horizontalGap);
				int y = calculatePosition(factor2, this.verticalGap);
				graphics.setColor(this.colors[index++ % this.colors.length]);
				paintDot(graphics, x, y);
			}
		}
	}

	private void scaleToSize(Graphics2D graphics) {
		if (this.targetSize == null) return;
		Dimension preferredSize = getPreferredSize();
		double scaleX = this.targetSize.getWidth() / preferredSize.getWidth();
		double scaleY = this.targetSize.getHeight() / preferredSize.getHeight();
		double scale = Math.min(scaleX, scaleY);

		if (scale > 0) {
			AffineTransform transform = graphics.getTransform() == null ? new AffineTransform() : graphics
					.getTransform();
			transform.concatenate(AffineTransform.getScaleInstance(scale, scale));
			graphics.setTransform(transform);
		}
	}

	int calculatePosition(int factor, int gap) {
		return this.padding + factor * this.size + factor * gap;
	}

	private void paintDot(Graphics graphics, int x, int y) {
		graphics.fillOval(x, y, this.size, this.size);
		graphics.setColor(Color.BLACK);
		graphics.drawOval(x, y, this.size, this.size);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(calculatePosition(this.input.getFactor1(), this.horizontalGap) - this.horizontalGap
				+ this.padding, calculatePosition(this.input.getFactor2(), this.verticalGap) - this.verticalGap
				+ this.padding);
	}

	public Color[] getColors() {
		return this.colors;
	}

	public InputPainter colors(Color[] newColors) {
		setColors(newColors);
		return this;
	}

	public void setColors(Color[] colors) {
		this.colors = Objects.requireNonNull(colors);
	}

	public int getHorizontalGap() {
		return this.horizontalGap;
	}

	public InputPainter horizontalGap(int newHorizontalGap) {
		setHorizontalGap(newHorizontalGap);
		return this;
	}

	public void setHorizontalGap(int horizontalGap) {
		this.horizontalGap = horizontalGap;
	}

	public int getPadding() {
		return this.padding;
	}

	public InputPainter padding(int newPadding) {
		setPadding(newPadding);
		return this;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}

	public int getSize() {
		return this.size;
	}

	public InputPainter size(int newSize) {
		setSize(newSize);
		return this;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public Dimension getTargetSize() {
		return this.targetSize;
	}

	public InputPainter targetSize(Dimension newTargetSize) {
		setTargetSize(newTargetSize);
		return this;
	}

	@Override
	public void setTargetSize(Dimension targetSize) {
		this.targetSize = targetSize;
	}

	public int getVerticalGap() {
		return this.verticalGap;
	}

	public InputPainter verticalGap(int newVerticalGap) {
		setVerticalGap(newVerticalGap);
		return this;
	}

	public void setVerticalGap(int verticalGap) {
		this.verticalGap = verticalGap;
	}

}
