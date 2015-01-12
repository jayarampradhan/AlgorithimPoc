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
package com.uimirror.showcase.audit.core.db;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.util.StringUtils;

/**
 * @author 096068
 *
 */
public class UimInMemory {
	
	static{
		concurrentMap = new ConcurrentHashMap<String, Object>(20);
	}
	private static final ConcurrentMap<String, Object> concurrentMap;
	//Singleton instance
	public static final UimInMemory db = new UimInMemory(); 
	private UimInMemory(){
		//NOP
	}
	
	/**
	 * When you want to query for to DB use the id to get the DB details.
	 * it will either will return null when no record found else cached object
	 * @param id on which you want to save the details
	 * @return found object else <code>null</code>
	 */
	public Object queryBiId(String id){
		return concurrentMap.get(id);
	}
	
	/**
	 * This will try to save the object by the ID specified.
	 * Make sure if the id is existing it will throw {@link IllegalArgumentException}
	 * oif ID is absent then it will generate a new UUId and save into DB.
	 * @param id obn which you want to save the object
	 * @param obj the object you want to save
	 * @return the id
	 */
	public String saveById(String id, Object obj){
		id = StringUtils.hasText(id) ? id : UUID.randomUUID().toString();
		if(concurrentMap.get(id) != null){
			throw new IllegalArgumentException("Primary key Exists");
		}
		concurrentMap.putIfAbsent(id, obj);
		return id;
	}

}
