package de.slothsoft.multiplier;

import org.junit.Assert;
import org.junit.Test;

public class CalculationTest {

	private Input input = new Input().factor1(1).factor2(2);
	private Calculation calculation = new Calculation(this.input);

	@Test
	public void test() throws Exception {
		this.input.factor1(3).factor2(5);
		Assert.assertEquals(15, this.calculation.calculate().getProduct());

		this.input.factor1(7).factor2(7);
		Assert.assertEquals(49, this.calculation.calculate().getProduct());

		this.input.factor1(10).factor2(10);
		Assert.assertEquals(100, this.calculation.calculate().getProduct());
	}
}
