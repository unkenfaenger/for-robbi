package de.slothsoft.multiplier.io;

import de.slothsoft.multiplier.Result;
import de.slothsoft.multiplier.graphics.ResultPainter;

public class ResultPngExporter extends AbstractPainterExporter<Result> {

	public ResultPngExporter() {
		super(Result.class, result -> new ResultPainter(result), "png");
	}

}
