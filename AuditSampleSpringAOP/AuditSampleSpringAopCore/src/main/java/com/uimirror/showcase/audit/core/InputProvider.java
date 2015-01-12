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
package com.uimirror.showcase.audit.core;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * An Wrapper class on top of the Scanner, to make the test driven more simpler
 * @author Jay
 *
 */
public class InputProvider {

	private @Autowired Scanner scanIn;
	
	public int inputInt(){
		return scanIn.nextInt();
	}
	
	public String inputString(){
		return scanIn.next();
	}
}
