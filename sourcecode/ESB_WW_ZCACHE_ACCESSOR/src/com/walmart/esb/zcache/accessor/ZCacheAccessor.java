package com.walmart.esb.zcache.accessor;

import java.io.Serializable;

import org.apache.logging.log4j.Logger;

import com.walmart.esb.zcache.logger.ZCacheLogger;
import com.walmart.platform.helix.cache.Cache;
import com.walmart.platform.helix.cache.CacheManager;

public class ZCacheAccessor {
	
	static final Logger	          logger	                 = ZCacheLogger.getLogger(ZCacheAccessor.class.getName());
	
	private static CacheManager	  cacheManager	             = null;
	private static ZCacheAccessor	zCacheAccessor;
	
	private static final String	  GIP_EAI_ZCACHE_CONFIG_FILE	= "/zcache/distributed-cache.yaml";
	
	private ZCacheAccessor() {
		
	}
	
	public static synchronized ZCacheAccessor getInstance() {
		logger.debug("START initialize getInstance");
		try
		{
			if (zCacheAccessor == null)
			{
				zCacheAccessor = new ZCacheAccessor();
			}
			if (cacheManager == null)
			{
				cacheManager = CacheManager.getInstance();
				cacheManager.setDistributedCacheConfiguration(GIP_EAI_ZCACHE_CONFIG_FILE);
			}
			logger.debug("END initialize getInstance zCacheAccessor " + zCacheAccessor + " cacheManager " + cacheManager);
		}
		catch (Exception e)
		{
			logger.error("Exception while getInstance " + e.getMessage());
			logger.error("Exception while getInstance ", e);
			cacheManager = null;
		}
		return zCacheAccessor;
	}
	
	private static Cache<String, Serializable> getCache(String zCacheTableName) {
		logger.debug("START getCache zCacheTableName " + zCacheTableName);
		Cache<String, Serializable> cache = null;
		try
		{
			cache = cacheManager.getCache(zCacheTableName);
			
			logger.debug("END getCache zCacheTableName " + zCacheTableName + " cache " + cache);
		}
		catch (Exception e)
		{
			logger.error("Exception getCache " + e.getMessage());
			logger.error("Exception getCache ", e);
		}
		return cache;
		
	}
	
	public Serializable get(String zCacheTableName, String key) {
		logger.debug("START get zCacheTableName " + zCacheTableName + " key " + key);
		Serializable serializable = null;
		try
		{
			serializable = getCache(zCacheTableName).get(key);
			logger.debug("END get zCacheTableName " + zCacheTableName + " key " + key + " serializable " + serializable);
		}
		catch (Exception e)
		{
			logger.error("Exception get " + e.getMessage());
			logger.error("Exception get ", e);
		}
		return serializable;
		
	}
	
	public void put(String zCacheTableName, String key, Serializable value) {
		
		logger.debug("START put zCacheTableName " + zCacheTableName + " key " + key + " value " + value);
		try
		{
			put(zCacheTableName, key, value, 0);
			logger.debug("END put zCacheTableName " + zCacheTableName + " key " + key + " value " + value);
		}
		catch (Exception e)
		{
			logger.error("Exception put " + e.getMessage());
			logger.error("Exception put ", e);
		}
		
	}
	
	public void put(String zCacheTableName, String key, Serializable value, int aliveTimeInSeconds) {
		logger.debug("START put zCacheTableName " + zCacheTableName + " key " + key + " value " + value + " aliveTimeInSeconds "
		        + aliveTimeInSeconds);
		try
		{
			getCache(zCacheTableName).put(key, value, aliveTimeInSeconds);
			logger.debug("END put zCacheTableName " + zCacheTableName + " key " + key + " value " + value + " aliveTimeInSeconds "
			        + aliveTimeInSeconds);
		}
		catch (Exception e)
		{
			logger.error("Exception put " + e.getMessage());
			logger.error("Exception put ", e);
		}
		
	}
	
}
