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
package com.uimirror.showcase.audit.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.uimirror.showcase.audit.core.AuditAspect;
import com.uimirror.showcase.audit.core.SomeService;

/**
 * @author 096068
 *
 */
@Configuration
@ComponentScan("com.uimirror.showcase.audit.core")
@EnableAspectJAutoProxy
public class AuditBeans {

	@Bean
	public SomeService someService(){
		return new SomeService();
	}
	
	@Bean
	public AuditAspect auditAspect(){
		return new AuditAspect();
	}
}
