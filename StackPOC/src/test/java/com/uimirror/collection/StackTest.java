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
package com.uimirror.collection;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Test Case for the {@link Stack}
 * This has Basic test cases as follows:
 * <ul>
 * <li>Basic functionality</li>
 * <li>Order property</li>
 * <li>Emptiness invariant</li>
 * <li>Failure mode</li>
 * </ul>
 * @author Jay
 *
 */
public class StackTest {

	@Test
	public void testPushAndPeek() {
		Stack<String> stack = new Stack<String>();
		stack.push("Jayaram");
		stack.push("Pradhan");
		assertThat(stack.peek()).isEqualTo("Pradhan");
	}
	
	@Test
	public void testPop(){
		Stack<String> stack = new Stack<String>();
		stack.push("Jayaram");
		stack.push("Pradhan");
		assertThat(stack.pop()).isEqualTo("Pradhan");
	}
	
	@Test
	public void testPushPopInOrder(){
		Stack<String> stack = new Stack<String>(3, 1);
		List<String> inputs = new ArrayList<String>();
		inputs.add("Jayaram");
		inputs.add("Pradhan");
		inputs.add("Test");
		inputs.add("Test2");
		for(String str : inputs){
			stack.push(str);
		}
		int count = inputs.size();
		while(count > 0){
			String poped = stack.pop();
			assertThat(poped).isEqualTo(inputs.get(count-1));
			count--;
		}
	}
	
	@Test
	public void testStackIsEmpty(){
		Stack<String> stack = new Stack<String>();
		try{
			stack.pop();
		}catch(IllegalArgumentException e){
			assertThat(e.getMessage()).isEqualTo("Stack Is Empty");
		}
	}

}
