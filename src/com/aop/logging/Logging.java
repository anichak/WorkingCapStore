package com.aop.logging;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.*;

public class Logging {
	static Logger log = Logger.getLogger(Logging.class);

	public void beforeAdvice(JoinPoint joinPoint) {
		log.info("                                                                  ");
		log.info("------------------------------------------------------------------");
		log.info("Before setting The Method    " +joinPoint.getSignature().getName());
		log.info(new Date() + ":     " + " " + " To be executing:     " + ""
				+ joinPoint.getSignature().getName());
		
		log.info("                                                                  ");
		log.info("------------------------------------------------------------------");
	}
	/**
	 * This is the method which I would like to execute after a selected method
	 * execution.
	 */
	public void afterAdvice(JoinPoint joinPoint) {
		log.info("                                                                  ");
		log.info("------------------------------------------------------------------");
		log.info("After setting The Method " + new Date() + ":     " + " "
				+ " Excecuted:           " + joinPoint.getSignature().getName());
		log.info("                                                                  ");
		log.info("------------------------------------------------------------------");
	}
	/**
	 * This is the method which I would like to execute when any method returns.
	 */
	public void afterReturningAdvice(Object retVal) {

		try {
			log.info("                                                                  ");
			log.info("------------------------------------------------------------------");
			log.info("After Returning from the method  " + new Date() + ":     "
					+ "  Returning:           " + retVal.toString());
			
			log.info("                                                                  ");
			log.info("------------------------------------------------------------------");
		} catch (Exception e) {

		}

	}
	public void AfterThrowingAdvice(JoinPoint joinPoint, Throwable ex) {
		log.info("                                                                  ");
		log.info("------------------------------------------------------------------");
		log.info(new Date() + ":     " + "  Method               "
				+ joinPoint.getSignature().getName() + "         exception "
				+ ex.toString());// +" "+ new new Date()() );
		log.info("                                                                  ");
		log.info("------------------------------------------------------------------");
	}
}