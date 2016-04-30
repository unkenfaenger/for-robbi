package de.slothsoft.multiplier.io;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Map;
import java.util.function.Function;

import javax.imageio.ImageIO;

import de.slothsoft.multiplier.graphics.Painter;

public class AbstractPainterExporter<T> implements Exporter {

	private final Class<T> classToBeExported;
	private final Function<T, Painter> painterFactory;
	private final String extension;

	public AbstractPainterExporter(Class<T> classToBeExported, Function<T, Painter> painterFactory, String extension) {
		this.classToBeExported = classToBeExported;
		this.painterFactory = painterFactory;
		this.extension = extension;
	}

	@Override
	public String getDisplayName() {
		return this.extension.toUpperCase() + " File";
	}

	@Override
	public String[] getSupportedExtensions() {
		return new String[] { this.extension };
	}

	@Override
	public boolean canExport(Class<?> classToBeExported) {
		return this.classToBeExported == classToBeExported;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void export(Object source, OutputStream target, Map<String, Object> config) {
		try {
			Painter painter = this.painterFactory.apply((T) source);
			Dimension size = painter.getPreferredSize();

			BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics = (Graphics2D) image.getGraphics();
			graphics.setColor(Color.WHITE);
			graphics.fillRect(0, 0, size.width, size.height);
			painter.paint(graphics);
			ImageIO.write(image, this.extension, target);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
