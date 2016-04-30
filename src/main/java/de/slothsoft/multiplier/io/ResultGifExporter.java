package de.slothsoft.multiplier.io;

import de.slothsoft.multiplier.Result;
import de.slothsoft.multiplier.graphics.ResultPainter;

public class ResultGifExporter extends AbstractPainterExporter<Result> {

	public ResultGifExporter() {
		super(Result.class, result -> new ResultPainter(result), "gif");
	}

}
