package de.slothsoft.multiplier.io;

import de.slothsoft.multiplier.Result;
import de.slothsoft.multiplier.graphics.ResultPainter;

public class ResultJpgExporter extends AbstractPainterExporter<Result> {

	private static final String[] EXTENSIONS = { "jpg", "jpeg" };

	public ResultJpgExporter() {
		super(Result.class, result -> new ResultPainter(result), EXTENSIONS[0]);
	}

	@Override
	public String[] getSupportedExtensions() {
		return EXTENSIONS.clone();
	}
}
