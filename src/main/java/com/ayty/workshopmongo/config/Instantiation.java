package com.ayty.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ayty.workshopmongo.domain.Post;
import com.ayty.workshopmongo.domain.User;
import com.ayty.workshopmongo.dto.AuthorDTO;
import com.ayty.workshopmongo.dto.CommentDTO;
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
		//User paulo = new User(null, "Paulo Jose", "paulo@gmail.com");
		
        userReposiroty.saveAll(Arrays.asList(lucas, marcelo, luiz));
		
        Post post1 = new Post(null, sdf.parse("01/07/2022"), "Good trip", "I will go to the capital!", new AuthorDTO(lucas));
		Post post2 = new Post(null, sdf.parse("22/06/2022"), "Good morning!", "I woke up very happy!", new AuthorDTO(lucas));

		CommentDTO c1 = new CommentDTO("Have a nice trip bro!", sdf.parse("21/03/2022"), new AuthorDTO(marcelo));
		CommentDTO c2 = new CommentDTO("Have a good time", sdf.parse("22/03/2019"), new AuthorDTO(luiz));
		CommentDTO c3 = new CommentDTO("May you have a great day!", sdf.parse("23/03/2021"), new AuthorDTO(lucas));

		post1.getComments().addAll(Arrays.asList(c1, c2));
		post1.getComments().addAll(Arrays.asList(c3));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2));

        lucas.getPosts().addAll(Arrays.asList(post1, post2));
        userReposiroty.save(lucas);
	}

}

