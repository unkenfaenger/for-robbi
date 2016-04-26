package de.slothsoft.multiplier;

/**
 * This class wraps all possible input objects
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */
public class Input implements Cloneable {

	private int factor1 = 5;
	private int factor2 = 3;

	public int getFactor1() {
		return this.factor1;
	}

	public Input factor1(int newFactor1) {
		setFactor1(newFactor1);
		return this;
	}

	public void setFactor1(int factor1) {
		this.factor1 = factor1;
	}

	public int getFactor2() {
		return this.factor2;
	}

	public Input factor2(int newFactor2) {
		setFactor2(newFactor2);
		return this;
	}

	public void setFactor2(int factor2) {
		this.factor2 = factor2;
	}

	@Override
	public Input clone() {
		try {
			return (Input) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError("This will never happen!");
		}
	}
}
