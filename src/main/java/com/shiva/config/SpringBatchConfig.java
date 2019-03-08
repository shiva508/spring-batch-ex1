package com.shiva.config;



import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.shiva.model.User;

@Configuration
public class SpringBatchConfig {
	@Bean
public Job job(JobBuilderFactory jobBuilderFactory,StepBuilderFactory stepBuilderFactory,ItemReader<User> itemReader
		,ItemProcessor<User, User> itemProcessor
		,ItemWriter<User> itemWriter) {
		
		Step step=stepBuilderFactory.get("Shiva-file-read")
				.<User,User>chunk(100)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
		return jobBuilderFactory.get("Shiva")
		.incrementer(new RunIdIncrementer())
		.start(step)
		.build();
}
	@Bean
	public FlatFileItemReader<User> fileItemReader(@Value("${input}")Resource resource){
		FlatFileItemReader< User> fileItemReader=new FlatFileItemReader<User>();
		fileItemReader.setResource(resource);
		fileItemReader.setName("CSV-File-Reader");
		fileItemReader.setLinesToSkip(1);
		fileItemReader.setLineMapper(lineMapper());
		return fileItemReader; 
	}
	
	private LineMapper<User> lineMapper(){
		DefaultLineMapper< User> defaultLineMapper=new DefaultLineMapper<User>();
		return null;
	}
}
