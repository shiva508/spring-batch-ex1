package com.shiva.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shiva.model.User;
import com.shiva.repository.UserRepository;

@Component
public class UserWriter implements ItemWriter<User> {
	@Autowired
	UserRepository userRepository;

	@Override
	public void write(List<? extends User> users) throws Exception {
		System.out.println("USER IS SAVED TO DATABASE:"+users);
		userRepository.saveAll(users);
	}

}
