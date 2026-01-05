package com.educandoweb.workshopmongo.config;

import com.educandoweb.workshopmongo.domain.Post;
import com.educandoweb.workshopmongo.domain.User;
import com.educandoweb.workshopmongo.dto.AuthorDTO;
import com.educandoweb.workshopmongo.repository.PostRepository;
import com.educandoweb.workshopmongo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Configuration
public class Instantiation implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        DateTimeFormatter fmt1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail");
        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post = new Post(null, fromDate("21/03/2018",fmt1), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, fromDate("23/03/2098",fmt1), "Bom dia", "Acordei feliz hoje!",new AuthorDTO(maria));
        postRepository.saveAll(Arrays.asList(post,post2));

        maria.getPostList().addAll(Arrays.asList(post,post2));
        userRepository.save(maria);

    }
    public static Instant fromDate(String dateStr, DateTimeFormatter formatter) {
        LocalDate data = LocalDate.parse(dateStr, formatter);
        return data.atStartOfDay(ZoneOffset.UTC).toInstant();
    }
}
