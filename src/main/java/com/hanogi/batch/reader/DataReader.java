package com.hanogi.batch.reader;

import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.hanogi.batch.constants.CacheType;
import com.hanogi.batch.constants.ExecutionStatusEnum;
import com.hanogi.batch.dto.Email;
import com.hanogi.batch.dto.batch.BatchRunDetails;
import com.hanogi.batch.services.IBatchService;
import com.hanogi.batch.services.ICacheService;
import com.hanogi.batch.utils.bo.EmailMessageData;

import net.sf.ehcache.Cache;

@Component
public class DataReader implements IDataReader {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	@Qualifier("mailReadersMaps")
	private Map<String, Map<String, IEmailReader>> mailReaders;

	@Autowired
	@Qualifier("emailDataQueue")
	private BlockingQueue<EmailMessageData> emailDataProcessingQueue;

	@Autowired
	private ToneReader<EmailMessageData> emailToneReader;

	@Autowired
	private ICacheService cacheService;

	@Autowired
	private IBatchService batchService;

	Long startTimeInMillSec = 0l;

	@Override
	public void readData(List<Email> emailList, BatchRunDetails batchRunDetails, CacheType cacheType) throws Exception {
		Map<String, ExecutionStatusEnum> emailProcessingStatusMap = new ConcurrentHashMap<>();

		Cache cacheInstance = cacheService.getCacheInstance(cacheType);

		loadCacheData(cacheInstance, batchRunDetails);

		Calendar calendar = Calendar.getInstance();

		startTimeInMillSec = calendar.getTimeInMillis();

		logger.warn("Batch processing start time:" + calendar.getTimeInMillis());

		for (Email email : emailList) {
			logger.info("setting execution status as Inprogress");
			emailProcessingStatusMap.put(email.getEmailId(), ExecutionStatusEnum.Inprogress);
		}

		for (Email email : emailList) {
			IEmailReader emailReader = mailReaders.get(email.getObjEmailDomainDetails().getEmailServiceProvider())
					.get(email.getObjEmailDomainDetails().getServerDeploymentType());

			logger.info("Start processing mailId:" + email.getEmailId());

			emailReader.readMail(email, batchRunDetails, emailProcessingStatusMap, cacheInstance);

		}

		while (!(emailDataProcessingQueue.isEmpty() && hasEmailReadersFinishedProcessing(emailProcessingStatusMap))) {

			EmailMessageData emailMessage = emailDataProcessingQueue.poll();

			if (null != emailMessage) {
				emailToneReader.readTone(emailMessage, cacheInstance);
			}

		}
		ExecutionStatusEnum batchExecutionStatus = hasEmailReadersContainsErrors(emailProcessingStatusMap)
				? ExecutionStatusEnum.failure
				: ExecutionStatusEnum.Complete;

		batchService.updateBatchExecutionStatus(batchRunDetails, batchExecutionStatus);

	}

	public boolean hasEmailReadersFinishedProcessing(Map<String, ExecutionStatusEnum> emailProcessingStatusMap) {

		boolean isComplete = emailProcessingStatusMap.containsValue(ExecutionStatusEnum.Inprogress) ? false : true;

		return isComplete;
	}

	/**
	 * This method checks that whether there is any email that whose processing
	 * status is Failure
	 * 
	 * @param emailProcessingStatusMap
	 * @return
	 */

	public boolean hasEmailReadersContainsErrors(Map<String, ExecutionStatusEnum> emailProcessingStatusMap) {

		boolean hasErrors = emailProcessingStatusMap.containsValue(ExecutionStatusEnum.failure) ? true : false;

		return hasErrors;
	}

	private void loadCacheData(Cache cacheInstance, BatchRunDetails batchRunDetails) {
		if (batchRunDetails.getBatchExecutionStatus().getStatusName().equals(ExecutionStatusEnum.failure)) {
			logger.info("Loading data from last failed batch already succeed records.");

		}
	}

}
