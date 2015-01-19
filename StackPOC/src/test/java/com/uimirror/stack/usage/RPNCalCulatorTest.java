/*******************************************************************************
 * Copyright (c) 2015 Uimirror.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of Apache V2.0
 * which accompanies this distribution, and is available at
 * 
 *
 * Contributors:
 * Uimirror.com. - initial API and implementation
 *******************************************************************************/
package com.uimirror.stack.usage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test Case for the {@link RPNCalculator}
 * It tests below senarios:
 * <ul>
 * <li>A valid +ve number syntax</li>
 * <li>A valid -ve number syntax</li>
 * <li>An invalid syntax</li>
 * </ul>
 * @author Jay
 *
 */
public class RPNCalCulatorTest {

	@Test
	public void testPositiveExpression() {
		String  expression = "15 4 3 + 2 * -";
		List<String> expressions = new ArrayList<String>();
		for(String str : expression.split(" ")){
			expressions.add(str);
		}
		RPNCalculator rpnCalculator = new RPNCalculator();
		double result = rpnCalculator.doCalculateRpn(expressions);
		assertThat(result).isEqualTo(1.00);
	}
	
	@Test
	public void testNegativeExpression() {
		String  expression = "10 4 3 + 2 * -";
		List<String> expressions = new ArrayList<String>();
		for(String str : expression.split(" ")){
			expressions.add(str);
		}
		RPNCalculator rpnCalculator = new RPNCalculator();
		double result = rpnCalculator.doCalculateRpn(expressions);
		assertThat(result).isEqualTo(-4.00);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidExpression() {
		String  expression = "10 4 3 - + 2 * -";
		List<String> expressions = new ArrayList<String>();
		for(String str : expression.split(" ")){
			expressions.add(str);
		}
		RPNCalculator rpnCalculator = new RPNCalculator();
		rpnCalculator.doCalculateRpn(expressions);
	}

}
