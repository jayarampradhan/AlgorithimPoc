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
package com.uimirror.showcase.audit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.showcase.audit.core.InputProvider;
import com.uimirror.showcase.audit.core.SomeService;
import com.uimirror.showcase.audit.core.db.UimInMemory;

/**
 * Sample Luncher class to invoke the service and DB
 * @author Jay
 *
 */
public class Luncher {

	protected static final Logger LOG = LoggerFactory.getLogger(Luncher.class);
	private @Autowired SomeService someService;
	private @Autowired InputProvider input; 
	private @Autowired UimInMemory uimInMemory;
	
	public void lunchApp(){
		LOG.info("[START]- Application Started Play with it");   
		int choice = 0;
		String id = null;
		do{
			promot();
			choice = input.inputInt();
			switch (choice) {
			case 1:
				someService.sayHello();
				break;
			case 2:
				System.out.println("Enter The Primary Key/ ID:");
				id = input.inputString();
				Object queryBiId = uimInMemory.queryBiId(id);
				System.out.println(queryBiId);
				break;
			default:
				break;
			}
			
		}while(choice != 3);
		LOG.info("[END]- I am closing Bye bye");
	}
	
	public static void promot(){
		System.out.println("1- For Runing the service");   
	    System.out.println("2- For querying DB");   
	    System.out.println("3- For Quit");
	}
}
