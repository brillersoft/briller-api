package com.hanogi.batch.audits;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditFieldsImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Abhi");
	}

}
