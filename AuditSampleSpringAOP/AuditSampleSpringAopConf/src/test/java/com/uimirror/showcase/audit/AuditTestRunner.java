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

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * @author Jay
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions( glue = {"com.uimirror.showcase.audit"}, features="classpath:com/uimirror/showcase/audit/features.feature")
public class AuditTestRunner {
	

}
