package de.slothsoft.multiplier.graphics;

import java.awt.Dimension;

import org.junit.Assert;
import org.junit.Test;

import de.slothsoft.multiplier.Input;
import de.slothsoft.multiplier.graphics.InputPainter;

public class ImagePainterTest {

	private Input input = new Input().factor1(1).factor2(1);
	private InputPainter painter = new InputPainter(this.input).horizontalGap(0).verticalGap(0).padding(0).size(0);

	@Test
	public void testCalculatePosition() throws Exception {
		Assert.assertEquals(0, this.painter.calculatePosition(0, 0));
		Assert.assertEquals(0, this.painter.calculatePosition(1, 0));
		Assert.assertEquals(0, this.painter.calculatePosition(2, 0));
	}

	@Test
	public void testCalculatePositionForSize() throws Exception {
		this.painter.setSize(10);
		Assert.assertEquals(0, this.painter.calculatePosition(0, 0));
		Assert.assertEquals(10, this.painter.calculatePosition(1, 0));
		Assert.assertEquals(20, this.painter.calculatePosition(2, 0));
	}

	@Test
	public void testCalculatePositionForGap() throws Exception {
		Assert.assertEquals(0, this.painter.calculatePosition(0, 10));
		Assert.assertEquals(10, this.painter.calculatePosition(1, 10));
		Assert.assertEquals(20, this.painter.calculatePosition(2, 10));
	}

	@Test
	public void testCalculatePositionForPadding() throws Exception {
		this.painter.setSize(5);
		this.painter.setPadding(10);
		Assert.assertEquals(10, this.painter.calculatePosition(0, 0));
		Assert.assertEquals(15, this.painter.calculatePosition(1, 0));
		Assert.assertEquals(20, this.painter.calculatePosition(2, 0));
	}

	@Test
	public void testCalculatePositionForEverything() throws Exception {
		this.painter.padding(10).size(5);
		Assert.assertEquals(10, this.painter.calculatePosition(0, 0));
		Assert.assertEquals(20, this.painter.calculatePosition(1, 5));
		Assert.assertEquals(50, this.painter.calculatePosition(2, 15));
	}

	@Test
	public void testPreferredSize() throws Exception {
		Assert.assertEquals(new Dimension(), this.painter.getPreferredSize());
	}

	@Test
	public void testPreferredSizeForSize() throws Exception {
		this.painter.setSize(10);
		Assert.assertEquals(new Dimension(10, 10), this.painter.getPreferredSize());

		this.input.factor1(3).factor2(5);
		Assert.assertEquals(new Dimension(30, 50), this.painter.getPreferredSize());
	}

	@Test
	public void testPreferredSizeForPadding() throws Exception {
		this.painter.setPadding(10);
		Assert.assertEquals(new Dimension(20, 20), this.painter.getPreferredSize());

		this.input.factor1(3).factor2(5);
		Assert.assertEquals(new Dimension(20, 20), this.painter.getPreferredSize());
	}

	@Test
	public void testPreferredSizeForHorizontalGap() throws Exception {
		this.painter.setHorizontalGap(5);
		Assert.assertEquals(new Dimension(0, 0), this.painter.getPreferredSize());

		this.input.factor1(3).factor2(5);
		Assert.assertEquals(new Dimension(10, 0), this.painter.getPreferredSize());
	}

	@Test
	public void testPreferredSizeForVerticalGap() throws Exception {
		this.painter.setVerticalGap(5);
		Assert.assertEquals(new Dimension(0, 0), this.painter.getPreferredSize());

		this.input.factor1(3).factor2(5);
		Assert.assertEquals(new Dimension(0, 20), this.painter.getPreferredSize());
	}

	@Test
	public void testPreferredSizeForEverything() throws Exception {
		this.painter.horizontalGap(1).verticalGap(2).padding(5).size(10);
		Assert.assertEquals(new Dimension(20, 20), this.painter.getPreferredSize());

		this.input.factor1(3).factor2(5);
		Assert.assertEquals(new Dimension(42, 68), this.painter.getPreferredSize());
	}
}
