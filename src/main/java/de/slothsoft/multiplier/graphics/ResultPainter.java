package de.slothsoft.multiplier.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.Objects;

import de.slothsoft.multiplier.Input;
import de.slothsoft.multiplier.Result;

public class ResultPainter implements Painter {

	private final Result result;
	private final Input input;

	private final InputPainter inputPainter;

	private int textHeight = 14;
	private Dimension targetSize = new Dimension(100, 100);

	public ResultPainter(Result result) {
		this.result = result;
		this.input = result.getInput();
		this.inputPainter = new InputPainter(this.input);
	}

	@Override
	public void paint(Graphics2D graphics) {
		String title = this.input.getFactor1() + " x " + this.input.getFactor2() + " = " + this.result.getProduct();

		FontMetrics metrics = graphics.getFontMetrics(graphics.getFont());
		this.textHeight = metrics.getHeight();
		this.inputPainter.setTargetSize(new Dimension(this.targetSize.width, this.targetSize.height - this.textHeight));

		graphics.setColor(Color.BLACK);
		graphics.drawString(title, (this.targetSize.width - metrics.stringWidth(title)) / 2, this.textHeight);
		graphics.setTransform(AffineTransform.getTranslateInstance(0, this.textHeight));
		this.inputPainter.paint(graphics);
	}

	@Override
	public Dimension getPreferredSize() {
		Dimension dimension = this.inputPainter.getPreferredSize();
		dimension.height += this.textHeight;
		return dimension;
	}

	@Override
	public Dimension getTargetSize() {
		return this.targetSize;
	}

	public ResultPainter targetSize(Dimension newTargetSize) {
		setTargetSize(newTargetSize);
		return this;
	}

	@Override
	public void setTargetSize(Dimension targetSize) {
		this.targetSize = Objects.requireNonNull(targetSize);
	}

}
