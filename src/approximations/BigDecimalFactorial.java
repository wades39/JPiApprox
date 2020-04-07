package approximations;

import java.math.BigDecimal;

/**
 * This class calculates the factorial value of a BigDecimal.
 * 
 * @author Kyler McMullin
 *
 */
public class BigDecimalFactorial {

	/**
	 * Recursively calculate the value of BigDecimal n!
	 * 
	 * @param n          The initial value of the BigDecimal.
	 * @param cumulative The cumulative value of the factorial.
	 * @return nFactorial.
	 */
	private BigDecimal factorial(BigDecimal n, BigDecimal cumulative) {
		if (n.equals(BigDecimal.ZERO)) {
			return BigDecimal.ONE;
		}
		if (n.equals(BigDecimal.ONE)) {
			return cumulative;
		}
		BigDecimal lessOne = n.subtract(BigDecimal.ONE);
		return factorial(lessOne, cumulative.multiply(lessOne));
	}
	
	/**
	 * Recursively calculate the value of BigDecimal n!
	 * 
	 * @param n          The initial value of the BigDecimal.
	 * @return nFactorial.
	 */
	public BigDecimal factorial(BigDecimal n) {
		if (n.equals(BigDecimal.ZERO)) {
			return BigDecimal.ONE;
		}
		if (n.equals(BigDecimal.ONE)) {
			return BigDecimal.ONE;
		}
		BigDecimal lessOne = n.subtract(BigDecimal.ONE);
		return factorial(lessOne, n.multiply(lessOne));
	}
}
