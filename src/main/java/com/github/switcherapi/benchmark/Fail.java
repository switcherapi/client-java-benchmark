package com.github.switcherapi.benchmark;

@SuppressWarnings("serial")
public class Fail extends RuntimeException {
	
	public Fail() {
		super("Something went wrong");
	}
}
