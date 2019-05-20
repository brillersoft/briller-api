package com.hanogi.batch.reader;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hanogi.batch.constants.ExecutionStatusEnum;
import com.hanogi.batch.dto.AggregatedTone;
import com.hanogi.batch.dto.CalculatedTone;
import com.hanogi.batch.dto.EmailHeader;
import com.hanogi.batch.dto.EmailMetaDataDto;
import com.hanogi.batch.dto.EmailMetadata;
import com.hanogi.batch.dto.IndividualTone;
import com.hanogi.batch.exceptions.BrillerBatchDataException;
import com.hanogi.batch.repositories.AggregatedToneRepositry;
import com.hanogi.batch.repositories.CalculatedToneRepositry;
import com.hanogi.batch.repositories.EmailHeaderRepositry;
import com.hanogi.batch.repositories.EmailMetadataRepositry;
import com.hanogi.batch.repositories.IndividualToneRepositry;
import com.hanogi.batch.services.ICacheService;
import com.hanogi.batch.services.IEmailMessageDataService;
import com.hanogi.batch.services.IToneAnalyser;
import com.hanogi.batch.utils.bo.EmailMessageData;

import net.sf.ehcache.Cache;

@Service
public class EmailToneReader implements ToneReader<EmailMessageData> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ICacheService cacheService;

	@Autowired
	private IEmailMessageDataService emailMessageDataService;

	@Autowired
	private IToneAnalyser toneAnalyser;

	@Autowired
	private AggregatedToneRepositry aggregatedToneRepo;

	@Autowired
	private EmailMetadataRepositry emailMetadataRepo;

	@Autowired
	private IndividualToneRepositry individualToneRepo;

	@Autowired
	private CalculatedToneRepositry calculatedToneRepo;

	@Autowired
	private EmailHeaderRepositry emailHeaderRepo;

	@Override
	@Async("toneProcessorThreadPool")
	public void readTone(EmailMessageData emailMessageData, Cache cacheInstance) {

		// Send the data to the Tone Analyzer and wait for the response

		String toneMsg = toneAnalyser.analyseTone(emailMessageData.getUniqueEmailBody());

		/*
		 * Integer aggreegatedToneId = saveToneToDb(tone);
		 * 
		 * if (aggreegatedToneId != null) {
		 * logger.info("Tone saved to Db successfully"); } else {
		 * logger.warn("Unable to save the tone to DB"); }
		 * 
		 * // Inserting the message data in the database Boolean isMessageInsertedToDB =
		 * emailMessageDataService.insertMessageToDB(emailMessageData);
		 */

		Boolean isMessageInsertedToDB = saveMetaData(toneMsg, emailMessageData);

		String MessageId = emailMessageData.getEmailMetaData().getEmailHeader().getMessageId();

		// String MessageId = "TEST";

		// Updating the cache for MessageId
		if (isMessageInsertedToDB) {
			logger.info(MessageId
					+ "execution COMPLETED and data inserted to DB successfully now updating status in cache with COMPLETE....");
			try {
				boolean doNotExists = cacheService.checkAddUpdateCache(MessageId, ExecutionStatusEnum.Complete,
						cacheInstance);
			} catch (BrillerBatchDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			logger.warn(MessageId
					+ "execution COMPLETED and data inserted to DB failed now updating status in cache with FAILURE....");
			try {
				boolean doNotExists = cacheService.checkAddUpdateCache(MessageId, ExecutionStatusEnum.failure,
						cacheInstance);
			} catch (BrillerBatchDataException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	/*
	 * private Integer saveToneToDb(String toneMsg) { // Inserting the tone response
	 * to the DB AggregatedTone aggregatedTone = new AggregatedTone();
	 * 
	 * aggregatedTone.setAggregatedTone(toneMsg); aggregatedTone.setStatus("1");
	 * 
	 * AggregatedTone isSaved = aggregatedToneRepo.save(aggregatedTone);
	 * 
	 * if (isSaved != null) { return isSaved.getAggregatedToneId(); } else { return
	 * null; } }
	 */

	@Transactional
	private boolean saveMetaData(String toneMsg, EmailMessageData emailMessageData) {
		try {
			EmailMetadata emailMetadata = emailMessageData.getEmailMetaData();

			// Setting aggregated tone
			AggregatedTone aggregatedTone = new AggregatedTone();

			aggregatedTone.setAggregatedTone(toneMsg);
			aggregatedTone.setStatus("1");

			CalculatedTone calculatedTone = new CalculatedTone();

			calculatedTone.setCalculatedTone("");
			calculatedTone.setStatus("1");

			IndividualTone indTone = new IndividualTone();
			indTone.setIndividualTone("");
			indTone.setStatus("1");

			Integer mailHeaderId = setMailHeader(emailMetadata.getEmailHeader());

			EmailMetaDataDto metaDataDto = new EmailMetaDataDto();

			metaDataDto.setAggregatedTone(aggregatedTone);

			metaDataDto.setCalculatedTone(calculatedTone);

			metaDataDto.setIndvidualToneIdindividualTone(indTone);

			metaDataDto.setStatus("1");
			// EmailMetadata isMetaDataSaved = emailMetadataRepo.save(emailMetadata);

			// setMailHeader(isMetaDataSaved.getEmailMetadataId());

			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public Integer setMailHeader(EmailHeader emailHeader) {

		emailHeader.setSenderIp("");
		emailHeader.setContentLanguage("ENG");

		EmailHeader isEmailHeaderSaved = emailHeaderRepo.save(emailHeader);

		if (isEmailHeaderSaved != null) {
			return isEmailHeaderSaved.getEmailMetaDataId();
		} else {
			return null;
		}
	}
}
