package com.hanogi.batch.services;

import com.hanogi.batch.constants.CacheType;
import com.hanogi.batch.constants.ExecutionStatusEnum;
import com.hanogi.batch.exceptions.BrillerBatchDataException;

import net.sf.ehcache.Cache;

public interface ICacheService {

	boolean checkAddUpdateCache(String messageId, ExecutionStatusEnum inprogress,Cache cache) throws BrillerBatchDataException;

	void cleanConcurrentCache();
	
	ExecutionStatusEnum getStatusOfMessage(String messageId);
	
	Cache getCacheInstance(CacheType cacheType);

}
