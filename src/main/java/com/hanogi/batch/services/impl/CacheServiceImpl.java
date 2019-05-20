package com.hanogi.batch.services.impl;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.hanogi.batch.constants.CacheType;
import com.hanogi.batch.constants.ErrorCodes;
import com.hanogi.batch.constants.ExecutionStatusEnum;
import com.hanogi.batch.dto.ExecutionStatus;
import com.hanogi.batch.exceptions.BrillerBatchDataException;
import com.hanogi.batch.services.ICacheService;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author abhishek.gupta02
 *
 */

@Component
public class CacheServiceImpl implements ICacheService, Serializable {

	int noOfElementsInCache = 0;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static CacheManager cacheManager = null;

	@PostConstruct
	public void getCacheManagerInstance() {
		cacheManager = CacheManager.newInstance();
		if (cacheManager != null) {
			logger.info("Cache manager has bean created....");
		} else {
			// TO-DO
		}
	}

	/*
	 * validate is messageId exists in cache or not. If exists then update its
	 * status in cache. Else add new messageId in Cache
	 */
	@Override
	public Cache getCacheInstance(CacheType cacheType) {
		Cache cache = null;
		if (cacheType.equals(CacheType.ConcurrentCache)) {
			cache = cacheManager.getCache("concurrentBatchScheduleCache");
		}
		if (cacheType.equals(CacheType.HistroicalCache)) {
			cache = cacheManager.getCache("concurrentBatchScheduleCache");
		}
		return cache;
	}

	@Override
	public boolean checkAddUpdateCache(String messageId, ExecutionStatusEnum executionStatus, Cache cacheInstance)
			throws BrillerBatchDataException {

		logger.info("Caching in progress  for messageId:" + messageId + "...");

		boolean exitsInCache = false;

		try {

			synchronized (this) {

				Element messageIdElement = cacheInstance.get(messageId);

				if (null != messageIdElement) {

					ExecutionStatus messageStatus = (ExecutionStatus) messageIdElement.getObjectValue();

					if (!ExecutionStatusEnum.Complete.equals(messageStatus)) {

						logger.info("MesaageId:" + messageId + "exists in cache.Updating its status.");
						cacheInstance.put(new Element(messageId, executionStatus));
						exitsInCache = true;
					}

				} else {

					logger.info("Updation in cache with adding new element messageId:" + messageId);
					Element element = new Element(messageId, executionStatus);
					cacheInstance.put(element);
				}
			}

		} catch (Exception e) {

			logger.error("Error while updating the message in cache with Error:" + e.getMessage());

			throw new BrillerBatchDataException(
					"Error while updating the message : " + messageId + " in cache with Error", e,
					ErrorCodes.CACHE_MODIFICATION_ERROR);
		}

		return exitsInCache;
	}

	@Override
	public void cleanConcurrentCache() {
		Cache concurrentCache = cacheManager.getCache("concurrentBatchScheduleCache");

		concurrentCache.removeAll();

		noOfElementsInCache = 0;
	}

	@Override
	public ExecutionStatusEnum getStatusOfMessage(String messageId) {
		Cache concurrentCache = cacheManager.getCache("concurrentBatchScheduleCache");

		if (concurrentCache.isKeyInCache(messageId)) {
			Element element = concurrentCache.get(messageId);

			ExecutionStatusEnum status = (ExecutionStatusEnum) element.getObjectValue();

			return status;
		} else {
			return null;
		}

	}

}
