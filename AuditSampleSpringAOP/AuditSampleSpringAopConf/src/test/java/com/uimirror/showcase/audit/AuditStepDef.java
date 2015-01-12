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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

import com.uimirror.showcase.audit.conf.AuditBeans;
import com.uimirror.showcase.audit.core.AuditData;
import com.uimirror.showcase.audit.core.InputProvider;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.runtime.java.StepDefAnnotation;

/**
 * @author Jay
 *
 */
//@SpringApplicationConfiguration(classes = StartApp.class)
@StepDefAnnotation
@ContextConfiguration(classes= AuditBeans.class)
public class AuditStepDef {
	@Mock
	private InputProvider input;
	@InjectMocks
	private  @Autowired Luncher luncher;
	private Map<String, AuditData> dataSet;
	private String primarykey;
	@Mock
    private Appender mockAppender;
	@Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
		dataSet = new WeakHashMap<String, AuditData>();
		final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(mockAppender);
	}
	
	 @After
	 public void teardown() {
		 final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		 logger.detachAppender(mockAppender);
	 }

	@Given("^I have PreLoaded Audit data$")
	public void i_have_PreLoaded_Audit_data(List<DataSet> dataSets) throws Throwable {
		for(DataSet data : dataSets){
			dataSet.put(data.getId(), data.getAuditObject());
		}
	}

	@Given("^I lunch the application$")
	public void i_lunch_the_application() throws Throwable {
	    luncher.lunchApp();
	}

	@Given("^I press \"(.*?)\"$")
	public void i_press(String what) throws Throwable {
		when(input.inputInt()).thenReturn(Integer.parseInt(what));
		
		when(input.inputString()).thenReturn(what);
	}

	@Given("^I gave primary key as \"(.*?)\"$")
	public void i_gave_primary_key_as(String what) throws Throwable {
		this.primarykey = what;
	}

	@Then("^I got response as \"(.*?)\", \"(.*?)\"$")
	public void i_got_response_as(long whatTime, String whatMessage) throws Throwable {
		AuditData result = dataSet.get(primarykey);
		assertThat(result.getMsg()).isEqualTo(whatMessage);
		assertThat(result.getTime()).isEqualTo(whatTime);
	}
	
	@Then("^Log Says \"(.*?)\"$")
	public void log_Says(String arg1) throws Throwable {
		//Now verify our logging interactions
        verify(mockAppender).doAppend(captorLoggingEvent.capture());
        //Having a genricised captor means we don't need to cast
        final LoggingEvent loggingEvent = captorLoggingEvent.getValue();
        //Check log level is correct
//        assertThat(loggingEvent.getLevel()).isEqualTo(Level.INFO);
        //Check the message being logged is correct
        System.out.println(loggingEvent.getFormattedMessage());
	}
	
	public static class DataSet {
		private String id;
		private long time;
		private String method;
		private String msg;
		
		public AuditData getAuditObject(){
			return new AuditData.AuditDataBuilder(time).addMessage(msg).addMethodName(method).build();
		}
		/**
		 * @return the id
		 */
		public String getId() {
			return id;
		}
	}
}
