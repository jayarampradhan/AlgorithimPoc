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

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Normal Bean for the Audit details
 * such as the time it got accessed and which method it got accessed.
 * @author 096068
 *
 */
public class AuditData {
	
	//Time in Epoch
	private long time;
	private String method;
	private String msg;
	
	public static class AuditDataBuilder{
		private long time;
		private String method;
		private String msg;
		
		public AuditDataBuilder(long time){
			time = (time == 0l) ? AuditData.getCurrentEpoch() : time;  
			this.time = time;
		}
		public AuditDataBuilder(){
			this.time = AuditData.getCurrentEpoch();  
		}
		public AuditDataBuilder addMethodName(String method){
			this.method = method;
			return this;
		}
		
		public AuditDataBuilder addMessage(String msg){
			this.msg = msg;
			return this;
		}
		
		public AuditData build(){
			return new AuditData(this);
		}
		
	}
	
	
	/**
	 * Builds the object and return the {@link AuditData}
	 * @param builder an isntance of {@link AuditDataBuilder}
	 * 
	 */
	private AuditData(AuditDataBuilder builder) {
		this.time = builder.time;
		this.method = builder.method;
		this.msg = builder.msg;
	}
	
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	public static long getCurrentEpoch(){
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		calendar.clear();
		calendar.set(2011, Calendar.OCTOBER, 1);
		return calendar.getTimeInMillis() / 1000L;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (time ^ (time >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuditData other = (AuditData) obj;
		if (time != other.time)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AuditData [time=" + time + ", method=" + method + ", msg="
				+ msg + "]";
	}

}
