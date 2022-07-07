package com.ayty.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ayty.workshopmongo.domain.User;
import com.ayty.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		userReposiroty.deleteAll();
		
		User lucas = new User(null, "Lucas Pereira", "lucas@gmail.com");
		User perdro = new User(null, "Pedro Araujo", "pedro@gmail.com");
		User leticia = new User(null, "Leticia Amorim", "leticia@gmail.com");

		userReposiroty.saveAll(Arrays.asList(lucas, perdro, leticia));
	}

}
