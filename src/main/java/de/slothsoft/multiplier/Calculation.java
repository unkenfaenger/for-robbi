package de.slothsoft.multiplier;

/**
 * This class is the complex calculation transforming an {@link Input} into an
 * {@link Result}
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */
public class Calculation {

	private final Input input;

	public Calculation(Input input) {
		this.input = input;
	}

	public Result calculate() {
		return new Result(this.input).product(this.input.getFactor1() * this.input.getFactor2());
	}

}
