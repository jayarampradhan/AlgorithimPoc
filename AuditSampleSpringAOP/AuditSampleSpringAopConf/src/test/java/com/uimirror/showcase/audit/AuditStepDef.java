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
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.StringUtils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.LoggingEvent;
import ch.qos.logback.core.Appender;

import com.uimirror.showcase.audit.conf.AuditBeans;
import com.uimirror.showcase.audit.core.AuditData;
import com.uimirror.showcase.audit.core.InputProvider;
import com.uimirror.showcase.audit.core.db.UimInMemory;

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
	private @Mock UimInMemory uimInMemory;
	private String primarykey;
	@SuppressWarnings("rawtypes")
	@Mock
    private Appender mockAppender;
	@Captor
    private ArgumentCaptor<LoggingEvent> captorLoggingEvent;
	
	private List<String> inputs;
	private List<String> queryInputs;
	
	@SuppressWarnings("unchecked")
	@Before
	public void before(){
		MockitoAnnotations.initMocks(this);
		dataSet = new WeakHashMap<String, AuditData>();
		inputs = new ArrayList<String>();
		queryInputs = new ArrayList<String>();
		final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        logger.addAppender(mockAppender);
	}
	
	 @SuppressWarnings("unchecked")
	@After
	 public void teardown() {
		 final Logger logger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		 logger.detachAppender(mockAppender);
	 }
	 
	 @Given("^I have PreLoaded Audit data$")
	 public void i_have_PreLoaded_Audit_data(List<DataSet> dataSets)throws Throwable {
		for (DataSet data : dataSets) {
			dataSet.put(data.getId(), data.getAuditObject());
		}
	 }
	 
	 @Given("^I will press \"(.*?)\"$")
	 public void i_will_press(String what) throws Throwable {
		 inputs.add(what);
	 }

	 @Given("^I will press \"(.*?)\" to query db$")
	 public void i_will_press_to_query_db(String what) throws Throwable {
		 inputs.add(what);
	 }

	 @Given("^I gave primary key as \"(.*?)\"$")
	 public void i_gave_primary_key_as(String what) throws Throwable {
		 queryInputs.add(what);
		 primarykey = what;
	 }

	 @Given("^I lunch the application$")
	 public void i_lunch_the_application() throws Throwable {
		 mockIntInput();
		 mockStrInput();
		 if(StringUtils.hasText(primarykey)){
			 when(uimInMemory.queryBiId(primarykey)).thenReturn(dataSet.get(primarykey));
		 }
		 luncher.lunchApp();
	 }

	@SuppressWarnings("unchecked")
	@Then("^Log Says \"(.*?)\"$")
	public void log_Says(String what) throws Throwable {
		verify(mockAppender,atLeastOnce()).doAppend(captorLoggingEvent.capture());
		//Having a genricised captor means we don't need to cast
		List<LoggingEvent> allValues = captorLoggingEvent.getAllValues();
		StringBuilder builder = new StringBuilder();
		for(LoggingEvent event : allValues){
			builder.append(event.getFormattedMessage());
			builder.append("\n");
		}
		//assertThat(loggingEvent.getLevel(), is(Level.INFO));
		//Check the message being logged is correct
		assertThat(builder.toString()).contains(what);
	}



	@Then("^I got response as \"(.*?)\", \"(.*?)\"$")
	public void i_got_response_as(String whatTime, String whatMessage) throws Throwable {
		AuditData result = (AuditData)uimInMemory.queryBiId(primarykey);
		assertThat(result.getMsg()).isEqualTo(whatMessage);
		assertThat(result.getTime()).isEqualTo(Long.parseLong(whatTime));
	}
	
	private void mockIntInput(){
		if(inputs.size() > 0){
			 String firstInput = inputs.get(0);
			 inputs.remove(0);
			 OngoingStubbing<Integer> thenReturn = when(input.inputInt()).thenReturn(Integer.parseInt(firstInput));
			 for(String in : inputs){
				 thenReturn.thenReturn(Integer.parseInt(in));
			 }
			 inputs.clear();
		 }
	}
	private void mockStrInput(){
		if(queryInputs.size() > 0){
			 String firstQueryIn = queryInputs.get(0);
			 queryInputs.remove(0);
			 OngoingStubbing<String> thenReturnQuery = when(input.inputString()).thenReturn(firstQueryIn);
			 for(String in : queryInputs){
				 thenReturnQuery.thenReturn(in);
			 }
			 queryInputs.clear();
		 }
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
