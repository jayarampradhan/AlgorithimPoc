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

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.uimirror.showcase.audit.core.db.UimInMemory;

@Aspect
public class AuditAspect {
	
	protected static final Logger LOG = LoggerFactory.getLogger(AuditAspect.class);
	private @Autowired UimInMemory uimInMemory;

	@Around("execution(* *(..)) && @annotation(audit)")
	public Object auditIt(ProceedingJoinPoint joinPoint, Audit audit) throws Throwable {
		
		LOG.debug("[AOP-START]- Invoking the before signature");
		Signature signature = joinPoint.getSignature();
		String methodName = signature.getName();
		AuditData beforeData = new AuditData.AuditDataBuilder().addMessage("Before").addMethodName(methodName).build();
		String beforeId = uimInMemory.saveById(null, beforeData);
		LOG.info("Before data ID: "+beforeId);
		try {
			Object proceed = joinPoint.proceed();
            AuditData afterData = new AuditData.AuditDataBuilder().addMessage("After").addMethodName(methodName).build();
            String id = uimInMemory.saveById(null, afterData);
            LOG.info("After data ID: "+id);
            LOG.info("Service Executed and Audit performed");
            return proceed;
        } finally {
            LOG.debug("[AOP-END]-Finally Returned");
        }
	}
}
