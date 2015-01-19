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

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.List;

import com.uimirror.collection.Stack;

/**
 * RPN- Reverse Polish Notaion Calculator, is a another way of solving the mathemetical problems.
 * 
 * For Example, we have a mathemetics statments 10-(4+3)*2, equivalent RPN 10 4 3 + 2 * -
 * 
 * @author Jay
 *
 */
public class RPNCalculator {

	
	/**
	 * Calculates the RPN expression into single result
	 * i.e 10 4 3 + 2 * - is equivalent to -4
	 * @param expressions
	 * @return
	 */
	public double doCalculateRpn(List<String> expressions){
		Stack<Double> stack = new Stack<Double>();
		for(String str : expressions){
			if(isNumeric(str)){
				stack.push(Double.parseDouble(str));
			}else{
				if(stack.getSize() < 2){
					throw new IllegalArgumentException("Not A valid expression");
				}
				double second_num = stack.pop();
				double first_num = stack.pop();
				stack.push(doCalculate(first_num, second_num, str));
			}
		}
		//By this it expects that stack will have only one number
		return stack.pop();
	}
	
	/**
	 * Based the numbers and operator specified does the calculation and return
	 * in case sufficent arguments or operator are invalid, then throws {@link IllegalArgumentException}
	 * @param num1 first operand
	 * @param num2 second operand
	 * @param op operator to be applied on the specified numbers
	 * @return result of the expression
	 */
	public double doCalculate(double num1, double num2, String op){
		if("*".equals(op)){
			return num1*num2;
		}else if("+".equals(op)){
			return num1 + num2;
		}else if("-".equals(op)){
			return num1 - num2;
		}else if("/".equals(op) && !Double.isNaN(num2) && !Double.isNaN(num1) && num2 > 0){
			return num1/num2;
		}else{
			throw new IllegalArgumentException("MalFormated Expression");
		}
	}
	
	public static boolean isNumeric(String str){
	  NumberFormat formatter = NumberFormat.getInstance();
	  ParsePosition pos = new ParsePosition(0);
	  formatter.parse(str, pos);
	  return str.length() == pos.getIndex();
	}
}
