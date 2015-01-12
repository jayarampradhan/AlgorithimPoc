/*******************************************************************************
 * Copyright (c) 2012 Pivotal Software, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Pivotal Software, Inc. - initial API and implementation
 *******************************************************************************/
package com.uimirror.showcase.audit;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.uimirror.showcase.audit.conf.AuditBeans;
import com.uimirror.showcase.audit.core.SomeService;
import com.uimirror.showcase.audit.core.db.UimInMemory;

public class StartApp {

	private static final Logger LOG = LoggerFactory.getLogger(StartApp.class);
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditBeans.class);
		SomeService someService = ctx.getBean(SomeService.class);
		Scanner scanIn = new Scanner(System.in);
		LOG.info("[START]- Application Started Play with it");   
		int choice = 0;
		String id = null;
		do{
			promot();
			choice = scanIn.nextInt();
			switch (choice) {
			case 1:
				someService.sayHello();
				break;
			case 2:
				System.out.println("Enter The Primary Key/ ID:");
				id = scanIn.next();
				Object queryBiId = UimInMemory.db.queryBiId(id);
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
