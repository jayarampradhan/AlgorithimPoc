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

/**
 * @author 096068
 *
 */
public class SomeService {
	
	@Audit
	public String sayHello(){
		return "Hello";
	}

}
