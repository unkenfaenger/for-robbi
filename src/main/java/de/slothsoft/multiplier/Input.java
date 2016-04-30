package de.slothsoft.multiplier;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class wraps all possible input objects
 * 
 * @author Unkenfaenger
 * @since 0.0.1
 */

@XmlRootElement
public class Input implements Cloneable, Serializable {

	private static final long serialVersionUID = 1043622810928944528L;

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
