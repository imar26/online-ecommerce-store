package com.neu.project.exception;

public class BuyerException extends Exception {
	public BuyerException(String message)
	{
		super("BuyerException-"+message);
	}
	public BuyerException(String message, Throwable cause)
	{
		super("BuyerException-"+message,cause);
	}
}
