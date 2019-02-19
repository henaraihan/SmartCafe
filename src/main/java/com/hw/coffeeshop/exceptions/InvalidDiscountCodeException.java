package com.hw.coffeeshop.exceptions;

public class InvalidDiscountCodeException extends Exception  {
	/**
	 * The InvalidDiscountCodeException wraps all checked standard Java exceptions and enriches them with a custom error code
	 * checked exception
	 */
	
	private static final long serialVersionUID = -8077082032232568032L;

		public InvalidDiscountCodeException(String mesg) {
			super(mesg);
		}
			public InvalidDiscountCodeException(Throwable cause)
			{
				super(cause);
			}
			
			public InvalidDiscountCodeException(String mesg, Throwable cause) {
				
				super(mesg, cause);
		}
			public InvalidDiscountCodeException(String mesg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
				
				super(mesg, cause, enableSuppression, writableStackTrace);
			}

	}




