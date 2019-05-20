package com.hanogi.batch.reader;

import net.sf.ehcache.Cache;

public interface ToneReader<T> {
	
	public void readTone(T data,Cache cache);

}
