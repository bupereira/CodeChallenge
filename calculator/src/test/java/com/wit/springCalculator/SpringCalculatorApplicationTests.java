package com.wit.springCalculator;

import com.wit.springCalculator.core.CalculatorCore;
import com.wit.springCalculator.core.exception.MathematicalException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class SpringCalculatorApplicationTests {

	private CalculatorCore calculatorCore = new CalculatorCore();

	@ParameterizedTest
	@MethodSource("getTestValues")
	void testAllOperations(String expected, String param) {
		assertEquals(expected, calculatorCore.run(param));
	}

	@ParameterizedTest
	@MethodSource("getErrorValues")
	void testAllErrors(String expected, String param) {
		Exception e = assertThrowsExactly(MathematicalException.class, () -> calculatorCore.run(param));
		assertEquals(expected, e.getMessage());
	}

	private static Stream<Arguments> getTestValues() {
		return Stream.of(
				Arguments.of("2", "1,1,Sum"),
				Arguments.of("3", "6,3,Subtract"),
				Arguments.of("25", "5,5,Multiply"),
				Arguments.of("10", "100,10,Divide"), // exact division
				Arguments.of("2.5", "10,4,Divide"), // having a remainder
				Arguments.of("3.3333333333333333333333333333333333333333333333334", "10,3,Divide") // repeated remainder

		);
	}

	private static Stream<Arguments> getErrorValues() {
		return Stream.of(
				Arguments.of("One of the supplied arguments is not a number. Please check and resubmit.", "Foo,1,Sum"),
				Arguments.of("Wrong number of parameters supplied. Acceptable Parameters are (Number) x, (Number) y, (String) operation", "1,1,1,Sum"),
				Arguments.of("Error finding operation: Foo", "1,1,Foo"),
				Arguments.of("Cannot divide by ZERO", "1,0,Divide")
		);
	}

}
