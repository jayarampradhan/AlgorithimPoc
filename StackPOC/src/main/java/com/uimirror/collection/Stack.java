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
package com.uimirror.collection;

import java.util.Arrays;

/**
 * This is basic implementation of the stack API.
 * Example to use 
 * <code>
 * Stack<String> stack = new Stack<String>(10)
 * </code>
 * <a href="http://en.wikipedia.org/wiki/Stack_%28abstract_data_type%29"/>Abstract Data Type</a>
 * If you indded to use the Stack API, suggested to use of {@link java.util.Stack}
 * @author Jayaram
 *
 */
public class Stack<E> {

	private transient static final int DEFAULT_SIZE = 16;
	private transient static final float DEFAULT_LOADFACTOR = 0.75f;
	private transient Object[] buckets;
	
	private transient float loadfactor;
	
	/**
	 *Number of element present in the Stack 
	 */
	private transient int elementCount;
	 /**
     * The next size value at which to resize (capacity * load factor).
     * @serial
     */
    private int threshold;
	
	/**
	 * Constructs the Stack using the intialCapacity supplied
	 * @param intialCapacity size of the bucket to be allocated
	 */
	public Stack(int intialCapacity){
		this(intialCapacity, DEFAULT_LOADFACTOR);
	}
	
	/**
	 * Constructs the stack with the given size and loadfactor
	 * @param intialCapacity size of the bucket
	 * @param loadFactor threashold, when bucket needs to be reintialized. 
	 */
	public Stack(int intialCapacity, float loadFactor){
		if(intialCapacity < 0)
			throw new IllegalArgumentException("Couldn't Intialize the Stack as Size is not valid.");
		if(loadFactor <= 0 || Float.isNaN(loadFactor))
			throw new IllegalArgumentException("LoadFactor to Intialize the Stack is not valid.");
		// Find a power of 2 >= initialCapacity
        int capacity = 1;
        while (capacity < intialCapacity)
            capacity <<= 1;
        threshold = (int)(capacity * loadFactor);
        this.loadfactor = loadFactor;
        this.buckets = new Object[capacity];
	}
	
	public Stack(){
		this(DEFAULT_SIZE, DEFAULT_LOADFACTOR);
	}
	
	/**
	 * Push The Element to the top of the stack 
	 * @param element to get inserted
	 * @return the updated element
	 */
	public synchronized E push(E element){
		ensureCapacityHelper(elementCount + 1);
		buckets[elementCount++] = element;
		return element;
	}
	
	/**
	 * Pop The Element from the top of the stack 
	 * @return the poped element
	 */
	public synchronized E pop(){
		//ensureCapacityHelper(elementCount + 1);
		E	obj;

		obj = peek();
		buckets[--elementCount] = null;
		return obj;
	}
	
	
	/**
	 * Returns the current Size of the Stack
	 * @return size of the stack
	 */
	public synchronized int getSize(){
		return elementCount;
	}
	
	/**
	 * Reads the top elemnet from the Stack
	 * @return the top element
	 */
	public synchronized E peek(){
		if(elementCount == 0){
			throw new IllegalArgumentException("Stack Is Empty");
		}
		return (E) buckets[elementCount - 1];
	}
	
	/**
	 * Search the Specified element
	 * @param o object to be search
	 * @return last found index
	 */
	public synchronized int search(E o) {
		if (o == null) {
			for (int i = elementCount - 1; i >= 0; i--)
				if (buckets[i] == null)
					return i;
		} else {
			for (int i = elementCount - 1; i >= 0; i--)
				if (o.equals(buckets[i]))
					return i;
		}
		return -1;
	}
	
	private void ensureCapacityHelper(int count){
		if (count >= threshold) {
			resize(2 * buckets.length);
		}
	}

	/**
	 * This will copy the old array to new Array
	 * @param i
	 */
	private void resize(int newSize) {
		buckets = Arrays.copyOf(buckets, newSize);
	}
}
