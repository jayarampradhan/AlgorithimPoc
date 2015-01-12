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
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.uimirror.showcase.audit.conf.AuditBeans;

public class StartApp {

	private static final Logger LOG = LoggerFactory.getLogger(StartApp.class);
	public static void main(String[] args) {
		LOG.info("[START]- Lunching.....");   
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AuditBeans.class);
		Luncher luncher = ctx.getBean(Luncher.class);
		LOG.info("[START]- Wow!!! Its Lunched, Play With it GentleMan.");   
		luncher.lunchApp();
		LOG.info("[END]- Sorry On Demand I am going Away...");
	}

}
