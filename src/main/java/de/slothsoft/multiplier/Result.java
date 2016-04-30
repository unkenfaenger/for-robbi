package de.slothsoft.multiplier;

import java.util.Objects;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class wraps the output
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

@XmlRootElement
public class Result {

	private Input input;
	private int product;

	Result() {
		// for XML parsing
	}

	public Result(Input input) {
		setInput(input);
	}

	public Input getInput() {
		return this.input;
	}

	public Result input(Input newInput) {
		setInput(newInput);
		return this;
	}

	public void setInput(Input input) {
		this.input = Objects.requireNonNull(input).clone();
	}

	public int getProduct() {
		return this.product;
	}

	public Result product(int newProduct) {
		setProduct(newProduct);
		return this;
	}

	public void setProduct(int product) {
		this.product = product;
	}

}
