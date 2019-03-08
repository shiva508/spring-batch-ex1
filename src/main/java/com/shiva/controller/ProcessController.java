package com.shiva.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job job;

	@GetMapping("/processjob")
	private BatchStatus processJopb() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> maps = new HashMap<String, JobParameter>();
		maps.put("time",new JobParameter(new Date()));
		JobParameters jobParameters = new JobParameters(maps);
		JobExecution exe = jobLauncher.run(job, jobParameters);
		System.out.println(exe.getStatus());
		while (exe.isRunning()) {
			System.out.println("JOB IS RUNNING");
		}
		return exe.getStatus();
	}
}
