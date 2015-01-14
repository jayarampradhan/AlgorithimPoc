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

import com.uimirror.collection.Stack;

/**
 * This will Reverse the sentence using the Stack.
 * Example: This is a test
 * will be out put: test a is This
 * 
 * Usage: Uses the {@link Stack} which tokenizer the string and put into the stack
 * and after the end of the string it will pop to get the reversed words.
 * 
 * @author Jay
 *
 */
public class ReverseSentence {

	/**
	 * Seprator which will be used for the string seprations
	 */
	static final String SPACE_SEPRATOR = " ";
	
	/**
	 * Reverse the provided Sentence and returns the reversed string using 
	 * {@link Stack}
	 * @param input which sentence needs to be reversed
	 * @return revesred string
	 */
	public String reverseIt(String input){
		if(null == input)
			throw new IllegalArgumentException("Invalid Input for the sentence reverse");
		
		System.out.println("Input String is: "+input);
		
		String[] tokens = input.split(SPACE_SEPRATOR);
		if(1 == tokens.length){
			throw new IllegalArgumentException("Can't Reverse the empty string");
		}
		Stack<String> stack = new Stack<String>(tokens.length+1, 1);
		for(String token : tokens){
			stack.push(token);
		}
		int count = tokens.length;
		StringBuilder sb = new StringBuilder();
		while(count > 0){
			String poped = stack.pop();
			sb.append(poped);
			count--;
			if(count != 0)
				sb.append(SPACE_SEPRATOR);
		}
		
		System.out.println("Reveres String is: "+sb.toString());
		return sb.toString();
	}
}
