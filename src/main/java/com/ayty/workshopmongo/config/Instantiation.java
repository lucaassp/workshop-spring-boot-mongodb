package com.ayty.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ayty.workshopmongo.domain.Post;
import com.ayty.workshopmongo.domain.User;
import com.ayty.workshopmongo.repository.PostRepository;
import com.ayty.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userReposiroty;

	@Autowired
	private PostRepository postReposiroty;

	@Override
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userReposiroty.deleteAll();
		postReposiroty.deleteAll();
		
		User lucas = new User(null, "Lucas Pereira", "lucas@gmail.com");
		User marcelo = new User(null, "Marcelo Silva", "marcelo@gmail.com");
		User luiz = new User(null, "Luiz araujo", "luiz@gmail.com");
		
		Post post1 = new Post(null, sdf.parse("01/07/2022"), "Good trip", "I will go to the capital!", lucas);
		Post post2 = new Post(null, sdf.parse("22/06/2022"), "Good morning!", "I woke up very happy!", lucas);

		userReposiroty.saveAll(Arrays.asList(lucas, marcelo, luiz));
		postReposiroty.saveAll(Arrays.asList(post1, post2));
	}

}

