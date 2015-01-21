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

import java.util.ArrayList;
import java.util.List;

import com.uimirror.collection.Stack;

/**
 * This will convert an infix notation to the post-fix notation.
 * i.e A + B = AB+
 * A * (B + C * D) + E becomes A B C D * + * E +
 * @author Jay
 *
 */
public class InFixToPostFix {
	
	public List<String> transformToPostFix(String infix){
		Stack<OperatorSymbol> opStack = new Stack<OperatorSymbol>();
		List<String> postFix = new ArrayList<String>();
		int skipCount = 0;
		for(int i = 0; i < infix.length() ; i++){
			String chunk = Character.toString(infix.charAt(i));
			OperatorSymbol sym = getSymbol(chunk);
			//If not operator simply append it
			if(sym == null){
				postFix.add(chunk);
				continue;
			}
			//Stack is empty no need to continue further, simply add to stack and continue
			if(opStack.getSize() == 0){
				opStack.push(sym);
				continue;
			}
			if(OperatorSymbol.LEFT_PARENTHESIS == sym){
				skipCount++;
				continue;
			}
			//No need to skip the 
			if(skipCount == 0 ){
				
			}
			
		}
		
		return null;
	}
	
	/**
	 * Determines if the provided string is a operator or not
	 * i.e. "+" will return true
	 * @param text which needs to check for
	 * @return <code>true</code> if the text is a operand else <code>false</code>
	 */
	private boolean isOpernadASymbol(String text){
		if(getSymbol(text) != null)
			return Boolean.TRUE;
		return Boolean.FALSE;
	}
	
	/**
	 * Gets the {@link OperatorSymbol} from the given text.
	 * 
	 * @param text neeeds to be checked
	 * @return opeartor if found else <code>null</code>
	 */
	private OperatorSymbol getSymbol(String text){
		return OperatorSymbol.getSymbol(text);
	}
	
	private boolean isHighPrecdence(String operator){
		return Boolean.FALSE;
	}
	
	
	/**
	 * All possible Mathematics operators and its precedence level.
	 * lower the levl is heigh in precedence for example 
	 * {@link #PLUS} is less precedence over {@link #MULTI}
	 * 
	 * @author Jay
	 *
	 */
	private enum OperatorSymbol{
		PLUS("+", 5),
		MINUS("-", 5),
		MULTI("*", 4),
		DEVIDE("/", 4),
		CARET("^", 3),
		LEFT_PARENTHESIS("(", 2),
		RIGHT_PARENTHESIS(")", 2);
		
		OperatorSymbol(String symbol, int level){
			this.symbol = symbol;
			this.level = level;
		}
		private final String symbol;
		private final int level;
		
		/**
		 * @return the symbol
		 */
		public String getSymbol() {
			return symbol;
		}
		
		/**
		 * @return the level
		 */
		public int getLevel() {
			return level;
		}

		@Override
		public String toString(){
			return symbol;
		}
		
		public static OperatorSymbol getSymbol(String symbol){
			if(symbol == null)
				return null;
			for( OperatorSymbol sy : OperatorSymbol.values()){
				if(symbol == sy.symbol){
					return sy;
				}
			}
			return null;
		}
		
	}

}
