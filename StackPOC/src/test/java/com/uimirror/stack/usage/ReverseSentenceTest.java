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

import org.junit.Test;

/**
 * Tests the logic of the reverse String.
 * <ul>
 * <li>testSentenceReverse with valid case</li>
 * <li>null as input</li>
 * <li>empty String as input</li>
 * </ul>
 * @author Jay
 *
 */
public class ReverseSentenceTest {

	@Test
	public void testSentenceReverse() {
		String input = "This is a test";
		ReverseSentence rs = new ReverseSentence();
		String output = rs.reverseIt(input);
		assertThat(output).isEqualTo("test a is This");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSentenceReverseInvalidInput() {
		String input = null;
		ReverseSentence rs = new ReverseSentence();
		rs.reverseIt(input);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSentenceReverseEmptyInput() {
		String input = "";
		ReverseSentence rs = new ReverseSentence();
		rs.reverseIt(input);
	}

}
