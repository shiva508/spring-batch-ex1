package com.shiva.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.adapter.ItemProcessorAdapter;
import org.springframework.stereotype.Component;

import com.shiva.model.User;

@Component
public class Processor implements ItemProcessor<User, User> {
	public Map<String, String> depots = new HashMap<String, String>();

	public Processor() {
		depots.put("01", "EMPLOYEE");
		depots.put("02", "CEO");
		depots.put("03", "MANAGER");
	}

	@Override
	public User process(User user) throws Exception {
		String dept = user.getDept();
		String modelDept = depots.get(dept);
		user.setDept(modelDept);
		return user;
	}

	
}
