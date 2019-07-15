package com.hanogi.batch.repositry;

import org.springframework.data.repository.CrudRepository;

import com.hanogi.batch.entity.SchedulerJobInfo;

public interface SchedulerJobInfoRepo extends CrudRepository<SchedulerJobInfo, Long> {

}
