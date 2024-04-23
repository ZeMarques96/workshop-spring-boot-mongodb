package com.zemarques96.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.zemarques96.workshopmongo.dto.AuthorDTO;
import com.zemarques96.workshopmongo.dto.CommentDTO;
import com.zemarques96.workshopmongo.entities.Post;
import com.zemarques96.workshopmongo.entities.User;
import com.zemarques96.workshopmongo.repositories.PostRepository;
import com.zemarques96.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null , "Maria Brown", "maria@mail.com");
        User alex = new User(null , "Alex Green", "alex@mail.com");
        User bob = new User(null , "Bob Grey", "bob@mail.com");

        userRepository.saveAll(Arrays.asList(maria,alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("21/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO c2 = new CommentDTO("Aproveite!", sdf.parse("22/03/2018"), new AuthorDTO(bob));

        post1.getComments().addAll(Arrays.asList(c1, c2));

        postRepository.saveAll(Arrays.asList(post1,post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);
    }

}
