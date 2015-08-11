package com.walmart.esb.zcache.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationFactory.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

public class ZCacheLogger {
	// declare a LOG4J_CONFIG_FILE variable and assign a unique value
	// across all the class
	private static final String	LOG4J_CONFIG_FILE	= "log4j2.xml";
	/**
	 * Static Block to executed first so that it helps to configure the resources
	 */
	
	static
	{
		ConfigurationSource source = new ConfigurationSource();
		source.setInputStream(ZCacheLogger.class.getClassLoader().getResourceAsStream(LOG4J_CONFIG_FILE));
		Configurator.initialize(ZCacheLogger.class.getClassLoader(), source);
	}
	
	/**
	 * getter method to get Logger
	 * 
	 * @param name hold logger value
	 */
	public static Logger getLogger(String name) {
		return LogManager.getLogger(name);
	}
}
